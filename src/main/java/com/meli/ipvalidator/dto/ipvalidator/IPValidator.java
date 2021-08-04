package com.meli.ipvalidator.dto.ipvalidator;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/***
 * Clase modelo para la respuesta del legado de IP
 * @author DYDIERARB
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "IPValidator")
public class IPValidator {

private String countryCode;
private String countryCode3;
private String countryName;
private String countryEmoji;

}
