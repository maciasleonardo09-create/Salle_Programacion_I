import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num;
        double CxL, km_recorrido, lts, RE, RR, porcentual, CTGas;
        String tipo;

        System.out.print("Numero de serie: ");
        num = scanner.nextInt();

        System.out.print("KM Recorridos: ");
        km_recorrido = scanner.nextDouble();

        System.out.print("Litros consumidos: ");
        lts = scanner.nextDouble();

        System.out.print("Costo x litro: ");
        CxL = scanner.nextDouble();

        System.out.print("Rendimiento esperado: ");
        RE = scanner.nextDouble();

    
        RR = km_recorrido / lts;
        CTGas = CxL * lts;
        porcentual = (RR / RE) * 100;

        if (porcentual >= 100) {
            tipo = "Eficiente";
        } else if (porcentual >= 90) {
            tipo = "Aceptable";
        } else {
            tipo = "Bajo";
        }

        System.out.println("\n--- RESULTADOS ---");
        System.out.println("No. de serie: " + num);
        System.out.printf("Rendimiento esperado: %.2f km/l\n", RE);
        System.out.printf("Rendimiento Real: %.2f km/l\n", RR);
        System.out.printf("Porcentaje de rendimiento: %.2f%%\n", porcentual);
        System.out.println("Tipo de rendimiento: " + tipo);
        System.out.printf("Costo total de la gas : $%.2f\n", CTGas);

        scanner.close(); 
    }
}