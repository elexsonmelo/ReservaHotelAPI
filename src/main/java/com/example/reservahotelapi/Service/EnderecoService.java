package Service;

import Dto.EnderecoRequest;
import Dto.EnderecoResponse;
import com.example.reservahotelapi.feign.EnderecoFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EnderecoService {
    private final EnderecoFeign enderecoFeign;
    public EnderecoResponse executa(EnderecoRequest request){
        return enderecoFeign.buscaEnderecoCep(request.getCep());

    }
}
