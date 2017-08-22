/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Joel
 */
public class NewMain {
public byte[] Stringcifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
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
    public static void main(String[] args) throws ClassNotFoundException, Exception {
    NewMain ibj = new NewMain();
  
        System.out.println(ibj.Stringcifra("joel"));
        System.out.println(ibj.descifra(ibj.Stringcifra("á")));
//     Conexion conn = new  Conexion();
//        try
//        {
//            conn.conectar();
//            String rutaInfrome="D:\\Archivos de Joel\\Documents\\NetBeansProjects\\trunk\\Web_SGRESAN\\src\\java\\vista\\reporte.jrxml";
//            Map parametros = new HashMap();
//            parametros.put("id","abc17");
//            JasperReport reporteJasper = JasperCompileManager.compileReport(rutaInfrome);
//            JasperPrint informe = JasperFillManager.fillReport(reporteJasper, parametros, conn.getConn());
//           
//            
//             JasperExportManager.exportReportToPdfFile(informe,
//          "D:\\InformePaisesMySQL.pdf");
//            JasperViewer.viewReport(informe,false);
////            JasperViewer ventanavisor = new JasperViewer(informe, false);
////            ventanavisor.setTitle("FACTURA");
////            ventanavisor.setVisible(true);
//         
//        
//        }   
//        catch(JRException e)
//        {
//            System.out.println("Error : "+e.getMessage());
//        } catch (SQLException ex) {
//            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
//  
//        }
    }
    
}
