/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxcupon;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxcuponex.modelo.ConexionServiciosWeb;
import javafxcuponex.pojos.Usuarios;
import javafxcuponex.util.Constantes;
import javafxcuponex.util.Utilidades;

/**
 * FXML Controller class
 *
 * @author denilson
 */
public class FXMLAdminUsuariosController implements Initializable {
    
    public TextField tfBusquedaUsuario;
    @FXML
    private TableView<Usuarios> tbUsuarios;
    @FXML
    private TableColumn colIdUsuario;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn  colCorreo;
    @FXML
    private TableColumn  colEstatus;
    
    private ObservableList<Usuarios> listaUsuarios; 
    @FXML
    private Button btRegresar;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInfoMedicoWS();
    }
    
    private void configurarTabla(){
        listaUsuarios = FXCollections.observableArrayList();
        colIdUsuario.setCellValueFactory(new PropertyValueFactory("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("password"));
        
        
    }
    
    private void cargarInfoMedicoWS(){
        String urlWS = Constantes.URL_BASE+ "usuarios/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaUsuario = new TypeToken<List<Usuarios>>(){}.getType();
            List UsuarioWS = gson.fromJson(jsonRespuesta, tipoListaUsuario);
            
            listaUsuarios.addAll(UsuarioWS);
            tbUsuarios.setItems(listaUsuarios);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }
    
    @FXML 
    private void clicIrAgregarUsuario(ActionEvent event){
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLUsuarios.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
                
        
    }
    
    
    /*
    @FXML 
    private void (ActionEvent event){
        
    }
    */

    @FXML
    private void clicModificarUsuario(ActionEvent event) {
               
        
        int filaSeleccionada = tbUsuarios.getSelectionModel().getSelectedIndex();

        if(filaSeleccionada >= 0){

            try{
                
                int idAdministradorSeleccionado = listaUsuarios.get(filaSeleccionada).getIdUsuario();
                String nombre = listaUsuarios.get(filaSeleccionada).getNombre();
                String apellidoPaterno = listaUsuarios.get(filaSeleccionada).getApellidoPaterno();
                String apellidoMaterno = listaUsuarios.get(filaSeleccionada).getApellidoMaterno();
                String correo = listaUsuarios.get(filaSeleccionada).getCorreo();
                String password = listaUsuarios.get(filaSeleccionada).getPassword();            

                FXMLLoader loadController = new FXMLLoader(getClass().getResource("FXMLActualizaUsuario.fxml"));
                Parent vistaFormulario = loadController.load();
                FXMLActualizaUsuarioController controllerFormulario = loadController.getController();
                
                controllerFormulario.inicializarInformacionVentana(idAdministradorSeleccionado, nombre, apellidoPaterno, apellidoMaterno, correo, password);
                
                controllerFormulario.guardaUsuarioSeleccionado(listaUsuarios, tbUsuarios);
                
                Scene escenaFormulario = new Scene(vistaFormulario);
                Stage escenarioFormulario = new Stage();
                escenarioFormulario.setScene(escenaFormulario);
                escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
                escenarioFormulario.showAndWait();
                
            }catch(IOException e){
                Utilidades.mostrarAlertaSimple("Error", "No se ha podido cargar la ventana principal -"+e, Alert.AlertType.ERROR);                
            }


        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Debes seleccionar un administrador para su modificación"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void clicIrEliminarUsuario(ActionEvent event) {
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLUsuarios.fxml"));
            Scene escenaFormulario = new Scene(vista);
            Stage escenarioFormulario = new Stage();
            escenarioFormulario.setScene(escenaFormulario);
            escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
            escenarioFormulario.showAndWait();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    

    @FXML
    private void clicRegresar(ActionEvent event) {
        abandonarPantalla();
    }
    
    
    private void abandonarPantalla() {
        Stage stage = (Stage) btRegresar.getScene().getWindow();        
        stage.close();        
    }
    
    
}
