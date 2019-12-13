package com.assignment.FlagPicker.manager;

import com.assignment.FlagPicker.bean.Continent;
import com.assignment.FlagPicker.bean.Country;
import com.assignment.FlagPicker.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    public List<Continent> getContinentDetails() {
        return continentRepository.getContentResult();
    }


    public List<Continent> getCountriesDetails(String continentName) {
        if (StringUtils.isEmpty(continentName)) {
            throw new RuntimeException("No entry passed");
        } else {
            return continentRepository.getCountryResultByContinent(continentName);
        }
    }

    public String getCountriesFlag(String countryName) {
        if (StringUtils.isEmpty(countryName)) {
            throw new RuntimeException("No country passed");
        } else {
            List<Continent> countryFlagByCountry = continentRepository.getCountryFlagByCountry(countryName);
            String flag = "Country does not exist";
            if (!CollectionUtils.isEmpty(countryFlagByCountry)) {
                Continent continentValue = countryFlagByCountry.get(0);
                if (Objects.nonNull(continentValue)) {
                    List<Country> countries = continentValue.getCountries();
                    if (!CollectionUtils.isEmpty(countries)) {
                        for (Country value : countries) {
                            String flagValue = value.getName();
                            if (flagValue.equalsIgnoreCase(countryName)) flag = value.getFlag();
                        }
                    }
                }
            }
            return flag;
        }
    }
}

