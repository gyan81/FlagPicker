package com.assignment.FlagPicker.controller;

import com.assignment.FlagPicker.bean.Continent;
import com.assignment.FlagPicker.exception.FlagPickerException;
import com.assignment.FlagPicker.manager.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flagpicker")
public class FlagPickerController {


    @Autowired
    private ContinentService continentService;

    @GetMapping("/continents")
    public List<Continent> getContinentDetails() {
        return continentService.getContinentDetails();
    }


    @GetMapping("/getContinentDetail/{continentName}")
    public ResponseEntity<?> getCountriesDetails(@PathVariable("continentName") String continentName) throws FlagPickerException {
        ResponseEntity<?> response ;
        try {
            response = new ResponseEntity<>(continentService.getCountriesDetails(continentName), HttpStatus.OK);
        } catch (Exception e) {
            throw new FlagPickerException("continentName not found");
        }
        return response;
    }

    @GetMapping("/getCountryFlag/{countryName}")
    public ResponseEntity<?> getCountryFlag(@PathVariable("countryName") String countryName) throws FlagPickerException {
        ResponseEntity<?> response = null;
        try {
            response = new ResponseEntity<>(continentService.getCountriesFlag(countryName), HttpStatus.OK);
        } catch (Exception e) {
            throw new FlagPickerException("countryName not found");
        }
        return response;
    }
}
