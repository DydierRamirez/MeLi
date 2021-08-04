package com.meli.ipvalidator.service.blacklistip;

import com.meli.ipvalidator.model.BlackList;

/***
 * Interface la cual determina las operaciones de busqueda por IP y adicionar una ip a la lista negra
 * @author DYDIERARB
 *
 */
public interface IBlackListIP {
	/**
	 * Metodo el cual entrega la ip a buscar
	 * @param ip
	 * @return Estructura con la IP y id del registro en la bd mongo
	 */
	public BlackList getByIP(String ip);
	/**
	 * Metodo el cual almaneca la ip a ingresar en la black list mongo
	 * @param ip
	 * @return Estructura con la IP y id del registro en la bd mongo
	 */
	public BlackList add(String ip);
}
