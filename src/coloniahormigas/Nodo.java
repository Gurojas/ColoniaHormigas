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
    private ArrayList<Double> feromonas;
    private ArrayList<Integer> distanciaManhattan;
    private ArrayList<Double> prob;
    private int padre;

    
    public Nodo(int id,int t[][]){
        this.id = id;
        this.estado = t;
        this.hijos = new ArrayList<>();
        this.feromonas = new ArrayList<>();
        this.distanciaManhattan = new ArrayList<>();
        this.prob = new ArrayList<>();
       
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
    
    public void addFeromonas(double feromonas){
        this.feromonas.add(feromonas);
    }
    
    public double getFeromonas(int f){
        return this.feromonas.get(f);
    }
    
    public void setFeromonas(double feromonas, int f){
        this.feromonas.set(f, feromonas);
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
    
    public void addDistancia(int d){
        this.distanciaManhattan.add(d);
    }
    
    public int getDistancia(int d){
        return this.distanciaManhattan.get(d);
    }
    
    public void addProb(double p){
        this.prob.add(p);
    }
    
    public double getProb(int p){
        return this.prob.get(p);
    }
   
    
    public int getPadre(){
        return this.padre;
    }
    
    public void setPadre(int padre){
        this.padre = padre;
    }
    
    public void mostrarInformacionCaminos(){
        System.out.println("---- Informacion ---");
        int n = this.hijos.size();
        for (int i = 0; i < n; i++) {
            int h = this.hijos.get(i);
            int dist = this.distanciaManhattan.get(i);
            double fero = this.feromonas.get(i);
            double prob = this.prob.get(i);
            System.out.println("Camino "+this.id+" -- "+h);
            System.out.println("Distancia Manhattan: "+dist);
            System.out.println("Feromonas: "+fero);
            System.out.println("Probabilidad: "+prob);
        }
        
    }
    

   
}
