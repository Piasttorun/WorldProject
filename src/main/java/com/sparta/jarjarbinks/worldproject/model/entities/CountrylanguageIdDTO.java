package com.sparta.jarjarbinks.worldproject.model.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Schema(description = "DTO used to represent composite key for CountryLanguageDTO." +
        " Must specify country code and language, and can then be linked to CountryLanguageDTO object.")
@Embeddable
public class CountrylanguageIdDTO implements Serializable {
    private static final long serialVersionUID = -3399698344831111007L;
    @Size(max = 3)
    @NotNull
    @Column(name = "CountryCode", nullable = false, length = 3)
    @Schema(name = "countryCode", defaultValue = "XYZ",
    description = "Linked country code of CountryLanguage composite key")
    private String countryCode;

    @Size(max = 30)
    @NotNull
    @Schema(name = "language", defaultValue = "English",
            description = "Linked language of CountryLanguage composite key")
    @Column(name = "Language", nullable = false, length = 30)
    private String language;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CountrylanguageIdDTO entity = (CountrylanguageIdDTO) o;
        return Objects.equals(this.countryCode, entity.countryCode) &&
                Objects.equals(this.language, entity.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, language);
    }

}