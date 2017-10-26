/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * Represents the Main Screen
 * 
 * @author Olavo Henrique Dias
 */
public class FXMainScreen extends FXScreen implements FXSceneCreator {

    /**
     * Initializes a new FXMainScreen
     * 
     * @param currentStage      The Stage where the screen is being presented
     */
    public FXMainScreen(Stage currentStage) {
        super(currentStage);
    }
    
    @Override
    public Scene createScene()
    {
        /******************************************
         * Header
         *****************************************/
        
        /* Label for Title */
        Label lblHeader_Title = new Label();
        lblHeader_Title.setText("Inventory Management System");
        lblHeader_Title.getStyleClass().add("darkblue-window-title-text");
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(lblHeader_Title);
        hBoxHeader.setMinHeight(50);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(20));
        hBoxHeader.getStyleClass().add("darkblue-window-title");

        /***********************************************************************
         * Bottom
         **********************************************************************/
        
        /* Button to Exit */
        Button btnBottom_Exit = new Button();
        btnBottom_Exit.setText("EXIT");
        btnBottom_Exit.getStyleClass().add("darkblue-button");
        btnBottom_Exit.setPrefSize(100, 25);
        
        btnBottom_Exit.setOnAction(e -> Platform.exit());
        
        /* Box to wrap the Button */
        HBox hBoxBottom = new HBox(btnBottom_Exit);
        hBoxBottom.setPrefHeight(57);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
        hBoxBottom.setPadding(new Insets(0, 20, 0, 0));
        
        /***********************************************************************
         * Center
         **********************************************************************/

        /***************************************
         * Left Panel
         **************************************/
        BorderPane paneParts = FXGUIHelper.createTitledPanel("Parts", 
                                                             this.createPartsPaneDetails(), 
                                                             "darkblue-titledpane");

        /***************************************
         * Right Panel
         **************************************/
        Label lblTeste2 = new Label();
        lblTeste2.setText("Testing");

        BorderPane paneProducts = FXGUIHelper.createTitledPanel("Products", 
                                                                lblTeste2, 
                                                                "darkblue-titledpane");

        /***************************************
         * GridPane for Both Panels
         **************************************/
        GridPane gridCenter = new GridPane();
        gridCenter.setId("gridCenter");
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        
        gridCenter.getColumnConstraints().addAll(col1, col1);
        
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        
        gridCenter.getRowConstraints().add(row1);
        
        gridCenter.add(paneParts, 0, 0);
        gridCenter.add(paneProducts, 1, 0);
        
        /***********************************************************************
         * Border Pane
         **********************************************************************/
        BorderPane border = new BorderPane();
        border.setTop(hBoxHeader);
        border.setCenter(gridCenter);
        border.setBottom(hBoxBottom);

        /***********************************************************************
         * Create the Scene
         **********************************************************************/
        Scene scene = new Scene(border, 1000, 450);
       
