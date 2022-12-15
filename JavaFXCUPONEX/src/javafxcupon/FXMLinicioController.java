/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import com.google.gson.Gson;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConexionServiciosWeb;
import javafxcuponex.pojos.RespuestaLogin;
import javafxcuponex.pojos.Usuarios;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author denilson
 */
public class FXMLinicioController implements Initializable {
    
   
   
    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField pfPassword;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    @FXML
    private void clicIniciarSesion(ActionEvent event) throws Exception {          
        String nombre = tfNombre.getText();
        String password = pfPassword.getText();
        
        if (!nombre.isEmpty() && !password.isEmpty()){
            verificarInicioSesion(nombre, password);
        }else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar el nombre y/o contraseña", 
                    Alert.AlertType.WARNING);
        }
        
        }
    
    private void verificarInicioSesion(String nombre, String password){
        try{
            String url = Constantes.URL_BASE + "acceso/escritorio";
            String parametros = "nombre="+nombre+"&password="+password;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
            
            respuesta.setNombre(nombre);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Bienvenido "+
                        respuesta.getNombre()+" al sistema ", Alert.AlertType.INFORMATION);
                irPantallaPrincipal();
            }else{
                Utilidades.mostrarAlertaSimple("Usuario incorrecto",
                        respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            System.out.println(e);
           mostrarAlerta("Error","Error en la peticion: "+e.getMessage());
                    
            //Utilidades.mostrarAlertaSimple("Error de conexion",
                //e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void irPantallaPrincipal(){
       try{
           Parent vistaPrincipal = FXMLLoader.load(getClass().getResource("FXMLDashboard.fxml"));
           Scene escenaPrincipal = new Scene(vistaPrincipal);
           Stage escenarioBase = (Stage) tfNombre.getScene().getWindow();
           escenarioBase.setScene(escenaPrincipal);
           escenarioBase.show();
       }catch(IOException e){
           System.out.println(e);
                 Utilidades.mostrarAlertaSimple("Error de conexion",
                e.getMessage(), Alert.AlertType.ERROR);
    }  
    }
        private void mostrarAlerta(String titulo, String mensaje){
        Alert dialogoAlerta = new Alert(Alert.AlertType.INFORMATION);
        dialogoAlerta.setTitle(titulo);
        dialogoAlerta.setContentText(mensaje);
        dialogoAlerta.setHeaderText(null);
        dialogoAlerta.showAndWait();
    }   
}