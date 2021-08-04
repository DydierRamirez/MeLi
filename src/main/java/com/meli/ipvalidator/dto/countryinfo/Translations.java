
package com.meli.ipvalidator.dto.countryinfo;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "Translations")
public class Translations {

    public String de;
    public String es;
    public String fr;
    public String ja;
    public String it;
    public String br;
    public String pt;
    public String nl;
    public String hr;
    public String fa;

}
