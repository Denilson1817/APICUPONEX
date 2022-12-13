/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Empresa;
import pojos.Respuesta;
import pojos.Sucursal;

/**
 * REST Web Service
 *
 * @author denilson
 */
@Path("sucursales")
public class SucursalesWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SucursalesWS
     */
    public SucursalesWS() {
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarTodos(){
        List<Sucursal> listaSucursales = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                listaSucursales = conexionBD.selectList("sucursales.getAllSucursales");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return listaSucursales;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta Registrar(
    @FormParam("idSucursal") Integer idSucursal,
    @FormParam("nombre") String nombre,
    @FormParam("direccion") String direccion,
    @FormParam("codigoPostal") String codigoPostal,
    @FormParam("colonia") String colonia,
    @FormParam("ciudad") String ciudad,
    @FormParam("telefono") String telefono,
    @FormParam("latitud") String latitud,
    @FormParam("longitud") String longitud,
    @FormParam("nombreEncargado") String nombreEncargado,
    @FormParam("idEmpresa") Integer idEmpresa,
    @FormParam("idEstatus") String idEstatus){
        
    Sucursal sucursalNueva= new Sucursal();
    sucursalNueva.setIdSucursal(idSucursal);
    sucursalNueva.setNombre(nombre);
    sucursalNueva.setDireccion(direccion);
    sucursalNueva.setCodigoPostal(codigoPostal);
    sucursalNueva.setColonia(colonia);
    sucursalNueva.setCiudad(ciudad);
    sucursalNueva.setTelefono(telefono);
    sucursalNueva.setLatitud(latitud);
    sucursalNueva.setLongitud(longitud);
    sucursalNueva.setNombreEncargado(nombreEncargado);
    sucursalNueva.setIdEmpresa(idEmpresa);
    sucursalNueva.setIdEstatus(idEstatus);
    
    Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.insert("sucursales.registrar",sucursalNueva);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion de la sucursal registrada");
                }else {
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Error al registrar la informacion de la empresa");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intente más tarde");  
        }
        return respuestaWS;
    }
    
    @Path("modificar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta modificar(
    @FormParam("idSucursal") Integer idSucursal,
    @FormParam("nombre") String nombre,
    @FormParam("direccion") String direccion,
    @FormParam("codigoPostal") String codigoPostal,
    @FormParam("colonia") String colonia,
    @FormParam("ciudad") String ciudad,
    @FormParam("telefono") String telefono,
    @FormParam("latitud") String latitud,
    @FormParam("longitud") String longitud,
    @FormParam("nombreEncargado") String nombreEncargado,
    @FormParam("idEmpresa") Integer idEmpresa,
    @FormParam("idEstatus") String idEstatus){
        
    Sucursal sucursalNueva= new Sucursal();
    sucursalNueva.setIdSucursal(idSucursal);
    sucursalNueva.setNombre(nombre);
    sucursalNueva.setDireccion(direccion);
    sucursalNueva.setCodigoPostal(codigoPostal);
    sucursalNueva.setColonia(colonia);
    sucursalNueva.setCiudad(ciudad);
    sucursalNueva.setTelefono(telefono);
    sucursalNueva.setLatitud(latitud);
    sucursalNueva.setLongitud(longitud);
    sucursalNueva.setNombreEncargado(nombreEncargado);
    sucursalNueva.setIdEmpresa(idEmpresa);
    sucursalNueva.setIdEstatus(idEstatus);
    
    Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.update("sucursales.modificar",sucursalNueva);
                conexionBD.commit();
                if(resultado > 0 ){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion actualizada");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Error al registrar la actualizacion");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
           }finally{
                conexionBD.close();
           }
       }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intente más tarde");  
       }
       return respuestaWS;   
    }
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(
    @FormParam("idSucursal") Integer idSucursal){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
                if (conexionBD != null){
            try{
                int respuesta = conexionBD.update("sucursales.eliminar",idSucursal);
                conexionBD.commit();
                if(respuesta > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Registro eliminado correctamente");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo eliminar el registro solicitado");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("servicio no disponible, intente mas tarde"); 
        }
    return respuestaWS;
    }
}
