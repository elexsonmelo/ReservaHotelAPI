package com.example.reservahotelapi.controller;

import com.example.reservahotelapi.dto.QuartoDto;
import com.example.reservahotelapi.service.QuartoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    @PostMapping("/criar")
    public ResponseEntity<QuartoDto> create(@RequestBody QuartoDto quartoDto) {
        QuartoDto dto = quartoService.salvar(quartoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<QuartoDto> update(@PathVariable Long id, @RequestBody QuartoDto quartoDTO) {
        quartoDTO = quartoService.atualizar(id, quartoDTO);
        return ResponseEntity.ok().body(quartoDTO);
    }

    @GetMapping("/listar")
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
    public ResponseEntity<Void > delete(@PathVariable Long id) throws Exception {
        quartoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
