import java.util.Scanner;

public class masa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            
            System.out.print("Ingresa su peso: ");
            double peso = scanner.nextDouble();

            System.out.print("Ingresa su estatura: ");
            double estatura = scanner.nextDouble();

            // Calcular el IMC
            double imc = peso / (estatura * estatura);
            System.out.printf("\nTu IMC es: %.2f\n", imc);

            // 2. Sentencias if - else (Estructura de control 2)
            if (imc < 18.5) {
                System.out.println("Tipo de peso: Bajo");
                System.out.println("Coma mas");
                System.out.println("Consulta a un nutriólogo para un plan de aumento");
            } else if (imc >= 18.5 && imc < 25.0) {
                System.out.println("Tipo de peso: Normal");
                System.out.println("Sigue asi.");
                System.out.println("Va muy bien");
            } else if (imc >= 25.0 && imc < 30.0) {
                System.out.println("Tipo de peso: Sobrepeso");
                System.out.println("Reduce el consumo de azúcares y comida rápida.");
                System.out.println("empieza a hacer ejercicio de cardio y tranquilo diario.");
            } else if (imc >= 30.0 && imc < 35.0) {
                System.out.println("Clasificación: Obesidad tipo 1");
                System.out.println("Inicia a comer mejor");
                System.out.println("Diseña un plan de alimentacion y ejercicio supervisado por un profesional.");
            } else if (imc >= 35.0 && imc < 40.0) {
                System.out.println("Clasificación: Obesidad tipo 2");
                System.out.println("Acude con un especialista en nutrición y medicina.");
                System.out.println("Evita ejercicios de alto impacto sin supervisión para cuidar tus articulaciones.");
            } else {
                System.out.println("Clasificación: Obesidad tipo 3");
                System.out.println("Busca valoración médica integral prioritaria.");
                System.out.println("Evalúa un tratamiento multidisciplinario");
            }

            System.out.print("\n¿Deseas calcular otro IMC? 1=Sí  0 = No: ");
            int opcion = scanner.nextInt();
            if (opcion == 0) {
                continuar = false;
            }
        } 

        scanner.close();
    } 
} 