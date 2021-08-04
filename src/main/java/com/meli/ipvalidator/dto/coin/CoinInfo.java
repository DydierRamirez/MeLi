package com.meli.ipvalidator.dto.coin;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.meli.ipvalidator.dto.countryinfo.Currency;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/***
 * Clase modelo de la respuesta del legado que entrega informacion de la moneda y tasa de cambio
 * @author DYDIERARB
 *
 */
@Data
@AllArgsConstructor
@JsonIgnoreType
@ApiModel(value = "CoinInfo")
public class CoinInfo {
	
	@JsonProperty("success")
	public Boolean success;
	@JsonProperty("timestamp")
	public Integer timestamp;
	@JsonProperty("base")
	public String base;
	@JsonProperty("date")
	public String date;
	@JsonProperty("rates")
	public Map<String, String> rates;

}
