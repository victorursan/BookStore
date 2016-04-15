package com.BookStore.Repository.DbRepository;

import com.BookStore.Models.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class ClientRowMapper implements RowMapper<Client> {
    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("clientid"));
        client.setFirstName(rs.getString("firstname"));
        client.setLastName(rs.getString("lastname"));
        return client;
    }
}
