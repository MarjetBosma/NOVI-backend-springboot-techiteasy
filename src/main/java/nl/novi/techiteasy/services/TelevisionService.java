package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.CIModuleRepository;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    private final TelevisionRepository tvRepos;

    private final RemoteControllerRepository rcRepos;

    private final RemoteControllerService remoteControllerService;

    private final CIModuleRepository cimRepos;

    private final CIModuleService ciModuleService;

    public TelevisionService(TelevisionRepository tvRepos, RemoteControllerRepository rcRepos, RemoteControllerService remoteControllerService, CIModuleRepository ciModuleRepository, CIModuleService ciModuleService) {
        this.tvRepos = tvRepos;
        this.rcRepos = rcRepos;
        this.remoteControllerService = remoteControllerService;
        this.cimRepos = ciModuleRepository;
        this.ciModuleService = ciModuleService;
    }

    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = tvRepos.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for (Television tv : tvList) {
            tvDtoList.add(convertTelevisionToTelevisionDto(tv));
        }
        return tvDtoList;
    }

    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = tvRepos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for (Television tv : tvList) {
            tvDtoList.add(convertTelevisionToTelevisionDto(tv));
        }
        return tvDtoList;
    }

    public List<TelevisionDto> convertTvListToTvDtoList(List<Television> televisions){
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : televisions) {
            TelevisionDto tvDto = convertTelevisionToTelevisionDto(tv);
            if(tv.getCiModule() != null){
                tvDto.ciModuleDto = ciModuleService.convertCIModuleToCIModuleDto(tv.getCiModule());
            }
            if(tv.getRemoteController() != null){
                tvDto.remoteControllerDto = remoteControllerService.convertRemoteControllerToRemoteControllerDto(tv.getRemoteController());
            }
            tvDtoList.add(tvDto);
        }
        return tvDtoList;
    }

    public TelevisionDto createTelevision(TelevisionInputDto createTelevisionDto) {
        Television tvInputDto = convertTelevisionDtoToTelevision(createTelevisionDto);
        tvRepos.save(tvInputDto);
        return convertTelevisionToTelevisionDto(tvInputDto);
    }

    public TelevisionDto getTelevisionById(long id) {
        Optional<Television> televisionId = tvRepos.findById(id);
        if (televisionId.isPresent()) {
            Television tv = televisionId.get();
            return convertTelevisionToTelevisionDto(tv);
        } else {
            throw new RecordNotFoundException("No television found with id " + id);
        }
    }

    public void deleteTelevision(long id) {
        Optional<Television> tv =  tvRepos.findById(id);
        if (tv.isPresent()) {
            tvRepos.deleteById(id);
        } else {
            throw new RecordNotFoundException("No television found with id " + id);
        }
    }

    public TelevisionDto updateTelevision(long id, TelevisionInputDto inputDto) {

        if (tvRepos.findById(id).isPresent()) {
            Television tv = tvRepos.findById(id).get();
            Television tv1 = convertTelevisionDtoToTelevision(inputDto);
            tv1.setId(tv.getId());
            tvRepos.save(tv1);
            return convertTelevisionToTelevisionDto(tv1);
        } else {
            throw new  RecordNotFoundException("no television found");
        }
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        var optionalTelevision = tvRepos.findById(id);
        var optionalRemoteController = rcRepos.findById(remoteControllerId);

        if (optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            var television = optionalTelevision.get();
            var remoteController = optionalRemoteController.get();

            television.setRemoteController(remoteController);
            tvRepos.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId) {
        var optionalTelevision = tvRepos.findById(id);
        var optionalCIModule = cimRepos.findById(ciModuleId);

        if (optionalTelevision.isPresent() && optionalCIModule.isPresent()) {
            var television = optionalTelevision.get();
            var ciModule = optionalCIModule.get();

            television.setCiModule(ciModule);
            tvRepos.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }

    // Dit hoeft niet voor wall brackets?

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

        if (tv.getCiModule() != null) {
            tvDto.ciModuleDto = ciModuleService.convertCIModuleToCIModuleDto(tv.getCiModule());
        }

        return tvDto;
    }

    public Television convertTelevisionDtoToTelevision(TelevisionInputDto tvDto) {

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
}

