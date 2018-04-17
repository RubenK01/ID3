import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		
		String juego = "archivos/juego.txt",
				atriutos = "archivos/AtributosJuego.txt",
				resultados = "archivos/ResultadoTabla_";
		
		Utils utils = new Utils();
		try {
			List<List<String>> ejemplos = utils.leerEjemplos(juego);
			List<String> columnas = utils.leerColumnas(atriutos);
			
			HashMap<String, HashMap<String, objID3> > mapaGeneral = new HashMap<String, HashMap<String, objID3> >();
			
			for(List<String> ej : ejemplos) {
				if(ej.get(0) != "si" || ej.get(0) != "no") {
					HashMap<String, objID3> map = new HashMap<String, objID3>();
					
					for(int i = 0; i< ej.size(); i++) {
						String ejemplo = ej.get(i);
						objID3 obj;
						if(map.containsKey(ejemplo)) {
							obj = map.get(ejemplo);
						}
						else {
							obj = new objID3();
						}
						if( "si".equals(ejemplos.get(ejemplos.size()-1).get(i))) {
							obj.addP();
						}
						else if( "no".equals(ejemplos.get(ejemplos.size()-1).get(i))) {
							obj.addN();
						}
						
						map.put(ejemplo, obj);
											
					}
					//añadir el hasmap al general
					mapaGeneral.put(columnas.get( ejemplos.indexOf(ej) ), map);
				}
			}
			
			mapaGeneral.remove("Jugar");
			
			HashMap<String,objID3> colMenorInfo = new HashMap<String,objID3>();
			
			double menorInf = 0, aux;
			for(int i = 0; i < mapaGeneral.size(); i++) {
				if(i == 0 ) {
					colMenorInfo = mapaGeneral.get(columnas.get(i));
					menorInf = utils.merito(colMenorInfo);
				}
				else {
					aux =  utils.merito(mapaGeneral.get(columnas.get(i)));
					if(aux < menorInf) {
						colMenorInfo = mapaGeneral.get(columnas.get(i));
						menorInf = aux;
					}
				}
			}
			
			String keyMenorMerito = "" ;
			for(Entry<String, HashMap<String, objID3>> atr : mapaGeneral.entrySet()) {
				if(atr.getValue() == colMenorInfo) {
					keyMenorMerito = atr.getKey();
				}
			}
			
			//imprimir al archivo
			utils.imprimeResutados(colMenorInfo, juego, resultados + keyMenorMerito + "_");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
