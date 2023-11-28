package com.sparta.jarjarbinks.worldproject.model.repositories;

import com.sparta.jarjarbinks.worldproject.model.entities.CityDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityDTO, Integer> {
}