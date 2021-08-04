
package com.meli.ipvalidator.dto.countryinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "Currency")
public class Currency {

    public String code;
    public String name;
    public String symbol;

}
