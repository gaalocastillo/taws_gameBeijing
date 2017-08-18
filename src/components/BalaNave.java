/*
* @(#)BalaNave.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components;

import controllers.Constants;
import components.enemies.Ovni;
import java.io.File;
import organizers.GameSceneOrganizer;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class BalaNave implements Runnable
{
    
    private GridPane plantilla;
    private Pane contenedor;
    private LinkedList<Ovni> enemigos;
    private boolean colision = false;
    
    public BalaNave(Pane root, Double posx, Double posy, LinkedList<Ovni> targets)
    {
        contenedor = root;
        enemigos = targets;
        plantilla = new GridPane();
        for (int i=0; i<=4; i++)
            for (int j=0; j<=1; j++)
                plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.WHITE),j,i);
            
        plantilla.setLayoutX(posx+(4*Constants.pixelSize));
        plantilla.setLayoutY(600);
        
        contenedor.getChildren().add(plantilla);
    }

    public void desaparecer()
    {contenedor.getChildren().remove(this.plantilla);}
    
    public void mover()
    {plantilla.setLayoutY(plantilla.getLayoutY() - Constants.pixelMovement);}

    @Override
    public void run()
    {
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
                        temp.setVidas(temp.getVidas()-1);
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
            try {Thread.sleep(15);}
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
