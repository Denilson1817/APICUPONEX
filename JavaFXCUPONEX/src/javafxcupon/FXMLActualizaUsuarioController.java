/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
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
 * @author javier
 */
public class FXMLActualizaUsuarioController implements Initializable {
    private int idUsuario;
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
    ObservableList<Usuarios> listaUsuariosX;
    TableView<Usuarios> tbUsuariosX;    
        
    
    @FXML
    private Button btCancelar;
    @FXML
    private Button btActualizar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void inicializarInformacionVentana(int idUsuario, String nombre, String aPaterno, String aMaterno, String correo, String password){
        this.idUsuario = idUsuario;      
        tfCorreoElectronico.setDisable(true);
        tfNombre.setText(nombre);
        tfApellidoPaterno.setText(aPaterno);
        tfApellidoMaterno.setText(aMaterno);
        tfCorreoElectronico.setText(correo);
        tfContrasena.setText(password);
    }


    @FXML
    private void clicActualizarInformacion(ActionEvent event) {
        //String idUsuario = ac.tfBusquedaUsuario.getText();
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
            //recuperaDatosTabla();
            actualizaRegistro(nombre, apellidoPaterno,apellidoMaterno,correo, password);
        }else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar todos los campos", 
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        abandonarPantalla();
    }
    
    
    private void actualizaRegistro(String nombre,String apellidoPaterno,String apellidoMaterno,
        String correo, String password){
        try{
            String url = Constantes.URL_BASE + "usuarios/modificar";
            String parametros = "nombre="+nombre+"&apellidoPaterno="+apellidoPaterno+"&apellidoMaterno="+apellidoMaterno+"&correo="+correo+"&password="+password;
            
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
            respuesta.setNombre(nombre);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Registro modificado", "El usuario "+
                respuesta.getNombre()+" fue modficado con exito ", Alert.AlertType.INFORMATION);
                //irPantallaAdmin();
                Stage stage = (Stage) this.btActualizar.getScene().getWindow();
                stage.close();
                cargarInformacionUsuarios();
                
            }else{
                Utilidades.mostrarAlertaSimple("Error al modificar el registro",
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
    
    void guardaUsuarioSeleccionado(ObservableList<Usuarios> listUsuarios, TableView<Usuarios> tbUsuarios){
        listaUsuariosX = listUsuarios;
        tbUsuariosX = tbUsuarios;
    }    
    
    private void cargarInformacionUsuarios(){
        String url = Constantes.URL_BASE+"usuarios/all";
        try{
            //String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);

            String resultado = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type  listaUsuarios = new TypeToken<ArrayList <Usuarios> >() {}.getType();
            //RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);

            ArrayList administradorWS = gson.fromJson(resultado, listaUsuarios);
            guardaUsuarioSeleccionado(listaUsuariosX, tbUsuariosX);
            listaUsuariosX.clear();
            listaUsuariosX.addAll(administradorWS);
            tbUsuariosX.setItems(listaUsuariosX);
                    
        }catch(Exception e){
            e.printStackTrace();
            Utilidades.mostrarAlertaSimple("Error de conexión", "Por el momento no se puede obtener la información de los administradores"
                    , Alert.AlertType.ERROR);
        }
        
    }    
    
}
