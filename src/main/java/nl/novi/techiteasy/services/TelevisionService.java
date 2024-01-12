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
public class  TelevisionService {

private final TelevisionRepository tvRepos;

    public TelevisionService(TelevisionRepository tvRepos) {
        this.tvRepos = tvRepos;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = tvRepos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            tvDtoList.add(convertTelevisionToTelevisionDto(tv));
        }
        return tvDtoList;
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = tvRepos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            tvDtoList.add(convertTelevisionToTelevisionDto(tv));
        }
        return tvDtoList;
    }

    public TelevisionDto createTelevision(TelevisionInputDto createTelevisionDto) {
        Television tv = dtoTransferToTelevision(createTelevisionDto);
        tvRepos.save(tv);
        return convertTelevisionToTelevisionDto(tv);
    }

    public TelevisionDto convertTelevisionToTelevisionDto(Television tv) {

        TelevisionDto tvDto = new TelevisionDto();

        tvDto.id = tv.getId();
        tvDto.type = tv.getType();
        tvDto.brand = tv.getBrand();
        tvDto.name = tv.getName();
        tvDto.price = tv.getPrice();
        tvDto.availableSize = tv.getAvailableSize();
        tvDto.refreshRate = tv.getRefreshRate();
        tvDto.screenType = tv.getScreenType();
        tvDto.screenQuality = tv.getScreenQuality();
        tvDto.smartTv = tv.getSmartTv();
        tvDto.wifi = tv.getWifi();
        tvDto.voiceControl = tv.getVoiceControl();
        tvDto.hdr = tv.getHdr();
        tvDto.bluetooth = tv.getBluetooth();
        tvDto.ambiLight = tv.getAmbiLight();
        tvDto.originalStock = tv.getOriginalStock();
        tvDto.sold = tv.getSold();
        tvDto.saleDate = tv.getSaleDate();
        tvDto.purchaseDate = tv.getPurchaseDate();

        return tvDto;
    }
    public Television dtoTransferToTelevision (TelevisionInputDto tvDto) {

        Television television = new Television();

        television.setType(tvDto.type);
        television.setBrand(tvDto.brand);
        television.setName(tvDto.name);
        television.setPrice(tvDto.price);
        television.setAvailableSize(tvDto.availableSize);
        television.setRefreshRate(tvDto.refreshRate);
        television.setScreenType(tvDto.screenType);
        television.setScreenQuality(tvDto.screenQuality);
        television.setSmartTv(tvDto.smartTv);
        television.setWifi(tvDto.wifi);
        television.setVoiceControl(tvDto.voiceControl);
        television.setHdr(tvDto.hdr);
        television.setBluetooth(tvDto.bluetooth);
        television.setAmbiLight(tvDto.ambiLight);
        television.setOriginalStock(tvDto.originalStock);
        television.setSold(tvDto.sold);
        television.setSaleDate(tvDto.saleDate);
        television.setPurchaseDate(tvDto.purchaseDate);

        return television;
    }

    public TelevisionDto getTelevisionId(long id) {
        Optional<Television> televisionId = tvRepos.findById(id);
        if (televisionId.isPresent()) {
            Television tv = televisionId.get();
            return convertTelevisionToTelevisionDto(tv);
        } else {
            throw new RecordNotFoundException("No television found with id ");
        }
    }

    public void deleteTelevision(long id){
        tvRepos.deleteById(id);
    }

    public TelevisionDto updateTelevision(long id, TelevisionInputDto inputDto){
        Optional<Television> getTelevision = tvRepos.findById(id);
        if (getTelevision.isEmpty()) {
            throw new RecordNotFoundException("No television found with id ");
        } else {
            Television changeTelevision1 = getTelevision.get();
            changeTelevision1.setType(inputDto.type);
            changeTelevision1.setBrand(inputDto.brand);
            changeTelevision1.setName(inputDto.name);
            changeTelevision1.setPrice(inputDto.price);
            changeTelevision1.setAvailableSize(inputDto.availableSize);
            changeTelevision1.setRefreshRate(inputDto.refreshRate);
            changeTelevision1.setScreenType(inputDto.screenType);
            changeTelevision1.setScreenQuality(inputDto.screenQuality);
            changeTelevision1.setSmartTv(inputDto.smartTv);
            changeTelevision1.setWifi(inputDto.wifi);
            changeTelevision1.setVoiceControl(inputDto.voiceControl);
            changeTelevision1.setHdr(inputDto.hdr);
            changeTelevision1.setBluetooth(inputDto.bluetooth);
            changeTelevision1.setAmbiLight(inputDto.ambiLight);
            changeTelevision1.setOriginalStock(inputDto.originalStock);
            changeTelevision1.setSold(inputDto.sold);
            changeTelevision1.setSaleDate(inputDto.saleDate);
            changeTelevision1.setPurchaseDate(inputDto.purchaseDate);

            Television returnTelevision = tvRepos.save(changeTelevision1);

            return convertTelevisionToTelevisionDto(returnTelevision);
        }

    }
}
