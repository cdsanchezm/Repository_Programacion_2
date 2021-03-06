package daos;


import java.io.BufferedReader ;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	    
	    /**
		 * this method is responsible for uploading the data to the system
		 * <b>pre</b> the variable is instantiated with the file address<br>
		 * <b>post</b> the data is loaded and the message appears <br>
		 
		 * @return a file object with the indicated properties
		 */
	              
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

		/**
		 * this method is in charge of assigning the id to the pets
		 * <b>pre</b>the arraylist should already contain the file information<br>
		 * <b>post</b> IDs are assigned to each pet <br> 
		 * @return a message on the console saying: "El proceso de asignaci�n de ids ha finalizado"
		 */
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
				}else if (petArr.get(i).getSize().equals("peque�o".toUpperCase())) {
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
	    		for (int j = 0; j < i; j++) {
	    			try {
	    				if (petArr.get(j).getId().equals(id)) {
							mc = mc +1;
							 throw new Exception("IdentifierExistsException");
						}
						
					} catch (Exception IdentifierExistsException) {
						
						id = mc + " - " + species+sex+size+PD+ " - " + petArr.get(i).getNeighborhood();
			    		petArr.get(i).setId(id);
					}
					
				}
				}
	    		
			return "El proceso de asignaci�n de ids ha finalizado";
	    }
	    
	    /**
		 * this method is responsible for searching the pet's information by the microchip 
		 * <b>pre</b> the arraylist should already contain the file information<br>
		 * <b>post</b> pet information is displayed<br>
		 * @param microchip:  pet identification
		 
		 * @return a pet type object with the information
		 */
	    public Pet findByMicrochip(long microchip) {
	    	Pet encontrado = null;
	    	
	    	for (int i = 0; i < petArr.size(); i++) {
	    		
				if (petArr.get(i).getMicrochip() == microchip) {
					
				return petArr.get(i);
					
				}
			}
	    	
	    	return encontrado;
	    }
	    
	    /**
		 * This method is responsible for returning the number of pets depending on the type that is chosen
		 * <b>pre</b> the arraylist should already contain the file information<br>
		 * <b>post</b> the total of the selected species is displayed <br>
		 * @param species: type of pet species
		 
		 * @return an integer with the total
		 */
	    public String countBySpecies (String species) {
	    	int contador = 0;
	    	String result = "";
	    	
	    for (int i = 0; i < petArr.size(); i++) {
	    	if (petArr.get(i).getSpecies().equals(species.toUpperCase())) {
				contador++;
			}
			
	    	result = "El n�mero de animales de la especie"+ " " + species.toUpperCase() + " "+ "es"+ " " + contador;
		}
	    	
	    	
	    	return result;
	    }
	    
	    /**
		 * This method will display the list of pets depending on the danger of the pet
		 * <b>pre</b> the arraylist should already contain the file information <br>
		 * <b>post</b> the list of pets the user wants to see <br>
		 * @param n: the number of animals to be displayed
		 * @param pos: the position in which they will be displayed
		 * @param neighborhood: the neighborhood where the pet is
		 
		 * @return an arraylist with the information
		 */
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
	    
	    /**
		 * This method is responsible for returning a list with the requested ids
		 * <b>pre</b> the arraylist should already contain the file information <br>
		 * <b>post</b> the information of the ids is displayed <br>
		 * @param sex: the sex of the pet
		 * @param species: type of pet species
		 * @param size: the size of the pet
		 * @param potentDangerous: dangerousness of the pet
		 
		 * @return a list with the requested ids
		 */
	     public List findByMultipleFields (String sex, String species,String size, String potentDangerous) {

	   	
	    List pd = new ArrayList<>();
	    String pc = "";
	    boolean dangerous = false;
	    
	    if (potentDangerous.equals("si".toUpperCase())) {
			dangerous = true;
		}else if (potentDangerous.equals("no".toUpperCase())) {
			dangerous = false;
		}
      
	      for (int i = 0; i < petArr.size(); i++) {
	    	 
	    	  
	     if (petArr.get(i).getSex().equals(sex.toUpperCase()) && petArr.get(i).getSpecies().equals(species.toUpperCase()) && petArr.get(i).getSize().equals(size.toUpperCase()) && petArr.get(i).isPotentDangerous() == dangerous){
				
	    		pd.add(petArr.get(i).getId());
			}
		}
	    	return pd;
	    }

}
 	


