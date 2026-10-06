package ar.edu.unju.escmi.tp5.dominio;

import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import java.util.HashMap;
import java.util.Map;

public class AgenteAdministrativo extends Empleado {

    public AgenteAdministrativo() {
        super();
    }

    public AgenteAdministrativo(int codigo, String nombre, String contra) {
        super(codigo, nombre, contra);
    }

    public void agregarProducto(Producto p) {
        if (p == null) {
            System.out.println("El producto no es valido");
            return;
        }
        if (CollectionProducto.buscar(p.getCodigo()) == null) {
            CollectionProducto.agregar(p);
            System.out.println("Producto agregado correctamente");
        } else {
            System.out.println("Ya existe un producto con el codigo " + p.getCodigo());
        }
    }

    public boolean realizarVenta(Factura factura) {
        if (factura == null || factura.getCliente() == null) {
            System.out.println("La factura debe tener un cliente.");
            return false;
        }

        if (factura.getDetalles() == null || factura.getDetalles().isEmpty()) {
            System.out.println("La factura debe tener al menos un producto.");
            return false;
        }

        if (CollectionFactura.buscarFactura(factura.getNroFactura()) != null) {
            System.out.println("Ya existe una factura con ese numero.");
            return false;
        }

        Map<Integer, Integer> necesario = new HashMap<>();
        for (Detalle d : factura.getDetalles()) {
            necesario.merge(d.getProducto().getCodigo(), d.getCantidad(), Integer::sum);
        }
        for (Map.Entry<Integer, Integer> e : necesario.entrySet()) {
            if (CollectionProducto.verificarStock(e.getKey()) < e.getValue()) {
                System.out.println("Stock insuficiente para el producto " + e.getKey() + ". Venta cancelada.");
                return false;
            }
        }

        for (Detalle d : factura.getDetalles()) {
            CollectionProducto.descontarStock(d.getProducto().getCodigo(), d.getCantidad());
        }

        CollectionFactura.agregar(factura);
        System.out.println("Venta realizada correctamente.");
        return true;
    }

    @Override
    public String toString() {
        return "Agente administrativo -> " + super.toString();
    }
}