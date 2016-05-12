package com.BookStore.web.controller;

import com.BookStore.core.models.Client;
import com.BookStore.core.service.ClientService;
import com.BookStore.web.dto.ClientDto;
import com.BookStore.web.dto.ClientsDto;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 4/28/16.
 */
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/clients", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public ClientsDto getClients() {

        List<Client> disciplineList = clientService.findAll();

        return new ClientsDto(disciplineList);
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT, consumes = "application/vnd.api+json")
    public Map<String, ClientDto> updateClient(@PathVariable final Integer clientId,
                                                @RequestBody final ClientDto clientDto) {

        Client client = clientService.updateClient(clientId, clientDto.getFirstName(), clientDto.getLastName());

        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", new ClientDto(client));

        return clientDtoMap;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    public Map<String, ClientDto> createClient(@RequestBody final ClientDto clientDto) {

        Client client = clientService.createClient(clientDto.getFirstName(), clientDto.getLastName());

        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", new ClientDto(client));

        return clientDtoMap;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE, consumes = "application/vnd.api+json")
    public ResponseEntity deleteClient(@PathVariable final Integer clientId) {

        clientService.deleteClient(clientId);
        //todo catch errors
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
