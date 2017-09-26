/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Usuario
 */
public class ColoniaHormigas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Tablero tablero = new Tablero();
        System.out.println("---- Tablero Inicial---");
        tablero.imprimirTablero(tablero.tableroInicial());
        System.out.println("---- Tablero Final ----");
        tablero.imprimirTablero(tablero.tableroFinal());
        
        Colonia colonia = new Colonia();
        colonia.construirColonia();
        colonia.recorrer(20);
          

        
    }
    
    
    
}
