package com.example.reservahotelapi.unit;


import com.example.reservahotelapi.dto.ClienteDto;
import com.example.reservahotelapi.model.Cliente;
import com.example.reservahotelapi.repository.ClienteRepository;
import com.example.reservahotelapi.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

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

        ClienteDto result = clienteService.atualizar(1L, clienteDto);

        assertNotNull(result);
        assertEquals(cliente.getId(), result.getId());
        assertEquals(cliente.getNome(), result.getNome());
        assertEquals(cliente.getEmail(), result.getEmail());

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testDeleteCliente() {

        clienteService.excluir(1L);

        verify(clienteRepository, times(1)).deleteById(1L);
    }
}

