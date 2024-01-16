package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private static WallBracketRepository wbRepos;

    public WallBracketService(WallBracketRepository wbRepos) {
        this.wbRepos = wbRepos;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBracketList = wbRepos.findAll();
        List<WallBracketDto> wbDtoList = new ArrayList<>();
        for (WallBracket wb : wallBracketList) {
            wbDtoList.add(convertWallBracketToWallBracketDto(wb));
        }
        return wbDtoList;
    }

    public WallBracketDto addWallBracket(WallBracketInputDto wbDto) {
        WallBracket wb = convertWallBracketDtoToWallBracket(wbDto);
        wbRepos.save(wb);
        return convertWallBracketToWallBracketDto(wb);
    }

    public Boolean deleteWallBracket(Long id) {
        wbRepos.deleteById(id);
        return null;
    }

    public static WallBracketDto updateWallBracket(Long id, WallBracketInputDto wbDto) {
        if (!wbRepos.existsById(id)) {
            throw new RecordNotFoundException("No wallbracket found");
        } else {
            WallBracket storedWallBracket = wbRepos.findById(id).orElse(null);
            assert  storedWallBracket != null;
            storedWallBracket.setName(wbDto.name);
            storedWallBracket.setSize(wbDto.size);
            storedWallBracket.setAdjustable(wbDto.adjustable);
            storedWallBracket.setPrice(wbDto.price);

            wbRepos.save(storedWallBracket);
        }
        return null;
    }

    public WallBracketDto convertWallBracketToWallBracketDto(WallBracket wb) {
        WallBracketDto wbDto = new WallBracketDto();

        wbDto.id = wb.getId();
        wbDto.name = wb.getName();
        wbDto.size = wb.getSize();
        wbDto.adjustable = wb.getAdjustable();
        wbDto.price = wb.getPrice();

        return wbDto;
    }

    public WallBracketDto getWallBracketId(long id) {
        Optional<WallBracket> wallBracketId = wbRepos.findById(id);
        if (wallBracketId.isPresent()) {
            WallBracket wb = wallBracketId.get();
            return convertWallBracketToWallBracketDto(wb);
        } else {
            throw new RecordNotFoundException("No wall bracket found");
        }
    }

    public WallBracket convertWallBracketDtoToWallBracket(WallBracketInputDto wbDto) {
        WallBracket wb = new WallBracket();

        wb.setName(wbDto.name);
        wb.setSize(wbDto.size);
        wb.setAdjustable(wbDto.adjustable);
        wb.setPrice(wbDto.price);

        return wb;
    }
}
