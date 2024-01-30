package Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Getter
@Service
public class EnderecoRequest {
    private String cep;
}
