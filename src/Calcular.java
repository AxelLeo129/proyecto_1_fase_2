package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Calcular {
    private ArrayList<String> caracteres;
    private ArrayList<String> numeros;
    private ArrayList<String> operadores;
    private HashMap<String, String> variables;

    public Calcular(){
        operadores= new ArrayList<String>(List.of("+", "-", "/", "*"));
        String caracteresString="qwertyuiopasdfghjklñzxcvbnmQWERTYUIOPASDFGHJKLÑZXCVBNM-_";
        caracteres= new ArrayList<String>(Arrays.asList(caracteresString.split("")));
        String numerosString="1234567890";
        numeros = new ArrayList<String>(Arrays.asList(numerosString.split("")));
        variables= new HashMap();
    }
    public String operar (String cadenaAOperear){
        boolean operadorEncontrado=false;
        boolean operadorUnoEncontrado=false;
        boolean operadorDosEncontrado=false;
        boolean encontroOperadorComoExpresion=false;
        boolean encontroOperadorComoNumero=false;
        boolean encontroOperadorComoVariable=false;
        String operador="";
        String primerOperando="";
        String segundoOperando="";
        String temporalAcumulado="";
        cadenaAOperear= cadenaAOperear.trim();
        String[] listaCadena = cadenaAOperear.split("");
        listaCadena[0]="";
        for (String caracter: listaCadena){
            if(!operadorEncontrado){
                if(operadores.contains(caracter)) {
                    operadorEncontrado=true;
                    operador=caracter;
                }
            }else
            if(!operadorUnoEncontrado){
                if(!encontroOperadorComoExpresion &&
                        !encontroOperadorComoNumero &&
                        !encontroOperadorComoVariable) {
                    if (caracter.equals("(")) {
                        encontroOperadorComoExpresion = true;}
                    else if (caracteres.contains(caracter)){
                        encontroOperadorComoVariable = true;}
                    else if (numeros.contains(caracter)) {
                        encontroOperadorComoNumero = true;}
                }
                if(encontroOperadorComoExpresion){
                    temporalAcumulado=temporalAcumulado+caracter;
                    if(caracter.equals(")")){
                        primerOperando= operar(temporalAcumulado);
                        temporalAcumulado="";
                        operadorUnoEncontrado=true;

                    }
                }
                if(encontroOperadorComoVariable){
                    if(!caracter.equals(" ")) temporalAcumulado=temporalAcumulado+caracter;
                    else{
                        primerOperando=temporalAcumulado;
                        temporalAcumulado="";
                        operadorUnoEncontrado=true;

                    }
                }
                if(encontroOperadorComoNumero){
                    if(!caracter.equals(" ")) temporalAcumulado=temporalAcumulado+caracter;
                    else{
                        primerOperando=temporalAcumulado;
                        temporalAcumulado="";
                        operadorUnoEncontrado=true;
                        encontroOperadorComoExpresion=false;
                        encontroOperadorComoNumero=false;
                        encontroOperadorComoVariable=false;
                    }

                }
                if(operadorUnoEncontrado){
                    encontroOperadorComoExpresion=false;
                    encontroOperadorComoNumero=false;
                    encontroOperadorComoVariable=false;
                }

            } else
            if(!operadorDosEncontrado){   if(!encontroOperadorComoExpresion &&
                    !encontroOperadorComoNumero &&
                    !encontroOperadorComoVariable) {
                if (caracter.equals("(")) {
                    encontroOperadorComoExpresion = true;}
                else if (caracteres.contains(caracter)){
                    encontroOperadorComoVariable = true;}
                else if (numeros.contains(caracter)) {
                    encontroOperadorComoNumero = true;}
            }
                if(encontroOperadorComoExpresion){
                    temporalAcumulado=temporalAcumulado+caracter;
                    if(caracter.equals(")")){
                        segundoOperando= operar(temporalAcumulado);
                        temporalAcumulado="";
                        operadorDosEncontrado=true;

                    }
                }
                if(encontroOperadorComoVariable){
                    if(!caracter.equals(" ")) temporalAcumulado=temporalAcumulado+caracter;
                    else{
                        segundoOperando=temporalAcumulado;
                        temporalAcumulado="";
                        operadorDosEncontrado=true;

                    }
                }
                if(encontroOperadorComoNumero){
                    if(!caracter.equals(" ")&&!caracter.equals(")")) temporalAcumulado=temporalAcumulado+caracter;
                    else{
                        segundoOperando=temporalAcumulado;
                        temporalAcumulado="";
                        operadorDosEncontrado=true;
                    }

                }

            }

        }
        if(!operador.equals("")&&!primerOperando.equals("")&&!segundoOperando.equals("")){
            switch (operador){
                case "*":return String.valueOf(Float.parseFloat(primerOperando)*Float.parseFloat(segundoOperando));
                case "+":return String.valueOf(Float.parseFloat(primerOperando)+Float.parseFloat(segundoOperando));
                case "-":return String.valueOf(Float.parseFloat(primerOperando)-Float.parseFloat(segundoOperando));
                case "/":return String.valueOf(Float.parseFloat(primerOperando)/Float.parseFloat(segundoOperando));
                default: return "Ocurrio un error inesperado";
            }
        }else return "Hubo un problema con la expresion ingresada";

    }


}
