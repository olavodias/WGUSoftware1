/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

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
    
    /**
     * Generates a Confirmation Box using the Alert class
     * @param title             The Window Title
     * @param headerText        The Header Text
     * @param contentText       The Content Text
     * @return                  A Optional ButtonType with the return of the Alert
     */
    public static Optional<ButtonType> ConfirmationBox(String title, String headerText, String contentText) 
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        
        return alert.showAndWait();
    }
    
    /**
     * Shows an Error Box
     * @param title             The Window Title
     * @param headerText        The Header Text
     * @param contentText       The Content Text
     */
    public static void ErrorBox(String title, String headerText, String contentText)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        
        alert.showAndWait();
    }
}
