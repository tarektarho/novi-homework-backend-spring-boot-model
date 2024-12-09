package nl.novi.techiteasy.mappers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.TelevisionCreateDTO;
import nl.novi.techiteasy.dtos.TelevisionResponseDTO;
import nl.novi.techiteasy.models.Television;

import java.util.List;
import java.util.stream.Collectors;

public class TelevisionMapper {

    public static TelevisionResponseDTO toResponseDTO(Television television) {
        // fallback to null if television is null
        if (television == null) {
            return null;
        }

        var result = new TelevisionResponseDTO();
        result.setId(television.getId());
        result.setName(television.getName());
        result.setBrand(television.getBrand());
        result.setType(television.getType());
        result.setYear(television.getAge());
        result.setPrice(television.getPrice());
        result.setAvailableSize(television.getAvailableSize());
        result.setRefreshRate(television.getRefreshRate());
        result.setScreenType(television.getScreenType());
        result.setScreenQuality(television.getScreenQuality());
        result.setBluetooth(television.isBluetooth());
        result.setWifi(television.isWifi());
        result.setHdr(television.isHdr());
        result.setSold(television.getSold());
        result.setSoldAt(television.getSoldAt());
        result.setBoughtAt(television.getBoughtAt());
        return result;
    }

    public static List<TelevisionResponseDTO> toResponseDTOList(List<Television> televisions) {
        if (televisions == null || televisions.isEmpty()) {
            return List.of();
        }
        return televisions.stream().map(TelevisionMapper::toResponseDTO).collect(Collectors.toList());
    }

    public static Television toEntity(@Valid TelevisionCreateDTO televisionCreateDTO) {
        if (televisionCreateDTO == null) {
            return null;
        }

        var result = new Television();
        result.setBrand(televisionCreateDTO.getBrand());
        result.setName(televisionCreateDTO.getName());
        result.setType(televisionCreateDTO.getType());
        result.setPrice(televisionCreateDTO.getPrice());
        result.setPrice(televisionCreateDTO.getPrice());
        result.setAvailableSize(televisionCreateDTO.getAvailableSize());
        result.setRefreshRate(televisionCreateDTO.getRefreshRate());
        result.setScreenType(televisionCreateDTO.getScreenType());
        result.setScreenQuality(televisionCreateDTO.getScreenQuality());
        result.setBluetooth(televisionCreateDTO.isBluetooth());
        result.setWifi(televisionCreateDTO.isWifi());
        result.setHdr(televisionCreateDTO.isHdr());
        result.setSold(televisionCreateDTO.getSold());
        result.setSoldAt(televisionCreateDTO.getSoldAt());
        result.setBoughtAt(televisionCreateDTO.getBoughtAt());
        return result;
    }

}
