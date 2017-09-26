/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Tablero {
    
    private final int n = 3;
    //private int t0[][] = {{1,2,3},{4,5,6},{7,8,0}};
    //private int tf[][] = {{4,5,7},{2,6,1},{8,0,3}};
    
    private int t0[][] = {{2,8,3},{1,6,4},{7,0,5}};
    private int tf[][] = {{1,2,3},{8,0,4},{7,6,5}};

        
    public Tablero(){

                
    }
    
    
    
    private int[][] clonar(int t[][]){
        int clone[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                clone[i][j] = t[i][j];
            }
        }
        return clone;
    }
    
    
    
    public ArrayList<int[][]> getMoves(int t[][]){
        
        ArrayList<int[][]> movs = new ArrayList<>();
        
        int i0 = this.i0(t); // posicion i del espacio en blanco de la matriz clonada
        int j0 = this.j0(t); // posicion j del espacio en blanco de la matriz clonada
        
        // Ahora yo se la posicion (i,j) del espacio en blanco
        int aux;
        int clone[][] = this.clonar(t);
        if (i0 - 1 >= 0){
            aux = t[i0-1][j0];
            clone[i0-1][j0] = 0;
            clone[i0][j0] = aux;
            movs.add(clone);
            clone = this.clonar(t);
        }
        if (j0 - 1 >= 0){
            aux = t[i0][j0-1];
            clone[i0][j0-1] = 0;
            clone[i0][j0] = aux;
            movs.add(clone);
            clone = this.clonar(t);
        }
        if (i0 + 1 < n){
            aux = t[i0+1][j0];
            clone[i0+1][j0] = 0;
            clone[i0][j0] = aux;
            movs.add(clone);
            clone = this.clonar(t);
        }
        if (j0 + 1 < n){
            aux = t[i0][j0+1];
            clone[i0][j0+1] = 0;
            clone[i0][j0] = aux;
            movs.add(clone);
            clone = this.clonar(t);
        }
        
        return movs;
    }
   
    
    private int i0(int tab[][]){
        int i0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i][j] == 0){
                    i0 = i;
                    return i0;
                }
            }
        }
        return 0;
    }
    
    private int j0(int tab[][]){
        int j0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tab[i][j] == 0){
                    j0 = j;
                    return j0;
                }
            }
        }
        return 0;
    }
       
    
    public void imprimirTablero(int t[][]){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(t[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("----------------");
    }
    
    public int[][] tableroInicial(){
        return this.t0;
    }
    
    public int[][] tableroFinal(){
        return this.tf;
    }
    
    public boolean esFinal(int t[][]){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tf[i][j] != t[i][j]){
                    return false;
                }
            }
        }     
        return true;
    }
    
    public static boolean hayRepeticion(int m[][], int n[][]){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < n.length; j++) {
                if (m[i][j] != n[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
     
     
}
