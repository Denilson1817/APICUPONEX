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
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author javier
 */
public class FXMLDashboardController implements Initializable {

    @FXML
    private Button clicIrAdministrador;
    @FXML
    private Button clicIrEmpresa;
    @FXML
    private Button clicIrSucursales;
    @FXML
    private Button clicirPromociones;
    @FXML
    private Button clicIrCerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicIrAdministrador(ActionEvent event) {
        irPantallaAdministrador();
    }

    @FXML
    private void clicIrEmpresa(ActionEvent event) {
        irPantallaEmpresa();
    }

    @FXML
    private void clicIrSucursales(ActionEvent event) {
    }

    @FXML
    private void clicirPromociones(ActionEvent event) {
    }

    @FXML
    private void clicIrCerrarSesion(ActionEvent event) {
    }
    
    private void irPantallaAdministrador(){
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLAdminUsuarios.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
        }

       
    }
    
    
    private void irPantallaEmpresa(){
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLAdminEmpresa.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
        }

       
    }
    
}
