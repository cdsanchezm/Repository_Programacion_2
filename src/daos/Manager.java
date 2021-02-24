package daos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Pet;

public  class Manager {
	
	   private ArrayList<Pet> petArr;
	    private String File;
	    public final String Separator = ";";
	    private Pet objpet;
	    private int n;
	    private String id = "NO-ID";

	    public Manager (String File) {

	       this.File = File;
	       petArr = new ArrayList<Pet>();
	       n = 2;
	    }
	              
	    public String uploadFile() throws IOException {
	        BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(File));
	            String line = br.readLine();
	            while (null != line){
	                String[] fields = line.split(Separator);
	                //System.out.println(Arrays.toString(fields));
	                try {
	                    String microship = fields[0];
	                    String species = fields [1];
	                    String sex = fields[2];
	                    String size = fields [3];
	                    String potentDangerousSt = fields[4];
	                    String neighborhood = fields [5];
	                    boolean potentDangerous = false;
	                    if (potentDangerousSt.equals("SI")){
	                        potentDangerous = true;
	                    }
	                  Pet addpet = new Pet(id,Long.parseLong(microship),species,sex,size,potentDangerous, neighborhood);
	                    petArr.add(addpet); 
	                    

	                }catch (Exception e){

	                }
	                line = br.readLine();
	            }
	        }catch (Exception e){
	            e.printStackTrace();
	            return null;
	        } finally {
	            if (null!=br) {
	                br.close();
	            }
	        }
	        return "El proceso de carga del archivo ha finalizado";
	    }
	    
	    
	    public Pet findByMicrochip(long microchip) {
	    	Pet encontrado = null;
	    	
	    	for (int i = 0; i < petArr.size(); i++) {
	    		
				if (petArr.get(i).getMicrochip() == microchip) {
					
				return petArr.get(i);
					
				}
			}
	    	
	    	return encontrado;
	    }

}
 	


