package nl.novi.techiteasy.controllers;
import nl.novi.techiteasy.dtos.TelevisionDto;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")

public class TelevisionController {

    private final TelevisionService televisionService;

    public TelevisionController(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

//    @GetMapping()
//    public ResponseEntity<List<Television>> showTvList() {
//        List<Television> televisions;
//        televisions = televisionRepository.findAll();
//        return ResponseEntity.ok().body(televisions);
//    }

    @PostMapping()
    public ResponseEntity<TelevisionDto> addTv(@RequestBody TelevisionDto televisionDto) {
        TelevisionDto savedTelevision = televisionService.createTelevision(televisionDto);

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + savedTelevision.id).toUriString());

        return ResponseEntity.created(uri).body(savedTelevision);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Television> showTv(@PathVariable ("id") Long id) {
//        Optional<Television> television = televisionRepository.findById(id);
//        if (television.isEmpty()) {
//            throw new RecordNotFoundException("Id " + id + " not found ");
//        } else {
//            Television foundTelevision = television.get();
//            return ResponseEntity.ok().body(foundTelevision);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTelevision (@PathVariable("id") Long id){
//        televisionRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Television> updateTelevision (@PathVariable("id") Long id, @RequestBody Television
//    updatedTelevision) {
//        Optional<Television> television = televisionRepository.findById(id);
//            if (television.isEmpty()) {
//                throw new RecordNotFoundException("Id " + id + " not found ");
//            } else {
//                Television television1 = television.get();
//                television1.setAmbiLight(updatedTelevision.getAmbiLight());
//                television1.setAvailableSize(updatedTelevision.getAvailableSize());
//                television1.setAmbiLight(updatedTelevision.getAmbiLight());
//                television1.setBluetooth(updatedTelevision.getBluetooth());
//                television1.setBrand(updatedTelevision.getBrand());
//                television1.setHdr(updatedTelevision.getHdr());
//                television1.setName(updatedTelevision.getName());
//                television1.setOriginalStock(updatedTelevision.getOriginalStock());
//                television1.setPrice(updatedTelevision.getPrice());
//                television1.setRefreshRate(updatedTelevision.getRefreshRate());
//                television1.setScreenQuality(updatedTelevision.getScreenQuality());
//                television1.setScreenType(updatedTelevision.getScreenType());
//                television1.setSmartTv(updatedTelevision.getSmartTv());
//                television1.setSold(updatedTelevision.getSold());
//                television1.setType(updatedTelevision.getType());
//                television1.setVoiceControl(updatedTelevision.getVoiceControl());
//                television1.setWifi(updatedTelevision.getWifi());
//                Television returnTelevision = televisionRepository.save(television1);
//                return ResponseEntity.ok().body(returnTelevision);
//            }
//        }
            // Bij de PutMapping was ik niet helemaal zelf uitgekomen, ik heb ik de uitwerkingen gekeken. Dit werkt nu, maar ik heb nog niet het idee dat ik alle onderdelen echt doorgrond.
    }
