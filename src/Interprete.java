package src;

import java.util.ArrayList;
import java.util.Scanner;

//Puede servir luego
//Por si quieren ver que son los predicadores:  http://web.cs.ucla.edu/~rosen/161/notes/lisp1.html#:~:text=Everything%20in%20Lisp%20is%20either,list)%2C%20ATOM%20returns%20NIL.

public class Interprete {

	private ArrayList < String > codigo = new ArrayList < String > ();
	private Lector lec = new Lector();
	private Imprimir im = new Imprimir();
	private Definir def = new Definir();
	private Calcular calc = new Calcular(def);

	Scanner sc = new Scanner(System.in);

	public Interprete() {
		codigo = lec.leerDocumento();
	}

	public void vistaPrueba() {
		for (String linea: codigo) {
			System.out.println(linea);
		}
	}

	public void ejecutar() {
		proceso(codigo);
	}

	public void proceso(ArrayList < String > code) {
		String iterated = "";
		String nameFunction = "";
		ArrayList < String > functionContain = new ArrayList < String > ();
		boolean nombreFuncion = false;
		int contador_funcion = 0;
		boolean lectorFuncion = false;
		boolean dentro_funcion = false;
		boolean calculo = false;

		for (String linea: code) {
			for (char x: linea.toCharArray()) {
				iterated += x;
				lectorFuncion = iterated.contains("defun");
				if (iterated.equals("\t") || iterated.equals(" ")) {
					iterated = "";
				}
				if (dentro_funcion) {
					if (x == '(') {
						contador_funcion += 1;
					} else if (x == ')') {
						contador_funcion -= 1;
					}
				}
				if (iterated.contains("princ") && !dentro_funcion) { //Por si se encuentra un princ
					System.out.println(im.resPrint(linea));
					iterated = "";
				} else if (iterated.contains("print") && !dentro_funcion) { //Por si se encuentra un print
					System.out.println(im.resPrint(linea));
					iterated = "";
				} else if (lectorFuncion) {
					iterated = "";
					nombreFuncion = true;
					contador_funcion = 1;
					dentro_funcion = true;
				} else if (iterated.contains("setq") && !dentro_funcion) { //Por si se encuentra un setq
					iterated = "";
					String[] values = im.resRead(linea.replace("setq", ""));
					def.guardarVariable(values[0], values[1]);
				} else if ((iterated.contains("/") || iterated.contains("-") || iterated.contains("+") || iterated.contains("*")) && !dentro_funcion) {
					calculo = true;
				} else if (!dentro_funcion) {
					if (iterated.length() > 2) {
						if (!iterated.substring(iterated.length() - 1).equals(" ")) {
							StringBuilder sb = new StringBuilder(iterated.trim());
							sb.deleteCharAt(iterated.length() - 1);
							sb.deleteCharAt(0);
							sb.deleteCharAt(0);
							String nombre = sb.toString();
							ArrayList < String > funcion = def.getBody(nombre + "()");
							if (funcion != null) {
								proceso(funcion);
							}
						}

					}

				}
				if (nombreFuncion) {
					String caracter = "";
					if (iterated.length() > 1) {
						caracter = iterated.substring(iterated.length() - 1);
					} else {
						caracter = iterated;
					}
					if (caracter.equals(")")) {
						nameFunction = iterated.trim();
						nombreFuncion = false;
						iterated = "";
					}
				}

				if (contador_funcion > 0 && !nombreFuncion) {
					if (contador_funcion == 1) {
						if (iterated.length() > 1) {
							functionContain.add(iterated.trim());
							iterated = "";
						}
					}
				}
				if (contador_funcion == 0) {
					dentro_funcion = false;
					if (functionContain.size() > 0) {
						ArrayList < String > copia = createCopy(functionContain);
						def.guardarFuncion(nameFunction, copia);
						functionContain.clear();
					}
				}
			}
			if (calculo) {
				System.out.println(calc.operar(linea));
			}
		}
		//def.getFunciones().forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
	}

	public ArrayList < String > createCopy(ArrayList < String > orginal) {
		ArrayList < String > copy = new ArrayList < String > ();
		for (String s: orginal) {
			copy.add(s);
		}
		return copy;
	}

}