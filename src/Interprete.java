package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

//Puede servir luego
//Por si quieren ver que son los predicadores:  http://web.cs.ucla.edu/~rosen/161/notes/lisp1.html#:~:text=Everything%20in%20Lisp%20is%20either,list)%2C%20ATOM%20returns%20NIL.

public class Interprete {
	
	private ArrayList<String> codigo = new ArrayList<String>();
	private Lector lec = new Lector();
	private Imprimir im = new Imprimir();
	private Definir def = new Definir();
	private Calcular calc = new Calcular();
	
	Scanner sc = new Scanner(System.in);
	
	public Interprete() {
		codigo = lec.leerDocumento();
	}
    
	public void vistaPrueba() {
		for(String linea: codigo) {
			System.out.println(linea);
		}
	}
	
	public void proceso() {
		String iterated = "";
		String nameFunction;
		ArrayList<String> functionContain = new ArrayList<String>();
		
		int contadorFuncion = 0;
		
		boolean reading = true;
		boolean nombreFuncion;
		boolean lectorFuncion = false;
		boolean calculo;

		for(String linea: codigo) {
				nameFunction = "";
				calculo = false;
				nombreFuncion = false;
				for(char x: linea.toCharArray()) {
					iterated += x;	
						
					if(iterated.equals("\t") || iterated.equals(" ")) {            //Quitar si hay tabulacion en el archivo
						iterated = "";
					} 
					if(x == '(') {
						contadorFuncion +=1;
					}else if (x == ')') {
						contadorFuncion -= 1;
					}
					//System.out.println(iterated);
					if(iterated.contains("princ")) {									//Por si se encuentra un princ
						System.out.println("Princ encontrado, el siguiente string sera lo que se pide");
						System.out.println(im.resPrint(linea));
						iterated = "";
					}else if(iterated.contains("print")) {									//Por si se encuentra un print
						System.out.println("Print encontrado, el siguiente string sera lo que se pide");
						System.out.println(im.resPrint(linea));
						iterated = "";
					}else if(iterated.contains("defun")) {							//Por si se encuentra una funcion
						System.out.println("funcion encontrada: ");
						iterated = "";
						nombreFuncion = true;										//Funcion encontrada
					}else if(iterated.contains("setq")) {							//Por si se encuentra un setq
						System.out.print("setq encontrado: ");
						iterated = "";
						String[] values = im.resRead(linea.replace("setq", ""));
						def.guardarVariable(values[0], values[1]);
						System.out.println("Variable: " + values[0] + " Valor de variable: " + values[1]);									
					}else if(iterated.contains("/") || iterated.contains("-") || iterated.contains("+") || iterated.contains("*")) {
						calculo = true;
					}
				}
				if(nombreFuncion) {													//Se activa si se encuentra una funcion
					nameFunction = iterated.trim();
					contadorFuncion = 1;
					lectorFuncion = true;
					System.out.println(nameFunction);
				}
				if(lectorFuncion) {
					functionContain.add(linea);
					if(contadorFuncion == 0) {
						lectorFuncion = false;
						def.guardarFuncion(nameFunction, functionContain);
						System.out.println("Se guardo la siguiente funcion correctamente: ");
						ArrayList<String> instrucciones = def.mostrarFuncion(nameFunction);
						if(instrucciones != null || !instrucciones.isEmpty()) {
							for(String l: instrucciones) {
								System.out.println(l);
							}
						}else {
							System.out.println("La funcion no existe");
						}
						functionContain.clear();
					}
				}
				if(calculo) {
					System.out.println(calc.operar(linea));
				}
		}
	}
	
}
