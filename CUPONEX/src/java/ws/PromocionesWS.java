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
import pojos.Promocion;
import pojos.Respuesta;
import pojos.Sucursal;

/**
 * REST Web Service
 *
 * @author denilson
 */
@Path("promociones")
public class PromocionesWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PromocionesWS
     */
    public PromocionesWS() {
    }
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarTodos(){
        List<Promocion> listaPromociones = null;
        SqlSession conexionBD = mybatis.MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                listaPromociones = conexionBD.selectList("promociones.getAllPromociones");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return listaPromociones;
    }
    
    
}
