package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.TelevisionResponseDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/televisions")
public class TelevisionsController {

    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @PostMapping
    public ResponseEntity<Object> createTelevision(@Valid @RequestBody TelevisionCreateDTO televisionCreateDTO) {
        TelevisionResponseDTO responseDTO = televisionService.createTelevision(televisionCreateDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TelevisionResponseDTO>> getTelevisions(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Double availableSize,
            @RequestParam(required = false) String screenType,
            @RequestParam(required = false) String screenQuality,
            @RequestParam(required = false) Boolean smartTv,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean voiceControl,
            @RequestParam(required = false) Boolean hdr,
            @RequestParam(required = false) Boolean bluetooth,
            @RequestParam(required = false) Boolean ambiLight,
            @RequestParam(required = false) Integer originalStock,
            @RequestParam(required = false) Integer sold,
            @RequestParam(required = false) String soldAt,
            @RequestParam(required = false) String boughtAt) {

        List<TelevisionResponseDTO> televisions = televisionService.getTelevisionsWithFilters(
                brand, name, type, price, availableSize, screenType, screenQuality,
                smartTv, wifi, voiceControl, hdr, bluetooth, ambiLight,
                originalStock, sold, soldAt, boughtAt);

        return ResponseEntity.ok(televisions);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(
            @PathVariable Long id,
            @Valid @RequestBody TelevisionCreateDTO televisionCreateDTO) {
        TelevisionResponseDTO responseDTO = televisionService.updateTelevision(id, televisionCreateDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable Long id) {
        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }
}
