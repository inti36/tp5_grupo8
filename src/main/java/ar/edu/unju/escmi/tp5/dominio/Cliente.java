package ar.edu.unju.escmi.tp5.dominio;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;

public abstract class Cliente {

    protected String direccion;
    protected String nombre;
    protected String apellido;
    protected String contra;

    public Cliente() {
    }

    public Cliente(String direccion, String nombre, String apellido, String contra) {
        this.direccion = direccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contra = contra;
    }

    public Factura buscarFactura(int nroFactura) {
        Factura factura = CollectionFactura.buscarFactura(nroFactura);

        if (factura != null && factura.getCliente() == this) {
            return factura;
        }

        return null;
    }

    public abstract int obtenerCodCliente();

    public abstract int calcularUnidades(int cantidad);

    public abstract double calcularPrecio(double precio);

    public abstract int calcularDescuento(boolean presentoDni);

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}