package com.example.reservahotelapi.controller;

import com.example.reservahotelapi.dto.ClienteDto;
import com.example.reservahotelapi.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

private final ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDto>> findAll() {
        List<ClienteDto> clientes = clienteService.buscarTodos();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.buscarPorId(id);
       return ResponseEntity.ok().body(clienteDto);
    }

    @PostMapping("/criar")
    public ResponseEntity<ClienteDto> create(@RequestBody ClienteDto clienteDto) {
        ClienteDto created = clienteService.salvar(clienteDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto updatedCliente = clienteService.atualizar(id, clienteDto);
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

