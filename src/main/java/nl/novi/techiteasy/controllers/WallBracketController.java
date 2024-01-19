package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy.dtos.wallbracket.WallBracketInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/wallbrackets")

public class WallBracketController {
    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {
        return ResponseEntity.ok(wallBracketService.getAllWallBrackets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable long id){
        if (id > 0) {
            WallBracketDto wbDto = wallBracketService.getWallBracketId(id);
            return ResponseEntity.ok(wbDto);
        } else {
            throw new RecordNotFoundException("no wall bracket found with this id");
        }
    }

    @PostMapping
    public ResponseEntity<WallBracketDto> addRemoteController(@RequestBody WallBracketInputDto wbInputDto, BindingResult br){
        if (br.hasFieldErrors()) {
            throw new ValidationException(checkForBindingResult(br));
        } else {
            WallBracketDto savedWallBracket;
            savedWallBracket = wallBracketService.addWallBracket(wbInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedWallBracket.id).toUriString());
            return ResponseEntity.created(uri).body(savedWallBracket);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable long id, @RequestBody WallBracketInputDto wallBracket ) {
        WallBracketDto changeWallBracketId = WallBracketService.updateWallBracket(id, wallBracket);

        return ResponseEntity.ok().body(changeWallBracketId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable("id") Long id) {

        wallBracketService.deleteWallBracket(id);
        return ResponseEntity.noContent().build();
        }
    }
