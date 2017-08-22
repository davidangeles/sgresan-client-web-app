/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Joel
 */
public class Encriptacion {
    public static String Encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
}
    
    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
}

    
    
    public String descifra(byte[] cifrado) throws Exception {
	final Cipher aes = obtieneCipher(false);
	final byte[] bytes = aes.doFinal(cifrado);
	final String sinCifrar = new String(bytes, "UTF-8");
	return sinCifrar;
}
private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
	final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
	final MessageDigest digest = MessageDigest.getInstance("SHA");
	digest.update(frase.getBytes("UTF-8"));
	final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");
 
	final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
	if (paraCifrar) {
		aes.init(Cipher.ENCRYPT_MODE, key);
	} else {
		aes.init(Cipher.DECRYPT_MODE, key);
	}
 
	return aes;
}
    /**
     * @param args the command line arguments
     */
   private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
    public static String encriptaEnMD5(String stringAEncriptar)
    {
        try
        {
           MessageDigest msgd = MessageDigest.getInstance("MD5");
           byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
           StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
           for (int i = 0; i < bytes.length; i++)
           { 
               int bajo = (int)(bytes[i] & 0x0f);
               int alto = (int)((bytes[i] & 0xf0) >> 4);
               strbCadenaMD5.append(CONSTS_HEX[alto]);
               strbCadenaMD5.append(CONSTS_HEX[bajo]);
           }
           return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }
    public static void main(String[] args) throws DecoderException, Exception {
     Encriptacion obj = new Encriptacion();
        String encriptado = obj.Encriptar("1234");
        System.out.println(encriptado);
        String desencriptado = obj.Desencriptar(encriptado);
        System.out.println(desencriptado);
        
// 
//        System.out.println("\n\nEncriptacion en MD5 de 123: '"+encriptaEnMD5("123")+"'");
//        System.out.println("Encriptacion en MD5 de hola: '"+encriptaEnMD5("hola")+"'");
    
//       MessageDigest md = null;
//	String password = "password D:";
//        try {
//            
//            //SHA-512
//            md= MessageDigest.getInstance("SHA-512");
//            md.update(password.getBytes());
//            byte[] mb = md.digest();
//            System.out.println(Hex.encodeHex(mb));
//            
//            //SHA-1
//            md= MessageDigest.getInstance("SHA-1");
//            md.update(password.getBytes());
//            mb = md.digest();
//            System.out.println(Hex.encodeHex(mb));
//            
//            //MD5
//            md= MessageDigest.getInstance("MD5");
//            md.update(password.getBytes());
//            mb = md.digest();
//            System.out.println(Hex.encodeHex(mb));  
//            System.out.println(Hex.decodeHex(Hex.encodeHex(mb)));
//            System.out.println(obj.descifra(Hex.decodeHex(Hex.encodeHex(mb))));
//        } catch (NoSuchAlgorithmException e) {
//            //Error
//        }
      
    }
    
}
