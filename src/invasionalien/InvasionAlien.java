/*
* @(#)InvasionAlien.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reserv ados.
*/

package invasionalien;

import organizers.ScoresSceneOrganizer;
import organizers.PauseSceneOrganizer;
import organizers.MenuOrganizer;
import organizers.GameSceneOrganizer;
import organizers.InputOrganizer;
import organizers.GameInformationOrganizer;
import organizers.EndSceneOrganizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Esta clase es donde se encuentra el método main 
 * para ejecutar el programa principal de InvasionAlien.
 * Extiende de la clase Application.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez.
 * @version: 2.0 08/02/2017
 */

public class InvasionAlien extends Application
{
    // ATRIBUTOS
    /** Instancia de la clase que organiza los elementos de la interfaz
     gráfica del menú del juego */
    private static MenuOrganizer menuScene;
    /** Instancia de la clase que organiza los elementos de la interfaz
     gráfica que solicita ingreso de datos por teclado al jugador */
    private static InputOrganizer inputScene;
    /** Instancia de la clase que organiza los elementos de la interfaz
     gráfica del juego al iniciar una partida */
    private static GameSceneOrganizer gameScene;
    /** Escena que se ejecuta en el escenario para mostrar la interfaz gráfica
     del juego */
    private static ScoresSceneOrganizer scoresScene;
    private static PauseSceneOrganizer pauseScene;
    private static GameInformationOrganizer gameInfo; 
    private static EndSceneOrganizer endScene;
    private static Scene scene;
    private static Thread game;
    
    // MÉTODOS
    /** 
     * Método que sobreescribe el método start(Stage s).
     * Se hereda de la clase Application y se define para poder ejecutar
     * la aplicación.
     */
    @Override
    public void start(Stage primaryStage)
    {
        menuScene = new MenuOrganizer(primaryStage);
        pauseScene = new PauseSceneOrganizer();
        gameInfo = new GameInformationOrganizer();
        
        scene = new Scene(menuScene.getRoot(), 800, 600);
        scene.getStylesheets().add("menuStyle.css");
        primaryStage.setTitle("Beijing Invaders");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    } // Cierre del método
    
    /**
     * Método main que permite la ejecución de InvasionAlien    
     * @param args El parámetro args es un arreglo de argumentos
     * de la línea de la consola.
     */
    public static void main(String[] args)
    {launch(args);} // Cierre del método
    
    /**
     * Método que permite cambiar la raíz de la escena del panel principal
     * a la escena del ingreso de usuario del jugador
     */
    public static void cambiarInputScene()
    {
        inputScene = new InputOrganizer();
        scene.setRoot(inputScene.getRoot());
    } // Cierre del método
    
    /**
     * Método que permite cambiar la raíz de la escena del panel principal
     * a la escena del juego (en donde se encuentran los ovnis y la nave)
     */
    public static void cambiarGameScene()
    {
        gameScene = new GameSceneOrganizer();
        scene.setRoot(gameScene.getRoot());
        game = new Thread(gameScene);
        game.start();
    } // Cierre del método
    
    public static void cambiarMenuScene()
    {scene.setRoot(menuScene.getRoot());}
    
    public static void cambiarInfoScene()
    {scene.setRoot(gameInfo.getRoot());}
            
    public static void cambiarScoresScene()
    {
        scoresScene = new ScoresSceneOrganizer();
        scene.setRoot(scoresScene.getRoot());
    }
    
    public static void cambiarPauseScene()
    {scene.setRoot(pauseScene.getRoot());}
    
    public static void cambiarEndScene(String mensaje)
    {
        endScene = new EndSceneOrganizer(mensaje);
        scene.setRoot(endScene.getRoot());
    }
    
    public static void regresarGameScene()
    {
        scene.setRoot(gameScene.getRoot());
        game.resume();
        gameScene.resume();
    }
    
    public static void terminarGameScene()
    {
        scene.setRoot(menuScene.getRoot());
        game.stop();
        gameScene.stop();
    }
    
    public static void pausarJuego()
    {game.suspend();}
        
} // Cierre de la clase
