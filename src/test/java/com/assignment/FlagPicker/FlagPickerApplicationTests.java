package com.assignment.FlagPicker;

import com.assignment.FlagPicker.bean.Continent;
import com.assignment.FlagPicker.controller.FlagPickerController;
import com.assignment.FlagPicker.exception.FlagPickerException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlagPickerApplicationTests {

    @Autowired
    private FlagPickerController flagPickerController;

    @Test
    public void getContinentDetails() {
        List<Continent> continentDetails = flagPickerController.getContinentDetails();
        Assert.assertFalse(continentDetails.isEmpty());
    }

    @Test
    public void getCountriesDetailByContinentNameExit() throws FlagPickerException {
        ResponseEntity<?> continentDetails = flagPickerController.getCountriesDetails("Africa");
        List<Continent> body = (List<Continent>) continentDetails.getBody();
        Assert.assertFalse(body.isEmpty());
    }

    @Test
    public void getCountriesDetailByContinentNameNotExit() throws FlagPickerException {
        ResponseEntity<?> continentDetails = flagPickerController.getCountriesDetails("Ind");
        List<Continent> body = (List<Continent>) continentDetails.getBody();
        Assert.assertTrue(body.isEmpty());
    }

    @Test
    public void getCountyFlagByCountryNameExist() throws FlagPickerException {
        ResponseEntity<?> responseEntity = flagPickerController.getCountryFlag("Australia");
        String body = (String) responseEntity.getBody();
        Assert.assertEquals(body, "AU");
    }

    @Test
    public void getCountyFlagByCountryNameDoesNotExist() throws FlagPickerException {
        ResponseEntity<?> responseEntity = flagPickerController.getCountryFlag("Aus");
        String body = (String) responseEntity.getBody();
        Assert.assertEquals(body, "Country does not exist");
    }

}
