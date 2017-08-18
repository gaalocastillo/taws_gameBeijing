/*
* @(#)GameSceneOrganizer.java 2.0 08/02/2017
*
* Copyright (c) 2016 Viviana Laurido, Alexis Rivadeneira y Miguel Sánchez.
* Escuela Superior Politécnica del Litoral. Guayaquil, Ecuador.
* Todos los Derechos Reservados.
*
*/
package organizers;

import controllers.Constants;
import components.enemies.Ovni1;
import components.enemies.Ovni3;
import components.enemies.Ovni2;
import components.enemies.Ovni;
import components.*;
import invasionalien.InvasionAlien;
import controllers.Jugador;
import controllers.Registro;
import java.io.File;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Circle;

/**
 * Esta clase es donde se  organiza llos elementos de la interfaz
 * gráfica del juego al iniciar una partida.
 * @author: Viviana Laurido, Alexis Rivadeneira y Miguel Sanchez
 * @version: 2.0 08/02/2017
 */
public class GameSceneOrganizer implements Runnable
{
    // ATRIBUTOS
    /** Panel raiz que contiene todos los nodos necesarios para
     la escena del juego al iniciar una partida */
    private BorderPane root;
    /** Objeto tipo Nave, con la cual interactúa el usuario
     para disparar a los ovnis */
    private Nave nave;
    /** Panel en el cual van colocados los ovnis enemigos, y brinda un 
     espacio para el movimiento libre de los mismos */
    private Pane field;
    /** Canción de fondo, que se reproduce mientras el jugador se encuentra
     jugando */
    private MediaPlayer backgroundSong;
    /** Objeto jugador que es la representación del usuario en el juego */
    private static Jugador jugador;
    /** Label en el cual se actualiza visualmente el puntaje que va siendo 
     obtenido por el jugador */
    private Label puntaje;
    /** Label en el cual se actualiza visualmente la cantidad de vidas que 
     tiene el jugador */
    private Label numVidas;
    /** Label en el cual se actualiza visualmente la cantidad de misiles que 
     tiene el jugador */
    private Label lblmisiles;
    /** Label en el cual se actualiza visualmente el nivel en el se encuentra
     el jugador */
    private Label lblnivel;
    /** Panel en el cual van colocados un objeto tipo vida y el label que
     actualiza la vida del jugador */
    private HBox vidas;
    
    private Circle poder;
    /** Contador que ayuda a llevar el control para la suma de una vida
     extra por cada 25000 puntos logrados */
    private int contador = 1;
    /** Arreglo dinamico que contiene todos los hilos creados en el juego */
    private LinkedList<Thread> hilos = new LinkedList<>();
    /** Arreglo dinamico que contiene todos los objetos tipo ovni */
    private LinkedList<Ovni> enemigos = new LinkedList<>();
    /** El numero del nivel en el que se encuentra el jugador */
    private int nivel = 1;
    /** La posibilidad que tiene un ovni a disparar a la nave del jugador */
    private int chance = 95;
    
    private int punt2;
    private int punt3;
 
    // MÉTODOS
    /**
     * Método constructor de la clase, que no recibe ningún parámetro
     * e instancia los atributos, y agrega los nodos a la raíz de
     * GameSceneOrganizer.
     */
    public GameSceneOrganizer()
    {
        this.setupBackgroundSong();
    
        root = new BorderPane();
        root.setId("rootGame");
        
        field = new Pane();
        
        nave = new Nave(root);

        root.setCenter(field);
             
        this.setUpPlayerInfo();
        
        field.setFocusTraversable(true);
        
        field.setOnKeyPressed((event) -> 
        {
            if(event.getCode()==KeyCode.RIGHT)
                nave.moverDerecha();
            
            else if (event.getCode()==KeyCode.LEFT)
                nave.moverIzquierda();
            
            else if (event.getCode()==KeyCode.SPACE)
            {
                nave.disparar(field,enemigos,hilos);
                setupBalaSound();
            }   
            
            else if (event.getCode()==KeyCode.M)
            {
                if (!(jugador.getMisiles()==0)){
                    nave.dispararMisil(field,enemigos,hilos);
                    jugador.setMisiles(jugador.getMisiles()-1);
                    setupMisilSound();
                }
            }
            
            else if (event.getCode()==KeyCode.ENTER)
            {
                if ((poder.getFill()==Color.CHARTREUSE)){
                    for (Ovni enemigo: enemigos)
                    {
                        if (enemigo.getColor()==Color.CHARTREUSE)
                        {
                            enemigo.morir();
                            enemigos.remove(enemigo);
                        }
                    }
                    setupMisilSound();
                    poder.setFill(Color.WHITE);
                }
            }
            
            else if (event.getCode()==KeyCode.P)
            {
                this.suspend();
                InvasionAlien.cambiarPauseScene();
                InvasionAlien.pausarJuego();
            }
                
            
            else if (event.getCode()==KeyCode.ESCAPE)
                InvasionAlien.terminarGameScene();
                
        });
        
    } // Cierre del constructor
    
