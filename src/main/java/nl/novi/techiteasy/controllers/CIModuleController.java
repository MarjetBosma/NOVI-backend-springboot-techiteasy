package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy.dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.ValidationException;
import nl.novi.techiteasy.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static nl.novi.techiteasy.controllers.ControllerHelper.checkForBindingResult;

@RestController
@RequestMapping("/cimodules")

public class CIModuleController {

    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {
        return ResponseEntity.ok(ciModuleService.getAllCIModules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable long id) {
        if (id > 0)  {
            CIModuleDto ciDto = ciModuleService.getCIModuleId(id);
            return ResponseEntity.ok(ciDto);
        } else {
            throw new RecordNotFoundException("No CI-module found with this id");
        }
    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleInputDto ciInputDto, BindingResult br){
        if (br.hasFieldErrors()) {
            throw new ValidationException(checkForBindingResult(br));
        } else {
            CIModuleDto savedCIModule;
            savedCIModule = ciModuleService.createCIModule(ciInputDto);
            URI uri = URI.create(
                    ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/" + savedCIModule.id).toUriString());
            return ResponseEntity.created(uri).body(savedCIModule);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable long id, @RequestBody CIModuleInputDto ciModule ) {
        CIModuleDto changeCIModuleId = ciModuleService.updateCIModule(id, ciModule);

        return ResponseEntity.ok().body(changeCIModuleId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") Long id) {
        Boolean check = ciModuleService.deleteCIModule(id);
        if(check) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().body("Cannot delete, this id does not exist");
        }
    }
}