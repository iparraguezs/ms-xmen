package com.meli.xmen.repository;

import com.meli.xmen.entity.TypePerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface TypePersonRepository extends JpaRepository<TypePerson, UUID> {
    BigDecimal countByismutant(Boolean ismutant);
}
