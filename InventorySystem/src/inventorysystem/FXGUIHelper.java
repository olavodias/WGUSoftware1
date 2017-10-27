/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import inventorysystem.screens.FXSceneCreator;
import inventorysystem.screens.FXScreen;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Provide static methods to create JavaFX screen objects and instantiate scenes.
 * 
 * @author Olavo Henrique Dias
 */
public class FXGUIHelper {
    
    /**
     * Method to return a Panel embedding the input content.
     * Use CSS to define the following classes:
     *    - .roundedpanel-content
     *    - .roundedpanel-border
     * 
     * @param content   A Node containing the Content
     * @return          A Panel to Wrap the Given Content
     */
    public static StackPane createPanel(Node content)
    {
        /* Create the panel without any css class */
        return createPanel(content, "");
    }
    
    public static StackPane createPanel(Node content, String cssclass)
    {
        /* Create Panes */
        StackPane wrapperPane = new StackPane();
        StackPane contentPane = new StackPane();
        
        /* Format Panes */
        if (cssclass != "")
        {
            content.getStyleClass().add(cssclass + "-content");
            wrapperPane.getStyleClass().add(cssclass + "-border");
        }

        /* Add the Panes */
        contentPane.getChildren().add(content);
        wrapperPane.getChildren().add(contentPane);
        
        /* Return the Wrapper Pane */
        return wrapperPane;               
    }
    
    public static StackPane createRoundedPanel(Node content)
    {
        return createPanel(content, "roundedpanel");
    }
    
    public static BorderPane createTitledPanel(String title, Node content)
    {
        /* Create Titled Panel with blank on the css class */
        return createTitledPanel(title, content, "");
    }
 
    public static BorderPane createTitledPanel(String title, Node content, String cssclass)
    {
        /* Create the Pane */
        BorderPane pane = new BorderPane();
        
        if (cssclass != "")
            pane.getStyleClass().add(cssclass);
        
        /* Create the Title Header */
        Label lblTitledPaneHeader = new Label();
        lblTitledPaneHeader.setText(title);
        
        if (cssclass == "")
            lblTitledPaneHeader.setStyle("-fx-text-fill: #ffffff");
        else
            lblTitledPaneHeader.getStyleClass().add(cssclass + "-title");
 
        /* Create the HBox to wrap the Title Header */
        HBox hBoxTitle = new HBox();
        hBoxTitle.getChildren().add(lblTitledPaneHeader);
        hBoxTitle.setAlignment(Pos.CENTER_LEFT);
        hBoxTitle.setPrefHeight(30);
        
        if (cssclass == "")
            hBoxTitle.setStyle("-fx-background-color: #c0c0c0;");
        else
            hBoxTitle.getStyleClass().add(cssclass + "-title-box");

        /* Add Contents to the BorderPane */
        pane.setTop(hBoxTitle);
        pane.setCenter(content);
        
        return pane;
    }
    
    public static Scene createScene(FXSceneCreator screen)
    {
        /* Make sure there is a valid screen */
        if (screen == null) return null;
            
        /* Create the Scene */
        return screen.createScene();
    }
    
    public static Stage createStage(FXSceneCreator screen)
    {
        return createStage(screen, "");
    }
    
    public static Stage createStage(FXSceneCreator screen, String title)
    {
        return createStage(screen, title, true);
    }
    
    public static Stage createStage(FXSceneCreator screen, String title, boolean resizeable)
    {
        return createStage(screen, title, resizeable, null);
    }
    
    public static Stage createStage(FXSceneCreator screen, String title, boolean resizeable, Stage parentStage)
    {
        return createStage(screen, title, resizeable, parentStage, "");
    }
    
    public static Stage createStage(FXSceneCreator screen, String title, boolean resizeable, Stage parentStage, String cssPath)
    {
        /* Make sure there is a valid screen */
        if (screen == null) return null;
        
        /* Create the Stage */
        Stage newStage = new Stage();
        newStage.setTitle(title);
        
        /* Create the Scene */
        Scene newScene = screen.createScene();

        /* Set Scene Stylesheet */
        if (cssPath != "")
            newScene.getStylesheets().add(cssPath);

        newStage.setScene(newScene);
        newStage.setResizable(resizeable);
        
        /* Set Modal */
        if (parentStage != null)
        {
            newStage.initOwner(parentStage);
            newStage.initModality(Modality.WINDOW_MODAL);
        }                   
        
        /* Set the Screen Stage */
        if (screen instanceof FXScreen)
            ((FXScreen)screen).setCurrentStage(newStage);
        
        return newStage;
    }
}
