package src;
import static org.junit.Assert.assertArrayEquals;

import junit.framework.TestCase;

/**
 *  Oscar Estrada, Luis Pedro, Axel Leonardo.
 */
public class TestIf extends TestCase{
	private Predicados predicados;
	private Definir def = new Definir();
	private Imprimir imp = new Imprimir();
	
	public void escenario() {
		predicados = new Predicados(def);
	}
	
	public void testcondicional() {
		escenario();
		assertEquals(true, predicados.condicional("(< 5 8)"));
	}
	
	public void testcondicional2() {
		escenario();
		assertEquals(false, predicados.condicional("(= 5 8)"));
	}
	
	public void testImpresion() {
		escenario();
		assertEquals("Funciona la impresion leida", imp.resPrint("\"Funciona la impresion leida\""));
	}
	
	public void testVariables() {
		escenario();
		def.guardarVariable("algo", "2");
		assertEquals("2", def.getValor("algo"));
	}
}
