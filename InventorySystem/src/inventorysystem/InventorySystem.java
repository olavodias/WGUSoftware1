/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import inventorysystem.screens.FXMainScreen;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Olavo Henrique Dias
 */
public class InventorySystem extends Application {
    
    /**
     * The Start Method of the Inventory System
     * @param primaryStage The Stage where the first scene will be displayed
     */
    @Override
    public void start(Stage primaryStage) {
        
        /***********************************************************************
         * Create the Main Screen
         **********************************************************************/
        //FXMainScreen formMainScreen = new FXMainScreen("inventorysystem/InventorySystem.css");
        FXMainScreen formMainScreen = new FXMainScreen("inventorysystem/wguTheme.css");
        formMainScreen.setCurrentStage(primaryStage);
                
        primaryStage.setTitle(formMainScreen.getTitle());
        primaryStage.setScene(formMainScreen.getScene());
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(1000);
        
        primaryStage.show();
    }

    /**
     * First method to be called by the application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
