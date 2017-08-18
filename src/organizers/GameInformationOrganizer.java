/*
* @(#)GameInformationOrganizer.java 2.0 08/02/2017
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
import javafx.scene.text.TextAlignment; 
import javafx.scene.text.TextFlow; 

/**
 * Esta clase es donde se  organiza los elementos de la interfaz 
 * gráfica de la ventana que brinda información del juego.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */

public class GameInformationOrganizer 
{
    private BorderPane root;
    
    public GameInformationOrganizer()
    {
        root = new BorderPane();
        root.setId("rootInfo");
        Label info = new Label("About Beijing Invaders");
        Label instructions = new Label("\nInstructions");
        Label ovnis = new Label("\nAbout the enemies");

        VBox texto = new VBox();
        texto.setPadding(new Insets(15));
        
        Text text1 = new Text("Beijing Invaders is one of the earliest shooting "
                + "games. \nThe goal is to defeat waves of aliens with a laser "
                + "cannon to \nearn as many points as possible. \nDestroy all "
                + "the aliens before they reach the city of Beijing.");
        
        Text text2 = new Text("\n\nEnjoy passing all "
                + "the levels.\nYou will love it.\nTrust me. \nIt is just awesome. "
                + "\nThe best game you will ever play.");
        
        Text text3 = new Text("*Press E to start the game"
                + "\n*Press space bar to shoot \n*Press M to shot missiles"
                + "\n*Press P to pause the game"
                + "\n*Press Esc to leave the game"
                + "\n\nThere are three levels and you get ten missiles on each one."
                + "\nEverytime you destroy an alien, you will get 1000 points."
                + "\nEverytime you earn 10000 points, you will get one live."
                + "\nEverytime an alien shots you, you will lose one live. "
                + "\nEverytime an alien gets to the Earth, you will lose two lives. ");
        
        Text text4 = new Text("There are three types of ovnis:"
                + "\n*Color grey: It gets destroy with one bullet shot or one missile"
                + "\n*Color green: It gets destroy with three bullet shots or one missile"
                + "\n*Color red: It gets destroy with five bullet shots or one missile");
        
        Text text5 = new Text("\n\nCopyright (c) 2016 Viviana Laurido, "
                + "Alexis Rivadeneira y Miguel Sánchez");
         
        text1.setFont(new Font(25));
        text1.setFill(Color.CHARTREUSE);
        
        text2.setFont(new Font(25));
        text2.setFill(Color.CHARTREUSE);
        
        text3.setFont(new Font(25));
        text3.setFill(Color.CHARTREUSE);
        
        text4.setFont(new Font(25));
        text4.setFill(Color.CHARTREUSE);
        
        text5.setFont(new Font(12));
        text5.setFill(Color.GRAY);
        
        TextFlow infoPane = new TextFlow();
        infoPane.setTextAlignment(TextAlignment.JUSTIFY);
        infoPane.getChildren().addAll(text1,text2);
        
        TextFlow instPane = new TextFlow();
        instPane.setTextAlignment(TextAlignment.JUSTIFY);
        instPane.getChildren().addAll(text3);
        
        TextFlow ovniInfoPane = new TextFlow();
        ovniInfoPane.setTextAlignment(TextAlignment.JUSTIFY);
        ovniInfoPane.getChildren().addAll(text4);
        
        TextFlow copyRightPane= new TextFlow();
        copyRightPane.setTextAlignment(TextAlignment.RIGHT);
        copyRightPane.getChildren().add(text5);
        
        texto.getChildren().addAll(info,infoPane,instructions,instPane,ovnis,ovniInfoPane,copyRightPane);
        texto.setAlignment(Pos.CENTER_LEFT);
        
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setContent(texto);
        sp.setId("rootInfo");
        
        HBox buttonBox = new HBox();
        buttonBox.setPadding(new Insets(15));
        buttonBox.setAlignment(Pos.CENTER);
        Button btnReturn = new Button("Return");
        buttonBox.getChildren().add(btnReturn);
  
        root.setCenter(sp);
        root.setBottom(buttonBox);
        
         btnReturn.setOnMouseClicked((event) ->
        {
            InvasionAlien.cambiarMenuScene();
        });
    }
    
    public BorderPane getRoot()
    {return root;}
    
} // Cierre de la clase
