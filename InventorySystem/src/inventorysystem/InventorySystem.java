/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Olavo Henrique Dias
 */
public class InventorySystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
        /***********************************************************************
         * Create the Main Screen
         **********************************************************************/
        
        FXMainScreen formMainScreen = new FXMainScreen();
        
        Scene scene = formMainScreen.createScene();
        scene.getStylesheets().add("inventorysystem/InventorySystem.css");
        
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
