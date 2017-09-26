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
public class Nodo {
    
    private int id;
    private int[][] estado;
    private ArrayList<Integer> hijos;
    private int padre;
    private ArrayList<Camino> caminos;

    
    public Nodo(int id,int t[][]){
        this.id = id;
        this.estado = t;
        this.hijos = new ArrayList<>();
        //this.feromonas = new ArrayList<>();
        //this.distanciaManhattan = new ArrayList<>();
        //this.prob = new ArrayList<>();
        this.caminos = new ArrayList<>();
       
    }
    
    public int getId(){
        return this.id;
    }

    public int[][] getEstado() {
        return estado;
    }

    public void setEstado(int[][] t) {
        this.estado = t;
    }
    
    public void addHijos(int h){
        this.hijos.add(h);
    }
    
    public int getHijo(int h){
        return this.hijos.get(h);
    }
    
    public int numHijos(){
        return this.hijos.size();
    }
    
    public int getPadre(){
        return this.padre;
    }
    
    public void setPadre(int padre){
        this.padre = padre;
    }
    
    public void mostrarInformacionCaminos(){
        System.out.println("---- Informacion ---");
        int numCaminos = this.caminos.size();
        for (int i = 0; i < numCaminos; i++) {
            Camino camino = this.caminos.get(i);
            int h = camino.getNodoFinal();  //this.hijos.get(i);
            int dist = camino.getDistancia();
            double fero = camino.getFeromonas();
            double prob = camino.getProb();
            System.out.println("Camino "+this.id+" -- "+h);
            System.out.println("Distancia Manhattan: "+dist);
            System.out.println("Feromonas: "+fero);
            System.out.println("Probabilidad: "+prob);
        }
        
    }
    
    public Camino getCamino(int i){
        return this.caminos.get(i);
    }
    
    public void addCamino(Camino c){
        this.caminos.add(c);
    }
    
    public int numCaminos(){
        return this.caminos.size();
    }
    

   
}
