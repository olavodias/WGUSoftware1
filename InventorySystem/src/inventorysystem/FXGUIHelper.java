/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
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
    
    public static GridPane createTitledPanel(String title, Node content)
    {
        /* Create Titled Panel with blank on the css class */
        return createTitledPanel(title, content, "");
    }
 
    public static GridPane createTitledPanel(String title, Node content, String cssclass)
    {
        /* Create the Grid */
        GridPane grid = new GridPane();
        
        if (cssclass != "")
            grid.getStyleClass().add(cssclass);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        
        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(35);
        row1.setMinHeight(35);
        
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(100);
        
        grid.getColumnConstraints().addAll(col1);
        grid.getRowConstraints().addAll(row1, row2);
        
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
        
        if (cssclass == "")
            hBoxTitle.setStyle("-fx-background-color: #c0c0c0;");
        else
            hBoxTitle.getStyleClass().add(cssclass + "-title-box");

        /* Add Nodes to the Grid */
        grid.add(hBoxTitle, 0, 0);
        grid.add(content, 0, 1);
        
        return grid;
    }
}
