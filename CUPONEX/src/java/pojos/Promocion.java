/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author denilson
 */
public class Promocion {
    private Integer idPromocion;
    private String nombre; 
    private String foto; 
    private String descripcion;
    private String fechaInicio;
    private String fechaTermino;
    private String restriccion;
    private String tipo;
    private String porcentajeDescuento;
    private int costo;
    private String categoria;
    private String estatus;
    private String idEmpresa;
    private String idSucursal;

    public Promocion(Integer idPromocion, String nombre, String foto, String descripcion, String fechaInicio, String fechaTermino, String restriccion, String tipo, String porcentajeDescuento, int costo, String categoria, String estatus, String idEmpresa, String idSucursal) {
        this.idPromocion = idPromocion;
        this.nombre = nombre;
        this.foto = foto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.restriccion = restriccion;
        this.tipo = tipo;
        this.porcentajeDescuento = porcentajeDescuento;
        this.costo = costo;
        this.categoria = categoria;
        this.estatus = estatus;
        this.idEmpresa = idEmpresa;
        this.idSucursal = idSucursal;
    }

    public Promocion() {
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(String fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(String porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    public String getIdEmpresa(){
        return idEmpresa;
    }
    
    public void setIdEmpresa(String idEmpresa){
        this.idEmpresa = idEmpresa;
    }
    
    public String getIdSucursal(){
        return idSucursal;
    }
    
    public void setIdSucursal(String idSucursal){
        this.idSucursal = idSucursal;
    }
    
}
