package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketInputDto;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.WallBracket;
import nl.novi.techiteasy.repositories.WallBracketRepository;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

private final WallBracketRepository wbRepos;

    public WallBracketService(WallBracketRepository wbRepos) {
        this.wbRepos = wbRepos;
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

    public WallBracket convertWallBracketDtoToWallBracket(WallBracketDto wbDto) {
        WallBracket wb = new WallBracket();

        wb.setId(wbDto.id);
        wb.setName(wbDto.name);
        wb.setSize(wbDto.size);
        wb.setAdjustable(wbDto.adjustable);
        wb.setPrice(wbDto.price);

        return wb;
    }


    }
