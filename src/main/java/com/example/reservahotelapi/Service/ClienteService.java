package com.example.reservahotelapi.Service;


import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Model.Cliente;
import com.example.reservahotelapi.Repository.ClienteRepository;
import com.example.reservahotelapi.Repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoRepository enderecoRepository;

    private boolean validarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepResultDto> resp = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json/", cep), CepResultDto.class);
        CepResultDto cepResultDto = resp.getBody();
        return cepResultDto != null && cepResultDto.getCep() != null;
    }

    public List<ClienteDto> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public ClienteDto getClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(this::convertToDto).orElse(null);
    }
    public ClienteDto salvarCliente(ClienteDto clienteDto) {
        if (!validarCep(clienteDto.getEndereco().getCep())) {
            throw new IllegalArgumentException("CEP inválido");
        }
        Cliente cliente = convertToEntity(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToDto(clienteSalvo);
    }
    public ClienteDto updateCliente(Long id, ClienteDto clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return convertToDto(cliente);
    }
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDTO = new ClienteDto();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        return clienteDTO;
    }
    private Cliente convertToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        return cliente;
    }
}
