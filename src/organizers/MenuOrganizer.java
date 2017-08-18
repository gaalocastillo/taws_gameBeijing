/*
* @(#)MenuOrganizer.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package organizers;

import invasionalien.InvasionAlien;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz
 * gráfica del menú del juego.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class MenuOrganizer
{
    // ATRIBUTOS
    /** Panel raiz que contiene todos los nodos necesarios para
     la visualización del menú del juego */
    private BorderPane rootMenu;
    
    // MÉTODOS
    /**
     * Método constructor de la clase, que no recibe ningún parámetro
     * e instancia los atributos, y agrega los nodos a la raíz de MenuOrganizer.
     */
    public MenuOrganizer(Stage stage)
    {
        rootMenu = new BorderPane();
        rootMenu.setId("rootMenu");
           
        Label texto = new Label("BEIJING INVADERS");
        texto.setTextAlignment(TextAlignment.CENTER);
            
        VBox contenedor = new VBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(25);
        
        VBox caja  = new VBox();
        caja.setAlignment(Pos.CENTER);
        caja.setSpacing(200);
        
        Button btnJugar = new Button("Play");
        Button btnPuntuaciones = new Button("Bests Scores");
        Button btnAcercade = new Button("About");
        Button btnQuit = new Button("Quit");
            
        contenedor.getChildren().addAll(btnJugar,btnPuntuaciones,btnAcercade,btnQuit);
        caja.getChildren().addAll(texto,contenedor);
        
        
        btnJugar.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarInputScene();
        });
        
        btnPuntuaciones.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarScoresScene();
        });
        
        btnAcercade.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarInfoScene();
        });
        
        btnQuit.setOnMouseClicked((event) ->
        {
            stage.close();
        });
        
        rootMenu.setOnKeyPressed((event) -> {
            if(event.getCode()==KeyCode.E)
                InvasionAlien.cambiarInputScene();
            else if (event.getCode()==KeyCode.Q)
                stage.close();
        });
            
        rootMenu.setCenter(caja);
    } // Cierre del constructor
    
    /**
     * Método que permite obtener la raíz que contiene la interfaz 
     * gráfica de la ventana del menú del juego.
     * @return BorderPane raiz, el cual contiene todos los elementos
     * de la la interfaz gráfica de la ventana del menú del juego.
     */
    public BorderPane getRoot()
    {return rootMenu;} // Cierre del método
    
} // Cierre de la clase