/*
* @(#)ControlRegistro.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package controllers;

import java.io.File;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Esta clase es donde se guarda el alias que el jugador elige para la partida
 * y el puntaje que obtiene
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class ControlRegistro
{
    // ATRIBUTOS
    /** Arreglo dinamico que contiene objetos tipo registo, en el cual se
     almacenan los distintos registros obtenidos en distintas partidas */
    public static LinkedList<Registro> registros = new LinkedList<>();
    
    // MÉTODOS
    /**
     * Método que permite leer el archivo de los 10 mejores puntajes 
     * obtenidos en distintas partidas, y almacenar la informacion en 
     * instancias de tipo registro
     * @return LinkedList con todos los registros obtenidos del achivo de 
     * mejores puntuaciones
     */
    public static LinkedList<Registro> leerArchivo()
    {
        try
        {
            registros = new LinkedList<>();
            Scanner scan = new Scanner(new File(System.getProperty("user.dir")+"/src/controllers/MejoresPuntajes.txt"));
            while (scan.hasNext())
            {
                String line = scan.nextLine();
                String campos[] = line.split("-");
                Registro r = new Registro(campos[0],campos[1]);
                registros.add(r);
                
            }
        }
        catch (Exception ex)
        {System.err.println(ex);}
        
        return registros;
    } // Cierre del metodo
    
    /**
     * Método que permite escribir el archivo de los 10 mejores puntajes 
     * obtenidos en distintas partidas, y comparar los distintos puntajes que 
     * ya constaban en dicho archivo con el puntaje obtenido en una 
     * actual partida y asi validar si éste debe ser guardado en el archivo 
     * o no
     * @param reg Registro que es comparado con los demas registros obtenidos 
     * del archivo
     */
    public static void escribirArchivo(Registro reg)
    {
        leerArchivo();
        registros.add(reg);
        registros.sort(new Comparator<Registro>()
        {
            @Override
            public int compare(Registro o1, Registro o2)
            {
                return  o2.puntaje.compareTo(o1.puntaje);
            }
        });
        try
        {
        PrintWriter writer = new PrintWriter(System.getProperty("user.dir")+"/src/controllers/MejoresPuntajes.txt");
        
        for (int i= 0; i<=9; i++)
            writer.println(registros.get(i).toString());
        
        writer.close();
        }
        catch (Exception e){System.err.println(e);}
    } // Cierre del metodo
    
} // Cierre de la clase
