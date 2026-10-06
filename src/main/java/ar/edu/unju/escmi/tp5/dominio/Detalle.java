package ar.edu.unju.escmi.tp5.dominio;

public class Detalle {

    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private Producto producto;

    public Detalle() {
    }

    public Detalle(Producto producto, int cantidad, double precioUnitario) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calculoSubtotal(0);
    }

    public double calculoSubtotal(int descCliente) {
        double importe = cantidad * precioUnitario;

        if (producto != null && producto.getDescuento() > 0) {
            importe -= importe * producto.getDescuento() / 100.0;
        }

        if (descCliente > 0) {
            importe -= importe * descCliente / 100.0;
        }

        subtotal = importe;
        return subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    @Override
    public String toString() {
        String desc = "-";
        int dto = 0;

        if (producto != null) {
            desc = producto.getDescripcion();
            dto = producto.getDescuento();
        }

        return "  " + cantidad + " x " + desc
                + " | precio unit.: $" + precioUnitario
                + " | dto. producto: " + dto + "%"
                + " | importe: $" + subtotal;
    }
}