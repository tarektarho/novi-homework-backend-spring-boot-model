package nl.novi.techiteasy.services;

import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository televisionRepository;

    /**
     * Constructor for the TelevisionService
     * @param televisionRepository The repository for the television
     */
    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    /**
     * Saves a television
     * @param television The television to save
     * @return The saved television
     */
    public Television save(Television television) {
        return televisionRepository.save(television);
    }

    /**
     * Returns a television by id
     * @param id The id of the television
     * @return The television
     */
    public Optional<Television> getTelevisionById(Long id) {
        return televisionRepository.findById(id);
    }

    /**
     * Returns a list of televisions based on the parameters
     * @param brand The brand of the television
     * @param name The name of the television
     * @param type The type of the television
     * @param price The price of the television
     * @param availableSize The available size of the television
     * @param screenType The screen type of the television
     * @param screenQuality The screen quality of the television
     * @param smartTv Whether the television is a smart tv
     * @param wifi Whether the television has wifi
     * @param voiceControl Whether the television has voice control
     * @param hdr Whether the television has HDR
     * @param bluetooth Whether the television has bluetooth
     * @param ambiLight Whether the television has ambi light
     * @param soldAt The date the television was sold
     * @param boughtAt The date the television was bought
     * @return A list of televisions
     */
    public List<Television> getTelevisions(
            String brand,
            String name,
            String type,
            Double price,
            Double availableSize,
            String screenType,
            String screenQuality,
            boolean smartTv,
            boolean wifi,
            boolean voiceControl,
            boolean hdr,
            boolean bluetooth,
            boolean ambiLight,
            String soldAt,
            String boughtAt) {
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
        } else if (soldAt != null) {
            televisions = televisionRepository.findBySoldAt(soldAt);
        } else if (boughtAt != null) {
            televisions = televisionRepository.findByBoughtAt(boughtAt);
        } else {
            televisions = televisionRepository.findAll();
        }

        return televisions;
    }

    /**
     * Updates a television by id
     * @param id The id of the television to update
     * @return The updated television
     */
    public boolean deleteTelevisionById(Long id) {
        if (televisionRepository.existsById(id)) {
            televisionRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
