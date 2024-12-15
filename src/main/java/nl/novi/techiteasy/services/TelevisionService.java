package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.TelevisionResponseDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.TelevisionNameTooLongException;
import nl.novi.techiteasy.mappers.TelevisionMapper;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public TelevisionResponseDTO createTelevision(TelevisionCreateDTO televisionCreateDTO) {
        // Validation: Check if the name length exceeds 30
        if (televisionCreateDTO.getName() != null && televisionCreateDTO.getName().length() > 30) {
            throw new TelevisionNameTooLongException("The name of the television is too long.");
        }

        // Map DTO to entity and save
        Television television = TelevisionMapper.toEntity(televisionCreateDTO);
        Television savedTelevision = televisionRepository.save(television);

        // Map saved entity to response DTO
        return TelevisionMapper.toResponseDTO(savedTelevision);
    }

    public List<TelevisionResponseDTO> getTelevisionsWithFilters(
            String brand, String name, String type, Double price, Double availableSize,
            String screenType, String screenQuality, Boolean smartTv, Boolean wifi,
            Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight,
            Integer originalStock, Integer sold, String soldAt, String boughtAt) {

        List<Television> televisions = fetchTelevisions(
                brand, name, type, price, availableSize, screenType, screenQuality,
                smartTv, wifi, voiceControl, hdr, bluetooth, ambiLight,
                originalStock, sold, soldAt, boughtAt);

        if (televisions.isEmpty()) {
            throw new RecordNotFoundException("No televisions found matching the provided criteria.");
        }

        return televisions.stream()
                .map(TelevisionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    public TelevisionResponseDTO updateTelevision(Long id, TelevisionCreateDTO televisionCreateDTO) {
        // Retrieve existing television
        Television existingTelevision = televisionRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("No television record exists for the given ID."));

        // Update fields and save
        Television updatedTelevision = TelevisionMapper.toEntity(televisionCreateDTO);
        updatedTelevision.setId(existingTelevision.getId());
        Television savedTelevision = televisionRepository.save(updatedTelevision);

        // Map saved entity to response DTO
        return TelevisionMapper.toResponseDTO(savedTelevision);
    }

    public void deleteTelevision(Long id) {
        if (!televisionRepository.existsById(id)) {
            throw new RecordNotFoundException("No television record exists for the given ID.");
        }
        televisionRepository.deleteById(id);
    }

    private List<Television> fetchTelevisions(
            String brand, String name, String type, Double price, Double availableSize,
            String screenType, String screenQuality, Boolean smartTv, Boolean wifi,
            Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight,
            Integer originalStock, Integer sold, String soldAt, String boughtAt) {

        if (brand != null && name != null) {
            return televisionRepository.findByBrandAndName(brand, name);
        }

        if (brand != null) {
            return televisionRepository.findByBrand(brand);
        }

        if (name != null) {
            return televisionRepository.findByName(name);
        }

        if (type != null) {
            return televisionRepository.findByType(type);
        }

        if (price != null) {
            return televisionRepository.findByPrice(price);
        }

        if (availableSize != null) {
            return televisionRepository.findByAvailableSize(availableSize);
        }

        if (screenType != null) {
            return televisionRepository.findByScreenType(screenType);
        }

        if (screenQuality != null) {
            return televisionRepository.findByScreenQuality(screenQuality);
        }

        if (smartTv != null) {
            return televisionRepository.findBySmartTv(smartTv);
        }

        if (wifi != null) {
            return televisionRepository.findByWifi(wifi);
        }

        if (voiceControl != null) {
            return televisionRepository.findByVoiceControl(voiceControl);
        }

        if (hdr != null) {
            return televisionRepository.findByHdr(hdr);
        }

        if (bluetooth != null) {
            return televisionRepository.findByBluetooth(bluetooth);
        }

        if (ambiLight != null) {
            return televisionRepository.findByAmbiLight(ambiLight);
        }

        if (originalStock != null) {
            return televisionRepository.findByOriginalStock(originalStock);
        }

        if (sold != null) {
            return televisionRepository.findBySold(sold);
        }

        if (soldAt != null) {
            return televisionRepository.findBySoldAt(soldAt);
        }

        if (boughtAt != null) {
            return televisionRepository.findByBoughtAt(boughtAt);
        }

        // If no filter is provided, return all televisions
        return televisionRepository.findAll();
    }

}
