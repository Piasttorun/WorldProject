package com.sparta.jarjarbinks.worldproject.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Schema(description = "DTO used to represent a city record. Notnullable fields include " +
        "name, countryCode (of CountryDTO type), district and population.  ")
@Table(name = "city", schema = "world")
public class CityDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 35)
    @NotNull
    @Schema(name = "name", defaultValue = "Springbootica",
            description = "Name of city.")
    @Column(name = "Name", nullable = false, length = 35)
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CountryCode", nullable = false)
    @JsonBackReference
    private CountryDTO countryCode;

    @Size(max = 20)
    @NotNull
    @Schema(name = "district", defaultValue = "Springbootistan",
            description = "District of city")
    @Column(name = "District", nullable = false, length = 20)
    private String district;

    @NotNull
    @Schema(name = "population", defaultValue = "12000",
            description = "Population of city.")
    @Column(name = "Population", nullable = false)
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryDTO countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

}