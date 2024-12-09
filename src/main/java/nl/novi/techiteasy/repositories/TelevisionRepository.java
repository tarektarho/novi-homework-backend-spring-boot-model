package nl.novi.techiteasy.repositories;

import nl.novi.techiteasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelevisionRepository extends JpaRepository<Television, Long> {
    List<Television> findByBrand(String brand);

    List<Television> findByBrandAndName(String brand, String name);

    List<Television> findByName(String name);

    List<Television> findByType(String type);

    List<Television> findByPrice(Double price);

    List<Television> findByAvailableSize(Double availableSize);

    List<Television> findByScreenType(String screenType);

    List<Television> findByScreenQuality(String screenQuality);

    List<Television> findBySmartTv(boolean smartTv);

    List<Television> findByWifi(boolean wifi);

    List<Television> findByVoiceControl(boolean voiceControl);

    List<Television> findByHdr(boolean hdr);

    List<Television> findByBluetooth(boolean bluetooth);

    List<Television> findByAmbiLight(boolean ambiLight);

    List<Television> findByOriginalStock(int originalStock);

    List<Television> findBySold(int sold);

    List<Television> findBySoldAt(String soldAt);

    List<Television> findByBoughtAt(String boughtAt);

}
