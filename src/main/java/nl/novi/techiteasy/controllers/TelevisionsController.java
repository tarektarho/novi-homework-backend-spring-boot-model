package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.TelevisionResponseDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.TelevisionNameTooLongException;
import nl.novi.techiteasy.mappers.TelevisionMapper;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/televisions")
public class TelevisionsController {

    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping
    public ResponseEntity<?> createTelevision(@Valid @RequestBody TelevisionCreateDTO televisionCreateDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        if (televisionCreateDTO.getName().length() > 30) {
            throw new TelevisionNameTooLongException("The name of the television is too long");
        }

        Television savedTelevision = TelevisionMapper.toEntity(televisionCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(TelevisionMapper.toResponseDTO(televisionService.save(savedTelevision)));
    }

    @GetMapping
    public ResponseEntity<Iterable<TelevisionResponseDTO>> getTelevisions(@RequestParam(required = false) String brand, @RequestParam(required = false) String name, @RequestParam(required = false) String type, @RequestParam(required = false) Double price, @RequestParam(required = false) Double availableSize, @RequestParam(required = false) String screenType, @RequestParam(required = false) String screenQuality, @RequestParam(required = false) boolean smartTv, @RequestParam(required = false) boolean wifi, @RequestParam(required = false) boolean voiceControl, @RequestParam(required = false) boolean hdr, @RequestParam(required = false) boolean bluetooth, @RequestParam(required = false) boolean ambiLight, @RequestParam(required = false) String soldAt, @RequestParam(required = false) String boughtAt) {
        List<Television> televisions = televisionService.getTelevisions(brand, name, type, price, availableSize, screenType, screenQuality, smartTv, wifi, voiceControl, hdr, bluetooth, ambiLight, soldAt, boughtAt);
        return ResponseEntity.ok(TelevisionMapper.toResponseDTOList(televisions));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTelevision(@Valid @PathVariable Long id, @RequestBody TelevisionCreateDTO televisionCreateDTO, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Television existingTelevision = televisionService.getTelevisionById(id)
                .orElseThrow(() -> new RecordNotFoundException("No television record exists for the given ID."));

        // Map and save the updated entity
        Television updatedTelevision = TelevisionMapper.toEntity(televisionCreateDTO);
        updatedTelevision.setId(existingTelevision.getId());
        Television savedTelevision = televisionService.save(updatedTelevision);

        TelevisionResponseDTO responseDTO = TelevisionMapper.toResponseDTO(savedTelevision);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        var deleted = televisionService.deleteTelevisionById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("No television record exists for the given ID.");
        }
    }
}
