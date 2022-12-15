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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConexionServiciosWeb;
import javafxcuponex.pojos.RespuestaLogin;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;
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
    private TextField tfPassword;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private Button btCancelar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void btGuardar(ActionEvent event)throws Exception {
        //System.out.println("clic sobre el boton");
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correo = tfCorreo.getText();
        String password= tfPassword.getText();
        //String idEstatus = tfIdEstatus.getText();
        

        if (!nombre.isEmpty() && !apellidoPaterno.isEmpty() && !apellidoMaterno.isEmpty()
                &&!correo.isEmpty()  && !password.isEmpty()){
            //verificarInicioSesion(noPersonal, password);
            guardarRegistro(nombre, apellidoPaterno,apellidoMaterno,correo, password);
        }else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar todos los campos", 
                    Alert.AlertType.WARNING);
        }
        
    }
    
    

    @FXML
    private void btCancelar(ActionEvent event) {
        //irPantallaAdmin();
        //System.out.println("clic sobre el boton");
        abandonarPantalla();

        
    }

    
    
    private void guardarRegistro(String nombre,String apellidoPaterno,String apellidoMaterno,
        String correo, String password){
        try{
            String url = Constantes.URL_BASE + "usuarios/registrar";
            String parametros = "nombre="+nombre+"&apellidoPaterno="+apellidoPaterno+"&apellidoMaterno="+apellidoMaterno+"&correo="+correo+"&password="+password;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
            respuesta.setNombre(nombre);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Registro guardado", "El usuario "+
                respuesta.getNombre()+" fue registrado con exito ", Alert.AlertType.INFORMATION);
                //irPantallaAdmin();
                
            }else{
                Utilidades.mostrarAlertaSimple("Error al guardar el registro",
                        respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",
                e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    
    
    private void abandonarPantalla() {
        Stage stage = (Stage) btCancelar.getScene().getWindow();        
        stage.close();        
    }

    
}
