import java.util.Random;
import java.util.Scanner;

public class preexam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== CARRERA ARITMÉTICA ===");

        System.out.print("Ingresa el dato de entrada inicial: ");
        double datoEntrada = scanner.nextDouble();

        System.out.println("Dato de entrada inicial ingresado: " + datoEntrada);
        
        System.out.print("¿Mes en que naciste? (1-12): ");
        int mesNacimiento = scanner.nextInt();
        
        double salidaActual = datoEntrada;

        System.out.println("\n--- ETAPA 1: Promedios ---");
        double sumaAleatorios = 0;
        System.out.println("Ingresa 5 números para calcular su promedio:");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Ingresa el número " + i + ": ");
            int numIngresado = scanner.nextInt();
            sumaAleatorios += numIngresado;
        }
        double promedio = sumaAleatorios / 5.0;
        System.out.println("Promedio de los 5 números: " + promedio);
        
        salidaActual = salidaActual + promedio;
        System.out.println("Salida etapa 1: " + salidaActual);

        System.out.println("\n--- ETAPA 2: Residuo ---");
        double residuo = salidaActual % 7;
        System.out.println("Residuo (Salida anterior % 7): " + residuo);
        
        salidaActual = salidaActual + residuo;
        System.out.println("Salida etapa 2: " + salidaActual);

        System.out.println("\n--- ETAPA 3: Porcentaje ---");
        double porcentaje = 0.12; // 12%
        double incrementoPorcentaje = salidaActual * porcentaje;
        
        salidaActual = salidaActual + incrementoPorcentaje;
        System.out.println("Salida etapa 3: " + salidaActual);

        System.out.println("\n--- ETAPA 4: Suma N valores enteros ---");
        System.out.print("¿Cuántos valores enteros (N) deseas sumar?: ");
        int n = scanner.nextInt();
        int sumaN = 0;
        
        for (int i = 1; i <= n; i++) {
            System.out.print("Ingresa el valor entero " + i + ": ");
            sumaN += scanner.nextInt();
        }
        
        salidaActual = salidaActual + sumaN;
        System.out.println("Salida etapa 4: " + salidaActual);

        System.out.println("\n--- ETAPA 5: Elige un Divisor (1-9) ---");
        int divisor;
        do {
            System.out.print("Ingresa un divisor entre 1 y 9: ");
            divisor = scanner.nextInt();
            if (divisor < 1 || divisor > 9) {
                System.out.println("Divisor inválido. Debe estar entre 1 y 9.");
            }
        } while (divisor < 1 || divisor > 9);

        salidaActual = salidaActual / divisor;
        System.out.println("Salida etapa 5: " + salidaActual);

        System.out.println("RESULTADO FINAL SEGÚN TU MES DE NACIMIENTO");
        System.out.println("Mes ingresado: " + mesNacimiento);
        
        if (mesNacimiento < 1 || mesNacimiento > 12) {
            System.out.println("Mes de nacimiento no válido (debe ser entre 1 y 12).");
        } else {
            switch (mesNacimiento % 2) {
                case 1: 
                    System.out.printf("Mes Non: Muestra los 4 decimales de la salida: %.4f\n", salidaActual);
                    break;
                case 0: 
                    System.out.println("Mes Par: Muestra el valor entero de la salida: " + (int) salidaActual);
                    break;
            }
        }

        scanner.close();
    }
}