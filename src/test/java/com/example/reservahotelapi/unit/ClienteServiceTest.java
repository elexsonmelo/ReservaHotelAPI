package com.example.reservahotelapi.unit;

import com.example.reservahotelapi.Dto.CepResultDto;
import com.example.reservahotelapi.Dto.ClienteDto;
import com.example.reservahotelapi.Model.Cliente;
import com.example.reservahotelapi.Repository.ClienteRepository;
import com.example.reservahotelapi.Service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDto clienteDto;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Teste");
        cliente.setEmail("teste@teste.com");

        clienteDto = new ClienteDto();
        clienteDto.setId(1L);
        clienteDto.setNome("Teste");
        clienteDto.setEmail("teste@teste.com");
    }

    @Test
    void testGetAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteDto> result = clienteService.buscarTodos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(cliente.getId(), result.get(0).getId());
        assertEquals(cliente.getNome(), result.get(0).getNome());
        assertEquals(cliente.getEmail(), result.get(0).getEmail());

        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testGetClienteById() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        ClienteDto result = clienteService.buscarPorId(1L);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        assertEquals(cliente.getNome(), result.getNome());
        assertEquals(cliente.getEmail(), result.getEmail());

        verify(clienteRepository, times(1)).findById(1L);
    }

    @Test
    void testSalvarCliente() {
        
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDto result = clienteService.salvar(clienteDto);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        assertEquals(cliente.getNome(), result.getNome());
        assertEquals(cliente.getEmail(), result.getEmail());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testUpdateCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        ClienteDto result = clienteService.updateCliente(1L, clienteDto);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        assertEquals(cliente.getNome(), result.getNome());
        assertEquals(cliente.getEmail(), result.getEmail());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testDeleteCliente() {
        clienteService.deletar(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testValidarCep_InvalidCep() {
        when(restTemplate.getForEntity(anyString(), eq(CepResultDto.class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        assertThrows(IllegalArgumentException.class, () -> clienteService.validarCep(clienteDto.getCep()));
    }
}

