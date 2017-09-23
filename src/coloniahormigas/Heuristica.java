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
public class Heuristica {
    
    
    public Heuristica(){
    

    
    }
    
    private int distanciaEntrePuntos(int i0, int j0, int i1, int j1){
        return Math.abs(i0-i1) + Math.abs(j0-j1);
    }
    
    
    public int distanciaManhattan(int t0[][], int ti[][]){
        ArrayList<Coordenada> piezasMalPuestas = this.piezasMalPuestas(t0, ti);
        int numPiezasMalPuestas = piezasMalPuestas.size();
        
        int distanciaManhattan = 0;
        
        for (int k = 0; k < numPiezasMalPuestas; k++) {
            Coordenada c0 = piezasMalPuestas.get(k);
            int pieza = c0.getPieza();
            int i0 = c0.getI();
            int j0 = c0.getJ();
            
            Coordenada c1 = this.encontrarCoord(pieza, ti);
            int i1 = c1.getI();
            int j1 = c1.getJ();
            
            int dist = this.distanciaEntrePuntos(i0, j0, i1, j1);
            distanciaManhattan = dist + distanciaManhattan;
        }
        
        return distanciaManhattan;
    }
    
   private Coordenada encontrarCoord(int pieza, int ti[][]){
        int n = ti.length;
        Coordenada coordenada = null;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (pieza == ti[i][j]){
                    coordenada = new Coordenada(ti[i][j],i,j);
                    return coordenada;
                }
            }
       }
        return coordenada;
   }
    
    
    private ArrayList<Coordenada> piezasMalPuestas(int t0[][], int ti[][]){
        int n = t0.length;
        ArrayList<Coordenada> piezas = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (t0[i][j] != ti[i][j]){
                    if (t0[i][j] != 0){
                        Coordenada c = new Coordenada(t0[i][j], i, j);
                        //System.out.println("Pieza agregada: "+t0[i][j]);
                        //System.out.println("posicion: "+i+","+j);
                        piezas.add(c);
                    } 
                }
            }
        }
        return piezas;
    }
    
    
    private class Coordenada{
        private int i;
        private int j;
        private int pieza;
        
        public Coordenada(int pieza,int i, int j){
            this.i = i;
            this.j = j;
            this.pieza = pieza;
        }
        
        public int getPieza(){
            return this.pieza;
        }
        
        public int getI(){
            return this.i;
        }
        
        public int getJ(){
            return this.j;
        }
    
    }
    
}
