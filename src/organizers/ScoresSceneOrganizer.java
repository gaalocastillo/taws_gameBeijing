/*
* @(#)ScoresSceneOrganizer.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package organizers;

import controllers.Registro;
import invasionalien.InvasionAlien;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz 
 * gráfica de la ventana que muestra los mejores puntajes obtenidos en 
 * el juego
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class ScoresSceneOrganizer
{
    // ATRIBUTOS
    /** Panel que contiene todos los nodos necesarios para la escena del 
     juego cuando se quiere revisar los mejores puntajes */
    BorderPane root;
    /** Panel que contiene el boton y el label usados para la interfaz grafica
     de esta pantalla */
    VBox container;
    /** Paneles que contienen el boton para retornar al menu principal y el 
     mensaje de mejores puntajes respectivamente */
    HBox bottomButton,title;
    /** Boton que nos permite regresar al menu del juego */
    Button goBack;
    /** Label que muestra el mensaje de mejores puntajes */
    Label titulo;
    /** Arreglo dinamico que contiene objetos tipo registro */
    LinkedList<Registro> registros = controllers.ControlRegistro.leerArchivo();
    
    // MÉTODOS
    /**
     * Método contructor de la clase que no recibe ningun parametro y en donde
     * se instancian todos los atributos que luego son agregados a la raiz
     * de ScoresSceneOrganizer
     */
    public ScoresSceneOrganizer()
    {
        String scores;
        Label resultado;

        root = new BorderPane();
        root.setId("rootScores");

        container = new VBox();
        bottomButton = new HBox();
        title = new HBox();
        titulo = new Label("Best Scores");
        
        for (Registro reg: registros)
        {
            resultado = new Label(reg.toString());
            resultado.setId("registroPuntaje");
            container.getChildren().add(resultado);
        }
        
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);
        
        goBack = new Button("Return");
        goBack.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarMenuScene();
        });
        
        bottomButton.getChildren().add(goBack);
        bottomButton.setPadding(new Insets(15));
        bottomButton.setAlignment(Pos.CENTER);
        
        title.getChildren().add(titulo);
        title.setPadding(new Insets(15));
        title.setAlignment(Pos.CENTER);
        
        root.setCenter(container);
        root.setTop(title);
        root.setBottom(bottomButton);
    } // Cierre del metodo
    
    /**
     * Método que permite obtener la raíz que contiene la pantalla de mejores
     * puntajes del juego
     * @return BorderPane raiz donde se encuentra la parte visual de la 
     * pantalla de mejores puntajes
     */
    public BorderPane getRoot() 
    {return root;} // Cierre del metodo
    
} // Cierre de la clase
