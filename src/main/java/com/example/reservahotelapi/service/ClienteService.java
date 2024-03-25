package com.example.reservahotelapi.service;


import com.example.reservahotelapi.dto.CepResultDto;
import com.example.reservahotelapi.dto.ClienteDto;
import com.example.reservahotelapi.model.Cliente;
import com.example.reservahotelapi.model.Endereco;
import com.example.reservahotelapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private  CepResultDto cepResultDto;

    public boolean consultaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepResultDto> resp = restTemplate.getForEntity(
                String.format("https://viacep.com.br/ws/%s/json/", cep), CepResultDto.class);
        CepResultDto cepResultDto = resp.getBody();
        return cepResultDto != null && cepResultDto.getCep() != null;
    }

    @Transactional(readOnly = true)
    public List<ClienteDto> buscarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public ClienteDto buscarPorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.map(this::mapToDto).orElse(null);
    }
    @Transactional
    public ClienteDto salvar(ClienteDto clienteDto) {
        Cliente cliente = mapToEntity(clienteDto);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return mapToDto(clienteSalvo);
    }
    @Transactional
    public ClienteDto atualizar(Long id, ClienteDto clienteDTO) {
        Cliente cliente = mapToEntity(clienteDTO);
        cliente.setId(id);
        cliente = clienteRepository.save(cliente);
        return mapToDto(cliente);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDto mapToDto(Cliente cliente) {
        ClienteDto clienteDTO = new ClienteDto();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setCep(cliente.getCep());
        return clienteDTO;
    }

    public Cliente mapToEntity(ClienteDto clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());

        return cliente;
    }
}
