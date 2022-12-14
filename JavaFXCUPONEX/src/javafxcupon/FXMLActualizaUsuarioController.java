/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConexionServiciosWeb;
import javafxcuponex.pojos.RespuestaLogin;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author javier
 */
public class FXMLActualizaUsuarioController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfContrasena;
    @FXML
    private TextField tfCorreoElectronico;

    FXMLAdminUsuariosController ac;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicActualizarInformacion(ActionEvent event) {
        String idUsuario = ac.tfBusquedaUsuario.getText();
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correo = tfCorreoElectronico.getText();
        String password= tfContrasena.getText();
        //String idEstatus = tfIdEstatus.getText();
        

        if (!nombre.isEmpty() && !apellidoPaterno.isEmpty() && !apellidoMaterno.isEmpty()
                &&!correo.isEmpty()  && !password.isEmpty()){
            //verificarInicioSesion(noPersonal, password);
            //todo
            recuperaDatosTabla();
            actualizaRegistro(idUsuario,nombre, apellidoPaterno,apellidoMaterno,correo, password);
        }else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar todos los campos", 
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        irPantallaAdmin();
    }
    
    
    private void actualizaRegistro(String idUsuario,String nombre,String apellidoPaterno,String apellidoMaterno,
        String correo, String password){
        try{
            String url = Constantes.URL_BASE + "usuarios/modificar";
            String parametros = "IdUsuario="+idUsuario+"&nombre="+nombre+"&apellidoPaterno="+apellidoPaterno+"&apellidoMaterno="+apellidoMaterno+"&correo="+correo+"&password="+password;
            
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
            respuesta.setNombre(nombre);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Registro modificado", "El usuario "+
                respuesta.getNombre()+" fue modficado con exito ", Alert.AlertType.INFORMATION);
                irPantallaAdmin();
                
            }else{
                Utilidades.mostrarAlertaSimple("Error al modificar el registro",
                        respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",
                e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    
    
    
    private void irPantallaAdmin(){
        try{
            Parent vistaPrincipal = FXMLLoader.load(getClass().getResource("FXMLAdminUsuarios.fxml"));
            Scene escenaPrincipal = new Scene(vistaPrincipal);
            Stage escenarioBase = (Stage) tfNombre.getScene().getWindow();
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.show();
        }catch(Exception e){
         Utilidades.mostrarAlertaSimple("Error", "Error al cargar la pantalla principal", Alert.AlertType.ERROR);  
         }  
    }
    
    private void recuperaDatosTabla(){
        
    }
    
}
