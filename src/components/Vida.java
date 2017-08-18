/*
* @(#)Vida.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/
package components;

import controllers.Constants;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Vida {
    
    private GridPane plantilla;
    
    public Vida(Pane root)
    {
        plantilla = new GridPane();
        
        for (int i=0;i<6;i++)
        {
            for (int j=0;j<7;j++)
            {
                if (i==0)
                {
                    if (j==1 || j==2 || j==4 || j==5)
                        plantilla.add(new Rectangle(Constants.pixelSize,Constants.pixelSize,Color.RED),j,i);
                }
                else if (i==1 || i==2)
                {
                    plantilla.add(new Rectangle(Constants.pixelSize,Constants.pixelSize,Color.RED),j,i);
                }
                else if (i==3)
                {
                    if (j!=0 && j!=6)
                        plantilla.add(new Rectangle(Constants.pixelSize,Constants.pixelSize,Color.RED),j,i);
                }
                else if (i==4)
                {
                    if (j==2 || j==3 || j==4)
                        plantilla.add(new Rectangle(Constants.pixelSize,Constants.pixelSize,Color.RED),j,i);
                }
                else if (i==5)
                {
                    if (j==3)
                        plantilla.add(new Rectangle(Constants.pixelSize,Constants.pixelSize,Color.RED),j,i);
                }
            }
        }
        
        root.getChildren().add(plantilla);
        
    }
    
}
