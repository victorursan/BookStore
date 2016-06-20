package com.BookStore.web.controller;

import com.BookStore.core.service.hessian.HessianControllerServiceClient;
import com.BookStore.web.dto.EmptyJsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by victor on 6/20/16.
 */

@RestController
public class ImportController {
    @Autowired
    private HessianControllerServiceClient hessianControllerServiceClient;

    @RequestMapping(value = "/import", method = RequestMethod.PUT)
    public ResponseEntity<?> importDisciplines() {

        hessianControllerServiceClient.importBooks();

        return new ResponseEntity(new EmptyJsonResponse(), HttpStatus.OK);
    }

}
