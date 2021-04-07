package src;

import java.util.Scanner;

public class Imprimir {

	Scanner sc = new Scanner(System.in);
	
	public Imprimir() {}
	
	public String resPrint(String lineaCodigo) {
		String iterated = "";
		int stateString = 0;
		for(char x: lineaCodigo.toCharArray()) {
			if(stateString == 1) {		
				iterated += x;
			}
			if(x == '\"') {			//Para seleccionar el string por caracter hasta que encuentre comillas
				if(stateString == 0) {
					stateString = 1;
				}
				else{
					iterated = iterated.substring(0, iterated.length() - 1).trim();
					stateString = 0;
				}
			}
		}
		return iterated;
	}
	
	public String[] resRead(String lineaCodigo) {
		lineaCodigo = lineaCodigo.replace("(", "");
		lineaCodigo = lineaCodigo.replace(")", "");
		String nameVariableSetQ;
		String nameValueVariableSetQ;
		
		String[] temp = lineaCodigo.trim().split(" ");
		nameVariableSetQ = temp[0];
		
		if(temp[1].equals("read")) {
			nameValueVariableSetQ = sc.nextLine();
		}else {
			nameValueVariableSetQ = temp[1];
		}
		
		return new String[] {nameVariableSetQ, nameValueVariableSetQ};
	
	}
}
