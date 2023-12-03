package com.sparta.jarjarbinks.worldproject.model.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "DTO used to represent CountryDTO." +
        "Not nullable values include name, continent, region," +
        " surface area, population, local name, government form and code2.")
@Entity
@Table(name = "country", schema = "world")
public class CountryDTO {
    @Id
    @Size(max = 3)
    @Schema(name = "code", defaultValue = "XYZ",
            description = "3 Lettered code used to identify country." +
            " Cannot be null.")
    @Column(name = "Code", nullable = false, length = 3)
    private String code;

    @Size(max = 52)
    @NotNull
    @Schema(name = "name", defaultValue = "XoYenZuan",
            description = "Global name of the country")
    @Column(name = "Name", nullable = false, length = 52)
    private String name;

    @NotNull
    @Lob
    @Schema(name = "continent", defaultValue = "Asia",
            description = "Enum with values ('Asia','Europe'," +
            "'North America','Africa','Oceania','Antarctica','South America')")
    @Column(name = "Continent", nullable = false)
    private String continent;

    @Size(max = 26)
    @NotNull
    @Schema(name = "region", defaultValue = "Eurasia",
            description = "Region where country is located. Cannot be null.")
    @Column(name = "Region", nullable = false, length = 26)
    private String region;

    @NotNull
    @Schema(name = "surfaceArea", defaultValue = "12500",
            description = "Surface area of country. Cannot be null.")
    @Column(name = "SurfaceArea", nullable = false, precision = 10, scale = 2)
    private BigDecimal surfaceArea;

    @Column(name = "indepYear")
    @Schema(name = "IndepYear", defaultValue = "1914",
            description = "Independence year of country. Can be null.")
    private Short indepYear;

    @NotNull
    @Schema(name = "population", defaultValue = "44500",
            description = "Total population of country. Cannot be null.")
    @Column(name = "Population", nullable = false)
    private Integer population;

    @Schema(name = "lifeExpectancy", defaultValue = "62.8",
            description = "Average life expectancy for person in country.")
    @Column(name = "LifeExpectancy", precision = 3, scale = 1)
    private BigDecimal lifeExpectancy;

    @Schema(name = "gNP", defaultValue = "400.75",
            description = "Gross national product.")
    @Column(name = "GNP", precision = 10, scale = 2)
    private BigDecimal gnp;

    @Schema(name = "gNPOld", defaultValue = "200.50",
            description = "Old gross national product.")
    @Column(name = "GNPOld", precision = 10, scale = 2)
    private BigDecimal gNPOld;


    @Size(max = 45)
    @NotNull
    @Schema(name = "localName", defaultValue = "XYZ Kingdom",
            description = "Local name of country.")
    @Column(name = "LocalName", nullable = false, length = 45)
    private String localName;

    @Size(max = 45)
    @NotNull
    @Schema(name = "governmentForm", defaultValue = "Monarchy",
            description = "Government form of country.")
    @Column(name = "GovernmentForm", nullable = false, length = 45)
    private String governmentForm;

    @Size(max = 60)
    @Schema(name = "headofState", defaultValue = "Affique Kabobdin III",
            description = "Name of the head of state of the country.")
    @Column(name = "HeadOfState", length = 60)
    private String headOfState;

    @Schema(name = "capital", defaultValue = "null",
            description = "Capital city of country, given as integer IDof city.")
    @Column(name = "Capital")
    private Integer capital;

    @Size(max = 2)
    @NotNull
    @Schema(name = "code2", defaultValue = "XZ",
            description = "Two lettered code for country")
    @Column(name = "Code2", nullable = false, length = 2)
    private String code2;

    //@OneToMany(mappedBy = "name", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    @OneToMany(mappedBy = "countryCode", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CityDTO> cities;

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Short indepYear) {
        this.indepYear = indepYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGnp() {
        return gnp;
    }

    public void setGnp(BigDecimal gnp) {
        this.gnp = gnp;
    }

    public BigDecimal getGNPOld() {
        return gNPOld;
    }

    public void setGNPOld(BigDecimal gNPOld) {
        this.gNPOld = gNPOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

}