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
	 /** 
	 * Guarda una funcion en el Hash
	 */ 
	 public void guardarFuncion(String nombre, ArrayList<String> instrucciones) {
		funciones.put(nombre, instrucciones);
	 }
	 
	 /** 
	 * @return ArrayList<String>
	 * Muestra todas las funciones guardadas
	 */ 
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
	 /** 
	 * Metodo para guardar una variable en el Hash
	 */ 
	 public void guardarVariable(String nombre, String value) {
		 variables.put(nombre, value);
	 }
	 
	 /** 
	 * @return String
	 * Muestra una variable seleccionada por su KeyValue.
	 */ 
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

	/**
	 * @return las variables
	 */
	public String getValor(String nombre) {
		return variables.get(nombre);
	}

	/**
	 * @return El cuerpo de la funcion
	 */
	public ArrayList<String> getBody(String key) {
		return funciones.get(key);
	}

	/**
	 * @return El tamano del array
	 */
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
