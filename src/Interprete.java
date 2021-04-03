package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Puede servir luego
//Por si quieren ver que son los predicadores:  http://web.cs.ucla.edu/~rosen/161/notes/lisp1.html#:~:text=Everything%20in%20Lisp%20is%20either,list)%2C%20ATOM%20returns%20NIL.

public class Interprete {
	
	private ArrayList<String> codigo;
	
	
	public Interprete() {
		codigo = new ArrayList<String>();
		leerDocumento();
	}
    
	//Prueba para lectura de datos y creacion del interprete
    
	public void leerDocumento() {
		String texto = new String();
		try {
			FileReader fr = new FileReader("./assets/prueba.txt");
			BufferedReader entrada = new BufferedReader(fr); 
			String s;
			
			while((s = entrada.readLine()) != null) {
				//String[] temp = s.split(", ");
				codigo.add(s);
			}
			
		}
		catch(java.io.FileNotFoundException fnfex) {
			System.out.println("Archivo no encontrado: " + fnfex);}
		catch(java.io.IOException ioex) {}
	}
	
	public void vistaPrueba() {
		for(String linea: codigo) {
			System.out.println(linea);
		}
	}
	
	public void proceso() {
		String iterated = "";
		String stringFound = "";
		int stateString = 0;
		for(String linea: codigo) {
			for(char x: linea.toCharArray()) {
				iterated += x;	
				
				if(iterated.equals("\t") || iterated.equals(" ")) {
					iterated = "";
				} 
				//System.out.println(iterated);
				if(iterated.equals("princ")) {
					System.out.println("Print encontrado");
					iterated = "";
				}
				if(stateString == 1) {
					stringFound += x;
				}
				if(x == '\"') {
					if(stateString == 0) {
						stateString = 1;
					}
					else{
						System.out.println("String encontrado: ");
						System.out.println(stringFound);
						stringFound = "";
						stateString = 0;
					}
				}
			}
		}
	}
	
}
