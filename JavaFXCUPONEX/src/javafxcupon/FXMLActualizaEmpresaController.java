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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConexionServiciosWeb;
import javafxcuponex.pojos.Empresa;
import javafxcuponex.pojos.Respuesta;
import javafxcuponex.pojos.Usuarios;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author javier
 */
public class FXMLActualizaEmpresaController implements Initializable {
    private int idEmpresa;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField tfIdEstatus;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfPaginaWeb;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNombreRepresentante;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfIdEmpresa;
    ObservableList<Empresa> listaEmpresaX;
    TableView<Empresa> tvEmpresaX;    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    private void abandonarPantalla() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();        
        stage.close();        
    }
    
    void guardaEmpresaSeleccionada(ObservableList<Empresa> listaEmpresa, TableView<Empresa> tablaEmpresa){
        listaEmpresaX = listaEmpresa;
        tvEmpresaX = tablaEmpresa;
    }    
    
    private void cargarInformacionEmpresa(){
        String url = Constantes.URL_BASE+"empresas/all";
        try{
            //String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);

            String resultado = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type  listaEmpresa = new TypeToken<ArrayList <Empresa> >() {}.getType();
            //RespuestaLogin respuesta = gson.fromJson(resultado, RespuestaLogin.class);

            ArrayList Empresalist = gson.fromJson(resultado, listaEmpresa);
            guardaEmpresaSeleccionada(listaEmpresaX, tvEmpresaX);
            listaEmpresaX.clear();
            listaEmpresaX.addAll(Empresalist);
            tvEmpresaX.setItems(listaEmpresaX);
                    
        }catch(Exception e){
            e.printStackTrace();
            Utilidades.mostrarAlertaSimple("Error de conexión", "Intente mas tarde"
                    , Alert.AlertType.ERROR);
        }
        
    }    
    public void inicializarInformacionVentana(int idEmpresa,String nombre, String nombreComercial, String nombreRepresentante,String email,String direccion,
                    Integer codigoPostal, String ciudad,Integer telefono, String paginaWeb, String rfc, Integer idEstatus){
        this.idEmpresa = idEmpresa;      
        tfNombre.setText(nombre);
        tfNombreComercial.setText(nombreComercial);
        tfNombreRepresentante.setText(nombreRepresentante);
        tfCorreo.setText(email);
        tfDireccion.setText(direccion);
        int cp=codigoPostal;
        String cpString= String.valueOf(cp);
        tfCP.setText(cpString);
        tfCiudad.setText(ciudad);
        int tel=telefono;
        String telefonoString= String.valueOf(tel);
        tfTelefono.setText(telefonoString);
        tfPaginaWeb.setText(paginaWeb);
        tfRFC.setText(rfc);
         int idEs=idEstatus;
        String idEstatusString= String.valueOf(idEs);
        tfIdEstatus.setText(idEstatusString);

        //tf
    }

    @FXML
    private void clicCancelar(ActionEvent event) {
    }

    @FXML
    private void clicGuardar(ActionEvent event) {
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
        //if (!nombre.isEmpty() && !nombreComercial.isEmpty() && !representante.isEmpty()
          //      &&!correo.isEmpty()  && !password.isEmpty()){
            //verificarInicioSesion(noPersonal, password);
            //todo
            //recuperaDatosTabla();
            actualizaRegistro(idEmpresa,nombre, nombreComercial,representante,correo, direccion,
                    codigoPostal,ciudad,telefono,pagina,rfc,estatus);
        //}else{ 
          //  Utilidades.mostrarAlertaSimple("Campos requeridos", 
            //        "Es necesario ingresar todos los campos", 
              //      Alert.AlertType.WARNING);
        //}

        
    }
    
    
    private void actualizaRegistro(int idEmpresa,String nombre, String nombreComercial, String nombreRepresentante,String email,String direccion,
                    Integer codigoPostal, String ciudad,Integer telefono, String paginaWeb, String rfc, Integer idEstatus){
        try{
            String url = Constantes.URL_BASE + "empresas/modificar";
            String parametros = "idEmpresa="+idEmpresa+"&nombre="+nombre+"&nombreComercial="+nombreComercial+"&nombreRepresentante="+nombreRepresentante+"&email="+email+"&direccion="+direccion+
                    "&codigoPostal="+codigoPostal+"&ciudad="+ciudad+"&telefono="+telefono+"&paginaWeb="+paginaWeb+"&rfc="+rfc+"&idEstatus="+idEstatus;
            
            String resultado = ConexionServiciosWeb.consumirServicioPUT(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);            
            if (!respuesta.getError()) {                
                Utilidades.mostrarAlertaSimple("Empresa modificado", 
                        " Empresa modificado correctamente "
                        , Alert.AlertType.INFORMATION);
                Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
                stage.close();
                cargarInformacionEmpresa();
            }else{
                Utilidades.mostrarAlertaSimple("Error al editar el Empresa", respuesta.getMensaje(),
                        Alert.AlertType.ERROR);
            }
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",
                e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    
}
