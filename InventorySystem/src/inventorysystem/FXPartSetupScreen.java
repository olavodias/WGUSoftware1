/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Represents the screen to Add/Modify Parts
 * 
 * @author Olavo Henrique Dias
 */
public class FXPartSetupScreen extends FXMultiModeScreen implements FXSceneCreator {
    
    public FXPartSetupScreen(Stage currentStage)
    {
        /* Initialize the screen in Modify Mode */
        this(currentStage, FXMultiModes.NONE);
    }
    
    public FXPartSetupScreen(Stage currentStage, FXMultiModes mode)
    {
        /* Initialize Base */
        super(currentStage, mode);
    }
    
    @Override
    public Scene createScene()
    {
        /******************************************
         * Header
         *****************************************/
        
        /* Label for Title */
        Label lblHeader_Title = new Label();
        
        if (super.getMode() == FXMultiModes.ADD)
            lblHeader_Title.setText("Add Parts");
        else 
            lblHeader_Title.setText("Modify Parts");
        
        lblHeader_Title.getStyleClass().add("darkblue-windowsmall-title-text");
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(lblHeader_Title);
        hBoxHeader.setMinHeight(30);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(12));
        hBoxHeader.getStyleClass().add("darkblue-windowsmall-title");

        /***********************************************************************
         * Border Pane
         **********************************************************************/
        BorderPane border = new BorderPane();
        border.setTop(hBoxHeader);
        //border.setCenter(gridCenter);
        //border.setBottom(hBoxBottom);

        /***********************************************************************
         * Create the Scene
         **********************************************************************/
        Scene scene = new Scene(border, 500, 450);
       
        return scene;        
    }
}
