package pe.com.sgresan.common;

public class CommonConstants {
	
	public static final String STR_EMPTY = "";	
	
	// Patterns
	public static final String PATTERN_LETRAS = "[^A-Za-zÃ±Ã‘Ã¡Ã©Ã­Ã³ÃºÃ¼Ã�Ã‰Ã�Ã“Ãš \\- ]";
    public static final String PATTERN_ALFA_NUMERICO = "[^A-Za-z0-9]";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_NUMEROS = ".*[^0-9].*";
    
    // KEY MAPS
    public static final String STR_KEY_MAP_ID = "P_ID";

}
