package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

private final ClienteService clienteService;

    @GetMapping("{cep}")
    public CepResultDto consultaCep(@PathVariable("cep") String cep) {
        return clienteService.consultarCep(cep);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAllClientes() {
        List<ClienteDto> clientes = clienteService.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.getClienteById(id);
        if (clienteDto != null) {
            return new ResponseEntity<>(clienteDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/criar")
    public ResponseEntity<ClienteDto> create (@RequestBody ClienteDto clienteDto) {
        ClienteDto createdCliente = clienteService.createCliente(clienteDto);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        ClienteDto updatedCliente = clienteService.updateCliente(id, clienteDto);
        return new ResponseEntity<>(updatedCliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

