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
public class Colonia {
    
    private Grafo g;
    private AlgoritmoHormigas ah;
    private Heuristica h;
    
    public Colonia(){
        this.g = new Grafo();
        this.ah = new AlgoritmoHormigas();
        this.h = new Heuristica();
    }
    
    public void construirColonia(){
        Tablero tablero = new Tablero();
        int pos = 1;
        int padre = 0;
        
        Nodo root = new Nodo(padre,tablero.tableroInicial());
        this.g.addEdge(root.getId(), root);
        root.setPadre(-1); // el nodo raiz no tiene padre
        boolean solucion = false;
        
        while(!solucion){
            root = this.g.getNodo(padre);
            ArrayList<int[][]> estados = tablero.getMoves(root.getEstado());
            int n = estados.size();
            for (int i = 0; i < n; i++) {
                Nodo hijo = new Nodo(pos,estados.get(i));
                hijo.setPadre(padre);
           
                // reviso si hay repeticiones
                if (!this.repiticion(hijo)){
            
                    root.addHijos(hijo.getId());
                    
                    this.añadirDistanciaCaminos(root, tablero);
                    this.añadirFeromonasCaminos(root);
                   
                    
                    
                    
                    System.out.println("Hijos de "+padre);
                    System.out.println(pos); 
                    tablero.imprimirTablero(hijo.getEstado());

                    this.g.addEdge(hijo.getId(), hijo);
                    pos++;

                    // compruebo si el nodo que agrege es solucion
                    if (tablero.esFinal(hijo.getEstado())){
                        solucion = true;
                        break;
                    }
                }
            }
            
            this.añadirProbCaminos(root);
            root.mostrarInformacionCaminos();
            
            
            padre++;           
        }

    }
    
    private void añadirProbCaminos(Nodo nodo){
        int n = nodo.numHijos();
        double sumaProductoDistanciaFeromonas = 0;
        for (int i = 0; i < n; i++) {
            int distancia = nodo.getDistancia(i);
            double feromonas = nodo.getFeromonas(i);
            sumaProductoDistanciaFeromonas = sumaProductoDistanciaFeromonas + distancia*feromonas;
        }
        
        for (int i = 0; i < n; i++) {
            int distancia = nodo.getDistancia(i);
            double feromonas = nodo.getFeromonas(i);
            
            double prob = (distancia*feromonas)/sumaProductoDistanciaFeromonas;
            nodo.addProb(prob);

        }
        
    }
    
    private void añadirDistanciaCaminos(Nodo nodo, Tablero tablero){
        int estado[][] = nodo.getEstado();
        int distancia = this.h.distanciaManhattan(estado, tablero.tableroFinal());
        nodo.addDistancia(distancia);
    }
    
    private void añadirFeromonasCaminos(Nodo nodo){
        nodo.addFeromonas(this.ah.feromonasIniciales);
    }
    
    public boolean repiticion(Nodo nodoHijo){
        boolean repeticion = false;
        
        int padre = nodoHijo.getPadre();
        while(padre != -1){
            Nodo nodoPadre = this.g.getNodo(padre);
            repeticion = Tablero.hayRepeticion(nodoHijo.getEstado(), nodoPadre.getEstado());
            
            if (repeticion){
                break;
            }
                  
            padre = nodoPadre.getPadre();
        }
        return repeticion;
    }
    
}
