/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showmap;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 9087
 */
public class Enemy extends Entity{
    Pane gameRoot;
    Node enemy;
    int width, height;
    double xVector, yVector;
    boolean moving;
    
    ArrayList<Node> walls;
    

    Enemy(int x, int y, Pane gameRoot, ArrayList<Node> walls) {
        this.gameRoot = gameRoot;
        this.walls = walls;
        moving = true;

        width = 20;
        height = 20;
        
        enemy = createEntity(x, y, width, height, Color.RED, gameRoot);
    }

    void move(double playerX, double playerY) {
        if (collide() == false) {
            if (moving) {
                Point2D playerVector = new Point2D(playerX, playerY);
                Point2D enemyVector = new Point2D(enemy.getTranslateX(), enemy.getTranslateY());

                Point2D direction = playerVector.subtract(enemyVector).normalize();

                xVector = direction.getX();
                yVector = direction.getY();

                enemy.setTranslateX(enemy.getTranslateX()+xVector);
                enemy.setTranslateY(enemy.getTranslateY()+yVector);
            }
        }

    }

    private boolean collide() {
        for (Iterator<Node> it = walls.iterator(); it.hasNext();) {
                Node wall = it.next();
                if (enemy.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    enemy.setTranslateX(enemy.getTranslateX()-xVector);
                    enemy.setTranslateY(enemy.getTranslateY()-yVector);
                    return true;
                    
                }
            }
        return false;
    }
 
}
