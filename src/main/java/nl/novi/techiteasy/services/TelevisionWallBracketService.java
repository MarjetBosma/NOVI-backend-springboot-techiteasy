package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.models.TelevisionWallBracket;
import nl.novi.techiteasy.models.TelevisionWallBracketKey;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import nl.novi.techiteasy.repositories.TelevisionWallBracketRepository;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Deze klasse bevat de service methodes van TelevisionWallBracketController.
// Deze klasse wijkt af van de andere service-klassen, omdat deze in 3 verschillende controllers wordt ge-autowired.

@Service
public class TelevisionWallBracketService{

    private TelevisionRepository tvRepos;

    private WallBracketRepository wbRepos;

    private TelevisionWallBracketRepository tvwbRepos;

    public TelevisionWallBracketService(TelevisionRepository tvRepos, WallBracketRepository wbRepos, TelevisionWallBracketRepository tvwbRepos) {
        this.tvRepos = tvRepos;
        this.wbRepos = wbRepos;
        this.tvwbRepos = tvwbRepos;
    }

    public Collection<TelevisionDto> getTelevisionsByWallBracketId(Long wallBracketId) {
        Collection<TelevisionDto> tvDtos = new HashSet<>();
        Collection<TelevisionWallBracket> televisionWallBrackets = tvwbRepos.findAllByWallBracketId(wallBracketId);

        for (TelevisionWallBracket televisionWallBracket : televisionWallBrackets) {

            Television tv = televisionWallBracket.getTelevision();
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

            tvDtos.add(tvDto);
        }
        return tvDtos;
    }

    // Collection is de super klasse van zowel List als Set.
    public Collection<WallBracketDto> getWallBracketsByTelevisionId(Long televisionId) {
        //We gebruiken hier Set om te voorkomen dat er dubbele entries in staan.
        Set<WallBracketDto> wbDtos = new HashSet<>();
        List<TelevisionWallBracket> televisionWallBrackets = tvwbRepos.findAllByTelevisionId(televisionId);
        for (TelevisionWallBracket televisionWallBracket : televisionWallBrackets) {
            WallBracket wb = televisionWallBracket.getWallBracket();

            WallBracketDto wbDto = new WallBracketDto();

            wbDto.id = wb.getId();
            wbDto.name = wb.getName();
            wbDto.size = wb.getSize();
            wbDto.adjustable = wb.getAdjustable();
            wbDto.price = wb.getPrice();

            wbDtos.add(wbDto);
        }
        return wbDtos;
    }

    public TelevisionWallBracketKey addTelevisionWallBracket(Long televisionId, Long wallBracketId) {
        var tvwb = new TelevisionWallBracket();
        if (!tvRepos.existsById(televisionId)) {throw new RecordNotFoundException();}
        Television tv = tvRepos.findById(televisionId).orElse(null);
        if (!wbRepos.existsById(wallBracketId)) {throw new RecordNotFoundException();}
        WallBracket wb = wbRepos.findById(wallBracketId).orElse(null);
        tvwb.setTelevision(tv);
        tvwb.setWallBracket(wb);
        TelevisionWallBracketKey id = new TelevisionWallBracketKey(televisionId, wallBracketId);
        tvwb.setId(id);
        tvwbRepos.save(tvwb);
        return id;
    }
}
