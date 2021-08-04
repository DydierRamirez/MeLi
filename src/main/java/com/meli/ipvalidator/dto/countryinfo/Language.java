
package com.meli.ipvalidator.dto.countryinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "Language")
public class Language {

	@JsonProperty("iso639_1")
    public String iso639_1;
	@JsonProperty("iso639_2")
    public String iso639_2;
	@JsonProperty("name")
    public String name;
	@JsonProperty("nativeName")
    public String nativeName;

}
