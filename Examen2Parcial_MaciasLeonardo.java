import java.util.ArrayList;
import java.util.Scanner;

public class Examen2Parcial_MaciasLeonardo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] nombresProductos = {"Chocolate", "Paleta", "Gomitas", "Galletas"};
        double[] preciosProductos = {18.0, 12.0, 25.0, 15.0};

        ArrayList<String> nombresClientes = new ArrayList<>();
        int[] unidadesVendidasPorProducto = new int[4]; 
        ArrayList<Double> totalesPorCliente = new ArrayList<>();

        double totalDineroRecaudado = 0.0;
        int numClientesAtendidos = 0;

        while (true) {
            System.out.println("\nREGISTRAR UN CLIENTE");
            System.out.println("1 = SÍ");
            System.out.println("2 = NO (Finalizar)");
            System.out.print("Seleccione una opción: ");

            int opcionCliente = 0;
            if (scanner.hasNextInt()) {
                opcionCliente = scanner.nextInt();
            } else {
                scanner.next(); 
                System.out.println("Ingrese 1 o 2.");
                continue;
            }
            scanner.nextLine(); 

            if (opcionCliente == 2) {
                break; 
            } else if (opcionCliente != 1) {
                System.out.println("Opción inválida");
                continue;
            }

            numClientesAtendidos++;
            System.out.print("nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            nombresClientes.add(nombreCliente);

            double totalCompraCliente = 0.0;
            char continuarComprando;

            do {
                System.out.println("\nCatálogo de Productos:");
                for (int p = 0; p < nombresProductos.length; p++) {
                    System.out.println((p + 1) + ". " + nombresProductos[p] + " - $" + preciosProductos[p]);
                }

                int codigoProducto = 0;
                while (true) {
                    System.out.print("Ingrese el código del producto (1-4): ");
                    if (scanner.hasNextInt()) {
                        codigoProducto = scanner.nextInt();
                        if (codigoProducto >= 1 && codigoProducto <= 4) {
                            break;
                        }
                    } else {
                        scanner.next();
                    }
                    System.out.println("Código inválido");
                }

                int cantidadDeseada = 0;
                while (true) {
                    System.out.print("Ingrese la cantidad que desea comprar: ");
                    if (scanner.hasNextInt()) {
                        cantidadDeseada = scanner.nextInt();
                        if (cantidadDeseada > 0) {
                            break;
                        }
                    } else {
                        scanner.next();
                    }
                    System.out.println("Cantidad inválida");
                }
                scanner.nextLine(); 

                double precioUnitario = 0.0;
                switch (codigoProducto) {
                    case 1:
                        precioUnitario = preciosProductos[0];
                        break;
                    case 2:
                        precioUnitario = preciosProductos[1];
                        break;
                    case 3:
                        precioUnitario = preciosProductos[2];
                        break;
                    case 4:
                        precioUnitario = preciosProductos[3];
                        break;
                }

                double importeProducto = precioUnitario * cantidadDeseada;
                totalCompraCliente += importeProducto;

                unidadesVendidasPorProducto[codigoProducto - 1] += cantidadDeseada;

                System.out.print("¿Desea agregar otro producto? (s/n): ");
                continuarComprando = scanner.next().toLowerCase().charAt(0);
                scanner.nextLine(); 
            } while (continuarComprando == 's');

            totalesPorCliente.add(totalCompraCliente);
            totalDineroRecaudado += totalCompraCliente;
        }

    
        System.out.println("\n--- RESUMEN Final ---");
        System.out.println("Cantidad total de clientes atendidos: " + numClientesAtendidos);
        
        System.out.println("\nLista de nombres de los clientes:");
        if (nombresClientes.isEmpty()) {
            System.out.println(" - No se registraron clientes en la jornada.");
        } else {
            for (int i = 0; i < nombresClientes.size(); i++) {
                System.out.println(" - " + nombresClientes.get(i));
            }
        }

        System.out.println("\nCantidad total vendida y total en dinero de cada tipo de producto:");
        for (int i = 0; i < nombresProductos.length; i++) {
            double importeBrutoProducto = preciosProductos[i] * unidadesVendidasPorProducto[i];
            System.out.println(" - " + nombresProductos[i] + ": " + unidadesVendidasPorProducto[i] + " unidades (Total vendido: $" + String.format("%.2f", importeBrutoProducto) + ")");
        }

        System.out.println("\nTotal vendido: $" + String.format("%.2f", totalDineroRecaudado));

        double promedioVenta = numClientesAtendidos > 0 ? (totalDineroRecaudado / numClientesAtendidos) : 0.0;
        System.out.println("Promedio de venta por cliente: $" + String.format("%.2f", promedioVenta));

        scanner.close();
    }
}