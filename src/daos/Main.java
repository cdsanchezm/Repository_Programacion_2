package daos;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public Main ( ) {
		Scanner sc = new Scanner(System.in);
		long microchip = 0;
		String File = "pets-citizens.csv";
		Manager mang = new Manager (File);
		
		System.out.println("hola");
		try {
			System.out.println(mang.uploadFile().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("microchip");
		microchip = sc.nextLong();
		System.out.println(mang.findByMicrochip(microchip).toString());
		
		
	}
	public static void main(String[] args) {
		
		Main main = new Main ();
		
	
		
	}

}
