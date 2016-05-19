package com.BookStore.web.controller;

import com.BookStore.core.models.Client;
import com.BookStore.core.service.ClientService;
import com.BookStore.web.converter.ClientConverter;
import com.BookStore.web.dto.ClientDto;
import com.BookStore.web.dto.ClientsDataDto;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by victor on 4/28/16.
 */
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET, produces = "application/vnd.api+json")
    public ClientsDataDto getClients() {

        List<Client> clientList = clientService.findAll();

        Set<ClientDto> clientDtos = clientConverter.convertModelsToDtos(clientList);

        return new ClientsDataDto(clientDtos);
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.PUT, consumes = "application/vnd.api+json")
    public Map<String, ClientDto> updateClient(@PathVariable final Integer clientId,  @RequestBody final Map<String, ClientDto> clientRequestDtoMap) {
        ClientDto clientDto = clientRequestDtoMap.get("client");
        Client client = clientService.updateClient(clientId, clientDto.getFirstName(), clientDto.getLastName(), clientDto.getBooks());

        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", clientConverter.convertModelToDto(client));

        return clientDtoMap;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST, consumes = "application/vnd.api+json", produces = "application/vnd.api+json")
    public Map<String, ClientDto> createClient(@RequestBody final Map<String, ClientDto> clientRequestDtoMap) {
        ClientDto clientDto = clientRequestDtoMap.get("client");
        System.out.println(clientDto.getFirstName() + clientDto.getLastName());
        Client client = clientService.createClient(clientDto.getFirstName(), clientDto.getLastName());

        Map<String, ClientDto> clientDtoMap = new HashMap<>();
        clientDtoMap.put("client", clientConverter.convertModelToDto(client));

        return clientDtoMap;
    }

    @RequestMapping(value = "/clients/{clientId}", method = RequestMethod.DELETE, consumes = "application/vnd.api+json")
    public ResponseEntity deleteClient(@PathVariable final Integer clientId) {

        clientService.deleteClient(clientId);
        //todo catch errors
        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
