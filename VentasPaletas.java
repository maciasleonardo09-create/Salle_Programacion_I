import java.util.Scanner;

public class VentasPaletas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int totalDias = 2;
        String[] sabores = {"Fresa", "Limón", "Coco"};
        double[] precios = {15.0, 12.0, 18.0};
        
        double dineroTotalGeneral = 0;
        int paletasTotalesGeneral = 0;
        
        for (int dia = 1; dia <= totalDias; dia++) {
            System.out.println("\n--- Registro de ventas para el Día " + dia + " ---");
            
            double dineroDia = 0;
            int paletasDia = 0;
            
            for (int i = 0; i < sabores.length; i++) {
                System.out.print("¿Cuántas paletas de " + sabores[i] + " ($" + precios[i] + ") se vendieron?: ");
                int cantidad = scanner.nextInt();
                
                paletasDia += cantidad;
                dineroDia += cantidad * precios[i];
            }
            
            System.out.println("--> Total de paletas vendidas en el Día " + dia + ": " + paletasDia);
            System.out.println("--> Dinero recaudado en el Día " + dia + ": $" + dineroDia);
            
            paletasTotalesGeneral += paletasDia;
            dineroTotalGeneral += dineroDia;
        }
        
        System.out.println("          RESUMEN GENERAL (2 DÍAS)      ");
        System.out.println("Total de paletas vendidas: " + paletasTotalesGeneral);
        System.out.println("Dinero total recaudado: $" + dineroTotalGeneral);
        
        scanner.close();
    }
}