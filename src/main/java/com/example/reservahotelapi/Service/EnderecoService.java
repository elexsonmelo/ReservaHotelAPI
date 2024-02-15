package com.example.reservahotelapi.Service;

import com.example.reservahotelapi.Dto.EnderecoRequest;
import com.example.reservahotelapi.Dto.EnderecoResponse;
import com.example.reservahotelapi.feign.EnderecoFeign;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoFeign enderecoFeign;
    public EnderecoResponse executa(EnderecoRequest request){
        return enderecoFeign.buscaEnderecoCep(request.getCep());
    }
}
