package nl.novi.techiteasy.controllers;
import nl.novi.techiteasy.exceptions.NameNotApprovedException;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")

public class TelevisionController {

    @Autowired private TelevisionRepository televisionRepository;

//    @GetMapping("/showTv")
//    public ResponseEntity<Television> showTvList() {
//        return ResponseEntity.ok("Lijst van alle tv's: ");
//    }

    @PostMapping("/addTv")
    public ResponseEntity<Television> addTv(@RequestBody Television television) {
        Television savedTelevision = televisionRepository.save(television);
        return ResponseEntity.created(null).body(savedTelevision);
    }

    @GetMapping("/showTv/{id}")
    public ResponseEntity<Television> showTv(@PathVariable ("id") long id) {
        Optional<Television> savedTelevision = televisionRepository.findById(id);
        return ResponseEntity.ok(savedTelevision.get());
    }
}
