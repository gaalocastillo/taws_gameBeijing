/*
* @(#)BalaOvni.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components;

import controllers.Constants;
import organizers.GameSceneOrganizer;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BalaOvni implements Runnable
{
    private GridPane plantilla;
    private Pane contenedor;
    private Nave nave;
    private boolean colision = false;
    
     public BalaOvni(Pane root, Double posx, Double posy, Nave nave)
    {
        contenedor = root;
        this.nave = nave;
        plantilla = new GridPane();
        for (int i=0; i<=2; i++)
            for (int j=0; j<=1; j++)
                plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.RED),j,i);
            
        plantilla.setLayoutX(posx+plantilla.getWidth()/2);
        plantilla.setLayoutY(posy);
        
        contenedor.getChildren().add(plantilla);
    }
     
    public void desaparecer()
    {contenedor.getChildren().remove(plantilla);}
     
    public void mover()
    {plantilla.setLayoutY(plantilla.getLayoutY() + Constants.pixelMovement);}

    @Override
    public void run() {
        while(plantilla.getLayoutY()+plantilla.getHeight()<600 && !colision)
        {
            Platform.runLater(() -> {
                mover();
                if (plantilla.getBoundsInParent().intersects(nave.getPlantilla().getBoundsInParent()))
                {
                    GameSceneOrganizer.getJugador().setVidas(GameSceneOrganizer.getJugador().getVidas()-1);
                    colision = true;
                }
            });
            try {Thread.sleep(35);}
            catch (InterruptedException ex) {}
        }
        Platform.runLater(() -> {desaparecer();});
    }    
}
