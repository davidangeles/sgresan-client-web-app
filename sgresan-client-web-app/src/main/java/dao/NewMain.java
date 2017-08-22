/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author Joel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//       Date start;  
//       Date end; 
//       Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));  
//        Date now = new Date();  
//  
//        cal.setTimeInMillis(now.getTime() - 4 * 60 * 60 * 1000);  
//        start = cal.getTime();  
//  
//        cal.setTimeInMillis(now.getTime() + 8 * 60 * 60 * 40000);  
//        end = cal.getTime(); 
//        System.out.println(start+"__"+end);
//        
//        CajaDao d = new CajaDao();
//        System.out.println(d.Saldo());
//        Date da = new Date();
//        System.out.println(da.getHours()+":"+da.getMinutes()+":"+da.getSeconds());
//        System.out.println(d.PK("Ap", "00:00:00"));
//        
//        System.out.println(d.fech());
        
        Calendar cal = new GregorianCalendar();
 
 
 
        cal.add(Calendar.DATE,1);
        System.out.println(cal.getTime());
                 
        
    }
    
}
