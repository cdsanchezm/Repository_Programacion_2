package daos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.Pet;

public  class Manager {
	
	   private ArrayList<Pet> petArr;
	   ArrayList<Pet>petdangerous;
	    private String File;
	    public final String Separator = ";";
	    private Pet objpet;
	    private int n;
	    private String id = "NO-ID";

	    public Manager (String File) {

	       this.File = File;
	       petArr = new ArrayList<Pet>();
	       petdangerous = new ArrayList<Pet>();
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
	                  Pet addpet = new Pet(id,Long.parseLong(microship),species,sex,size,potentDangerous,neighborhood);
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
	    
	    public String assignID () {
	    	String mc = "";
	    	String species = "";
	    	String sex = "";
	    	String size = "";
	    	String PD = "";
	    	String id = "";
	    	for (int i = 0; i < petArr.size(); i++) {
	    		mc = String.valueOf(petArr.get(i).getMicrochip());
	    		mc = mc.substring(mc.length()-3,mc.length());
	    		if (petArr.get(i).getSpecies().equals("canino".toUpperCase())) {
					species = "C";
				}else if (petArr.get(i).getSpecies().equals("felino".toUpperCase())) {
					species = "F";
				}
	    		
	    		if (petArr.get(i).getSex().equals("macho".toUpperCase())) {
					sex = "M";
				}else if (petArr.get(i).getSex().equals("hembra".toUpperCase())) {
					sex = "H";
				}
	    		
	    		if (petArr.get(i).getSize().equals("miniatura".toUpperCase())) {
					size = "MI";
				}else if (petArr.get(i).getSize().equals("pequeño".toUpperCase())) {
					size = "P";
				}else if (petArr.get(i).getSize().equals("mediano".toUpperCase())) {
					size = "M";
				}else if (petArr.get(i).getSize().equals("grande".toUpperCase())) {
					size = "G";
				}
	    		
	    		if (petArr.get(i).isPotentDangerous()) {
					PD = "T";
				}else {
					PD = "F";
				}
	    		id = mc + "-" + species+sex+size+PD+ "-" + petArr.get(i).getNeighborhood();
	    		petArr.get(i).setId(id);
				}
	    		
			return "El proceso de asignación de ids ha finalizado";
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
	    
	    public String countBySpecies (String species) {
	    	int contador = 0;
	    	String result = "";
	    	
	    for (int i = 0; i < petArr.size(); i++) {
	    	if (petArr.get(i).getSpecies().equals(species.toUpperCase())) {
				contador++;
			}
			
	    	result = "El número de animales de la especie"+ " " + species.toUpperCase() + " "+ "es"+ " " + contador;
		}
	    	
	    	
	    	return result;
	    }
	    
	    public ArrayList<Pet> findBypotentDangerousInNeighborhood ( int n, String pos, String neighborhood) {
	    	
	    	Pet encontrado = null;
	    	int cont = 0;
	    	if (pos.toUpperCase().equals("top".toUpperCase())) {
	    		for (int i = 0; i < petArr.size(); i++) {
	    			
		    		if (petArr.get(i).isPotentDangerous() && petArr.get(i).getNeighborhood().equals(neighborhood.toUpperCase())) {
		    			petdangerous.add(petArr.get(i));
						cont ++;
						
					}
		    		
		    		if (cont == n) {
		    			return petdangerous;
		    		}
			}
	    	
				
	    	} if (pos.toUpperCase().equals("last".toUpperCase())) {
				for (int i = petArr.size()-1; i >= 0; i --) {
					if (petArr.get(i).isPotentDangerous() && petArr.get(i).getNeighborhood().equals(neighborhood.toUpperCase())) {
						petdangerous.add(petArr.get(i));
						cont ++;	
					}
		    		if (cont == n) {
		    			return petdangerous;
		    		}
				}
			}
	    	
	    	return null;
	    }

}
 	


