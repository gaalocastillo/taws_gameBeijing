/*
* @(#)Misil.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components;

import components.enemies.Ovni;
import controllers.Constants;
import java.io.File;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import organizers.GameSceneOrganizer;

public class Misil implements Runnable
{
    private GridPane plantilla;
    private Pane contenedor;
    private Pane enemyHit;
    private LinkedList<Ovni> enemigos;
    private boolean colision = false;
    
    public Misil(Pane root)
    {this(root,0.0,0.0,null);}
    
    public Misil(Pane root, Double posx, Double posy,LinkedList<Ovni> targets)
    {
        contenedor = root;
        enemigos = targets;
        plantilla = new GridPane();
        
        for (int i=0; i<=6; i++)
        {
            for (int j=0; j<=4; j++)
            {
                if (i==1 || i==3 || i==4 || i==5 || i==6)
                {
                    if(j!=0 && j!=4)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE),j,i);
                }
                else if (i==0)
                {
                    if(j==2)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE),j,i);
                }
                else if (i==2)
                    plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE),j,i);
            }
        }
        
        plantilla.setLayoutX(posx+(3*Constants.pixelSize));
        
        plantilla.setLayoutY(600);
        
        contenedor.getChildren().add(plantilla);
    }
    
    public void desaparecer()
    {contenedor.getChildren().remove(this.plantilla);}
    
    public void mover()
    {plantilla.setLayoutY(plantilla.getLayoutY() - Constants.pixelMovement);}
    
    public Pane getHit()
    {return enemyHit;}
    
    @Override
    public void run() {
        while(plantilla.getLayoutY()>-plantilla.getHeight() && !colision)
        {
            Platform.runLater(() -> {
                mover();
                Ovni temp = null;
                for (Ovni enemy: enemigos)
                {
                    temp = enemy;
                    if (plantilla.getBoundsInParent().intersects(temp.getPane().getBoundsInParent()))
                    {
                        temp.setVidas(0);
                        colision = true;
                        break;
                    }
                }
                if (temp!=null && temp.getVidas()==0)
                {
                    setupOvniSound();
                    GameSceneOrganizer.getJugador().setPuntaje(temp.getPuntos());
                    enemigos.remove(temp);
                    contenedor.getChildren().remove(temp.getPane());
                }   
            });
            try {Thread.sleep(10);}
            catch (InterruptedException ex) {}
        }
        Platform.runLater(() -> {desaparecer();});
    }
    
    private void setupOvniSound()
    {
        MediaPlayer ovniSound = new MediaPlayer(new Media(new File(Constants.soundOvni).toURI().toString()));
        ovniSound.play();
    } // Cierre del método
}
