package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Lector {
	
	//Metodo para leer documento y enviar lineas de codigo
	public ArrayList<String> leerDocumento() {
		ArrayList<String> codigo = new ArrayList<String>();
		String texto = new String();
		try {
			FileReader fr = new FileReader("./assets/pruebaIf.txt");
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
		return codigo;
	}

}
