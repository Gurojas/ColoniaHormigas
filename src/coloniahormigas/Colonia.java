/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coloniahormigas;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    Camino camino = new Camino(root.getId(),hijo.getId());
                    root.addCamino(camino);
                    
                    this.añadirDistanciaCaminos(camino, root.getEstado(), tablero.tableroFinal());
                    this.añadirFeromonasCaminos(camino, this.ah.feromonasIniciales);
                            
                    //System.out.println("Hijos de "+padre);
                    //System.out.println(pos); 
                    //tablero.imprimirTablero(hijo.getEstado());

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
    
    private Camino calcularProbCuatroCaminos(Nodo nodo, double random){
        Camino camino1,camino2,camino3;
        double rango1, rango2, rango3;
        
        camino1 = nodo.getCamino(0);
        camino2 = nodo.getCamino(1);
        camino3 = nodo.getCamino(2);
        
        rango1 = camino1.getProb();
        rango2 = camino2.getProb() + rango1;
        rango3 = camino3.getProb() + rango2;
        
        if(random >= 0 && random <= rango1){
            return nodo.getCamino(0);
        }
        else if (random > rango1 && random <= rango2){
            return nodo.getCamino(1);
        }
        else if(random > rango2 && random <= rango3){
            return nodo.getCamino(2);
        }
        else{
            return nodo.getCamino(3);
        }
    }
    
    private Camino calcularProbTresCaminos(Nodo nodo, double random){
        Camino camino1, camino2;
        camino1 = nodo.getCamino(0);
        camino2 = nodo.getCamino(1);
        
        double rango1, rango2;
        rango1 = camino1.getProb();
        rango2 = rango1 + camino2.getProb();
        
        if (random >= 0 && random <= rango1){
            return nodo.getCamino(0);
        }
        else if(random > rango1 && random <= rango2){
            return nodo.getCamino(1);
        }
        else{
            return nodo.getCamino(2);
        }
    }
    
    private Camino calcularProbDosCaminos(Nodo nodo, double random){
        Camino camino1;
        camino1 = nodo.getCamino(0);
        double rango1;
        
        rango1 = camino1.getProb();

        if (random >= 0 && random <= rango1){
            return nodo.getCamino(0);
        }
        else{
            return nodo.getCamino(1);
        }
    }
    
    private Camino calcularProbUnCaminos(Nodo nodo, double random){
        return nodo.getCamino(0);
    }
    
    private void actualizarFeromonas(Hormiga hormiga){
        int numNodos = this.g.V();
        int numCaminosSolucion = hormiga.numCaminos();
        int i = 0;
        for (int n = 0; n < numNodos; n++) {
            Nodo nodo = this.g.getNodo(n);
            int numCaminos = nodo.numCaminos();
            for (int c = 0; c < numCaminos; c++) {
                Camino camino = nodo.getCamino(c);
                //Camino caminoHormiga = hormiga.getCamino(i);
                if (i < numCaminosSolucion){
                    Camino caminoHormiga = hormiga.getCamino(i);
                    if(caminoHormiga.igualdadCaminos(camino)){
                        i++;
                        // cambiar feromonas camino solucion
                        double feromonasDepositadas = this.ah.feroDepositadas(hormiga.costoCamino());
                        double nuevasFeromonas = this.ah.nuevasFeromonas(camino.getFeromonas(),feromonasDepositadas);
                        camino.setFeromonas(nuevasFeromonas);      
                    }
                    else{
                        // cambiar feromonas resto del camino
                        double feromonasDepositadas = 0;
                        double nuevasFeromonas = this.ah.nuevasFeromonas(camino.getFeromonas(),feromonasDepositadas);
                        camino.setFeromonas(nuevasFeromonas);
                    }
                }
                else{
                    // cambiar feromonas resto del camino
                    double feromonasDepositadas = 0;
                    double nuevasFeromonas = this.ah.nuevasFeromonas(camino.getFeromonas(),feromonasDepositadas);
                    camino.setFeromonas(nuevasFeromonas);
                }
            }
        }
    }
    
    
    public void recorrer(){
        int numHormigas = 5;
        Tablero tablero = new Tablero();
        boolean solucion = false;
        Random random = new Random();
        Hormiga hormiga = new Hormiga();
        int costoCamino = 0;
        
        for (int i = 0; i < numHormigas; i++) {
            System.out.println("--- Hormiga "+i+"---");
            int nodoInicial = 0;
        
            while (!solucion) {
                try {
                    Thread.sleep(250);
                } 
                catch (InterruptedException ex) {
                    Logger.getLogger(Colonia.class.getName()).log(Level.SEVERE, null, ex);
                }
                Nodo nodo = this.g.getNodo(nodoInicial);
                double numRandom = random.nextDouble();
                int numHijos = nodo.numHijos();

                if (tablero.esFinal(nodo.getEstado())){
                    System.out.println("Hay solucion");
                    solucion = true;
                }
                else{
                    if (numHijos != 0){
                         if (numHijos == 1){
                            Camino camino = this.calcularProbUnCaminos(nodo, numRandom);
                            costoCamino = camino.getDistancia() + costoCamino;
                            int nodoFinal = camino.getNodoFinal();
                            hormiga.addCamino(new Camino(nodoInicial,nodoFinal));
                            System.out.println(nodoInicial+"--"+nodoFinal);     
                            nodoInicial = nodoFinal;
                        }
                        else if(numHijos == 2){
                            Camino camino = this.calcularProbDosCaminos(nodo, numRandom);
                            costoCamino = camino.getDistancia() + costoCamino;
                            int nodoFinal = camino.getNodoFinal();
                            hormiga.addCamino(new Camino(nodoInicial,nodoFinal));
                            System.out.println(nodoInicial+"--"+nodoFinal);
                            nodoInicial = nodoFinal;
                        }
                        else if(numHijos == 3){
                            Camino camino = this.calcularProbTresCaminos(nodo, numRandom);
                            costoCamino = camino.getDistancia() + costoCamino;
                            int nodoFinal = camino.getNodoFinal();
                            hormiga.addCamino(new Camino(nodoInicial,nodoFinal));
                            System.out.println(nodoInicial+"--"+nodoFinal);
                            nodoInicial = nodoFinal;
                        }
                        else if(numHijos == 4){
                            Camino camino = this.calcularProbCuatroCaminos(nodo, numRandom);
                            costoCamino = camino.getDistancia() + costoCamino;
                            int nodoFinal = camino.getNodoFinal();
                            hormiga.addCamino(new Camino(nodoInicial,nodoFinal));
                            System.out.println(nodoInicial+"--"+nodoFinal);
                            nodoInicial = nodoFinal;
                        }  
                    }
                    else{
                        break;
                    }
                }
            }
            
            hormiga.setCostoCamino(costoCamino);
            this.actualizarFeromonas(hormiga);
            this.actualizarProb();
            
            
        }
    }
    
    private void actualizarProb(){
        int n = this.g.V();
        for (int j = 0; j < n; j++) {
                Nodo nodo = this.g.getNodo(j);
                this.añadirProbCaminos(nodo);
                if (nodo.numCaminos() != 0){
                    nodo.mostrarInformacionCaminos();
                }
            }
    }
    
    
    private void añadirProbCaminos(Nodo nodo){
        int numCaminos = nodo.numCaminos();
        double sumaProductoDistanciaFeromonas = 0;
        for (int i = 0; i < numCaminos; i++) {
            Camino camino = nodo.getCamino(i);
            int distancia = camino.getDistancia();
            double feromonas = camino.getFeromonas();
            sumaProductoDistanciaFeromonas = sumaProductoDistanciaFeromonas + distancia*feromonas;
        }
        
        for (int i = 0; i < numCaminos; i++) {
            Camino camino = nodo.getCamino(i);
            int distancia = camino.getDistancia();
            double feromonas = camino.getFeromonas();
            double prob = (distancia*feromonas)/sumaProductoDistanciaFeromonas;
            camino.setProb(prob);
        }
    }
    
 
    
    private void añadirDistanciaCaminos(Camino camino,int t0[][], int tf[][]){
        int distancia = this.h.distanciaManhattan(t0, tf);
        camino.setDistancia(distancia);
    }
    
    
    
    private void añadirFeromonasCaminos(Camino camino, double feromona){
        camino.setFeromonas(feromona);
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
