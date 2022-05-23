
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author david
 */
public class Casilla 
{
    private int fila, columna, minasProximas;
    private boolean mina, abierta, marcada;
    
    
    //constructor
    public Casilla (int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
        mina = false;
        abierta = false;
        marcada = false;
        minasProximas = 0;
    }
    
    //get y set
    public void setMina(boolean b)
    {
        mina = b;
    }
    public void setAbierta(boolean b)
    {
        abierta = b;
    }
    public void setMarcada(boolean b)
    {
        marcada = b;
    }
    public void incrementarMina()
    {
        minasProximas++;
    }
    public int getFila()
    {
        return fila;
    }
    public int getColumna()
    {
        return columna;
    }
    public int getMinasProximas()
    {
        return minasProximas;
    }
    public boolean isMina()
    {
        return mina;
    }
    public boolean isAbierta()
    {
        return abierta;
    }
    public boolean isMarcada()
    {
        return marcada;
    }
    
    
    
}
