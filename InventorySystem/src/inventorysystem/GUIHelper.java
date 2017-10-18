/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Olavo Henrique Dias
 */
public class GUIHelper {
    
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
        /* Create Panes */
        StackPane wrapperPane = new StackPane();
        StackPane contentPane = new StackPane();
        
        /* Format Panes */
        content.getStyleClass().add("roundedpanel-content");
        wrapperPane.getStyleClass().add("roundedpanel-border");

        /* Add the Panes */
        contentPane.getChildren().add(content);
        wrapperPane.getChildren().add(contentPane);
        
        /* Return the Wrapper Pane */
        return wrapperPane;
    }
    
}
