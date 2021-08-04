package com.meli.ipvalidator.service;

import java.util.HashMap;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meli.ipvalidator.dto.DataDto;
import com.meli.ipvalidator.dto.coin.CoinInfo;
import com.meli.ipvalidator.dto.countryinfo.CountryCoin;
import com.meli.ipvalidator.dto.ipvalidator.IPValidator;
import com.meli.ipvalidator.exception.BadRequestException;
import com.meli.ipvalidator.exception.CallLegacyException;
import com.meli.ipvalidator.service.blacklistip.BlackListIP;
import com.meli.ipvalidator.service.coin.CountryCoinInfo;
import com.meli.ipvalidator.service.countryinfo.CountryInfo;
import com.meli.ipvalidator.service.iplocation.IPLocation;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de negocio la cual realiza los consumos pertinenes y elabora la respuesta de la validacion de IP
 * @author DYDIERARB
 *
 */
@Service
@Slf4j
public class Businnes{
	
	@Autowired
	private IPLocation rsIPLocation;
	
	@Autowired
	private CountryInfo rsCountryInfo;
	
	@Autowired
	private CountryCoinInfo rsCoinInfo;
	
	@Autowired
	private BlackListIP srBlackLIst;
	
	/**
	 * Metodo el cual realiza la logica de la operacion para obtener la informacion del pais y tasa de cambio
	 * @param ip
	 * @return Estructura con informacion de pais y tasa de cambio
	 */
	public Object getInfo(String ip){
		var response = new DataDto();
		try {
			if(!findBlackList(ip)) {
				var responseIP = rsIPLocation.getLocation(ip);
				if(Objects.nonNull(responseIP) && Objects.nonNull(((IPValidator)responseIP).getCountryCode3()) && Objects.nonNull(((IPValidator)responseIP).getCountryName())) {
					response.setName(((IPValidator)responseIP).getCountryName());
					var responseCountInfo = rsCountryInfo.getCountryInfo(((IPValidator)responseIP).getCountryCode3());
					if(Objects.nonNull(responseCountInfo) && !((CountryCoin)responseCountInfo).getCurrencies().isEmpty() && !((CountryCoin)responseCountInfo).getLanguages().isEmpty()) {
						response.setIso(((CountryCoin)responseCountInfo).getLanguages().get(0).getIso639_1());
						response.setCoinName(((CountryCoin)responseCountInfo).getCurrencies().get(0).getName());
						response.setCoinSymbol(((CountryCoin)responseCountInfo).getCurrencies().get(0).getSymbol());
						
						var responseCoinInfo = rsCoinInfo.getCoinInfo(((CountryCoin)responseCountInfo).getCurrencies().get(0).getCode());
						if(Objects.nonNull(responseCoinInfo) && !((CoinInfo)responseCoinInfo).getRates().isEmpty()) {
							HashMap<String, String> map = new HashMap<>(((CoinInfo)responseCoinInfo).getRates());
							response.setRates(Float.parseFloat(map.get(((CountryCoin)responseCountInfo).getCurrencies().get(0).getCode())));
							response.setBase(((CoinInfo)responseCoinInfo).getBase());
						}
					}
				}
			}else 
				throw new BadRequestException("IP was found on blacklist");
			
		}catch(BadRequestException ex) {
			throw new BadRequestException(ex.getMessage());
		}
		catch(Exception ex) {
			throw new CallLegacyException("ERROR when validating the data");
		}
		return response;
	}
	
	/**
	 * Metodo que valida si la IP existe en la lista negra
	 * @param ip
	 * @return true, si existe en la lista negra o false si no existe
	 */
	private boolean findBlackList(String ip) {
		var isBList = false;
		var res = srBlackLIst.getByIP(ip);
		if(Objects.nonNull(res) && !res.getIp().isEmpty())
			isBList = true;
		return isBList;
	}
}