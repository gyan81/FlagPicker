package com.assignment.FlagPicker.repository;


import com.assignment.FlagPicker.bean.Continent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@org.springframework.stereotype.Repository
public class ContinentRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Continent> getContentResult() {
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Continent.class);
    }

    public List<Continent> getCountryResultByContinent(String continent) {
        Query query = new Query();
        query.addCriteria(Criteria.where("continent").is(continent));
        return mongoTemplate.find(query, Continent.class);
    }


    public List<Continent> getCountryFlagByCountry(String countryName) {
        Query query = new Query();
        Criteria criteria = Criteria.where("countries").elemMatch(Criteria.where("name").is(countryName));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Continent.class);
    }
}