    /**
     * Método que permite obtener la raíz que contiene la interfaz 
     * gráfica de la ventana de la partida del juego.
     * @return BorderPane raiz, el cual contiene todos los elementos
     * de la la interfaz gráfica de la ventana de la partida del juego.
     */
    public BorderPane getRoot()
    {return root;} // Cierre del método
    
    /**
     * Método que va permite colocar una cierta cantidad de ovnis dependiendo
     * del nivel en el que se encuentre el jugador
     */
    private void setUpfloat()
     {
        if (nivel==1)
            setUpEnemies(4,4,4,Constants.speed1,1);
        else if (nivel==2)
        {
            punt2 = jugador.getPuntaje();
            setUpEnemies(5,5,5,Constants.speed2,2);
            jugador.setMisiles(10);
        }
        else if (nivel==3)
        {
            punt3 = jugador.getPuntaje();
            setUpEnemies(6,7,8,Constants.speed3,3);
            jugador.setMisiles(10);
        }
     } // Cierre del metodo
    
    /**
     * Método que va permite colocar los ovnis de cada tipo en el root de 
     * manera ordenada, ademas crea el hilo para cada ovni y lo pone a correr
     */
    private void setUpEnemies(int c1, int c2, int c3, int v, int n)
    {  
        for (int i=1; i<=c1; i++)
        {
            Ovni3 enemigo3 = new Ovni3(80.0*i,10.0,field,v);
            Thread malo = new Thread(enemigo3);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo3);
        }
        
