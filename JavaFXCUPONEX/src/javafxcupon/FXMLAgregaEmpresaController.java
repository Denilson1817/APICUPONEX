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
import javafx.fxml.Initializable;
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
 * @author javier
 */
public class FXMLAgregaEmpresaController implements Initializable {

    @FXML
    private TextField tfIdEmpresa;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfNombreRepresentante;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfIdEstatus;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clicGuardar(ActionEvent event) {
        //System.out.println("clic sobre el boton");
        String nombre = tfNombre.getText();
        String nombreComercial = tfNombreComercial.getText();
        String representante = tfNombreRepresentante.getText();
        String correo = tfCorreo.getText();
        String direccion = tfDireccion.getText();
        Integer codigoPostal = Integer.parseInt(tfCP.getText());
        String ciudad = tfCiudad.getText();
        Integer telefono = Integer.parseInt(tfTelefono.getText());
        String pagina = tfPaginaWeb.getText();
        String rfc = tfRFC.getText();
        Integer estatus = Integer.parseInt(tfIdEstatus.getText());
        //String idEstatus = tfIdEstatus.getText();
        

       
            guardarRegistro(nombre, nombreComercial,representante,correo, direccion,
                    codigoPostal,ciudad,telefono,pagina,rfc,estatus);
        /*}else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar todos los campos", 
                    Alert.AlertType.WARNING);
        }*/
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
        abandonarPantalla();
    }
    
    
    private void guardarRegistro(String nombre, String nombreComercial, String nombreRepresentante,String email,String direccion,
                    Integer codigoPostal, String ciudad,Integer telefono, String paginaWeb, String rfc, Integer idEstatus){
        try{
            String url = Constantes.URL_BASE + "empresas/registrar";
            String parametros = "nombre="+nombre+"&nombreComercial="+nombreComercial+"&nombreRepresentante="+nombreRepresentante+"&email="+email+"&direccion="+direccion+
                    "&codigoPostal="+codigoPostal+"&ciudad="+ciudad+"&telefono="+telefono+"&paginaWeb="+paginaWeb+"&rfc="+rfc+"&idEstatus="+idEstatus;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);
            respuesta.setNombre(nombre);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Registro guardado", "La empresa "+
                respuesta.getNombre()+" fue registrada con exito ", Alert.AlertType.INFORMATION);
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
        Stage stage = (Stage) btnCancear.getScene().getWindow();        
        stage.close();        
    }
    
}
