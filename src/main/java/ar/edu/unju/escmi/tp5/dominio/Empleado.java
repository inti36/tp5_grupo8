package ar.edu.unju.escmi.tp5.dominio;

public abstract class Empleado {

    protected int codigo;
    protected String nombre;
    protected String contra;

    public Empleado() { }

    public Empleado(int codigo, String nombre, String contra) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.contra = contra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + " | Nombre: " + nombre;
    }
}