/*
* @(#)EndSceneOrganizer.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package organizers;

import invasionalien.InvasionAlien;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz 
 * gráfica de la ventana que se muestra al jugador
 * una vez que termina el juego.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class EndSceneOrganizer 
{    
    // ATRIBUTOS
    /** Panel raiz que contiene todos los nodos necesarios para
     la escena del juego al terminar una partida */
    private BorderPane root;
    /** Label que contiene el mensaje que se le muestra al jugador
     dependiendo si gana o pierde la partida */
    private Label mensaje;
    
    // MÉTODOS
    /**
     * Método constructor de la clase, que recibe como parámetro
     * un string e instancia los atributos, y agrega los nodos a la raíz de
     * EndSceneOrganizer.
     * @param s que representa el mensaje que recibira la instancia de 
     * esta clase, el cual sera diferente dependiendo si el jugador gana o
     * pierde la partida
     */
    public EndSceneOrganizer(String s) 
    {
        root = new BorderPane();
        root.setId("rootMenu");
        
        mensaje = new Label(s);
        mensaje.setPadding(new Insets(15));
        
        root.setCenter(mensaje);
        
        Button playAgain = new Button("Play Again");
        playAgain.setOnMouseClicked((event) ->
        {
            GameSceneOrganizer.getJugador().resetAll();
            InvasionAlien.cambiarGameScene();
        });
        
        Button menu = new Button("Menu");
        menu.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarMenuScene();
        });
        
        HBox bottomButton = new HBox(menu,playAgain);
        bottomButton.setSpacing(25);
        bottomButton.setPadding(new Insets(15));
        bottomButton.setAlignment(Pos.CENTER);
        
        root.setBottom(bottomButton);
    } // Cierre del metodo
    
    /**
     * Método que permite obtener la raíz que contiene la pantalla final del
     * juego
     * @return BorderPane raiz, el cual contiene el mensaje final de la partida
     */
    public BorderPane getRoot()
    {return root;} // Cierre del metodo
    
} // Cierre de la clase
