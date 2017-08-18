/*
* @(#)Ovni2.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components.enemies;

import controllers.Constants;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Ovni2 extends Ovni implements Runnable
{
    
    
    public Ovni2(Double posx, Double posy, Pane root,int v)
    {
        super(root,Constants.ovniPoints,Constants.lifesOvni2,v);
        plantilla.setLayoutX(posx);
        plantilla.setLayoutY(posy);
        super.color = Color.CHARTREUSE;
        for (int i=0; i<=7; i++)
        {
            for (int j=0; j<=9; j++)
            {
                if (i==0)
                {
                    if (j==2 || j==7)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==1)
                {
                    if (j==0 || j==3 || j==6 || j==9)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==2)
                {
                    if (j!=1 && j!=8)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==3)
                {
                    if (j!=3 && j!=6 && j!=10)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==4)
                {
                    if (j!=10)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==5)
                {
                    if (j!=0 && j!=9 && j!=10)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==6)
                {
                    if (j==2 || j==7)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
                else if (i==7)
                {
                    if (j==1 || j==8)
                        plantilla.add(new Rectangle(Constants.pixelSize, Constants.pixelSize, Color.CHARTREUSE), j, i);
                }
            }
        }
        
        root.getChildren().add(this.plantilla);
        
    }
    
    public int getPuntos()
    {return puntos;}
    
    public GridPane getPane()
    {return plantilla;}
    
    public void moverDerecha()
    {plantilla.setLayoutX(plantilla.getLayoutX()+Constants.movePixels2);}
    
    public void moverIzquierda()
    {plantilla.setLayoutX(plantilla.getLayoutX()-Constants.movePixels2);}
    
    public void moverAbajo()
    {plantilla.setLayoutY(plantilla.getLayoutY()+Constants.downPixels);}

    @Override
    public void run()
    {
        while (vidas!=0 && plantilla.getLayoutY()+plantilla.getHeight() <= 555)
        {
            try
            {
                for (int i=1; i<=5; i++)
                {
                    Platform.runLater(() -> {moverDerecha();});
                    Thread.sleep(velocidad);
                }
                Platform.runLater(() -> {moverAbajo();});
                Thread.sleep(velocidad);
                for (int i=1; i<=5; i++)
                {
                    Platform.runLater(() -> {moverIzquierda();});
                    Thread.sleep(velocidad);
                }
                Platform.runLater(() -> {moverAbajo();});
                Thread.sleep(velocidad);
               
            }
            catch(Exception ex){}
        }
        Platform.runLater(() -> {morir();});
    }
}