        return scene;
    }
    
    /**
     * Creates the Parts Border Pane (the screen where the parts will be displayed).
     *      * 
     * @return      A BorderPane with all JavaFX objects composing the Parts display.
     */
    private BorderPane createPartsPaneDetails()
    {
        /**********************************************
         * BorderPane to display the contents 
         **********************************************/
        BorderPane partsPane = new BorderPane();
        
        /**********************************************
         * Search Bar 
         **********************************************/
        GridPane searchBarGridPane = new GridPane();
        searchBarGridPane.setHgap(8);
        searchBarGridPane.setPadding(new Insets(6, 6, 6, 6));
                
        ColumnConstraints[] colConstraints = new ColumnConstraints[3];
        
        colConstraints[0] = new ColumnConstraints();
        colConstraints[0].setPercentWidth(20);
        colConstraints[0].setHalignment(HPos.RIGHT);
        
        colConstraints[1] = new ColumnConstraints();
        colConstraints[1].setPercentWidth(66);
        
        colConstraints[2] = new ColumnConstraints();
        colConstraints[2].setPercentWidth(14);
        
        searchBarGridPane.getColumnConstraints().addAll(colConstraints);
        
        /* Add Search Bar Contents */
        Label lblSearch = new Label();
        lblSearch.setText("Search:");
        
        TextField txtSearch = new TextField();
        
        Button btnSearch = new Button();
        btnSearch.setText("Go");
        
        searchBarGridPane.add(lblSearch, 0, 0);
        searchBarGridPane.add(txtSearch, 1, 0);
        searchBarGridPane.add(btnSearch, 2, 0);

        /**********************************************
         * Actions Bar 
         **********************************************/
        GridPane actionsBarGridPane = new GridPane();
        actionsBarGridPane.setPadding(new Insets(6, 6, 6, 6));
        
        ColumnConstraints[] colConstraintsActionBar = new ColumnConstraints[3];
        
        colConstraintsActionBar[0] = new ColumnConstraints();
        colConstraintsActionBar[0].setPercentWidth(33);
        colConstraintsActionBar[0].setHalignment(HPos.CENTER);
        
        colConstraintsActionBar[1] = new ColumnConstraints();
        colConstraintsActionBar[1].setPercentWidth(34);
        colConstraintsActionBar[1].setHalignment(HPos.CENTER);
        
        colConstraintsActionBar[2] = new ColumnConstraints();
        colConstraintsActionBar[2].setPercentWidth(33);
        colConstraintsActionBar[2].setHalignment(HPos.CENTER);
        
        actionsBarGridPane.getColumnConstraints().addAll(colConstraintsActionBar);
        
        /* Add Action Bar Contents */
        Button btnActionAdd = new Button();
        btnActionAdd.setText("Add");
        btnActionAdd.getStyleClass().add("darkblue-button");
        btnActionAdd.setPrefSize(130, 20);
        btnActionAdd.setOnAction((ActionEvent e) -> {
            /* Create the FXPartSetupScreen at Add Mode and show it */
            FXGUIHelper.createStage(new FXPartSetupScreen(null, FXMultiModes.ADD),
                                    "Add Parts",
                                    false,
                                    this.getCurrentStage()).showAndWait();
        });

        Button btnActionModify = new Button();
        btnActionModify.setText("Modify");
        btnActionModify.getStyleClass().add("darkblue-button");
        btnActionModify.setPrefSize(130, 20);        
        btnActionModify.setOnAction((ActionEvent e) -> {
            /* Create the FXPartSetupScreen at Modify Mode and show it */
            FXGUIHelper.createStage(new FXPartSetupScreen(null, FXMultiModes.MODIFY),
                                    "Modify Parts",
                                    false,
                                    this.getCurrentStage()).showAndWait();

        });

        Button btnActionDelete = new Button();
        btnActionDelete.setText("Delete");
        btnActionDelete.getStyleClass().add("darkblue-button");
        btnActionDelete.setPrefSize(130, 20);        
        
        actionsBarGridPane.add(btnActionAdd, 0, 0);
        actionsBarGridPane.add(btnActionModify, 1, 0);
        actionsBarGridPane.add(btnActionDelete, 2, 0);

        /**********************************************
         * Table View
         **********************************************/
        TableView tableParts = new TableView();
        tableParts.setEditable(false);
        
        /* Define Columns */
        TableColumn[] tableColumns = new TableColumn[4];
        
        /* Column 01: Part ID */
        tableColumns[0] = new TableColumn("Part ID");
        tableColumns[0].setMinWidth(55);
        
        /* Column 02: Part Name */
        tableColumns[1] = new TableColumn("Part Name");
        tableColumns[1].setMinWidth(95);

        /* Column 03: Inventory Level */
        tableColumns[2] = new TableColumn("Inventory Level");
        tableColumns[2].setMinWidth(130);

        /* Column 04: Price/Cost per Unit */
        tableColumns[3] = new TableColumn("Price/Cost per Unit");
        tableColumns[3].setMinWidth(150);
        
        tableParts.getColumns().addAll(tableColumns[0],
                                       tableColumns[1],
                                       tableColumns[2],
                                       tableColumns[3]);
        
        /**********************************************
         * Finalize (Add all objects together)
         **********************************************/
        partsPane.setTop(searchBarGridPane);
        partsPane.setCenter(tableParts);
        partsPane.setBottom(actionsBarGridPane);
        
        return partsPane;
    }
    
}
