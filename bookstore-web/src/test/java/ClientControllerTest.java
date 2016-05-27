import com.BookStore.core.models.Client;
import com.BookStore.core.service.ClientService;
import com.BookStore.web.controller.ClientController;
import com.BookStore.web.converter.ClientConverter;

import com.BookStore.web.dto.ClientDto;
import com.BookStore.web.dto.ClientsDataDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @Mock
    private ClientConverter clientConverter;

    @InjectMocks
    private ClientController clientController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getClients() throws Exception {
        List<Client> clients = new ArrayList<>();

        Set<ClientDto> clientDtos = new HashSet<>();
        clientDtos.add(mock(ClientDto.class));
        clientDtos.add(mock(ClientDto.class));

        when(clientService.findAll()).thenReturn(clients);
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDtos);

        ClientsDataDto response = clientController.getClients();

        assertEquals("There should be two students", 2, response.getClients().size());
    }

    @Test
    public void updateClient() throws Exception {
        Client client = new Client("ana", "ana");

        ClientDto clientDto = mock(ClientDto.class);
        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", clientDto);

        when(clientService.updateClient(anyInt(), anyString(), anyString(), anySet())).thenReturn(client);

        ClientDto result = new ClientDto();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        when(clientConverter.convertModelToDto(client)).thenReturn(result);
        Map<String, ClientDto> map = clientController.updateClient(client.getId(), clientDtoMap);
        assertEquals("Names should be equal", "ana", map.get("client").getFirstName());
    }

    @Test
    public void createClient() throws Exception {
        Client client = new Client("ana", "ana");

        ClientDto clientDto = mock(ClientDto.class);
        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", clientDto);

        when(clientService.createClient(anyString(), anyString())).thenReturn(client);

        ClientDto result = new ClientDto();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        when(clientConverter.convertModelToDto(client)).thenReturn(result);

        Map<String, ClientDto> map = clientController.createClient(clientDtoMap);
        assertEquals("Names should be equal", "ana", map.get("client").getFirstName());
    }


    @Test(expected = Exception.class)
    public void deleteClient() throws Exception {
        List<Client> clients = new ArrayList<>();

        Set<ClientDto> clientDtos = new HashSet<>();
        clientDtos.add(mock(ClientDto.class));
        clientDtos.add(mock(ClientDto.class));

        doThrow(new Exception()).when(clientService).deleteClient(anyInt());
        when(clientConverter.convertModelsToDtos(clients)).thenReturn(clientDtos);

        ResponseEntity<ClientsDataDto> response =
                (ResponseEntity<ClientsDataDto>) clientController.deleteClient(clientDtos.iterator().next().getId());
        ClientsDataDto clientsDataDto = response.getBody();

        assertEquals("There should be one client", 1, clientsDataDto.getClients().size());
    }

}