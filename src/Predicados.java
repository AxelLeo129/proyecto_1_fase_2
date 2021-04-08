package src;

import java.util.HashMap;

public class Predicados {

	private HashMap<String, String> variables;
    
	public Predicados(Definir def) {
		variables = def.getVariables();
	}
	
	/**
	 * @return boolean
	 * Se activa cuando se le envia una linea de codigo completa y solamente realiza la validacion
	 */
	public boolean condicional(String condicion) {
		int var1 = 0, var2 = 0;
		condicion = condicion.replace("(", "");
		condicion = condicion.replace(")", "");
		String[] temp =  condicion.trim().split(" ");
		
		try {
			var1 = Integer.parseInt(temp[1]);
		}catch(NumberFormatException e){
			var1 = Integer.parseInt(variables.get(temp[1]));
		}
		
		
		try {
			var2 = Integer.parseInt(temp[2]);
		}catch(NumberFormatException e){
			var2 = Integer.parseInt(variables.get(temp[2]));
		}
	
		switch(temp[0]) {
		case ">":
			if(var1 > var2) {
				return true;
			}else return false;
		case "<":
			if(var1 < var2) {
				return true;
			}else return false;
		case "=":
			if(var1 == var2) {
				return true;
			}else return false;
		default:
			return false;
		}
	}
	
}
