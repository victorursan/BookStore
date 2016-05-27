import com.BookStore.core.models.Client;
import com.BookStore.core.service.BookStoreService;
import com.BookStore.web.controller.BookStoreController;
import com.BookStore.web.converter.ClientConverter;
import com.BookStore.web.dto.ClientDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class BookStoreControllerTest {

    @Mock
    private BookStoreService bookService;

    @Mock
    private ClientConverter clientConverter;

    @InjectMocks
    private BookStoreController bookController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getClientSpentMost() throws Exception {
        Client client = new Client("ana", "ana");

        ClientDto clientDto = mock(ClientDto.class);
        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", clientDto);

        when(bookService.clientThatSpentMost()).thenReturn(client);

        ClientDto result = new ClientDto();
        result.setFirstName(client.getFirstName());
        result.setLastName(client.getLastName());
        when(clientConverter.convertModelToDto(client)).thenReturn(result);

        ClientDto r = bookController.clientThatSpentMost();
        assertEquals("Names should be equal", "ana", r.getFirstName());
    }

}