        for (int i=1; i<=c2; i++)
        {
            Ovni2 enemigo2 = new Ovni2(80.0*i,80.0,field,v);
            Thread malo = new Thread(enemigo2);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo2);
        }
        
        if (n == 2 || n == 3)
        {
            for (int i=1; i<=c2; i++)
            {
            Ovni2 enemigo2 = new Ovni2(80.0*i,150.0,field,v);
            Thread malo = new Thread(enemigo2);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo2);
            }
            
            for (int i=1; i<=c3; i++)
            {
            Ovni1 enemigo1 = new Ovni1(80.0*i,220.0,field,v);
            Thread malo = new Thread(enemigo1);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo1);
            }
        } else
        {
            for (int i=1; i<=c3; i++)
            {
            Ovni1 enemigo1 = new Ovni1(80.0*i,150.0,field,v);
            Thread malo = new Thread(enemigo1);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo1);
            }
        }  
        
        /*
        for (int i=1; i<=c3; i++)
        {
            Ovni1 enemigo1 = new Ovni1(80.0*i,220.0,field,v);
            Thread malo = new Thread(enemigo1);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo1);
        }*/
        
        if (n == 3)
        {
            for (int i=1; i<=c3; i++)
            {
            Ovni1 enemigo1 = new Ovni1(80.0*i,220.0,field,v);
            Thread malo = new Thread(enemigo1);
            malo.start();
            hilos.add(malo);
            enemigos.add(enemigo1);
            }
        }
    } // Cierre del metodo
    
    /**
     * Método que va permite colocar toda la informacion del jugador en el root,
     * la cual va siendo actualizada a medida que el jugador va jugando
     */
    public void setUpPlayerInfo()
    {
        jugador = InputOrganizer.getJugador();
        GridPane playerInfo = new GridPane();
        playerInfo.setPadding(new Insets(10));
        playerInfo.getColumnConstraints().addAll(new ColumnConstraints(160),
                new ColumnConstraints(160),new ColumnConstraints(160),new ColumnConstraints(160),
                new ColumnConstraints(160));
        
        Label playerName = new Label(jugador.getAlias());
        playerName.setId("infoJugador");
        
        vidas = new HBox();
        vidas.setSpacing(8);
        vidas.setAlignment(Pos.TOP_CENTER);
        numVidas = new Label("");
        numVidas.setId("infoJugador");
        Pane vida = new Pane();
        Vida life = new Vida(vida);
        poder = new Circle(10);
        poder.setFill(Color.WHITE);
        numVidas.setText("x"+jugador.getVidas());
        vidas.getChildren().addAll(vida,numVidas,poder);  
        
        lblmisiles = new Label("Misils:" + jugador.getMisiles());
        lblmisiles.setId("infoJugador");

        lblnivel = new Label("Level: " + this.nivel);
        lblnivel.setId("infoJugador");
        
        puntaje = new Label("Score: " + String.valueOf(jugador.getPuntaje()));
        puntaje.setId("infoJugador");
        
        playerInfo.add(playerName, 0, 0);
        playerInfo.add(lblnivel, 1, 0);
        playerInfo.add(lblmisiles, 2, 0);
        playerInfo.add(vidas, 3, 0);
        playerInfo.add(puntaje, 4, 0);
        
        root.setTop(playerInfo);
    } // Cierre del metodo
    
    /**
     * Método que sobreescribe el metodo run del interface Runnable. Aquí se 
     * llama al metodo setUpFloat(), tambien se actualiza constantemente la 
     * informacion del jugador, se validan las vidas que pierde o gana y se 
     * pone a los ovnis a disparar
     */
    @Override
    public void run()
    {
        Platform.runLater(() -> {this.setUpfloat();});
        try {Thread.sleep(1000);} 
        catch (InterruptedException ex){}
        while(jugador.getVidas()>0 && !(enemigos.isEmpty()&&nivel==3))
        {
            if (nivel==1 && enemigos.isEmpty())
            {
                int puntajeNivel1 = jugador.getPuntaje();
                nivel = 2;
                chance = 92;
                jugador.setPuntaje(500);
                Platform.runLater(() -> 
                {
                    this.setUpfloat();
                });
            }
            else if (nivel==2 && enemigos.isEmpty())
            {
                nivel = 3;
                chance = 90;
                jugador.setPuntaje(500);
                Platform.runLater(() -> 
                {
                    this.setUpfloat();
                });
            }
            Platform.runLater(() ->
            {
                enemigos.forEach((enemy) ->
                {
                   if (enemy.isDead())
                   {
                       int nueva_vida = jugador.getVidas()-2;
                       if (nueva_vida >= 0)
                           jugador.setVidas(nueva_vida);
                       else
                           jugador.setVidas(0);
                       enemigos.remove(enemy);
                   }
                });
                puntaje.setText("Score: " + String.valueOf(jugador.getPuntaje()));
                lblmisiles.setText("Misils:" + jugador.getMisiles());
                lblnivel.setText("Level: " + nivel);
                if (jugador.getPuntaje() >= 25000*contador)
                {
                   jugador.setVidas(jugador.getVidas()+1);
                   contador++;
                }
                if (jugador.getPuntaje() == 4000*contador || jugador.getPuntaje()-punt2 == 4000 || jugador.getPuntaje()-punt3 ==4000)
                {
                   poder.setFill(Color.CHARTREUSE);
                }
                numVidas.setText("x"+jugador.getVidas());
            });
            Platform.runLater(() -> 
            {
                for (Ovni enemigo: enemigos)
                {
                    enemigo.disparar(field, nave, chance);
                }
            });
            try {Thread.sleep(1500);} 
            catch (InterruptedException ex){}
        }
        controllers.ControlRegistro.escribirArchivo(new Registro(jugador.getAlias(),String.valueOf(jugador.getPuntaje())));
        backgroundSong.stop();
        if (enemigos.isEmpty() && nivel==3 && jugador.getVidas()!=0)
            InvasionAlien.cambiarEndScene(Constants.win);
        else
            InvasionAlien.cambiarEndScene(Constants.lose);
    } // Cierre del metodo
    
    /**
     * Método que pone todos los hilos a correr nuevamente, después de haber
     * sido pausados
     */
    public void resume()
    {
        for (Thread h: hilos)
            h.resume();
        backgroundSong.play();
    } // Cierre del metodo
    
    /**
     * Método que permite detener todos los hilos al terminar la partida
     */
    public void stop()
    {
        for (Thread h: hilos)
            h.stop();
        backgroundSong.stop();
    } // Cierre del metodo
    
    /**
     * Método que permite pausar todos los hilos del juego
     */
    public void suspend()
    {
        for (Thread h: hilos)
            h.suspend();
        backgroundSong.pause();
    } // Cierre del metodo
    
    /**
     * Método que permite obtener el objeto tipo jugador del juego
     * @return Jugador objeto que contiene toda la informacion del usuario 
     * dentro del juego
     */
    public static Jugador getJugador()
    {return jugador;} // Cierre del metodo
    
    /**
     * Método que permite colocar como canción un Media File y
     * la reproduce.
     */
    
    private void setupBackgroundSong()
    {
        backgroundSong = new MediaPlayer(new Media(new File(Constants.songName).toURI().toString()));
        backgroundSong.setOnEndOfMedia(new Runnable()
        {
              public void run()
              {backgroundSong.seek(Duration.ZERO);}
          });
        backgroundSong.play();
    } // Cierre del método
    
    /**
     * Método que permite colocar como sonido del misil un Media File y 
     * lo reproduce
     */
    private void setupMisilSound()
    {
        MediaPlayer misilSound = new MediaPlayer(new Media(new File(Constants.soundMisil).toURI().toString()));
        misilSound.play();
    } // Cierre del método
    
    /**
     * Método que permite colocar como sonido de la bala un Media File y 
     * lo reproduce
     */
    private void setupBalaSound()
    {
        MediaPlayer balaSound = new MediaPlayer(new Media(new File(Constants.soundBala).toURI().toString()));
        balaSound.play();
    } // Cierre del método
    
} // Cierre de la clase
