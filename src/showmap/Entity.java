/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package showmap;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author 9087
 */
public class Entity {
    
    public Node createEntity(int x, int y, int w, int h, Color color, Pane root){
        
        Rectangle entity = new Rectangle(w, h, color);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        
        root.getChildren().add(entity);
        
        return entity;
        
    }
    
    public Node createEntity(int x, int y, int radius, Color color, Pane gameRoot){
        
        Circle entity = new Circle(radius, color);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        
        gameRoot.getChildren().add(entity);
        
        return entity;
        
    }
}
