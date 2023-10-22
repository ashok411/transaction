package com.ashok.transaction.controller;

import com.ashok.transaction.entity.Address;
import com.ashok.transaction.exception.MyCustomException;
import com.ashok.transaction.model.AddressDto;
import com.ashok.transaction.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Implement CRUD operations for Address here (POST, GET, PUT, DELETE)

       // Save a new address
    @PostMapping
    public ResponseEntity<Address> saveAddress(@RequestBody AddressDto address)
        throws MyCustomException {
        Address savedAddress = addressService.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }
}
