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
public class Grafo {
    
    private int V;
    private int E;
    //private Nodo nodos[];
    private ArrayList<Nodo> adj;
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Grafo() {

        this.V = 0;
        this.E = 0;
        //nodos = new Nodo[V];
        adj = new ArrayList<>();
    }
    
     /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    /*
    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, Nodo nodo) {
        E++;
        //adj[v].add(e);
        //nodos[v] = nodo;
        adj.add(nodo);
        V++;
        
    }
    
    public Nodo getNodo(int v){
        //return nodos[v];
        return adj.get(v);
    }
    
    
    
    
    
    


    
    
}
