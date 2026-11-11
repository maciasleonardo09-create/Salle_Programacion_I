import java.util.Scanner;

public class Vivero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productos = {"Cactus", "Suculenta", "Maceta", "Tierra", "Fertilizante"};
        double[] precios = {45.5, 35.5, 60.0, 80.0, 95.0};
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        double[][] matrizMontos = new double[6][5];
        
        for (int i = 0; i < dias.length; i++) {
            System.out.println("\n--- Día: " + dias[i] + " ---");
            for (int j = 0; j < productos.length; j++) {
                System.out.print("Ingrese unidades vendidas de " + productos[j] + " ($" + precios[j] + " c/u): ");
                int unidades = scanner.nextInt(); 
                matrizMontos[i][j] = unidades * precios[j];
            }
        }
        System.out.printf("%-12s", "Día");
        for (String prod : productos) {
            System.out.printf("%-15s", prod);
        }
        System.out.println();
        for (int i = 0; i < dias.length; i++) {
            System.out.printf("%-12s", dias[i]);
            for (int j = 0; j < productos.length; j++) {
                System.out.printf("$%-14.2f", matrizMontos[i][j]);
            }
            System.out.println();
        }
        scanner.close();
    }
}