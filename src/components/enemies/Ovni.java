/*
* @(#)Ovni.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package components.enemies;

import components.BalaOvni;
import components.Nave;
import java.util.Random;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Esta clase es donde se encuentran los atributos y métodos generales
 * que van a ser heredados a los diferentes tipos de ovnis.
 * Es una clase padre.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */
public class Ovni {
    
    protected Pane contenedor;
    protected int puntos;
    protected GridPane plantilla = new GridPane();
    protected int vidas;
    protected int velocidad;
    protected Color color;
    
    public Ovni(Pane contenedor, int puntos,int vidas,int velocidad)
    {
        this.contenedor = contenedor;
        this.puntos = puntos;
        this.vidas = vidas;
        this.velocidad = velocidad;
    }
    
    public int getPuntos()
    {return puntos;}
    
    public GridPane getPane()
    {return plantilla;}
    
    public int getVidas()
    {return vidas;}
    
    public void setVidas(int vidas)
    {this.vidas = vidas;}

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    
    
    
     public void disparar(Pane root, Nave nave, int chance)
    {
        Random rd = new Random();
        int posibilidad = rd.nextInt(100)+1;
        if (posibilidad > chance)
        {
            BalaOvni balaOvni = new BalaOvni(root, plantilla.getLayoutX(), plantilla.getLayoutY(), nave);
            Thread hiloOvni = new Thread(balaOvni);
            hiloOvni.start();
        }     
    }
     
    public void morir()
    {
        this.setVidas(0);
        this.contenedor.getChildren().remove(this.plantilla);
    }
    
    public boolean isDead()
    {return this.getVidas()==0;}

} // Cierre de la clase