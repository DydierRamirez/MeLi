package com.meli.ipvalidator.service.coin;

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
import com.meli.ipvalidator.dto.coin.CoinInfo;
import com.meli.ipvalidator.exception.CallLegacyException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;

/***
 * Clase la cual realiza el consumo del legado para obtener la información de la moneda y tasa de cambios en base EUR con base en el codigo de la moneda
 * @author DYDIERARB
 *
 */
@Service
@Slf4j
public class CountryCoinInfo {
	
	@Value("${rs.countrycoin.url}")
    private String url;
	
	/***
	 * Metodo que realiza consumo del legado a partir del codigo de la moneda
	 * @param ip
	 * @return Estructura con la informacion de la moneda y tasa de cambio
	 */
	@HystrixCommand(fallbackMethod = "defaultGetCoinInfo", commandKey = "getCoinInfo", groupKey = "getCoinInfo")
	public Object getCoinInfo(String code) {
		try {
			var restTemplate = new RestTemplate();
			var gson = new Gson();
            var uuid = UUID.randomUUID();
            var headers = new HttpHeaders();
            ResponseEntity<String> responseCountry = null;
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Object> request = new HttpEntity<>(null, headers);

            log.info("BEGIN CONSUME SERVICE - {} - REQUEST:{}", uuid, url);
            responseCountry = restTemplate.exchange(url.replace("{code}", code), HttpMethod.GET, request, String.class);
            CoinInfo response = gson.fromJson(responseCountry.getBody(), CoinInfo.class);
            log.info("END CONSUME SERVICE - {} - RESPONSE:{}", uuid, response);
            return response;
        } catch (Exception ex) {
        	log.error("ERROR Exception", ex);
            throw new CallLegacyException("ERROR to connect with endpoint Coin: " + ex.getMessage());
        }
	}
	
	/***
	 * Metodo default que se ejecuta cuando se supera el tiempo limite para esperar respuesta de la peticion
	 * @param ip
	 * @param ex
	 * @return null cuando es por limite de tiempo
	 */
	public Object defaultGetCoinInfo(String code, Throwable ex) {
		log.info(code);
		if (ex instanceof HystrixTimeoutException) {
			log.info("EJECUTANDO FALLBACKMETHOD defaultGetCoinInfo");
			return null;
		}
		throw new CallLegacyException("ERROR consuming legacy getCoinInfo");
	}

}
