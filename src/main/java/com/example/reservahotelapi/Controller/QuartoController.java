package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.QuartoDTO;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Service.QuartoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    private final QuartoService quartoService;
    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<Quarto> create(@RequestBody Quarto quarto) {
        Quarto entity = quartoService.salvar(quarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QuartoDTO> update(@PathVariable Long id, @RequestBody QuartoDTO quartoDTO) {
        quartoDTO = quartoService.update(id, quartoDTO);
        return ResponseEntity.ok().body(quartoDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuartoDTO>> findAll() {
        List<QuartoDTO> quartos = quartoService.buscarTodos();
        return ResponseEntity.ok().body(quartos);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuartoDTO> findById(@PathVariable Long id) {
        QuartoDTO quarto = quartoService.buscarPorId(id);
        return ResponseEntity.ok().body(quarto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        quartoService.delete(id);
        return ResponseEntity.ok().body("Excluído com sucesso.");
    }
}
