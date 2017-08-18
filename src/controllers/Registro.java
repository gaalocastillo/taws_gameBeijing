/*
* @(#)Registro.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package controllers;

/**
 * Esta clase es donde se guarda el alias que el jugador elige para la partida
 * y el puntaje que obtiene
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class Registro
{
    // ATRIBUTOS
    /** String que almacena el alias del jugador */
    String jugador;
    /** String que almacena el puntaje del jugador */
    String puntaje;
    
    // MÉTODOS
    /**
     * Método constructor de la clase, que recibe como parámetro
     * dos strings y los guarda en los atributos de la clase
     * @param jugador String que se guarda en el atributo jugador 
     * @param puntaje String que se guarda en el atributo puntaje
     */
    public Registro(String jugador, String puntaje) 
    {
        this.jugador = jugador;
        this.puntaje = puntaje;
    } // Cierre del metodo
    
    /**
     * Método que permite obtener un solo String con los atributos de
     * la clase concatenados
     * @return String de los datos del jugador por partida
     */
    public String toString()
    {return this.jugador + "-" + this.puntaje;} // Cierre del metodo
    
} // Cierre de la clase
