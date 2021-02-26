package daos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public Main ( ) {
		Scanner sc = new Scanner(System.in);

		boolean salir = false;
	    int opcion;
		int number = 0;
		String neigneighborhood = "";
		String position;
		String sex;
		String size;
		String potentdangerous;
		long microchip = 0;
		String species;
		String File = "pets-citizens.csv";
		Manager mang = new Manager (File);
		
		System.out.println("Bienvenido al programa de ciudadanos de 4 patas ");
		try {
			System.out.println(mang.uploadFile().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	        
	       while(!salir){
	            
	           System.out.println("1. Para la asignacion de ID");
	           System.out.println("2. Para buscar por el microchip");
	           System.out.println("3. Para saber la cantidad de especies");
	           System.out.println("4. Para buscar por la peligrosidad de la mascota");
	           System.out.println("5. Para buscar ID'S diferentes ");
	           System.out.println("6. Para salir del sistema ");
	            
	           try {
	        	   
	                System.out.println("Escribe una de las opciones");
	                opcion = sc.nextInt();
	 
	                switch (opcion) {
	                    case 1:
	                    System.out.println("Has seleccionado la opcion 1");
	                    System.out.println(" ");
	                    System.out.println(mang.assignID().toString());
	                        break;
	                    case 2:
	                        System.out.println("Has seleccionado la opcion 2");
	                        System.out.println(" ");
	                        System.out.println("Por favor ingresa el microchip de la mascota que deseas averiguar.");
	                        microchip = sc.nextLong();
	                        System.out.println(mang.findByMicrochip(microchip).toString());
	                        break;
	                    case 3:
	                        System.out.println("Has seleccionado la opcion 3");
	                        System.out.println(" ");
	                        System.out.println("Por favor ingresa el tipo de especie que quieres averiguar.");
	                        species = sc.next();
	                        System.out.println(mang.countBySpecies(species).toString());
	                        break;
	                    case 4:
	                    	System.out.println("Has seleccionado la opcion 4");
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingrese el numero de mascotas que quiere saber.");
	                    	number = sc.nextInt();
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingrese la palabra TOP si quiere ver del primero al ultimo o ingrese LAST si quiere ver del ultimo al primero.");
	                        position = sc.next();
	                        System.out.println(" ");
	                        System.out.println("Por favor ingrese el barrio que quiere averiguar.");
	                        neigneighborhood = sc.next();
	                        System.out.println(mang.findBypotentDangerousInNeighborhood(number, position, neigneighborhood).toString());
	                        break;
	                    case 5:
	                    	System.out.println("Has seleccionado la opcion 5");
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingresa el tipo de mascota.");
	                    	species = sc.next();
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingrese el genero de su mascota.");
	                    	sex = sc.next();
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingrese el tamaño de su mascota.");
	                    	size = sc.next();
	                    	System.out.println(" ");
	                    	System.out.println("Por favor ingrese la peligrosidad de su mascota.");
	                    	potentdangerous = sc.next();
	                    	System.out.println(mang.findByMultipleFields(sex, species, size, potentdangerous).toString());
	                        break;
	                    case 6:
	                    	System.out.println("Hasta la proxima :3 .");
	                    	salir = true;
	                    	break;
	                    default:
	                        System.out.println("Solo números entre 1 y 6");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Debes insertar un número");
	                sc.next();
	            }
	        }
	            
	       }
		

	public static void main(String[] args) {
		
		Main main = new Main ();
		
	
		
	}

}
