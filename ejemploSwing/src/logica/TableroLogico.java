/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author david
 */
public class TableroLogico 
{
    private int dim; //solo se indica un valor ya que el tablero siempre será cuadrado.
    private int numMinas;
    private int numCasillasAbiertas;
    private Casilla[][] tableroJuego;
    private ArrayList<Casilla> listaMinas;//se guardan las casillas con mina
    private boolean juegoGanado;
    
    //Constructor
    public TableroLogico(int dimension, int minas)
    {
        dim=dimension;
        numMinas=minas;
        listaMinas = new ArrayList<Casilla>();
        tableroJuego = new Casilla [dim][dim];
        juegoGanado=false;
        inicioTablero();
        colocaMinas();
        colocaPistas();
    }
    
    //get/set
    public Casilla[][] getTableroJuego() {
        return tableroJuego;
    }

    public ArrayList<Casilla> getListaMinas() {
        return listaMinas;
    }

    public boolean isJuegoGanado() {
        return juegoGanado;
    }
    
    
    
    //funciones
    private void inicioTablero()
    {
       for(int i=0; i<dim; i++) 
       {
           for (int j=0; j<dim; j++)
           {
               tableroJuego [i][j] = new Casilla (i,j);
           }
       }
    
    }

    
    private void colocaMinas()
    {
        Random r = new Random();
        int f, c, cont=0;
        do
        {
            f = r.nextInt(dim);
            c= r.nextInt(dim);
            if(!tableroJuego[f][c].isMina())
            {
                tableroJuego[f][c].setMina(true);
                cont++;
                listaMinas.add(tableroJuego[f][c]);
            }
        }while (cont < numMinas);
    }
    
    private void colocaPistas()
    {
        //se podria hacer recorriendo el arrayList
       for(int i=0; i<dim; i++) 
       {
           for (int j=0; j<dim; j++)
           {
               if(tableroJuego [i][j].isMina())
               {
                   calcular(i,j);
               }
           }
       }
    
    }
    
    private void calcular(int f, int c)
    {
        for(int i= Math.max(0,f-1); i<= Math.min(f+1,dim-1); i++)
        {
            for(int j= Math.max(0,c-1); j<=Math.min(c+1,dim-1);j++)
            {
                if(!tableroJuego[i][j].isMina())
                {
                    tableroJuego[i][j].incrementarMina();
                }
            }
        }
    }
    
    public void pintarTablero()
    {
        for(int i=0; i<dim; i++) 
       {
           for (int j=0; j<dim; j++)
           {
               if(tableroJuego[i][j].isMina())
               {
                   System.out.print("*\t");
               }
               else
               {
                    System.out.print(tableroJuego[i][j].getMinasProximas()+"\t");
               }
           }
           System.out.println();
       }
    }
    
    public void destaparCasilla(int f, int c)
    {
        //pongo abierta la casilla
        tableroJuego[f][c].setAbierta(true);
        numCasillasAbiertas++;
        if(comprobarGanado())
        {
            return;
        }
        //pone abiertas las de alrededor salvo que sea mina.
        for(int i= Math.max(0,f-1); i<= Math.min(f+1,dim-1); i++)
        {
            for(int j= Math.max(0,c-1); j<=Math.min(c+1,dim-1);j++)
            {
                if(!tableroJuego[i][j].isMina() && !tableroJuego[i][j].isAbierta() )
                {
                    if(tableroJuego[i][j].getMinasProximas()==0)
                    {
                        destaparCasilla(i, j);
                    }
                    else
                    {
                        tableroJuego[i][j].setAbierta(true);
                        numCasillasAbiertas++;
                        if(comprobarGanado())
                        {
                            return;
                        }
                    }
                }
            }
        }
    }
    
    private boolean comprobarGanado()
    {
        if((dim*dim)-numCasillasAbiertas == numMinas)
        {
            juegoGanado = true;
            return juegoGanado;
        }
        return juegoGanado;
    }

}
