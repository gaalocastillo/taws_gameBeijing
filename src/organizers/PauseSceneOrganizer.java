/*
* @(#)PauseSceneOrganizer.java 2.0 08/02/2017
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
import javafx.scene.layout.VBox;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz 
 * gráfica de la ventana que se muestra al jugador cuando se pausa el juego
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class PauseSceneOrganizer
{
    // ATRIBUTOS
    /** Panel que contiene todos los nodos necesarios para la escena del 
     juego cuando se pone pausa */
    private BorderPane root;
    /** Panel que contiene los botones para retomar la partida o 
     regresar al menu principal */
    private VBox container;
    /** Panel que contiene el Label que muestra el mensaje en pantalla */
    private HBox title;
    /** Label que contiene el mensaje de juego pausado que se muestra al
     jugador */
    private Label mensaje;
    /** Botones que permiten regresar al menu principal o continuar la 
     partida */
    private Button resume,menu;
    
    // MÉTODOS
    /**
     * Método contructor de la clase que no recibe ningun parametro y en donde
     * se instancian todos los atributos que luego son agregados a la raiz
     * de PauseSceneOrganizer
     */
    public PauseSceneOrganizer()
    {
        root = new BorderPane();
        root.setId("rootPause");
        
        mensaje = new Label("Pause");
        
        resume = new Button("Resume");
        resume.setOnMouseClicked((event) ->
        {
            InvasionAlien.regresarGameScene();
        });
        
        menu = new Button("Menu");
        menu.setOnMouseClicked((event) ->
        {
            InvasionAlien.terminarGameScene();
        });
        
        title = new HBox(mensaje);    
        title.setPadding(new Insets(15));
        title.setAlignment(Pos.CENTER);
        
        container = new VBox(resume,menu);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(50);
        
        root.setCenter(container);
        root.setTop(title);
    } // Cierre del metodo
    
    /**
     * Método que permite obtener la raíz que contiene la pantalla de pausa 
     * del juego
     * @return BorderPane raiz donde se encuentra la parte visual de la 
     * pantalla de pausa
     */
    public BorderPane getRoot()
    {return root;} // Cierre del metodo 
    
} // Cierre de la clase
