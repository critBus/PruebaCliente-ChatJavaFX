/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacliente;

import pruebaObjetoRemoto.ObjetoRemoto;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.remote.rmi.RMIConnectionImpl;

/**
 *
 * @author Rene
 */
public class ObjetoCliente {

    public ObjetoCliente() {
    }
    
    public void run(){
        try {
            System.out.println("comienza cliente");
            Object o=Naming.lookup("rmi://localhost:1099/ElObjetoRemoto");
            System.out.println("paso");
            ObjetoRemoto calculador=(ObjetoRemoto)o;
            System.out.println(calculador.getMensaje(" uno"));
        } catch (NotBoundException ex) {
            Logger.getLogger(ObjetoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ObjetoCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(ObjetoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
}
