package com.sparta.jarjarbinks.worldproject.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "DTO used to represent Country Language." +
        " NotNullable values include IsOfficial (T/F) and percentage.")
@Entity
@Table(name = "countrylanguage", schema = "world")
public class CountrylanguageDTO {
    @EmbeddedId
    private CountrylanguageIdDTO id;

    @MapsId("countryCode")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CountryCode", nullable = false)
    @JsonBackReference
    private CountryDTO countryCode;

    @NotNull
    @Lob
    @Column(name = "IsOfficial", nullable = false)
    private String isOfficial;

    @NotNull
    @Column(name = "Percentage", nullable = false, precision = 4, scale = 1)
    private BigDecimal percentage;

    public CountrylanguageIdDTO getId() {
        return id;
    }

    public void setId(CountrylanguageIdDTO id) {
        this.id = id;
    }

    public CountryDTO getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryDTO countryCode) {
        this.countryCode = countryCode;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

}