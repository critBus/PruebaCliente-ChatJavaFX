
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacliente;

import Utiles.ClasesUtiles.Interfases.Funcionales.Realizar;
import Utiles.FX.Ventanas.Controladores.ControladorResizable;
import Utiles.FX.VisualFX;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.omg.CORBA.Environment;

public class Ventan_PrincipalController extends ControladorResizable {
      @FXML
    private JFXButton BRMI;
 @FXML
    private AnchorPane PAnchorInferior;

    @FXML
    private AnchorPane PAnchorSuperior;

    @FXML
    private JFXButton BClearTodo;

    @FXML
    private JFXButton BActualizarTodo;

    @FXML
    private TextArea TA;

    @FXML
    private JFXTextField TEnviar;

    @FXML
    private JFXButton BEnviar;
    
    
private Socket conexion;
private ObjectOutputStream salida;
  @FXML
    void apretoBRMI(ActionEvent event) {
        BRMI.setDisable(true);
        Executors.newCachedThreadPool().execute(new ClienteRMIrun());
      //ClienteRMIrun.run();
      
    }

    @FXML
    void apretoActualizarTodo(MouseEvent event) {
intentarConetar();
    }

    @FXML
    void apretoBEnviar(ActionEvent event) {
 try {
           // Socket su=new Socket(InetAddress.getLocalHost(), 3000);
          
            salida.writeObject(TEnviar.getText());
           // O.close();
          //  su.close();
        } catch (Exception ex) {
            VisualFX.responerException(ex);
        }
    }

    @FXML
    void apretoClearTodo(MouseEvent event) {

    }

   

    @Override
    public void iniStage(Parent p) {
        super.iniStage(p); //To change body of generated methods, choose Tools | Templates.
         intentarConetar();
        addOnClosed(()->{
            try {
                System.out.println(" cirra cliente");
                conexion.close();
                System.exit(0);
            } catch (Exception ex) {
                responerException(ex);
            }
        });
        new ObjetoCliente().run();
    }
    
      private void intentarConetar()  {
          
     try {   
         conexion=new Socket(InetAddress.getLocalHost(), 3000);
           salida=new ObjectOutputStream(conexion.getOutputStream());
            salida.flush();
     } catch (Exception ex) {
         responerException(ex);
     }
      }

    
    
   @Override
    public Parent[] darMovimiento() {
        return new Parent[]{PAnchorInferior,PAnchorSuperior}; //To change body of generated methods, choose Tools | Templates.
    }

    

   

}
