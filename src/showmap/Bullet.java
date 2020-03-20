/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showmap;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 9087
 */
class Bullet extends Entity{
    Node bullet;
    MapCreation map;
    
    Pane root;
    double xVector, yVector;
    
    boolean firing;
    int radius;
    double x,y, speed;
    
    Bullet(int playerX, int playerY, int mouseX, int mouseY, Pane root, MapCreation map) {
        this.root = root;
        this.map = map;
        
        int centerX = playerX+20;
        int centerY = playerY+20;
        
        radius = 5;
        this.speed = 6;
        this.x = centerX;
        this.y = centerY;
        firing = true;
        
        bullet = createEntity(centerX, centerY, radius, Color.AQUA, root);
        
        Point2D playerVector = new Point2D(centerX, centerY);
        Point2D bulletVector = new Point2D(mouseX, mouseY);
        Point2D direction = bulletVector.subtract(playerVector).normalize();
        
        xVector = direction.getX();
        yVector = direction.getY();
    }

    public void move(ArrayList<Enemy> enemies){
        if (firing) {
            
            x += xVector;
            y += yVector; 
        
            bullet.setTranslateX(x);
            bullet.setTranslateY(y);
                   
        }
        
    }
    
}

