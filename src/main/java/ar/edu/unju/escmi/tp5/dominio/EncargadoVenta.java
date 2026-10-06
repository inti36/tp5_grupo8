package ar.edu.unju.escmi.tp5.dominio;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;

public class EncargadoVenta extends Empleado {

    public EncargadoVenta() {
        super();
    }

    public EncargadoVenta(int codigo, String nombre, String contra) {
        super(codigo, nombre, contra);
    }

    public void mostrarVentas() {
        CollectionFactura.mostrar();
    }

    public void mostrarTotalVentas() {
        System.out.println("Total de todas las ventas: $" + CollectionFactura.totalVentas());
    }

    public void verificarStock(int codigo) {
        Producto producto = CollectionProducto.buscar(codigo);
        if (producto != null) {
            System.out.println("Producto: " + producto.getDescripcion());
            System.out.println("Stock disponible: " + producto.getStock());
        } else {
            System.out.println("No existe un producto con el codigo " + codigo);
        }
    }

    @Override
    public String toString() {
        return "Encargado de ventas -> " + super.toString();
    }
}