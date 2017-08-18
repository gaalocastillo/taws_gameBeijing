/*
* @(#)Jugador.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package controllers;

/**
 * Esta clase es la que contiene toda la informacion del jugador en una partida
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class Jugador
{
    // ATRIBUTOS
    /** String que almacena el alias que el jugador elige para la partida */
    private String alias;
    /** Almacena la cantidad de puntos que el jugador logra en la partida */
    private int puntaje;
    /** Almacena la cantidad de vidas que el jugador tiene en la partida */
    private int vidas;
    /** Almacena la cantidad de misiles que el jugador tiene por nivel */
    private int misiles;
    
    // MÉTODOS
    /**
     * Método contructor que recibe un String, donde se instancian todos los
     * atributos de la clase
     * @param alias String que se guarda como alias del jugador
     */
    public Jugador(String alias)
    {
        this.alias = alias;
        puntaje = 0;
        vidas = Constants.playerLifes;
        misiles = 10;
    } // Cierre del metodo
    
    /**
     * Método contructor que recibe un String, y un entero
     * @param alias String que se guarda como alias del jugador
     * @param puntaje entero que se guarda como puntaje del jugador 
     */
    public Jugador(String alias,int puntaje)
    {
        this.alias = alias;
        this.puntaje = puntaje;
    } // Cierre del metodo
    
    /**
     * Método que permite resetear la informacion del jugador. Se usa para 
     * iniciar la partida nuevamente
     */
    public void resetAll()
    {   
        vidas = 5;
        puntaje = 0;
        misiles = 10;
    } // Cierre del metodo 
    
    /**
     * Método que permite actualizar el puntaje del jugador 
     * @param puntos entero que se suma al puntaje del jugador
     */
    public void setPuntaje(int puntos)
    {puntaje += puntos;} // Cierre del metodo
    
    /**
     * Método que permite obtener el puntaje del jugador
     * @return puntaje del jugador
     */
    public int getPuntaje()
    {return puntaje;} // Cierre del metodo
    
    /**
     * Método que permite cambiar el alias del jugador
     * @param alias String que se guarda como nuevo alias del jugador 
     */
    public void setAlias(String alias)
    {this.alias = alias;} // Cierre del metodo
    
    /**
     * Método que permite obtener el alias del jugador
     * @return alias del jugador
     */
    public String getAlias()
    {return alias;} // Cierre del metodo
    
    /**
     * Método que permite cambiar el numero de vidas del jugador
     * @param vidas entero que se guarda como la cantidad de vidas del jugador
     */
    public void setVidas(int vidas)
    {this.vidas = vidas;} // Cierre del metodo
    
    /**
     * Método que permite obtener el numero de vidas del jugador
     * @return numero de vidas del jugador 
     */
    public int getVidas()
    {return vidas;} // Cierre del metodo
    
    /**
     * Método que permite cambiar el numero de misiles del jugador
     * @param misiles entero que se guarda como la cantidad de misiles que 
     * tiene el jugador
     */
    public void setMisiles(int misiles)
    {this.misiles = misiles;} // Cierre del metodo
    
    /**
     * Método que permite obtener el numero de misiles del jugador
     * @return numero de misiles que tiene el jugador
     */
    public int getMisiles()
    {return misiles;} // Cierre del metodo
    
} // Cierre de la clase
