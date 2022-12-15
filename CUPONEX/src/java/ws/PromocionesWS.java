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
    
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta Registrar(
    @FormParam("idPromocion") Integer idPromocion,
    @FormParam("nombre") String nombre,
    @FormParam("foto") String foto,
    @FormParam("descripcion") String descripcion,
    @FormParam("fechaInicio") String fechaInicio,
    @FormParam("fechaTermino") String fechaTermino,
    @FormParam("restriccion") String restriccion,
    @FormParam("tipo") String tipo,
    @FormParam("porcentajeDescuento")String porcentajeDescuento,
    @FormParam("costo") int costo,
    @FormParam("categoria") String categoria,
    @FormParam("estatus") String estatus,
    @FormParam("idEmpresa")String idEmpresa,
    @FormParam("idSucursal")String idSucursal){
        
        Promocion promocionNueva = new Promocion();
        promocionNueva.setIdPromocion(idPromocion);
        promocionNueva.setNombre(nombre);
        promocionNueva.setFoto(foto);
        promocionNueva.setDescripcion(descripcion);
        promocionNueva.setFechaInicio(fechaInicio);
        promocionNueva.setFechaTermino(fechaTermino);
        promocionNueva.setRestriccion(restriccion);
        promocionNueva.setTipo(tipo);
        promocionNueva.setPorcentajeDescuento(porcentajeDescuento);
        promocionNueva.setCosto(costo);
        promocionNueva.setCategoria(categoria);
        promocionNueva.setEstatus(estatus);
        promocionNueva.setIdEmpresa(idEmpresa);
        promocionNueva.setIdSucursal(idSucursal);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.insert("promociones.registrar",promocionNueva);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informacion registrada");
                }else {
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Error al registrar la informacion");
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
    @FormParam("idPromocion") Integer idPromocion,
    @FormParam("nombre") String nombre,
    @FormParam("foto") String foto,
    @FormParam("descripcion") String descripcion,
    @FormParam("fechaInicio") String fechaInicio,
    @FormParam("fechaTermino") String fechaTermino,
    @FormParam("restriccion") String restriccion,
    @FormParam("tipo") String tipo,
    @FormParam("porcentajeDescuento") String porcentajeDescuento,
    @FormParam("costo") int costo,
    @FormParam("categoria") String categoria,
    @FormParam("estatus") String estatus,
    @FormParam("idEmpresa")String idEmpresa,
    @FormParam("idSucursal")String idSucursal){
        
        Promocion promocionNueva= new Promocion();
        promocionNueva.setIdPromocion(idPromocion);
        promocionNueva.setNombre(nombre);
        promocionNueva.setFoto(foto);
        promocionNueva.setDescripcion(descripcion);
        promocionNueva.setFechaInicio(fechaInicio);
        promocionNueva.setFechaTermino(fechaTermino);
        promocionNueva.setRestriccion(restriccion);
        promocionNueva.setTipo(tipo);
        promocionNueva.setPorcentajeDescuento(porcentajeDescuento);
        promocionNueva.setCosto(costo);
        promocionNueva.setCategoria(categoria);
        promocionNueva.setEstatus(estatus);
        promocionNueva.setIdEmpresa(idEmpresa);
        promocionNueva.setIdSucursal(idSucursal);
        
         Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null ){
            try{
                int resultado = conexionBD.update("promociones.modificar",promocionNueva);
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
    public Respuesta eliminar(@FormParam("idPromocion") Integer idPromocion){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
                if (conexionBD != null){
            try{
                int respuesta = conexionBD.update("promociones.eliminar",idPromocion);
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
