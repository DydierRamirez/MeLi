package com.meli.ipvalidator.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Modelo de la coleccion a manejar par ala lista negra
 * @author DYDIERARB
 *
 */
@Document(collection="blacklist")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlackList {
	@Id
	private String id;
	
	private String ip;
}
