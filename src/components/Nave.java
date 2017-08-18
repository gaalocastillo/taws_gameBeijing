/*
* @(#)Nave.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components;

import controllers.Constants;
import components.enemies.Ovni;
import java.util.LinkedList;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Esta clase es donde se encuentran los atributos y métodos del objeto
 * Nave, encargado de destruir a los ovnis con los disparos. Interactúa
 * con los Ovnis, la bala y el misil.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */
public class Nave
{
    // ATRIBUTOS
    /** Panel raiz que contiene todos los nodos necesarios para
     la visualización de la nave*/
    GridPane plantilla;
    Pane contenedor;
    
    public Nave(Pane root)
    {
        plantilla = new GridPane();
        
        plantilla.setLayoutX(375.0);
        plantilla.setLayoutY(590.0);
        
        for (int i=0; i<=3; i++)
        {
            for (int j=0; j<=9; j++)
            {
                if (i==0)
                {
                    if (j==4 || j==5)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE), j, i);
                }
                else if (i==1)
                {
                    if (j!=0 && j!=1 && j!=8 && j!=9)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.GRAY), j, i);
                }
                else
                    plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE), j, i);
            }
        }
        contenedor = root;
        root.getChildren().addAll(plantilla);
    }
    
    public void moverDerecha()
    {
        double distancia = plantilla.getLayoutX() + Constants.pixelMovement;
        if (distancia + plantilla.getWidth() - Constants.pixelSize < 750)
            plantilla.setLayoutX(distancia);
    }
    
    public void moverIzquierda()
    {
        double distancia = plantilla.getLayoutX() - Constants.pixelMovement;
        if (distancia + Constants.pixelSize > 0)
            plantilla.setLayoutX(distancia);
    }
    
    public void disparar(Pane root, LinkedList<Ovni> enemigos, LinkedList<Thread> hilos)
    {
        BalaNave balaNave = new BalaNave(root, plantilla.getLayoutX(), plantilla.getLayoutY(), enemigos);
        Thread hilobala = new Thread(balaNave);
        hilobala.start();       
        hilos.add(hilobala);
    }
    
    public void dispararMisil(Pane root, LinkedList<Ovni> enemigos, LinkedList<Thread> hilos)
    {
        Misil misil = new Misil(root, plantilla.getLayoutX(), plantilla.getLayoutY(),enemigos);
        Thread hilomisil = new Thread(misil);
        hilomisil.start();
        hilos.add(hilomisil);
    }

    public GridPane getPlantilla() {
        return plantilla;
    }
    public void setPlantilla(GridPane plantilla) {
        this.plantilla = plantilla;
    } 
}
