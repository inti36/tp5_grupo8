package ar.edu.unju.escmi.tp5.dominio;

public class ClienteMayorista extends Cliente {

    private int codCliente;

    public ClienteMayorista() {
        super();
    }

    public ClienteMayorista(String direccion, String nombre, String apellido,
                            String contra, int codCliente) {
        super(direccion, nombre, apellido, contra);
        this.codCliente = codCliente;
    }

    @Override
    public int obtenerCodCliente() {
        return codCliente;
    }

    // Cada bulto contiene 10 unidades
    @Override
    public int calcularUnidades(int cantidad) {
        return cantidad * 10;
    }

    // El precio de cada producto es la mitad
    @Override
    public double calcularPrecio(double precio) {
        return precio / 2.0;
    }

    // El cliente mayorista no tiene descuento PAMI
    @Override
    public int calcularDescuento(boolean presentoDni) {
        return 0;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    @Override
    public String toString() {
        return "ClienteMayorista{" +
                "codCliente=" + codCliente +
                ", direccion='" + direccion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}