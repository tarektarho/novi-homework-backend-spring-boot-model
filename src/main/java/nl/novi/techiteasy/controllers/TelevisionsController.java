package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.TelevisionNameTooLongException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;


@RestController
@RequestMapping("/api/televisions")
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @PostMapping
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {

        if (television.getName().length() > 50) {
            throw new TelevisionNameTooLongException("The name of the television is too long");
        }

        Television savedTelevision = televisionRepository.save(television);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTelevision);
    }

    @GetMapping
    public ResponseEntity<Iterable<Television>> getTelevisions(@RequestParam(required = false) String brand, @RequestParam(required = false) String name, @RequestParam(required = false) String type, @RequestParam(required = false) Double price, @RequestParam(required = false) Double availableSize, @RequestParam(required = false) String screenType, @RequestParam(required = false) String screenQuality, @RequestParam(required = false) boolean smartTv, @RequestParam(required = false) boolean wifi, @RequestParam(required = false) boolean voiceControl, @RequestParam(required = false) boolean hdr, @RequestParam(required = false) boolean bluetooth, @RequestParam(required = false) boolean ambiLight, @RequestParam(required = false) String soldDate, @RequestParam(required = false) String boughtDate) {
       List<Television> televisions;
        if (brand != null && name != null) {
            televisions = televisionRepository.findByBrandAndName(brand, name);
        } else if (brand != null) {
            televisions = televisionRepository.findByBrand(brand);
        } else if (name != null) {
            televisions = televisionRepository.findByName(name);
        } else if (type != null) {
            televisions = televisionRepository.findByType(type);
        } else if (price != null) {
            televisions = televisionRepository.findByPrice(price);
        } else if (availableSize != null) {
            televisions = televisionRepository.findByAvailableSize(availableSize);
        } else if (screenType != null) {
            televisions = televisionRepository.findByScreenType(screenType);
        } else if (screenQuality != null) {
            televisions = televisionRepository.findByScreenQuality(screenQuality);
        } else if (smartTv) {
            televisions = televisionRepository.findBySmartTv(smartTv);
        } else if (wifi) {
            televisions = televisionRepository.findByWifi(wifi);
        } else if (voiceControl) {
            televisions = televisionRepository.findByVoiceControl(voiceControl);
        } else if (hdr) {
            televisions = televisionRepository.findByHdr(hdr);
        } else if (bluetooth) {
            televisions = televisionRepository.findByBluetooth(bluetooth);
        } else if (ambiLight) {
            televisions = televisionRepository.findByAmbiLight(ambiLight);
        } else if (soldDate != null) {
            televisions = televisionRepository.findBySoldDate(soldDate);
        } else if (boughtDate != null) {
            televisions = televisionRepository.findByBoughtDate(boughtDate);
        } else {
            televisions = televisionRepository.findAll();
        }

        return ResponseEntity.ok(televisions);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television television) {
        if (!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("Invalid ID: The ID That has been provided is not valid or does not exist");
        }
        television.setId(id);
        Television savedTelevision = televisionRepository.save(television);
        return ResponseEntity.ok(savedTelevision);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable Long id) {
        if (!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("Invalid ID: The ID That has been provided is not valid or does not exist");
        }
        televisionRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("The television with ID: " + id + " has been deleted.");
    }
}
