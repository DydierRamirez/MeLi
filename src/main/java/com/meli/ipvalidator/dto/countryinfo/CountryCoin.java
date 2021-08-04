
package com.meli.ipvalidator.dto.countryinfo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "CountryCoin")
public class CountryCoin {

    public String name;
    public List<String> topLevelDomain = null;
    public String alpha2Code;
    public String alpha3Code;
    public List<String> callingCodes = null;
    public String capital;
    public List<String> altSpellings = null;
    public String region;
    public String subregion;
    public Integer population;
    public List<Float> latlng = null;
    public String demonym;
    public Float area;
    public Float gini;
    public List<String> timezones = null;
    public List<String> borders = null;
    public String nativeName;
    public String numericCode;
    public List<Currency> currencies = null;
    public List<Language> languages = null;
    public Translations translations;
    public String flag;
    public List<RegionalBloc> regionalBlocs = null;
    public String cioc;

}
