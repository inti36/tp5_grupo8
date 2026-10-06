package ar.edu.unju.escmi.tp5.dominio;

import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {

    private int nroFactura;
    private LocalDate fecha;
    private double total;
    private Cliente cliente;
    private boolean presentoDni;
    private List<Detalle> detalles = new ArrayList<>();

    public Factura() {
    }

    public Factura(int nroFactura, LocalDate fecha, Cliente cliente) {
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.presentoDni = false;
    }

    public Factura(int nroFactura, LocalDate fecha, Cliente cliente, boolean presentoDni) {
        this.nroFactura = nroFactura;
        this.fecha = fecha;
        this.cliente = cliente;
        this.presentoDni = presentoDni;
    }

    public boolean agregarDetalle(Producto p, int cantidad) {
        if (p == null || cliente == null || cantidad <= 0) {
            return false;
        }

        Producto producto = CollectionProducto.buscar(p.getCodigo());
        if (producto == null) {
            return false;
        }

        // El cliente (mayorista o minorista) calcula sus unidades y su precio
        int unidadesReales = cliente.calcularUnidades(cantidad);
        double precio = cliente.calcularPrecio(producto.getPrecioUnitario());

        int yaEnFactura = 0;
        for (Detalle d : detalles) {
            if (d.getProducto().getCodigo() == producto.getCodigo()) {
                yaEnFactura += d.getCantidad();
            }
        }
        if (!CollectionProducto.verificarStockVendido(producto, yaEnFactura + unidadesReales)) {
            return false;
        }

        detalles.add(new Detalle(producto, unidadesReales, precio));
        calculoTotal();
        return true;
    }

    public double calculoTotal() {
        int descCliente = 0;
        if (cliente != null) {
            descCliente = cliente.calcularDescuento(presentoDni);
        }

        double suma = 0;
        for (Detalle d : detalles) {
            suma += d.calculoSubtotal(descCliente);
        }

        total = suma;
        return total;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        if (!detalles.isEmpty()) {
            return;
        }
        this.cliente = cliente;
    }

    public boolean isPresentoDni() {
        return presentoDni;
    }

    public void setPresentoDni(boolean presentoDni) {
        this.presentoDni = presentoDni;
        calculoTotal();
    }

    public List<Detalle> getDetalles() {
        return new ArrayList<>(detalles);
    }

    @Override
    public String toString() {
        String texto = "==============================================\n";
        texto += "FACTURA Nro: " + nroFactura + "   Fecha: " + fecha + "\n";
        if (cliente != null) {
            texto += "Cliente: " + cliente.getApellido() + ", " + cliente.getNombre()
                    + " - " + cliente.getDireccion() + "\n";
        }
        texto += "----------------------------------------------\n";
        for (Detalle d : detalles) {
            texto += d + "\n";
        }
        texto += "----------------------------------------------\n";
        texto += "TOTAL: $" + total + "\n";
        texto += "==============================================";
        return texto;
    }
}