package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Model.Cliente;
import com.example.reservahotelapi.Model.Endereco;
import com.example.reservahotelapi.Repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public void validarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Endereco> resp = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json/", cep), Endereco.class);
        Endereco endereco = resp.getBody();
        if (endereco == null || endereco.getCep() == null) {
            throw new IllegalArgumentException("CEP inválido");
        }
    }

    public List<ClienteDto> buscarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ClienteDto buscarPorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(this::mapToDto).orElse(null);
    }

    public ClienteDto salvar(ClienteDto clienteDto) {
        Cliente cliente = mapToEntity(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return mapToDto(clienteSalvo);
    }

    public ClienteDto updateCliente(Long id, ClienteDto clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return mapToDto(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDto mapToDto(Cliente cliente) {
        ClienteDto clienteDTO = new ClienteDto();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setEndereco(cliente.getEndereco());
        return clienteDTO;
    }

    public Cliente mapToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setEndereco(clienteDTO.getEndereco());
        return cliente;
    }
}
