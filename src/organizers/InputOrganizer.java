/*
* @(#)InputOrganizer.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/

package organizers;

import invasionalien.InvasionAlien;
import controllers.Jugador;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Esta clase es donde se  organiza los elementos de la interfaz
 * gráfica que solicita ingreso de datos por teclado al jugador.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class InputOrganizer
{
    // ATRIBUTOS
    /** Panel raiz que contiene todos los nodos necesarios para
     el ingreso del usuario del jugador */
    private BorderPane root;
    /** Nodo que permite al usuario el ingreso de texto, se muestra
     como una ventana en la cual se puede escribir */
    private static TextField ingreso;
    /** Mensaje que se muestra al usuario, en el cual se le solicita 
     el ingreso del nombre */
    private Label mensaje;
    private static Jugador jugador;
    
    // MÉTODOS
    /**
     * Método constructor de la clase, que no recibe ningún parámetro
     * e instancia los atributos, y agrega los nodos a la raíz de InputOrganizer.
     */
    public InputOrganizer()
    {
        jugador = new Jugador("");
        root = new BorderPane();
        root.setId("rootInput");
        mensaje = new Label("Enter username");
        ingreso  = new TextField();
        ingreso.setMaxWidth(180);
        VBox container = new VBox(mensaje,ingreso);
        container.setSpacing(20);
        container.setAlignment(Pos.CENTER);
        
        ingreso.setOnKeyPressed((event)->{
            if (event.getCode()==KeyCode.ENTER && !ingreso.getText().trim().equals(""))
            {
                jugador.setAlias(ingreso.getText());
                InvasionAlien.cambiarGameScene();
            }
        });
        
        root.setCenter(container);
    } // Cierre del constructor
    
    /**
     * Método que permite obtener la raíz que contiene la interfaz 
     * gráfica de la ventana de ingreso de usuario del jugador.
     * @return BorderPane raiz, el cual contiene todos los elementos
     * de la interfaz gráfica de la ventana de ingreso de usuario del jugador.
     */
    public BorderPane getRoot()
    {return root;} // Cierre del método
    
    public static Jugador getJugador()
    {return jugador;}
    
} // Cierre de la clase
