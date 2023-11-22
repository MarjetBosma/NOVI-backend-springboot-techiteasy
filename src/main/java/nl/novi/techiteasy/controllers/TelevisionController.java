package nl.novi.techiteasy.controllers;
import nl.novi.techiteasy.exceptions.NameNotApprovedException;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TelevisionController {
    private ArrayList<String> tvList = new ArrayList<>();

    @GetMapping("/showTv")
    public ResponseEntity<Object> showTvList() {
    return ResponseEntity.ok("Lijst van alle tv's");
    }

    @PostMapping("/addTv")
    public ResponseEntity<Object> addTv(@RequestBody String tv) {
        if (tv.length() > 20) {
            throw new NameNotApprovedException("Tv-naam is groter dan 20 karakters");
        } else if (tv.length() < 4) {
            throw new NameNotApprovedException("Tv-naam is kleiner dan 4 karakters");
        } else {
            this.tvList.add(tv);
            return ResponseEntity.created(null).body(tv + " tv toegevoegd");
        }
    }

    @GetMapping("/showTv/{id}")
    public ResponseEntity<Object> tv(@PathVariable ("id") int id, @RequestBody String name) {
        if (id < 10) {
            return ResponseEntity.ok(id + " Dit is een tv");
        } else {
            throw new RecordNotFoundException("Getal is hoger dan 10");
        }
    }

    @PutMapping("/changeTvList/{id}")
    public ResponseEntity<Object> tvList(@PathVariable ("id") int id) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteTv/{id}")
    public ResponseEntity<Object> deleteTv(@PathVariable ("id") int id) {
        return ResponseEntity.noContent().build();
    }
}
