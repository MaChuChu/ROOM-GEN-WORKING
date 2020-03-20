/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showmap;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 9087
 */
public class MapCreation extends Entity{
    
    RoomGen gen;
    Pane root;
    
    ArrayList<Node> walls;
    ArrayList<Enemy> enemies;
    
    int currentRoomX;
    int currentRoomY;
    
    
    
    public MapCreation(Pane root) {
        gen = new RoomGen();
        this.root = root;
        walls = new ArrayList();
        enemies = new ArrayList();
        
        System.out.println("\n\nPRINT FROM MAP CREATION");
        gen.printRooms();
        
        this.currentRoomX = gen.playerStartRoomX;
        this.currentRoomY = gen.playerStartRoomY;
    }
    
    public void createRoom(){
        
        for (int w = 0; w < 10; w++) {
            for (int h = 0; h < 10; h++) {
                switch(gen.roomMap[currentRoomY][currentRoomX][h][w]){
                    case 0:
                        //floor
                        break;
                    case 1:
                        //wall
                        Node wall = createEntity(w * 64, h * 64, 64, 64, Color.BROWN, root);
                        walls.add(wall);
                        break;
                    case 2:
                        //obstacle
                        Node obstacle = createEntity(w * 64, h * 64, 64, 64, Color.CORAL, root);
                        walls.add(obstacle);
                        break;
                    case 3:
                        //door
                        Node door = createEntity(w * 64, h * 64, 64, 64, Color.GOLD, root);
                        
                        break;
                    case 4:
                        //enemy
                        Enemy enemy = new Enemy(w * 64, h * 64, root, walls);
                        enemies.add(enemy);
                        break;
                }
            }
        }       
       
    }
    
    
    public void goneLeft(){
        currentRoomX--;
        root.getChildren().clear();
        walls.clear();
        enemies.clear();
        createRoom();
    }
    
    public void goneRight(){
        currentRoomX++;
        root.getChildren().clear();
        walls.clear();
        enemies.clear();
        createRoom();
    }
    
    public void goneUp(){
        
        currentRoomY--;
        root.getChildren().clear();
        walls.clear();
        enemies.clear();
        createRoom();
    }
    
    public void goneDown(){
        currentRoomY++;
        root.getChildren().clear();
        walls.clear();
        enemies.clear();
        createRoom();
    }
    
}
