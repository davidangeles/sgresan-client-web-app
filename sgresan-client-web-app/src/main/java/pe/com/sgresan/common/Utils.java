package pe.com.sgresan.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {

	/**
	 * Validate if a Object is null
	 * 
	 * @param objGeneric
	 *            <code>Object</code>
	 * @return Object <code>boolean</code>
	 */
	public static boolean isNull(Object objGeneric) {
		return (objGeneric == null);
	}
	
	/**
	 * Validate if a Object is null
	 * 
	 * @param objGeneric
	 *            <code>Object</code>
	 * @return Object <code>boolean</code>
	 */
	public static boolean isNotNull(Object objGeneric) {
		return !isNull(objGeneric);
	}

	/**
	 * Validate if a string is empty
	 * 
	 * @param strText
	 *            <code>String</code>
	 * @return Object <code>boolean</code>
	 */
	public static boolean isEmpty(String strText) {
		return (CommonConstants.STR_EMPTY.equals(strText));
	}

	/**
	 * Validate if an object is null or empty
	 * 
	 * @param p
	 *            <code>String</code>
	 * @return blResult <code>boolean</code>
	 */
	public static boolean isNullOrEmpty(Object objGeneric) {
		boolean blResult = isNull(objGeneric);
		if (!blResult) {
			blResult = isEmpty(objGeneric.toString());
		}
		return blResult;
	}

	/**
	 * Concat with delimiter
	 * 
	 * @param strDelimiter
	 *            <code>String</code>
	 * @param objects
	 *            <code>Object ...</code>
	 * @return Object <code>String</code>
	 */
	public static String concatWithDelimiter(String strDelimiter, Object... objects) {
		StringBuilder objValue = new StringBuilder();
		for (Object object : objects) {
			objValue.append(object);
			objValue.append(strDelimiter);
		}
		return objValue.toString().substring(CommonConstants.I_CERO, objValue.length() - strDelimiter.length());
	}

	/**
	 * Method Get String, return empty when strValue is Null
	 * 
	 * @author GMD
	 * @param objGeneric
	 *            <code>Object</code>
	 * @return strValue <code>String</code>
	 */
	public static String getString(Object objGeneric) {
		String strValue = CommonConstants.STR_EMPTY;
		try {
			if (!Utils.isNull(objGeneric)) {
				if (objGeneric instanceof BigDecimal || objGeneric instanceof BigInteger || objGeneric instanceof Integer) {
					strValue = objGeneric.toString();
				} else if (objGeneric instanceof BigInteger) {
					strValue = objGeneric.toString();
				} else if (objGeneric instanceof String) {
					strValue = objGeneric.toString();
				} else if (objGeneric instanceof Date) {
					strValue = convertDatetoString((Date)objGeneric, CommonConstants.STR_DATE_FORMAT);
				} else if (objGeneric instanceof Double) {
					Double dblData = (Double) objGeneric;
					DecimalFormat objPattern = new DecimalFormat(CommonConstants.STR_DECIMAL_FORMAT);
					NumberFormat objNumberFormat = NumberFormat.getNumberInstance();
					String strMob = objNumberFormat.format(dblData);
					Number objNum = objPattern.parse(strMob);
    	        	strValue = objNum.toString();
				}else {
					throw new IllegalArgumentException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strValue;
	}
	
	/**
	 * Gets string from date
	 * @param dtDate <code>Date</code>
	 * @param strFormat <code>String</code>
	 * @return strNewDate <code>String</code>
	 */
	public static String convertDatetoString(Date dtDate, String strFormat) {
		String strNewDate = CommonConstants.STR_EMPTY;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
			if (dtDate != null){
				strNewDate = sdf.format(dtDate);
			}	
		} catch (Exception e) {
			return null;
		}
		return strNewDate;
	}
	
	/**
	 * Takes the specific format and converts the date in that format
	 * @param strDate <code>Date</code>
	 * @param strFormat <code>String</code>
	 * @return dtDate <code>Date</code>
	 */
	public static Date convertStringtoDate(String strDate, String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		Date dtDate = null;
		try {
			if (strDate != null && strDate.trim().length() > CommonConstants.I_CERO)
				dtDate = sdf.parse(strDate);
		} catch (Exception e) {
			return  null;
		}

		return dtDate;
	}

	public static boolean validaCantidad(String p, int cant) {
		if (p == null || p.trim().length() != cant) {
			return true;
		}
		return false;
	}

	public static void mensajeError(String p) {
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

	public static boolean esSoloNumero(String texto) {texto = texto + "G";
		Pattern pattern = Pattern.compile(CommonConstants.PATTERN_NUMEROS);
		Matcher matcher = pattern.matcher(texto);
		return matcher.find() != true ? false : true;
	}

}
