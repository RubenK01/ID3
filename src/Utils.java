import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Utils {
	ArrayList<List<String>> leerEjemplos(String archivo) throws FileNotFoundException, IOException {
		ArrayList<List<String>> lista = new ArrayList<List<String>>();
		
		  String cadena;
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      List<String> listaLinea = new ArrayList<String>();
	      while((cadena = b.readLine())!=null && (cadena != "")) {
	    	  String[] linea = cadena.split(",");
	    	  listaLinea = Arrays.asList(linea);
	    	  lista.add(listaLinea);
	          System.out.println(cadena);
	      }
	      b.close();
	      
	      ArrayList<List<String>> result = new ArrayList<List<String>>();
	      
	      for(int i = 0; i < lista.get(i).size(); i++) {
	    	  List<String> columna = new ArrayList<String>();
	    	  for(int j = 0; j < lista.size()-1; j++) {
	    		  columna.add(lista.get(j).get(i));
	    	  }
	    	  result.add(columna);
	      }
		
		return result;
	}
	
	List<String> leerColumnas(String archivo) throws FileNotFoundException, IOException {
		
		  String cadena;
	      FileReader f = new FileReader(archivo);
	      BufferedReader b = new BufferedReader(f);
	      List<String> listaLinea = new ArrayList<String>();
	      if((cadena = b.readLine())!=null && (cadena != "")) {
	    	  String[] linea = cadena.split(",");
	    	  listaLinea = Arrays.asList(linea);
	      }
	      b.close();
		
		return listaLinea;
	}
	
	double infor(double p, double n) {
		double result = 0;
		
		
		//comprueba si es cero!!??
		if(p != 0)
			result = -p * logBaseDos(p);
		
		if(n != 0)
			result = -n * logBaseDos(n);
		
		return result;
	}
	
	double merito(HashMap<String,objID3> columna) {
		double result = 0;
		
		double N = 0;
		
		for(Entry<String, objID3> c : columna.entrySet()) {
			N += c.getValue().getN() + c.getValue().getP();
		}		
		
		for(Entry<String, objID3> c : columna.entrySet()) {
			double cuantos = c.getValue().getP()+c.getValue().getN();
			result += (double)( ( cuantos )/N ) * infor( (double)c.getValue().getP()/cuantos, (double)c.getValue().getN()/cuantos );
		}
		
		
		return result;
	}
	
	double logBaseDos(double num) {
		double result;
		
		result = Math.log(num) / Math.log(2);
		
		return result;
	}
	
	void imprimeResutados(HashMap<String,objID3> colMenorInfo, String juego, String resultados) throws IOException {
		String cadena;
	    FileReader f;
		try {
			
			for(Entry<String, objID3> c : colMenorInfo.entrySet()) {
				f = new FileReader(juego);
				BufferedReader b = new BufferedReader(f);
				BufferedWriter bw = new BufferedWriter(new FileWriter(resultados + c.getKey() + ".txt"));

				if(c.getValue().getP() == 0) {
					bw.write("CUANDO EL ATRIBUTO SEA IGUAL A " + c.getKey() + " NUNCA SE JUEGA" + '\n' + '\n');
				}
				else if(c.getValue().getN() == 0) {
					bw.write("CUANDO EL ATRIBUTO SEA IGUAL A " + c.getKey() + " SIEMPRE SE JUEGA"+ '\n' + '\n');
				}
				
				 while((cadena = b.readLine())!=null && (cadena != "")) {

			    		String cadenaBuscada = c.getKey() + ",";
			    	  if(cadena.contains(cadenaBuscada)) {
			    		  cadena = cadena.replace(cadenaBuscada, "");
			    		  
			    		  bw.write(cadena + '\n');
			    	  }
			    	  
			    }
				
			   
			    bw.close();
			    b.close();
			}
			  
		    
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
