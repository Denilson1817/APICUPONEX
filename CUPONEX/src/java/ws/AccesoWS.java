/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.RespuestaLogin;
import pojos.Usuario;

/**
 *
 * @author denilson
 */
@Path("acceso")
public class AccesoWS {
    @Context
    private UriInfo context;
    
    @Path("escritorio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
     public RespuestaLogin iniciarSesionEscritorio(
            @FormParam("nombre") String nombre,
            @FormParam("password") String password){
        RespuestaLogin respuesta = new RespuestaLogin(); 
        //temporal
       
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);
        
       
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                
               int usuario1 = conexionBD.selectOne("usuarios.usuarioLogin", usuario);
                conexionBD.commit();
                
                if(usuario1 >0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido " + usuario.getNombre());
                    
                }else{
                    respuesta.setError(true);
                    respuesta.setMensaje("Usuario no encontrado, verifica tu estatus");
                    
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
                //respuesta.setMensaje("Usuario no encontrado, verifica tu estatus");

            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Servicio no disponible, intentelo más tarde.");
        }
        
        return respuesta;
    }
     
    @Path("movil")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin  iniciarSesionMovil(
      @FormParam("nombre") String nombre,
      @FormParam("password") String password){
        
        RespuestaLogin resp = new RespuestaLogin();
        
        Usuario usuario =  new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);
                
        Usuario usuarioResultado = null;
        
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if (conexionBD != null) {
            try{
                usuarioResultado = conexionBD.selectOne("usuarios.usuarioLoginMovil",usuario);

                conexionBD.commit();
                if(usuarioResultado != null){

                    resp.setError(false);
                    resp.setMensaje("Bienvenido ");
                    resp.setNombre(usuarioResultado.getNombre());
                }else{
                    resp.setError(true);
                    resp.setMensaje("Error al iniciar sesión");
                }
            }catch(Exception e){
                resp.setError(true);
                resp.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            resp.setError(true);
            resp.setMensaje("Sin conexión al sistema");
        }        


        return resp;
    }
}



