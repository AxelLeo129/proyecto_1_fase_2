package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Definir {

	 private HashMap<String, ArrayList<String>> funciones;
	 private HashMap<String, String> variables;
	 
	 public Definir() {
		 funciones = new HashMap <String, ArrayList<String>>();
		 variables = new HashMap <String, String>();
	 }
	 
	 
	 //======================================FUNCIONES=========================================
	 public void guardarFuncion(String nombre, ArrayList<String> instrucciones) {
		funciones.put(nombre, instrucciones);
	 }
	 
	 public ArrayList<String> mostrarFuncion(String keyValue) {
		 ArrayList<String> resultado = new ArrayList<String>();
		 resultado = null;
		 for(Map.Entry<String, ArrayList<String>> funcionIndividual: funciones.entrySet()) {	
			if(funcionIndividual.getKey().equals(keyValue)) {
				resultado = funcionIndividual.getValue();
			}
		}
		return resultado;
	}
	 
	//======================================VARIBLES=========================================
    
	 public void guardarVariable(String nombre, String value) {
		 variables.put(nombre, value);
	 }
	 
	 public String mostrarVariable(String keyValue) {
		 String valueToReturn = "";
		 for(Map.Entry<String, String> varibleIndividual: variables.entrySet()) {	
			if(varibleIndividual.getKey().equals(keyValue)) {
				valueToReturn = varibleIndividual.getValue();
			}
		}
		if(valueToReturn.equals("")) {
			return "Error, esta funcion no existe";
		}else {
			return valueToReturn;
		}
	}
	 
	 //====================================SETTERS Y GETTERS====================================
	 /**
	 * @return the funciones
	 */
	public HashMap<String, ArrayList<String>> getFunciones() {
		return funciones;
	}

	public ArrayList<String> getBody(String key) {
		return funciones.get(key);
	}

	public int getSize() {
		return funciones.size();
	}

	/**
	 * @return the variables
	 */
	public HashMap<String, String> getVariables() {
		return variables;
	}

	 
}
