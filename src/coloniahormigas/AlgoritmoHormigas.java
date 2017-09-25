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
public class AlgoritmoHormigas {
    
    public double feromonasIniciales = 0.1;
    public final double indiceEvaporacion = 0.01;
    public final double constante = 1;
    
    
    public AlgoritmoHormigas(){
        
    }
    public double feroDepositadas(int costoCamino){
        return this.constante/costoCamino;
    }
    
    public double nuevasFeromonas(double feromonasCamino, double feromonasDepositadas){
        
        return (1-this.indiceEvaporacion)*feromonasCamino + feromonasDepositadas;
    }
    
    
    
    /*
    public double calculoProb(double distancia, double feromonas, int numCaminos){
        for (int i = 0; i < numCaminos; i++) {
            
        }
    }
    */
    
    
}
