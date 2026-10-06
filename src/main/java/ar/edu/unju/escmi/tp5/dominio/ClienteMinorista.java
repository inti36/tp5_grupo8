package ar.edu.unju.escmi.tp5.dominio;

public class ClienteMinorista extends Cliente {

    private int dni;
    private boolean tienePami;

    public ClienteMinorista() {
        super();
    }

    public ClienteMinorista(String direccion, String nombre, String apellido, String contra, int dni, boolean tienePami) {
        super(direccion, nombre, apellido, contra);
        this.dni = dni;
        this.tienePami = tienePami;
    }

    @Override
    public int obtenerCodCliente() {
        return dni;
    }

    // Compra por unidad
    @Override
    public int calcularUnidades(int cantidad) {
        return cantidad;
    }

    // Paga el precio completo
    @Override
    public double calcularPrecio(double precio) {
        return precio;
    }

    // 10% si presenta DNI y tiene PAMI
    @Override
    public int calcularDescuento(boolean presentoDni) {
        if (presentoDni && tienePami) {
            return 10;
        }
        return 0;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isTienePami() {
        return tienePami;
    }

    public void setTienePami(boolean tienePami) {
        this.tienePami = tienePami;
    }

    @Override
    public String toString() {
        return "ClienteMinorista{" +
                "dni=" + dni +
                ", tienePami=" + tienePami +
                ", direccion='" + direccion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}