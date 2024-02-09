package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Service.QuartoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    @PostMapping
    public ResponseEntity<Quarto> create(@RequestBody Quarto quarto) {
        Quarto entity = quartoService.salvar(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    @GetMapping
    public ResponseEntity<List<Quarto>> findAll() {
        List<Quarto> quartos = quartoService.buscarTodos();
        return ResponseEntity.ok().body(quartos);
    }
    @GetMapping("{id}")
    public ResponseEntity<Quarto> findById(@PathVariable Integer id) {
        Quarto quarto = quartoService.buscarPorId(id);
        return ResponseEntity.ok().body(quarto);
    }
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        quartoService.delete(id);
        return ResponseEntity.ok().body("Excluido com sucesso.");
    }
}
