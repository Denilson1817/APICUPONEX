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

/**
 * REST Web Service
 *
 * @author denilson
 */
@Path("empresas")
public class EmpresaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpresasWS
     */
    public EmpresaWS() {
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> buscarTodos(){
        List<Empresa> listaEmpresas = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                listaEmpresas = conexionBD.selectList("empresas.getAllEmpresas");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return listaEmpresas;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta Registrar(
        @FormParam("idEmpresa") Integer idEmpresa,
        @FormParam("nombre") String nombre,
        @FormParam("nombreComercial") String  nombreComercial,
        @FormParam("nombreRepresentante") String nombreRepresentante,
        @FormParam("email") String email,
        @FormParam("direccion") String direccion,
        @FormParam("codigoPostal") Integer codigoPostal,
        @FormParam("ciudad") String ciudad,
        @FormParam("telefono") Integer telefono,
        @FormParam("paginaWeb") String web,
        @FormParam("rfc") String RFC,
        @FormParam("idEstatus") Integer estatus){
        
        Empresa empresaNueva= new Empresa();
        empresaNueva.setIdEmpresa(idEmpresa);
        empresaNueva.setNombre(nombre);
        empresaNueva.setNombreComercial(nombreComercial);
        empresaNueva.setNombreRepresentante(nombreRepresentante);
        empresaNueva.setEmail(email);
        empresaNueva.setDireccion(direccion);
        empresaNueva.setCodigoPostal(codigoPostal);
        empresaNueva.setCiudad(ciudad);
        empresaNueva.setTelefono(telefono);
        empresaNueva.setPaginaWeb(web);
        empresaNueva.setRfc(RFC);
        empresaNueva.setIdEstatus(estatus);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.insert("empresas.registrar",empresaNueva);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion de la empresa registrada");
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
    @FormParam("idEmpresa") Integer idEmpresa,
        @FormParam("nombre") String nombre,
        @FormParam("nombreComercial") String  nombreComercial,
        @FormParam("nombreRepresentante") String nombreRepresentante,
        @FormParam("email") String email,
        @FormParam("direccion") String direccion,
        @FormParam("codigoPostal") Integer codigoPostal,
        @FormParam("ciudad") String ciudad,
        @FormParam("telefono") Integer telefono,
        @FormParam("paginaWeb") String web,
        @FormParam("rfc") String RFC,
        @FormParam("idEstatus") Integer estatus){
        
        Empresa empresaNueva= new Empresa();
        empresaNueva.setIdEmpresa(idEmpresa);
        empresaNueva.setNombre(nombre);
        empresaNueva.setNombreComercial(nombreComercial);
        empresaNueva.setNombreRepresentante(nombreRepresentante);
        empresaNueva.setEmail(email);
        empresaNueva.setDireccion(direccion);
        empresaNueva.setCodigoPostal(codigoPostal);
        empresaNueva.setCiudad(ciudad);
        empresaNueva.setTelefono(telefono);
        empresaNueva.setPaginaWeb(web);
        empresaNueva.setRfc(RFC);
        empresaNueva.setIdEstatus(estatus);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.update("empresas.modificar",empresaNueva);
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
    @FormParam("idEmpresa") Integer idEmpresa){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
                if (conexionBD != null){
            try{
                int respuesta = conexionBD.update("empresas.eliminar",idEmpresa);
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
