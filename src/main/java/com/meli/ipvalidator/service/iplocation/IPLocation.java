package com.meli.ipvalidator.service.iplocation;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.meli.ipvalidator.dto.ipvalidator.IPValidator;
import com.meli.ipvalidator.exception.CallLegacyException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;

import lombok.extern.slf4j.Slf4j;

/***
 * Clase la cual realiza el consumo del legado para obtener la información del pais con base en la IP
 * @author DYDIERARB
 *
 */
@Service
@Slf4j
public class IPLocation {
	
	@Value("${rs.iplocation.url}")
    private String url;
	
	/***
	 * Metodo que realiza consumo del legado a partir de la ip recibida
	 * @param ip
	 * @return Estructura con la infromacion del país
	 */
	@HystrixCommand(fallbackMethod = "defaultGetLocation", commandKey = "getLocation", groupKey = "getLocation")
	public Object getLocation(String ip) {
		try {
			var restTemplate = new RestTemplate();
			var gson = new Gson();
            var uuid = UUID.randomUUID();
            var headers = new HttpHeaders();
            ResponseEntity<String> responseIP = null;
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> request = new HttpEntity<>(null, headers);

            log.info("BEGIN CONSUME SERVICE - {} - REQUEST:{}", uuid, url);
            responseIP = restTemplate.exchange(url.replace("{ip}", ip), HttpMethod.GET, request, String.class);
            IPValidator response = gson.fromJson(responseIP.getBody(), IPValidator.class);
            log.info("END CONSUME SERVICE - {} - RESPONSE:{}", uuid, response);
            return response;
        } catch (Exception ex) {
        	log.error("ERROR Exception", ex);
            throw new CallLegacyException("ERROR to connect with endpoint IP: ");
        }
	}
	
	/***
	 * Metodo default que se ejecuta cuando se supera el tiempo limite para esperar respuesta de la peticion
	 * @param ip
	 * @param ex
	 * @return null cuando es por limite de tiempo
	 */
	public Object defaultGetLocation(String ip, Throwable ex) {
		log.info(ip);
		if (ex instanceof HystrixTimeoutException) {
			log.info("EJECUTANDO FALLBACKMETHOD defaultGetLocation");
			return null;
		}
		throw new CallLegacyException("ERROR consuming legacy getLocation");
	}

}
