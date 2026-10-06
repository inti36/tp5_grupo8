package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.Factura;

public class CollectionFactura {

    public static List<Factura> factura = new ArrayList<>();

    private static int ultimo = 0;

    public static int siguiente() {
        ultimo++;
        return ultimo;
    }

    public static void agregar(Factura f) {
        if (f != null && buscarFactura(f.getNroFactura()) == null) {
            factura.add(f);
        }
    }

    public static Factura buscarFactura(int nroFactura) {
        for (Factura f : factura) {
            if (f.getNroFactura() == nroFactura) {
                return f;
            }
        }
        return null;
    }

    public static double totalVentas() {
        double suma = 0;
        for (Factura f : factura) {
            suma += f.getTotal();
        }
        return suma;
    }

    public static void mostrar() {
        if (factura.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        for (Factura f : factura) {
            System.out.println(f);
        }
    }
}