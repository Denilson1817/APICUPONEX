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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
public class FXMLAdminEmpresaController implements Initializable {

    @FXML
    private TableView<Empresa> tvEmpresa;
    @FXML
    private TableColumn colidEmpresa;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colNombreComercial;
    @FXML
    private TableColumn colRepresentanteLegal;
    //private TableColumn colCorreo;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colCP;
    @FXML
    private TableColumn colCiudad;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colPaginaWeb;
    @FXML
    private TableColumn colRFC;
    @FXML
    private TableColumn colEstatus;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Button btnSallir;
    private ObservableList<Empresa> listaEmpresas; 
    @FXML
    private TableColumn colEmail;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInfoUsuarioWS();
    }   
    
    
    private void configurarTabla(){
        listaEmpresas = FXCollections.observableArrayList();
        colidEmpresa.setCellValueFactory(new PropertyValueFactory("idEmpresa"));
        colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        colNombreComercial.setCellValueFactory(new PropertyValueFactory("nombreComercial"));
        colRepresentanteLegal.setCellValueFactory(new PropertyValueFactory("nombreRepresentante"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        colCP.setCellValueFactory(new PropertyValueFactory("codigoPostal"));
        colCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colPaginaWeb.setCellValueFactory(new PropertyValueFactory("paginaWeb"));
        colRFC.setCellValueFactory(new PropertyValueFactory("rfc"));
        colEstatus.setCellValueFactory(new PropertyValueFactory("idEstatus"));
        

        
        
    }
    
    private void cargarInfoUsuarioWS(){
        String urlWS = Constantes.URL_BASE+ "empresas/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaEmpresa = new TypeToken<List<Empresa>>(){}.getType();
            List EmpresaWS = gson.fromJson(jsonRespuesta, tipoListaEmpresa);
            
            listaEmpresas.addAll(EmpresaWS);
            tvEmpresa.setItems(listaEmpresas);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }
    
    private void abandonarPantalla() {
        Stage stage = (Stage) btnRegistrar.getScene().getWindow();        
        stage.close();        
    }
    
    private void eliminar(int idEmpresa){
        
        try{
            
            String urlServicio = Constantes.URL_BASE+"empresas/eliminar";
            
            String parametros = "idEmpresa="+idEmpresa;
            String resultado = ConexionServiciosWeb.consumirServicioDELTE(urlServicio, parametros);
            Gson gson = new Gson() ;
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if (!respuesta.getError()) {
                
                Utilidades.mostrarAlertaSimple("Empresa eliminado", 
                        " Empresa eliminado correctamente "
                        , Alert.AlertType.INFORMATION);
            }else{
                Utilidades.mostrarAlertaSimple("Error al eliminar empresa","No se a podido eliminar la empresa porque tiene una sucursal dada de alta",
                        Alert.AlertType.ERROR);
            }            
            
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión", e.getMessage(), Alert.AlertType.ERROR);            
        }
        
    }

    
    

    @FXML
    private void clicIrEliminar(ActionEvent event) {
        
        int filaSeleccionada = tvEmpresa.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
            try{  
                int id = listaEmpresas.get(filaSeleccionada).getIdEmpresa();
                    eliminar(id);
                    listaEmpresas.clear();
                    cargarInfoUsuarioWS();
                
            }catch(Exception e){
                Utilidades.mostrarAlertaSimple("Error", "No se ha podido cargar la ventana principal -"+e, Alert.AlertType.ERROR);                
            }


        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Selecciona una empresa para su modificación"
                    , Alert.AlertType.WARNING);
        }   

       
    }

    @FXML
    private void clicIrEditar(ActionEvent event) {
         int filaSeleccionada = tvEmpresa.getSelectionModel().getSelectedIndex();

        if(filaSeleccionada >= 0){

            try{
                
                int idEmpresa = listaEmpresas.get(filaSeleccionada).getIdEmpresa();
                String nombre = listaEmpresas.get(filaSeleccionada).getNombre();
                String nombreComercial = listaEmpresas.get(filaSeleccionada).getNombreComercial();
                String nombreRepresentante = listaEmpresas.get(filaSeleccionada).getNombreRepresentante();
                String email = listaEmpresas.get(filaSeleccionada).getEmail();
                String direccion = listaEmpresas.get(filaSeleccionada).getDireccion();   
                Integer cp = listaEmpresas.get(filaSeleccionada).getCodigoPostal();  
                //int CP = Integer.parseInt(cp);
                String ciudad = listaEmpresas.get(filaSeleccionada).getCiudad();            
                Integer telefono = listaEmpresas.get(filaSeleccionada).getTelefono();            
                String paginaWeb = listaEmpresas.get(filaSeleccionada).getPaginaWeb();            
                String rfc = listaEmpresas.get(filaSeleccionada).getRfc();            
                Integer idEstatus = listaEmpresas.get(filaSeleccionada).getIdEstatus();            


                FXMLLoader loadController = new FXMLLoader(getClass().getResource("FXMLActualizaEmpresa.fxml"));
                Parent vistaFormulario = loadController.load();
                FXMLActualizaEmpresaController controllerFormulario = loadController.getController();
                
                controllerFormulario.inicializarInformacionVentana(idEmpresa, nombre, nombreComercial, nombreRepresentante,email, direccion, cp, ciudad, telefono, paginaWeb, rfc,idEstatus);
                
                controllerFormulario.guardaEmpresaSeleccionada(listaEmpresas, tvEmpresa);
                
                Scene escenaFormulario = new Scene(vistaFormulario);
                Stage escenarioFormulario = new Stage();
                escenarioFormulario.setScene(escenaFormulario);
                escenarioFormulario.initModality(Modality.APPLICATION_MODAL);
                escenarioFormulario.showAndWait();
                
            }catch(IOException e){
                Utilidades.mostrarAlertaSimple("Error", "No se ha podido cargar la ventana principal -"+e, Alert.AlertType.ERROR);                
            }


        }else{
            Utilidades.mostrarAlertaSimple("No hay ningún registro seleccionado", "Selecciona una empresa para su modificación"
                    , Alert.AlertType.WARNING);
        }   
    }

    @FXML
    private void clicIrRegistrar(ActionEvent event) {
        try{
            Parent vista = FXMLLoader.load(getClass().getResource("FXMLAgregaEmpresa.fxml"));
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
    private void clicIrSalir(ActionEvent event) {
        abandonarPantalla();
    }
    
}
