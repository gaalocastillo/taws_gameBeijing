/*
* @(#)Constants.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package controllers;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz
 * gráfica que solicita ingreso de datos por teclado al jugador.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public final class Constants
{
    // ATRIBUTOS
    /** Valor final del tamaño de los pixeles utilizado en la creación
     de los diferentes tipos de Ovnis, en la nave, en el misil y en la bala*/
    public static int pixelSize = 5;
    /** Valor final del tamaño de pixeles que se desplaza la nave, la bala y los ovnis */
    public static int pixelMovement = 10;
    
    public static int ovniPoints = 1000;
    public static int lifesOvni1 = 1;
    public static int lifesOvni2 = 3;
    public static int lifesOvni3 = 5;
    
    public static int playerLifes = 5;
    
    /** Canción final utilizada en todo el juego, la cual se obtiene
     de una carpeta perteneciente al proyecto*/
    public static String songName = System.getProperty("user.dir")+"/src/media/background_song.mp3";
    public static String soundMisil = System.getProperty("user.dir")+"/src/media/misil_sound.mp3";
    public static String soundBala = System.getProperty("user.dir")+"/src/media/bala_sound.mp3";
    public static String soundOvni = System.getProperty("user.dir")+"/src/media/ovni_sound.mp3";
    
    public static String win = "You've saved BEIJING!";
    public static String lose = "Aliens took control of BEIJING,\nYou failed us!";
    
    public static int downPixels = 10;
    public static int movePixels1 = 8;
    public static int movePixels2 = 12;
    public static int movePixels3 = 15;

    public static int speed1 = 400;
    public static int speed2 = 300;
    public static int speed3 = 150;
} // Cierre de la clase
