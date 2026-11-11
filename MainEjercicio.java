import java.util.Arrays;
import java.util.Scanner; 

public class MainEjercicio {
    public static void main(String[] args) {
        
        int[] valores = new int[10];
        
        Scanner scanner = new Scanner(System.in);

        for (int x = 0; x < valores.length; x++) {
            System.out.println("Digite el valor número " + x + ":");
            valores[x] = scanner.nextInt(); 
        }
        System.out.println("\nValores guardados en el arreglo:");
        for (int k = 0; k < valores.length; k++) {
            System.out.println("Posición [" + k + "] = " + valores[k]);
        }

        Arrays.sort(valores);
        System.out.println("\nEl número menor es: " + valores[0]);
        System.out.println("El número mayor es: " + valores[9]);
    }
}