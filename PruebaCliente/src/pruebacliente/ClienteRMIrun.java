/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacliente;

import chat.Gui;
import chat.IServidor;
import chat.Mensaje;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public  class ClienteRMIrun implements Runnable{
    private static int miSesion=0;
    private static IServidor servidor;

    public ClienteRMIrun() {
    }
    
    @Override
    public  void run(){
        try {
            Registry registry=LocateRegistry.getRegistry("localhost", 8888);
            servidor= (IServidor)registry.lookup("ServidorRMI");
            gui();
            
        } catch (Exception ex) {
          ex.printStackTrace();
          System.exit(0);
        }
    }
    public static void gui()throws RemoteException{
        int out=0;
        do{
        out= Gui.menu("Menu Principal", new String[]{"Autenticarse","agregar contacto","enviar mensaje","recibir mensaje","Salir"});
    switch(out){
        case 0:auntenticarse();break;
        case 1:agregarContacto();break;
        case 2:enviarMensajes();break;
        case 3:resibirMensajes();break;
       
    }
        }while(out!=4);
        
     
    }
    public static void auntenticarse()throws RemoteException{
    String nombre= Gui.input("Autenticarse", "Ingrese su nombre");
        System.out.println("nombre="+nombre);
        
    if(nombre!=null&&!nombre.isEmpty()){
        miSesion=servidor.autenticar(nombre);
        System.out.println("entro au");
    }
        System.out.println("termino");
    }
    public static void agregarContacto()throws RemoteException{
      String contacto= Gui.input("Agregar Contacto", "Ingrese el contacto");
        System.out.println("contacto="+contacto);
        
    if(contacto!=null&&!contacto.isEmpty()){
        int suSesion=servidor.agregar(contacto,miSesion);
        System.out.println("la sesion del "+contacto+" es "+suSesion);
        System.out.println("2 entro au");
    }
        System.out.println("2 termino");
    }
    public static void resibirMensajes()throws RemoteException{
        System.out.println("=== Mensajes recibidos ===");
    }
    public static void enviarMensajes()throws RemoteException{
    String opts[]=Gui.input("Enviar mensaje", new String[]{"Ingrese la sesion del contacto","ingrese el mensaje"});
    int suSesion=Integer.parseInt(opts[0]);
    String mensaje=opts[1];
    servidor.enviar(mensaje, miSesion, suSesion);
    List<Mensaje> mensajes=servidor.recibir(miSesion);
        for (Mensaje m : mensajes) {
            System.out.println("De "+m.getRemitente());
            System.out.println("\t"+m.getCuerpo()+"\n");
        }
        System.out.println("");
        servidor.limpiarBuffer(miSesion);
    }
    
}
