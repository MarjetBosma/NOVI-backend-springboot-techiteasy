package nl.novi.techiteasy.controllers;
import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.television.IdInputDto;
import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.services.TelevisionService;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.dtos.television.TelevisionInputDto;
import nl.novi.techiteasy.models.Television;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/televisions")

public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }


    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions() {
        return ResponseEntity.ok(televisionService.getAllTelevisions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable long id){
        if (id > 0) {
            TelevisionDto tvDto = televisionService.getTelevisionById(id);
            return ResponseEntity.ok(tvDto);
        } else {
            throw new RecordNotFoundException("no television found with this id");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> deleteTelevision(@PathVariable long id){

        televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();

    }
    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@RequestBody TelevisionInputDto tvInputDto, BindingResult br){
        if (br.hasFieldErrors()) {
            throw new ValidationException(checkForBindingResult(br));
        } else {
            TelevisionDto savedTelevision = televisionService.createTelevision(tvInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedTelevision.id).toUriString());
            return ResponseEntity.created(uri).body(savedTelevision);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable long id, @RequestBody TelevisionInputDto television ) {
        TelevisionDto changeTelevisionId = televisionService.updateTelevision(id, television);
        return ResponseEntity.ok().body(changeTelevisionId);
    }

    @PutMapping("/televisions/{id}/remotecontroller")
    public ResponseEntity<Object> assignRemoteControllerToTelevision(@PathVariable("id") Long id,@Valid @RequestBody IdInputDto input) {
        televisionService.assignRemoteControllerToTelevision(id, input.id);
        return ResponseEntity.noContent().build();
    }

}
