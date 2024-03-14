package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.QuartoDto;
import com.example.reservahotelapi.Model.Quarto;
import com.example.reservahotelapi.Service.QuartoService;
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
    public ResponseEntity<QuartoDto> update(@PathVariable Long id, @RequestBody QuartoDto quartoDTO) {
        quartoDTO = quartoService.atualizar(id, quartoDTO);
        return ResponseEntity.ok().body(quartoDTO);
    }

    @GetMapping
    public ResponseEntity<List<QuartoDto>> findAll() {
        List<QuartoDto> quartos = quartoService.buscarTodos();
        return ResponseEntity.ok().body(quartos);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuartoDto> findById(@PathVariable Long id) {
        QuartoDto quarto = quartoService.buscarPorId(id);
        return ResponseEntity.ok().body(quarto);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        quartoService.deletar(id);
        return ResponseEntity.ok().body("Excluído com sucesso.");
    }
}
