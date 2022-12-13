/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author denilson
 */
public class FXMLUsuariosController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfIdEstatus;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btGuardar(ActionEvent event) {
        
    }

    @FXML
    private void btCancelar(ActionEvent event) {
    }

    @FXML
    private void btSelecFoto(ActionEvent event) {
    }
    
}
