
package com.meli.ipvalidator.dto.countryinfo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "RegionalBloc")
public class RegionalBloc {

    public String acronym;
    public String name;
    public List<Object> otherAcronyms = null;
    public List<Object> otherNames = null;

}
