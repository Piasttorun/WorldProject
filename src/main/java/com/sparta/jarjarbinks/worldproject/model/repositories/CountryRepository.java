package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryDTO, String> {

    public List<CountryDTO> findByCode(String countryCode);

}