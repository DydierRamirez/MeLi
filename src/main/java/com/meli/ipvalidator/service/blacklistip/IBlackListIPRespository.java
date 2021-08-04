package com.meli.ipvalidator.service.blacklistip;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.meli.ipvalidator.model.BlackList;

/***
 * Interface la cual define el repository para acceder y manipular la bd mongo
 * @author DYDIERARB
 *
 */
public interface IBlackListIPRespository extends MongoRepository<BlackList,String>{
	/**
	 * Query que realiza busqueda por IP
	 * @param ip
	 * @return Estructura con la IP y id del registro en la bd mongo
	 */
	public BlackList findByIp(String ip);
	
}
