/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

/**
 *
 * @author Usuario
 */
public class Camino {
    
    private Nodo nodoInicial;
    private Nodo nodoFinal;
    private double feromonas;
    private double prob;
    
    public Camino(Nodo nodoInicial, Nodo nodoFinal, double feromonas, double prob){
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.feromonas = feromonas;
        this.prob = prob;
    }
    
    public Nodo getNodoInicial(){
        return this.nodoInicial;
    }
    
    public Nodo getNodoFinal(){
        return this.nodoFinal;
    }
    
    public double getFeromonas(){
        return this.feromonas;
    }
    
    public void setFeromonas(double f){
        this.feromonas = f;
    }
    
    public double getProb(){
        return this.prob;
    }
    
}
