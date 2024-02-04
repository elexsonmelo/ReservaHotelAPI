package Controller;

import Model.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        return null;
    }
}
