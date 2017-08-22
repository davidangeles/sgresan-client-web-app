/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.ArchivoDao;
import model.TCliente;
import model.TReserva;

/**
 *
 * @author Joel
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      ArchivoDao ar = new ArchivoDao();
      TReserva reserva = new TReserva();
        reserva.setTCliente(new TCliente());
        reserva = ar.BuscaporId("abc13");
        System.out.println(reserva.getIdReserva()+"\n"+reserva.getTCliente().getIdCliente());
    }
    
}
