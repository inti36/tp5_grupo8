package ar.edu.unju.escmi.tp5.collections;

import ar.edu.unju.escmi.tp5.dominio.Producto;
import java.util.ArrayList;
import java.util.List;

public class CollectionProducto {

    public static List<Producto> Producto = new ArrayList<>();

    public static int verificarStock(int cod) {

        Producto producto = buscar(cod);

        if (producto != null) {
            return producto.getStock();
        }

        return 0;
    }

    public static Producto buscar(int cod) {

        for (Producto producto : Producto) {

            if (producto.getCodigo() == cod) {
                return producto;
            }
        }

        return null;
    }

    public static void agregar(Producto p) {

        if (p == null) {
            return;
        }

        if (buscar(p.getCodigo()) == null) {
            Producto.add(p);
        }
    }

    public static boolean verificarStockVendido(Producto p, int cantidad) {
        if (p == null || cantidad <= 0) {
            return false;
        }

        Producto producto = buscar(p.getCodigo());

        if (producto == null || producto.getStock() < cantidad) {
            return false;
        }

        return true;
    }

    public static boolean descontarStock(int codigo, int cantidad) {
        Producto producto = buscar(codigo);

        if (producto == null || cantidad <= 0 || producto.getStock() < cantidad) {
            return false;
        }

        producto.setStock(producto.getStock() - cantidad);
        return true;
    }
}