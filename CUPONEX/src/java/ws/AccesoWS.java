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
import pojos.RespuestaLogin;

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
            @FormParam("user") String user,
            @FormParam("password") String password){
        RespuestaLogin respuestaWS = new RespuestaLogin(); 
       
        if(user.equals("Admin") && password.equals("qwerty")){
            respuestaWS.setError(false);
            respuestaWS.setMensaje("Usuario correcto...");
            respuestaWS.setNombre("USER");
            respuestaWS.setApellidoParterno("ADMIN");
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Credenciales de acceso incorrectas");
        }
        return respuestaWS;
    }
    
}



