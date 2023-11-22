package nl.novi.techiteasy.controllers;
import nl.novi.techiteasy.exceptions.NameNotApprovedException;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/televisions")

public class TelevisionController {
    private ArrayList<String> televisionDatabase = new ArrayList<>();

    @GetMapping("/showTv")
    public ResponseEntity<Object> showTvList() {
        return ResponseEntity.ok("Lijst van alle tv's: " + televisionDatabase);
    }

    @PostMapping("/addTv")
    public ResponseEntity<Object> addTv(@RequestBody String tv) {
        if (tv.length() > 20) {
            throw new NameNotApprovedException("Tv-naam is groter dan 20 karakters");
        } else if (tv.length() < 4) {
            throw new NameNotApprovedException("Tv-naam is kleiner dan 4 karakters");
        } else {
            this.televisionDatabase.add(tv);
            return ResponseEntity.created(null).body("Tv toegevoegd: " + tv);
        }
    }

    @GetMapping("/showTv/{id}")
    public ResponseEntity<Object> showTv(@PathVariable ("id") int id) {
        return ResponseEntity.ok(televisionDatabase.get(id));
    }

    @PutMapping("/changeTv/{id}")
    public ResponseEntity<Object> changeTv(@PathVariable ("id") int id, @RequestBody String tv) {
        if (televisionDatabase.isEmpty() || id>televisionDatabase.size()) {
            throw new RecordNotFoundException("Id-nummer " + id + " staat niet in de database.");
            // Ik krijg niet bovenstaande melding, maar de standaardmelding uit de exceptionhandler.
        } else {
            televisionDatabase.set(id, tv);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/deleteTv/{id}")
    public ResponseEntity<Object> deleteTv(@PathVariable ("id") int id) {
        televisionDatabase.set(id, null);
        return ResponseEntity.noContent().build();
    }
}
