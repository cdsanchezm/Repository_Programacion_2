package daos;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public Main ( ) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		String neigneighborhood = "";
		String pos;
		long microchip = 0;
		String species = "";
		String File = "pets-citizens.csv";
		Manager mang = new Manager (File);
		
		System.out.println("hola");
		try {
			System.out.println(mang.uploadFile().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(mang.assignID().toString());
//		System.out.println("microchip");
//		microchip = sc.nextLong();
//		System.out.println(mang.findByMicrochip(microchip).toString());
		
		
		System.out.println("n");
		n = sc.nextInt();
		System.out.println("pos");
		pos = sc.next();
		System.out.println("neigh");
		neigneighborhood = sc.next();
		System.out.println(mang.findBypotentDangerousInNeighborhood(n, pos, neigneighborhood).toString());
		
		
	}
	public static void main(String[] args) {
		
		Main main = new Main ();
		
	
		
	}

}
