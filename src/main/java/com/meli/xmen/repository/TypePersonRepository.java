package com.meli.xmen.repository;


import com.meli.xmen.entity.TypePerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TypePersonRepository  {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public BigDecimal countByismutant(Boolean isMutant){
        return jdbcTemplate.queryForObject("select count(*) from type_person where ismutant=?",new Boolean[]{isMutant},BigDecimal.class);
    }

    public int save(TypePerson typePerson){
        return jdbcTemplate.update("insert into type_person (dna,ismutant) values(?,?)",typePerson.getDna(),typePerson.getIsmutant());
    }
}
