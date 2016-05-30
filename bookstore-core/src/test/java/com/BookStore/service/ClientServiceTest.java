package com.BookStore.service;

import com.BookStore.ITConfig;
import com.BookStore.core.models.Client;
import com.BookStore.core.service.ClientService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by victor on 5/27/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ITConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@DatabaseSetup("/META-INF/dbtest/db-data.xml")
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientService.findAll();
        assertEquals("there should be 4 clients", 4, clients.size());
    }

    @Test
    public void updateClient() throws Exception {
        Client client = clientService.updateClient(1, "name1", "name2", Collections.emptySet());
        assertEquals("the name should be 'name1'", client.getFirstName(), "name1");
    }

    @Test
    public void createClient() throws Exception {
        Client client = clientService.createClient("name5", "name5");
        assertEquals("the name should be 'name5'", client.getFirstName(), "name5");
    }

    @Ignore
    @Test
    public void deleteClient() throws Exception {
        clientService.deleteClient(1);
        List<Client> clients = clientService.findAll();
        assertEquals("there should be 3 clients", 3, clients.size());
    }

}
