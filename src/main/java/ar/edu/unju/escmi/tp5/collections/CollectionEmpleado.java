package ar.edu.unju.escmi.tp5.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp5.dominio.AgenteAdministrativo;
import ar.edu.unju.escmi.tp5.dominio.Empleado;
import ar.edu.unju.escmi.tp5.dominio.EncargadoVenta;

public class CollectionEmpleado {

    public static List<Empleado> Empleado = new ArrayList<>();

    public static boolean autenticacion(int cod, String contra) {
        return buscarEmpleado(cod, contra) != null;
    }

    public static String tipoEmpleado(int cod, String contra) {
        Empleado empleado = buscarEmpleado(cod, contra);
        if (empleado instanceof EncargadoVenta) {
            return "Encargado de ventas";
        } else if (empleado instanceof AgenteAdministrativo) {
            return "Agente administrativo";
        }
        return null;
    }

    public static Empleado buscarEmpleado(int cod, String contra) {
        for (Empleado empleado : Empleado) {
            if (empleado.getCodigo() == cod && empleado.getContra().equals(contra)) {
                return empleado;
            }
        }
        return null;
    }

    public static void precargarEmpleado() {

        Empleado.clear();

        Empleado.add(new EncargadoVenta(1, "marcos", "1234"));
        Empleado.add(new AgenteAdministrativo(2, "lucia", "5678"));
        Empleado.add(new AgenteAdministrativo(3, "diego", "9012"));
    }
}