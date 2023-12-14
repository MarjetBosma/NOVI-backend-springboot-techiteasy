package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

private final TelevisionRepository repos;

    public TelevisionService(TelevisionRepository repos) {
        this.repos = repos;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = repos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            tvDtoList.add(transferToDto(tv));
        }
        return tvDtoList;
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            tvDtoList.add(transferToDto(tv));
        }
        return tvDtoList;
    }

    public TelevisionDto createTelevision(TelevisionInputDto createTelevisionDto) {
        TelevisionDto tvInputDto = dtoTransferToTelevision(createTelevisionDto);
        repos.save(tvInputDto);
        return convertTelevisionToTelevisionDto(tvInputDto);
    }

    public TelevisionDto convertTelevisionToTelevisionDto(Television television) {

        TelevisionDto televisionDto = new TelevisionDto();
        televisionDto.id = television.getId();
        televisionDto.type = television.getType();
        televisionDto.brand = (television.getBrand());
        televisionDto.name = (television.getName());
        televisionDto.price = (television.getPrice());
        televisionDto.availableSize = (television.getAvailableSize());
        televisionDto.refreshRate = (television.getRefreshRate());
        televisionDto.screenType = (television.getScreenType());
        televisionDto.screenQuality = (television.getScreenQuality());
        televisionDto.smartTv = (television.getSmartTv());
        televisionDto.wifi = (television.getWifi());
        televisionDto.voiceControl = (television.getVoiceControl());
        televisionDto.hdr = (television.getHdr());
        televisionDto.bluetooth = (television.getBluetooth());
        televisionDto.ambiLight = (television.getAmbiLight());
        televisionDto.originalStock = (television.getOriginalStock());
        televisionDto.sold = (television.getSold());
        televisionDto.saleDate = (television.getSaleDate());
        televisionDto.purchaseDate = (television.getPurchaseDate());

        return televisionDto;
    }
    public Television dtoTransferToTelevision (TelevisionInputDto dto) {

        Television television = new Television();

        television.setType(dto.getType());
        television.setBrand(dto.getBrand());
        television.setName(dto.getName());
        television.setPrice(dto.getPrice());
        television.setAvailableSize(dto.getAvailableSize());
        television.setRefreshRate(dto.getRefreshRate());
        television.setScreenType(dto.getScreenType());
        television.setScreenQuality(dto.getScreenQuality());
        television.setSmartTv(dto.getSmartTv());
        television.setWifi(dto.getWifi());
        television.setVoiceControl(dto.getVoiceControl());
        television.setHdr(dto.getHdr());
        television.setBluetooth(dto.getBluetooth());
        television.setAmbiLight(dto.getAmbiLight());
        television.setOriginalStock(dto.getOriginalStock());
        television.setSold(dto.getSold());
        television.setSaleDate(dto.getSaleDate());
        television.setPurchaseDate(dto.getPurchaseDate());

        return television;
    }

    public TelevisionDto getTelevisionId(long id) {
        Optional<Television> televisionId = repos.findById(id);
        if (televisionId.isPresent()) {
            Television tv = televisionId.get();
            return convertTelevisionToTelevisionDto(tv);
        } else {
            throw new RecordNotFoundException("No television found with id ");
        }

    public void deleteTelevision(long id){
        repos.deleteById(id);
    }

    public TelevisionDto changeTelevision(long id, Television television){
        Optional<Television> getTelevision = repos.findById(id);
        if (getTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id ");
        } else {
            Television changeTelevision1 = getTelevision.get();
            changeTelevision1.setType(television.getType());
            changeTelevision1.setBrand(television.getBrand());
            changeTelevision1.setName(television.getName());
            changeTelevision1.setPrice(television.getPrice());
            changeTelevision1.setAvailableSize(television.getAvailableSize());
            changeTelevision1.setRefreshRate(television.getRefreshRate());
            changeTelevision1.setScreenType(television.getScreenType());
            changeTelevision1.setScreenQuality(television.getScreenQuality());
            changeTelevision1.setSmartTv(television.getSmartTv());
            changeTelevision1.setWifi(television.getWifi());
            changeTelevision1.setVoiceControl(television.getVoiceControl());
            changeTelevision1.setHdr(television.getHdr());
            changeTelevision1.setBluetooth(television.getBluetooth());
            changeTelevision1.setAmbiLight(television.getAmbiLight());
            changeTelevision1.setOriginalStock(television.getOriginalStock());
            changeTelevision1.setSold(television.getSold());
            changeTelevision1.setSaleDate(television.getSaleDate());
            changeTelevision1.setPurchaseDate(television.getPurchaseDate());

            Television returnTelevision = repos.save(changeTelevision1);

            return convertTelevisionToTelevisionDto(returnTelevision);
        }

    }
}
