package ar.edu.unju.escmi.tp5.principal;

import ar.edu.unju.escmi.tp5.collections.CollectionCliente;
import ar.edu.unju.escmi.tp5.collections.CollectionEmpleado;
import ar.edu.unju.escmi.tp5.collections.CollectionFactura;
import ar.edu.unju.escmi.tp5.collections.CollectionProducto;
import ar.edu.unju.escmi.tp5.dominio.AgenteAdministrativo;
import ar.edu.unju.escmi.tp5.dominio.Cliente;
import ar.edu.unju.escmi.tp5.dominio.ClienteMayorista;
import ar.edu.unju.escmi.tp5.dominio.ClienteMinorista;
import ar.edu.unju.escmi.tp5.dominio.Empleado;
import ar.edu.unju.escmi.tp5.dominio.EncargadoVenta;
import ar.edu.unju.escmi.tp5.dominio.Factura;
import ar.edu.unju.escmi.tp5.dominio.Producto;
import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        CollectionCliente.precarga();
        CollectionEmpleado.precargarEmpleado();
        precargarProductos();

        int opcion;

        do {
            System.out.println("\n===== SISTEMA DE VENTAS =====");
            System.out.println("1 - Ingresar como cliente");
            System.out.println("2 - Ingresar como empleado");
            System.out.println("0 - Salir");
            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuEmpleado();
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);

        sc.close();
    }

    // ---------------------- CLIENTE ----------------------

    private static void menuCliente() {

        int cod = leerEntero("Codigo de cliente (o DNI): ");
        System.out.print("Contrasenia: ");
        String contra = sc.nextLine();

        if (!CollectionCliente.verificacion(cod, contra)) {
            System.out.println("Codigo o contrasenia incorrectos");
            return;
        }

        Cliente cliente = CollectionCliente.buscarCliente(cod);
        int opcion;

        do {
            System.out.println("\n----- MENU CLIENTE -----");
            System.out.println("1 - Buscar factura");
            System.out.println("0 - Volver");
            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    int nroFactura = leerEntero("Numero de factura: ");
                    Factura factura = cliente.buscarFactura(nroFactura);
                    if (factura != null) {
                        System.out.println(factura);
                    } else {
                        System.out.println("No existe la factura " + nroFactura);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    // ---------------------- EMPLEADOS ----------------------

    private static void menuEmpleado() {

        int cod = leerEntero("Codigo de empleado: ");
        System.out.print("Contrasenia: ");
        String contra = sc.nextLine();

        if (!CollectionEmpleado.autenticacion(cod, contra)) {
            System.out.println("Codigo o contrasenia incorrectos");
            return;
        }

        String tipo = CollectionEmpleado.tipoEmpleado(cod, contra);
        Empleado empleado = CollectionEmpleado.buscarEmpleado(cod, contra);
        System.out.println("Bienvenido/a " + tipo);

        if (empleado instanceof EncargadoVenta) {
            menuEncargadoVenta((EncargadoVenta) empleado);
        } else if (empleado instanceof AgenteAdministrativo) {
            menuAgenteAdministrativo((AgenteAdministrativo) empleado);
        }
    }

    private static void menuEncargadoVenta(EncargadoVenta encargado) {

        int opcion;

        do {
            System.out.println("\n----- MENU ENCARGADO DE VENTAS -----");
            System.out.println("1 - Mostrar las ventas");
            System.out.println("2 - Mostrar el total de todas las ventas");
            System.out.println("3 - Verificar stock de un producto");
            System.out.println("0 - Volver");
            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    encargado.mostrarVentas();
                    break;
                case 2:
                    encargado.mostrarTotalVentas();
                    break;
                case 3:
                    int codigo = leerEntero("Codigo de producto: ");
                    encargado.verificarStock(codigo);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    private static void menuAgenteAdministrativo(AgenteAdministrativo agente) {

        int opcion;

        do {
            System.out.println("\n----- MENU AGENTE ADMINISTRATIVO -----");
            System.out.println("1 - Alta de producto");
            System.out.println("2 - Realizar venta");
            System.out.println("0 - Volver");
            opcion = leerEntero("Ingrese una opcion: ");

            switch (opcion) {
                case 1:
                    altaProducto(agente);
                    break;
                case 2:
                    realizarVenta(agente);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (opcion != 0);
    }

    private static void altaProducto(AgenteAdministrativo agente) {

        int codigo = leerEntero("Codigo de producto: ");
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();
        double precioUnitario = leerDouble("Precio unitario: ");

        int descuento = leerEntero("Descuento (0, 25 o 30): ");
        while (descuento != 0 && descuento != 25 && descuento != 30) {
            System.out.println("El descuento solo puede ser 0, 25 o 30");
            descuento = leerEntero("Descuento (0, 25 o 30): ");
        }

        int stock = leerEntero("Stock inicial: ");

        agente.agregarProducto(new Producto(codigo, descripcion, precioUnitario, descuento, stock));
    }

    private static void realizarVenta(AgenteAdministrativo agente) {

        int codCliente = leerEntero("Codigo del cliente (o DNI): ");
        Cliente cliente = CollectionCliente.buscarCliente(codCliente);

        if (cliente == null) {
            System.out.println("No existe un cliente con ese codigo");
            return;
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        if (cliente instanceof ClienteMinorista) {
            ClienteMinorista minorista = (ClienteMinorista) cliente;

            if (minorista.isTienePami()) {
                System.out.print("¿Presentó DNI? (s/n): ");
                factura.setPresentoDni(sc.nextLine().trim().equalsIgnoreCase("s"));
            }
        }
        factura.setFecha(LocalDate.now());

        boolean hayDetalle = false;
        String seguir;

        do {
            int codProducto = leerEntero("Codigo de producto: ");
            Producto producto = CollectionProducto.buscar(codProducto);

            if (producto == null) {
                System.out.println("No existe un producto con el codigo " + codProducto);
            } else {
                System.out.println(cliente instanceof ClienteMayorista
                        ? "Ingrese cantidad de bultos (10 unidades cada uno)"
                        : "Ingrese cantidad de unidades");
                int cantidad = leerEntero("Cantidad: ");
                if (factura.agregarDetalle(producto, cantidad)) {
                    hayDetalle = true;
                    System.out.println("Producto agregado a la venta");
                } else {
                    System.out.println("No se pudo agregar el producto. Verifique la cantidad y el stock.");
                }
            }

            System.out.print("Desea agregar otro producto? (s/n): ");
            seguir = sc.nextLine();
        } while (seguir.equalsIgnoreCase("s"));

        if (hayDetalle) {
            factura.setNroFactura(CollectionFactura.siguiente());
            if (agente.realizarVenta(factura)) {
                System.out.println(factura);
            }
        } else {
            System.out.println("Venta cancelada, no se cargo ningun producto");
        }
    }

    // ---------------------- PRECARGA Y LECTURA ----------------------

    private static void precargarProductos() {
        CollectionProducto.agregar(new Producto(1002, "Fideo Knorr Spaghetti x 500 gr", 1200.00, 0, 5000));
        CollectionProducto.agregar(new Producto(1003, "Arroz Gallo Oro x 1 kg", 1500.00, 25, 3000));
        CollectionProducto.agregar(new Producto(1004, "Aceite Cocinero x 900 ml", 2500.00, 30, 2000));
        CollectionProducto.agregar(new Producto(1005, "Galletitas Oreo x 118 gr", 900.00, 0, 4000));
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero");
            }
        }
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido");
            }
        }
    }
}