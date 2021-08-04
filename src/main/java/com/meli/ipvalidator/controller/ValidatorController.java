package com.meli.ipvalidator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.meli.ipvalidator.dto.ResponseDto;
import com.meli.ipvalidator.exception.BadRequestException;
import com.meli.ipvalidator.service.Businnes;
import com.meli.ipvalidator.service.blacklistip.BlackListIP;
import com.meli.ipvalidator.util.IPValidationUtil;
import brave.Tracer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


/**
 * Controlador de microservicio ip Validator
 * Metodo GET para consultar la informacion del pais con respecto a la IP del usuario
 * Metodo POST el cual almacena una IP en la lista negra y no permite obtener la info del pais
 *
 * @author DYDIERARB
 * @versión 0.0.1-SNAPSHOT
 */
@RestController
@RequestMapping("/api/MeLi/v1")
@Slf4j
public class ValidatorController {
	
	@Autowired
    private Tracer tracer;
	
	@Autowired
	private Businnes businnes;
	
	@Autowired
	private BlackListIP businnesBL;

	/***
	 * Metodod expuesto que entrega informacion del pais con base en la ip sumnistrada
	 * @param ip
	 * @return Estructura de respuesta con informacion del pais
	 */
	@GetMapping("/ipValidator")
    public ResponseEntity<?> getIPValidator(@RequestParam("ip") String ip) {
		if(IPValidationUtil.isValidInet4Address(ip)) {
			var responseDto = new ResponseDto();
			var responseBusinnes = businnes.getInfo(ip);
			responseDto.setData(responseBusinnes);
			responseDto.setTransactionId(tracer.currentSpan().context().traceIdString());
			responseDto.setResponseCode(HttpStatus.OK);
			responseDto.setMessage("");
			return ResponseEntity.ok(responseDto);
		}else
			throw new BadRequestException("IP is not valid.");
    }
	
	/***
	 * Metodo el cual registra en la base de datos la ip suministrada, para que luego no se le permita el consumo de la info del pais
	 * @param ip
	 * @return Estructura con la ip registrada en la bd mongo
	 */
	@PostMapping("/blacklist")
	@ApiOperation(
			value = "Agregar IP a la lista negra",
			notes = "Retorna una estructura con un objeto que contiene "
					+ "la IP adicionada a la lista negra",
			response = ResponseDto.class)
	@ApiResponses(
			value = {@ApiResponse(code = 200, response = ResponseDto.class,
			message = "OK"), @ApiResponse(code = 404,
			response = ResponseDto.class, message = "Not found")})
	public ResponseEntity<?> addtoBlackList(
			@ApiParam(
					type = "Estructura de entrada que contiene el "
							+ "serviceId - vBajada - vSubida",
					required = true,
					value = "request" )@RequestParam String ip){
		if(!IPValidationUtil.isValidInet4Address(ip)) 
			throw new BadRequestException("IP is not valid.");
		
		var responseDto = new ResponseDto();
		var responseBusinnes = businnesBL.add(ip);
		responseDto.setData(responseBusinnes);
		responseDto.setTransactionId(tracer.currentSpan().context().traceIdString());
		responseDto.setResponseCode(HttpStatus.OK);
		responseDto.setMessage("");
		return ResponseEntity.ok(responseDto);
	}
}
