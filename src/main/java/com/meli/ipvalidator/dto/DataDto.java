package com.meli.ipvalidator.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DataDto")
public class DataDto {
	
	private String name;	
	private String iso;
	private String coinName;
	private String coinSymbol;
	private String base;
	private float rates;
}
