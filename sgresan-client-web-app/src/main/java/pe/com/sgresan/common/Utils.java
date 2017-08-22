package pe.com.sgresan.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {
    
    /**
	* Validate if a Object is null
	* @param objGeneric <code>Object</code>
	* @return Object <code>boolean</code>
	*/
	public static boolean isNull(Object objGeneric) {
        return (objGeneric == null);
    }
	
	/**
	* Validate if a string is empty
	* @param strText <code>String</code>
	* @return Object <code>boolean</code>
	*/
	public static boolean isEmpty(String strText) {
        return (CommonConstants.STR_EMPTY.equals(strText));
    }
    
	/**
	* Validate if an object is null or empty
	* @param p <code>String</code>
	* @return blResult <code>boolean</code>
	*/
    public static boolean isNullOrEmpty(Object objGeneric){
    	boolean blResult = isNull(objGeneric);
        if(!blResult){
        	blResult = isEmpty(objGeneric.toString());
        }
        return blResult;
    }
    
    public static boolean validaCantidad(String p, int cant){
        if(p == null || p.trim().length() != cant){
            return true;
        }
        return false;
    }
    
    public static void mensajeError(String p){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, p, p));
    }
    
    public static boolean esSoloLetras(String texto) {
        Pattern pattern = Pattern.compile(CommonConstants.PATTERN_LETRAS);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() != true ? false : true;
    }

    public static boolean esAlfaNumerico(String texto) {
        Pattern pattern = Pattern.compile(CommonConstants.PATTERN_ALFA_NUMERICO);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() != true;
    }

    public static boolean esCorreoValido(String texto) {
        Pattern pattern = Pattern.compile(CommonConstants.PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find();
    }

    public static boolean esSoloNumero(String texto) {
        Pattern pattern = Pattern.compile(CommonConstants.PATTERN_NUMEROS);
        Matcher matcher = pattern.matcher(texto);
        return matcher.find() != true ? false : true;
    }

}
