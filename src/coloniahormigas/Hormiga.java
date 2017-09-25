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
public class Hormiga {
    
    private ArrayList<Camino> caminos;
    private boolean llegoFinal;
    private int costoCamino;
    
    public Hormiga(){
        this.caminos = new ArrayList<>();
        this.llegoFinal = false;
        this.costoCamino = 0;
    }
    
    public int costoCamino(){
        return this.costoCamino; 
    }
    
    public void setCostoCamino(int c){
        this.costoCamino = c;
    }
   
    
    public Camino getCamino(int c){
        return this.caminos.get(c);
    }
    
    public void addCamino(Camino c){
        this.caminos.add(c);
    }
    
    public int numCaminos(){
        return this.caminos.size();
    }
    
    
    
}
