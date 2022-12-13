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
import pojos.Respuesta;
import pojos.Usuario;

/**
 * REST Web Service
 *
 * @author denilson
 */
@Path("usuarios")
public class UsuariosWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MedicosWS
     */
    public UsuariosWS() {
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> buscarTodos(){
        List<Usuario> listaUsuarios = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                listaUsuarios = conexionBD.selectList("usuarios.getAllUsuarios");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return listaUsuarios;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta Registrar(
            @FormParam("IdUsuario") Integer IdUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno, 
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("correo") String correo,
            @FormParam("password") String password){
        
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setIdUsuario(IdUsuario);
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setApellidoPaterno(apellidoPaterno);
        usuarioNuevo.setApellidoMaterno(apellidoMaterno);
        usuarioNuevo.setCorreo(correo);
        usuarioNuevo.setPassword(password);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.insert("usuarios.registrar",usuarioNuevo);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion del usuario registrada");
                }else {
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Error al registrar la informacion del usuario");
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
            @FormParam("IdUsuario") Integer IdUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno, 
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("password") String password,
            @FormParam("idEstatus") String idEstatus){
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setIdUsuario(IdUsuario);
        usuarioNuevo.setNombre(nombre);
        usuarioNuevo.setApellidoPaterno(apellidoPaterno);
        usuarioNuevo.setApellidoMaterno(apellidoMaterno);
        usuarioNuevo.setPassword(password);
        usuarioNuevo.setIdEstatus(idEstatus);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.update("usuarios.modificar",usuarioNuevo);
                conexionBD.commit();
                if(resultado > 0 ){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion del usuario actualizada");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Error al registrar la actualizar la informacion del usuario");
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
    @FormParam("idUsuario") Integer idUsuario){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
                if (conexionBD != null){
            try{
                int respuesta = conexionBD.update("usuarios.eliminar",idUsuario);
                conexionBD.commit();
                if(respuesta > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Registro del usuario eliminado correctamente");
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