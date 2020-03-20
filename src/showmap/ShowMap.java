
package showmap;

import java.util.HashMap;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author 9087
 */
public class ShowMap extends Application {
    Scene scene;
    Pane root ;
    int WIDTH = 10*64;
    int HEIGHT = 10*64;
    HashMap<KeyCode, Boolean> keys = new HashMap<>();
    
    Player player;
    
    final int bullet_maxTime = 7;
    private int bullet_timeRemain;
    
    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        
        MapCreation map = new MapCreation(root);                
        map.createRoom();
        
        player = new Player(root, keys, map);
        
        
        
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now){
                player.move();
                
                for (Iterator<Enemy> it = map.enemies.iterator(); it.hasNext();) {
                    Enemy enemy = it.next();
                    enemy.move(player.player.getTranslateX(), player.player.getTranslateY());
                }
                 
                if(bullet_timeRemain>0){
                    bullet_timeRemain--;
                }
                player.bulletMove(map.enemies);
                
                if(player.player.getTranslateX()>WIDTH){ //gone through right door
                    map.goneRight();
                    root.getChildren().add(player.player);
                    player.player.setTranslateX(64+player.width);
                    player.player.setTranslateY(HEIGHT/2);
                }
                else if(player.player.getTranslateX()<0){ //gone through left door
                    map.goneLeft();
                    player.player.setTranslateX(WIDTH-64-player.width);
                    player.player.setTranslateY(HEIGHT/2);
                    root.getChildren().add(player.player);
                }
                else if(player.player.getTranslateY()>HEIGHT){ //gone through right door
                    map.goneDown();
                    player.player.setTranslateX(WIDTH/2);
                    player.player.setTranslateY(64+player.height);
                    root.getChildren().add(player.player);
                }
                else if(player.player.getTranslateY()<0){ //gone through right door
                    map.goneUp();
                    player.player.setTranslateX(WIDTH/2);
                    player.player.setTranslateY(HEIGHT-64-player.height);
                    root.getChildren().add(player.player);
                }
                
            }
            
        };
        timer.start();
        
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), Boolean.TRUE));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), Boolean.FALSE));
        
        bullet_timeRemain = 0;
        scene.setOnMouseDragged((MouseEvent event) -> {
            if (event.isPrimaryButtonDown() && bullet_timeRemain == 0) {
                player.shootBullet((int)event.getX(),(int)event.getY());
                bullet_timeRemain = bullet_maxTime;
            }
        });
        
        primaryStage.setTitle("ROOM GENERATION");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
