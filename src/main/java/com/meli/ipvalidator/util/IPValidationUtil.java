package com.meli.ipvalidator.util;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/***
 * Clase utilitaria que valida si la direccion IP es correcta en cuanto a su formato IPV4
 * @author DYDIERARB
 *
 */
public final class IPValidationUtil {
	
	/***
	 * Constructor
	 */
	private IPValidationUtil() {
	    throw new IllegalStateException("Utility class");
	  }
	
	/***
	 * Metodo que valida el formato de la IP 
	 * @param ip
	 * @return true o false
	 */
	public static boolean isValidInet4Address(String ip)
    {
        try {
            return Inet4Address.getByName(ip)
                        .getHostAddress().equals(ip);
        }
        catch (UnknownHostException ex) {
            return false;
        }
    }
}
