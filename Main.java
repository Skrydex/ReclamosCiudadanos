package reclamosmunicipales;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        GestorReclamos gestor = new GestorReclamos();

        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== SISTEMA DE GESTION DE RECLAMOS =====");
            System.out.println("1. Registrar nuevo reclamo");
            System.out.println("2. Modificar descripcion de un reclamo");
            System.out.println("3. Eliminar reclamo");
            System.out.println("4. Actualizar estado de un reclamo");
            System.out.println("5. Mostrar todos los reclamos");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    System.out.print("Codigo: ");
                    int codigo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nombre del ciudadano: ");
                    String nombre = sc.nextLine();
                    System.out.print("RUT del ciudadano: ");
                    String rut = sc.nextLine();
                    System.out.print("Tipo de reclamo: ");
                    String tipo = sc.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = sc.nextLine();
                    System.out.print("Prioridad (1 alta, 2 media, 3 baja): ");
                    int prioridad = sc.nextInt();
                    System.out.print("Dias de plazo para responder: ");
                    int diasPlazo = sc.nextInt();
                    sc.nextLine();

                case 2:

                case 3:
                    
                case 4:
                    
                case 5:
                    
                case 0:
