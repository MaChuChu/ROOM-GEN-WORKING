/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author 9087
 */
public class Player extends Entity {

    Node player;
    int width, height;
    Pane root;
    HashMap<KeyCode, Boolean> keys;

    MapCreation map;
    ArrayList<Bullet> bullets;

    Player(Pane root, HashMap<KeyCode, Boolean> keys, MapCreation map) {
        this.keys = keys;
        this.map = map;
        this.root = root;

        width = 40;
        height = 40;
        player = createEntity(100, 100, width, height, Color.AQUA, root);
        
        bullets = new ArrayList();
    }

    public Boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, Boolean.FALSE);
    }

    public void move() {
        if (isPressed(KeyCode.A)) {
            movePlayerX(-7);
        }
        if (isPressed(KeyCode.D)) {
            movePlayerX(7);
        }
        if (isPressed(KeyCode.W)) {
            movePlayerY(-5);
        }
        if (isPressed(KeyCode.S)) {
            movePlayerY(5);
        }

    }

    void movePlayerX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node wall : map.walls) {
                if (player.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + width == wall.getTranslateX()) {
                            return;
                        }
                    } else {
                        if (player.getTranslateX() == wall.getTranslateX() + 64) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    void movePlayerY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node wall : map.walls) {
                if (player.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + height == wall.getTranslateY()) {
                            return;
                        }
                    } else {
                        if (player.getTranslateY() == wall.getTranslateY() + 64) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }
    
    public void shootBullet(int mouseX, int mouseY) {
        Bullet bullet = new Bullet((int) player.getTranslateX(), (int) player.getTranslateY(), mouseX, mouseY, root, map);
        bullets.add(bullet);
    }
    
    public void bulletMove(ArrayList<Enemy> enemies) {

        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext();) {
            Bullet bullet = iterator.next();
            for (Iterator<Node> iteratorWall = map.walls.iterator(); iteratorWall.hasNext();) {
                Node wall = iteratorWall.next();
                if (bullet.bullet.getBoundsInParent().intersects(wall.getBoundsInParent())) {
                    root.getChildren().remove(bullet.bullet);
                    iterator.remove();
                    break;
                }
                
            }
            
            for (Iterator<Enemy> iteratorEnemy = bullet.map.enemies.iterator(); iteratorEnemy.hasNext();) {
                Enemy enemy = iteratorEnemy.next();
                if (bullet.bullet.getBoundsInParent().intersects(enemy.enemy.getBoundsInParent())) {
                    root.getChildren().remove(enemy.enemy);
                    iteratorEnemy.remove();
                    
                    root.getChildren().remove(bullet.bullet);
                    iterator.remove();
                    
                    break;
                }
            }
            bullet.move(enemies);
        }

    }
    
}
