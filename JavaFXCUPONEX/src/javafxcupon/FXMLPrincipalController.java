/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author denilson
 */
public class FXMLPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void clicIrAdminMedicos(ActionEvent event) {
        try{
            Parent vistaAdminUsuarios = FXMLLoader.load(getClass().getResource("FXMLAdminUsuarios.fxml"));
            Scene scenaUsuario = new Scene(vistaAdminUsuarios);
            Stage scenarioUsuario = new Stage();
            scenarioUsuario.setScene(scenaUsuario);
            scenarioUsuario.initModality(Modality.APPLICATION_MODAL);
            scenarioUsuario.show();
            
        }catch(IOException e){
             System.out.print(e);
                 Utilidades.mostrarAlertaSimple("Error de conexion",
                e.getMessage(), Alert.AlertType.ERROR);
     
        }
        
        
        // TODO
    }

    @FXML
    public void clicIrAdminPacientes (ActionEvent event){
        
    }
    
    @FXML
    public void clicIrAdminAlimentos(ActionEvent event){
        
    }
    
    @FXML
    public void clicCerrarSesion(ActionEvent event){
        
    }
    
    
    
}
