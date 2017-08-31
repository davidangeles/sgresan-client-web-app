package pe.com.sgresan.common;

public class CommonConstants {
	
	public static final String STR_EMPTY = "";
	public static final String STR_SPACE = " ";
	public static final String STR_SEPARATOR = ",";
	
	public static final String STR_DATE_FORMAT = "dd/MM/yyyy";
	public static final String STR_DECIMAL_FORMAT = "#,#,#,#,#,#,#,#,#,#";
	public static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
	
	// Patterns
	public static final String PATTERN_LETRAS = "[^A-Za-zÃ±Ã‘Ã¡Ã©Ã­Ã³ÃºÃ¼Ã�Ã‰Ã�Ã“Ãš \\- ]";
    public static final String PATTERN_ALFA_NUMERICO = "[^A-Za-z0-9]";
    public static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATTERN_NUMEROS = ".*[^0-9].*";
    
    // KEY MAPS
    public static final String STR_KEY_MAP_ID = "P_ID";
    public static final String STR_KEY_MAP_FECHAINICIO = "FECHA_INICIO";
    public static final String STR_KEY_MAP_FECHAFIN = "FECHA_FIN";
    public static final String STR_KEY_MAP_EMAIL = "P_EMAIL";
    public static final String STR_KEY_MAP_CLIENTE = "P_CLIENTE";
    public static final String STR_KEY_MAP_PERSONA = "P_PERSONA";
    public static final String STR_KEY_MAP_USUARIO = "P_USUARIO";
    public static final String STR_KEY_MAP_HABITACION = "P_HABITACION";
    public static final String STR_KEY_MAP_CANTIDAD = "P_CANTIDAD";
    public static final String STR_KEY_MAP_PRECIO = "P_PRECIO";
    public static final String STR_KEY_MAP_ESTADO = "P_ESTADO";
    public static final String STR_KEY_MAP_ACCION = "P_ACCION";
    
    //
    public static final Integer INT_CORREO_PRERESERVA = 1;
    
    
	// Numeros
	public static final int I_NEG_UNO = -1;
	public static final int I_CERO = 0;
	public static final int I_UNO = 1;
	public static final int I_DOS = 2;

}
