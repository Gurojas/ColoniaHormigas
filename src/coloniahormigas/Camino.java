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
    
    private int nodoInicial;
    private int nodoFinal;
    private double feromonas;
    private double prob;
    private int distancia;
    
    public Camino(int nodoInicial, int nodoFinal){
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        this.feromonas = 0.0;
        this.prob = 0.0;
        this.distancia = 0;
    }
    
    public int getNodoInicial(){
        return this.nodoInicial;
    }
    
    public int getNodoFinal(){
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
    
    public void setProb(double p){
        this.prob = p;
    }
    
    public int getDistancia(){
        return this.distancia;
    }
    
    public void setDistancia(int d){
        this.distancia = d;
    }
    
    public boolean igualdadCaminos(Camino camino){
        int nodoInicial = camino.getNodoInicial();
        int nodoFinal = camino.getNodoFinal();
        return this.nodoInicial == nodoInicial && this.nodoFinal == nodoFinal;
    }
    
}
