package com.example.reservahotelapi.Controller;

import com.example.reservahotelapi.Dto.EnderecoRequest;
import com.example.reservahotelapi.Service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;
@GetMapping("/consulta")
    public ResponseEntity consultaCep(@RequestBody EnderecoRequest enderecoRequest){
        return ResponseEntity.ok(enderecoService.executa(enderecoRequest));
    }
}
