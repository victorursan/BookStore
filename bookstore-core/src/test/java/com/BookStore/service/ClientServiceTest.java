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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

    @Ignore
    @Test
    public void updateClient() throws Exception {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void createClient() throws Exception {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void deleteClient() throws Exception {
        fail("Not yet implemented");
    }

}
