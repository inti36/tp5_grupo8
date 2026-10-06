package ar.edu.unju.escmi.tp5.collections;

import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMayorista;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;
import java.util.ArrayList;
import java.util.List;

public class CollectionCliente {

    public static List<Cliente> Cliente = new ArrayList<>();

    public static boolean verificacion(int cod, String contra) {

        Cliente cliente = buscarCliente(cod);

        return cliente != null
                && cliente.getContra() != null
                && cliente.getContra().equals(contra);
    }

    public static void precarga() {

        Cliente.clear();

        Cliente.add(new ClienteMayorista(
                "Independencia 734",
                "Inti",
                "Aragón",
                "1234",
                1001
        ));

        Cliente.add(new ClienteMinorista(
                "San Luis 94",
                "Paula",
                "Aragón",
                "5678",
                21665742,
                true
        ));

        Cliente.add(new ClienteMinorista(
                "Independencia 746",
                "Lucas",
                "Atipaj",
                "1234",
                25123456,
                false
        ));
    }

    public static Cliente buscarCliente(int cod) {

        for (Cliente cliente : Cliente) {

            if (cliente.obtenerCodCliente() == cod) {
                return cliente;
            }
        }

        return null;
    }
}