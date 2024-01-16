package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy.dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/remotecontrollers")

public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {
        return ResponseEntity.ok(remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable long id){
        if (id > 0) {
            RemoteControllerDto rcDto = remoteControllerService.getRemoteControllerId(id);
            return ResponseEntity.ok(rcDto);
        } else {
            throw new RecordNotFoundException("no remote controller found with this id");
        }
    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@RequestBody RemoteControllerInputDto rcInputDto, BindingResult br){
        if (br.hasFieldErrors()) {
            throw new ValidationException(checkForBindingResult(br));
        } else {
            RemoteControllerDto savedRemoteController;
            savedRemoteController = RemoteControllerService.createRemoteController(rcInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedRemoteController.id).toUriString());
            return ResponseEntity.created(uri).body(savedRemoteController);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@PathVariable long id, @RequestBody RemoteControllerInputDto remoteController ) {
        RemoteControllerDto changeRemoteControllerId = RemoteControllerService.updateRemoteController(id, remoteController);

        return ResponseEntity.ok().body(changeRemoteControllerId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable("id") Long id) {
        Boolean check = remoteControllerService.deleteRemoteController(id);
        if(check) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Cannot delete, this id does not exist");
        }
    }
}
