package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CountryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryDTO, String> {

    public Optional<CountryDTO> findByCode(String countryCode);
    public void deleteCountryDTOByCode(String code);
}