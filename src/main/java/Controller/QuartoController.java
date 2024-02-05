package Controller;


import Model.Quarto;
import Service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/quartos")
public class RoomController {

    @Autowired
    private QuartoService quartoService;

    @PostMapping
    public ResponseEntity<Quarto> create(@RequestBody Quarto quarto) {
        quarto = quartoService.create(quarto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(quarto.getQuartoId()).toUri();
        return ResponseEntity.created(uri).body(quarto);
    }

    @GetMapping
    public ResponseEntity<List<Quarto>> findAll() {
        List<Quarto> list = quartoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quarto> findById(@PathVariable(value = "id") Long id) {
        Quarto quarto = quartoService.findById(id);
        return ResponseEntity.ok().body(quarto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quarto> update(@PathVariable(value = "id") Long id, @RequestBody Quarto quarto) {
        quarto = quartoService.update(id, quarto);
        return ResponseEntity.ok().body(quarto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        quartoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
