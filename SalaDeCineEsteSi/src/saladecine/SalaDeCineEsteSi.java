package saladecine;

import java.util.*;

public class SalaDeCineEsteSi {

    //globales 
    public static final int filas = 10;
    public static final int asientos = 10;
    public static final int ninos = 2000;
    public static final int adultos = 5000;
    public static final int adultoMayor = 3500;
    public static final int bebe = 0;
    public static final char dispo = 'D';
    public static final char reserv = 'X';
    public static int totalPersonasReserva = 0;
    public static int espaciosDispo = filas * asientos;
    public static int total = 0;

    //main
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        char butacas[][] = new char[filas][asientos];

            
        // Iniciar el array y mostrarlo disponible
        for (int i = 0; i < filas; i++) {
            Arrays.fill(butacas[i], dispo);
        }

        //menu
        int opcion;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Informacion del cine");
            System.out.println("2. Comprar tiquetes");
            System.out.println("3. Eliminar todas las reservas");
            System.out.println("4. Mostrar datos para el dueno");
            System.out.println("5. Salir");
            System.out.print("Ingrese su opcion: \n");
            opcion = console.nextInt();

            switch (opcion) {
                case 1:
                    info(butacas);
                    break;
                case 2:
                    comprarTiquetes(console, butacas);
                    break;
                case 3:
                    eliminarReservas(butacas);
                    break;
                case 4:
                    datosParaElDueno();
                    break;
                case 5:
                    System.out.println("Gracias por utilizar nuestros servicios!");
                    break;
                default:
                    System.out.println("Opcion invalida. Intentelo nuevamente.");
                    break;
            }
        } while (opcion != 5);
    }

    //mostrar la info
    public static void info(char butacas[][]) {
        
        System.out.println("\nBievenidos a La Magia del Cine, un lugar en donde tu imaginacion no tiene limites!\n");
        System.out.println("Pelicula: ** Spider-Man: A traves del Spider-Verso. **");
        System.out.println("Precio de las entradas: \n Ninos: 2000. \n Adultos mayores: 3500. \n General: 5000.\n");
        System.out.println("--- Informacion de la sala ---\n Los asientos disponibles se encuentran marcados con una D, los ocupados con una X.\n");
        System.out.println("--Pantalla Aqui Arriba--");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < asientos; j++) {
                System.out.print(butacas[i][j] + " ");
            }
            System.out.println();
        }
    }

    //comprar tiquetes
    public static void comprarTiquetes(Scanner scanner, char butacas[][]) {
        System.out.print("Ingrese la cantidad de tiquetes que desea adquirir: ");
        int tiquetes = scanner.nextInt();
        int recaudado = 0;

        for (int i = 0; i < tiquetes; i++) {
            System.out.print("\nPersona #" + (i + 1));
            System.out.print("\nIngrese la fila: ");
            int fila = scanner.nextInt();

            System.out.print("Ingrese el numero de asiento: ");
            int asiento = scanner.nextInt();

            if (fila < 1 || fila > filas || asiento < 1 || asiento > asientos) {
                System.out.println("La fila o el numero de asiento es invalido. Intentelo nuevamente.");
                i--;
                continue;
            }

            if (butacas[fila - 1][asiento - 1] == dispo) {
                System.out.print("Ingrese la edad de la persona: ");
                int edad = scanner.nextInt();
                

                int costo = calcularCosto(edad);
                recaudado += costo;
                butacas[fila - 1][asiento - 1] = reserv;
                totalPersonasReserva++;
                espaciosDispo--;
                total += costo;
                System.out.println("Asiento reservado exitosamente. El costo es: $" + costo);
                //totalPersonasReserva += tiquetes;
                //espaciosDispo -= tiquetes;
            } else {
                System.out.println("El asiento seleccionado ya esta reservado. Intentelo nuevamente.");
                i--;
            }
        }

        System.out.println("El total a pagar es de: $" + recaudado);
    }

    //eliminar reservas
    public static void eliminarReservas(char butacas[][]) {
        for (int i = 0; i < filas; i++) {
            Arrays.fill(butacas[i], dispo);
        }
        totalPersonasReserva = 0;
        espaciosDispo = asientos * filas;
        total = 0;
        System.out.println("Todas las reservas han sido eliminadas.");
    }

    //calcular costo segun edad
    public static int calcularCosto(int edad) {
        if (edad<1){
            System.out.println("Los bebes entran gratis!");
            return bebe;
        } else if (edad < 18 && edad > 1) {
            return ninos;
        } else if (edad >= 65) {
            return adultoMayor;
        } else {
            return adultos;
        }
    }
    

    //mostrar datos para el dueno
    public static void datosParaElDueno() {
        System.out.println("--- Datos para el dueno ---");
        System.out.println("Total de personas con reserva: " + totalPersonasReserva);
        System.out.println("Total de espacios disponibles: " + espaciosDispo);
        System.out.println("Total recaudado en el dia: $" + total);
    }

}
