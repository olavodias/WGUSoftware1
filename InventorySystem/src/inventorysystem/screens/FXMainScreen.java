/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import inventorysystem.FXGUIHelper;
import inventorysystem.models.Inventory;
import inventorysystem.models.Part;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 * Represents the Main Screen
 * 
 * @author Olavo Henrique Dias
 */
public class FXMainScreen extends FXScreen {

    /* The default name for this form */
    private static final String DEFAULTTITLE = "Inventory Management System";
    
    /* UI Elements to be shared accoss the Form */
    TableView<Part> tableParts = new TableView();
    
    /**
     * Initializes a new FXMainScreen
     * 
     * @param cssPath           The CSS Path
     */
    public FXMainScreen(String cssPath) {
        this(cssPath, DEFAULTTITLE);
    }

    /**
     * Initializes a new FXMainScreen
     * 
     * @param cssPath           The CSS Path
     * @param title             The Screen Title
     */
    public FXMainScreen(String cssPath, String title) {
        super(cssPath, title);
        
        /* Creates the Scene itself */
        this.createScene();
    }

    /**
     * Creates the Main Form Scene
     */
    @Override
    public final void createScene()
    {
        /******************************************
         * Header
         *****************************************/
        
        /* Label for Title */
        Label lblHeader_Title = new Label();
        lblHeader_Title.setText(super.getTitle());
        
        if (super.isStyled()) 
            lblHeader_Title.getStyleClass().add("darkblue-window-title-text");
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(lblHeader_Title);
        hBoxHeader.setMinHeight(50);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(20));
        
        if (super.isStyled()) 
            hBoxHeader.getStyleClass().add("darkblue-window-title");

        /***********************************************************************
         * Bottom
         **********************************************************************/
        
        /* Button to Exit */
        Button btnBottom_Exit = new Button();
        btnBottom_Exit.setText("EXIT");
        btnBottom_Exit.setPrefSize(100, 25);
        
        if (super.isStyled())
            btnBottom_Exit.getStyleClass().add("darkblue-button");        
        
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
                                                             (super.isStyled() ? "darkblue-titledpane" : ""));

        /***************************************
         * Right Panel
         **************************************/
        Label lblTeste2 = new Label();
        lblTeste2.setText("Testing");

        BorderPane paneProducts = FXGUIHelper.createTitledPanel("Products", 
                                                                lblTeste2, 
                                                                (super.isStyled() ? "darkblue-titledpane" : ""));

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
        super.scene = new Scene(border, super.getWidth(), super.getHeight());
        super.applyCss();
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
        btnSearch.setOnAction((ActionEvent event) -> { 
        
            /* Do nothing if there is nothing on the search textfield */
            if (txtSearch.getText().trim().equals("")) return;
            
            /* Define Variables */
            int searchID = 0;
            String searchName = "";
            Part part;
            
            /* Check if the search criteria is numeric */
            try
            {
                /* Try to parse it to integer */
                searchID = Integer.parseInt(txtSearch.getText());
                
                /* Search for the Part ID */
                part = Inventory.getInstance().lookupPart(searchID);
                if (part != null) {
                    /* Select it on the TableView and exit */
                    tableParts.getSelectionModel().select(part);
                    return;
                }
            }
            catch (NumberFormatException e)
            {
                /* This is not a numeric field, do nothing */
            }
            
            /* Search for text */
            searchName = txtSearch.getText().trim();
            
            part = Inventory.getInstance().lookupPart(searchName);
            if (part != null) 
                /* Select it on the TableView */
                tableParts.getSelectionModel().select(part);
        });
        
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
        btnActionAdd.setPrefSize(130, 20);
        btnActionAdd.setOnAction((ActionEvent e) -> {
            /* Create the FXPartSetupScreen at Add Mode and show it */
            FXPartSetupScreen newForm = new FXPartSetupScreen(getCssPath());
            
            /* Show screen and, if user clicked ok, save it */
            if (newForm.show(getCurrentStage()) == FXScreenResult.OK)
                Inventory.getInstance().addPart(newForm.getModifiedPart());
            else
                e.consume();
        });
        
        if (super.isStyled())
            btnActionAdd.getStyleClass().add("darkblue-button");

        Button btnActionModify = new Button();
        btnActionModify.setText("Modify");
        btnActionModify.setPrefSize(130, 20);        
        btnActionModify.setOnAction((ActionEvent e) -> {
            
            /* Retrieve Selected Item */
            if (tableParts.getSelectionModel().getSelectedItem() == null) {
                e.consume();
                return;
            }
                
            Part originalPart = (Part)tableParts.getSelectionModel().getSelectedItem();
            
            /* Create the FXPartSetupScreen at Modify Mode and show it */            
            FXPartSetupScreen newForm = new FXPartSetupScreen(originalPart,
                                                              getCssPath());
            
            /* Show screen and, if user clicked ok, change it on the collection */
            if (newForm.show(getCurrentStage()) == FXScreenResult.OK)
                Inventory.getInstance().replacePart(originalPart, newForm.getModifiedPart());
            else
                e.consume();
        });
        
        if (super.isStyled())
            btnActionModify.getStyleClass().add("darkblue-button");

        Button btnActionDelete = new Button();
        btnActionDelete.setText("Delete");
        btnActionDelete.setPrefSize(130, 20);
        btnActionDelete.setOnAction((ActionEvent e) -> { 
        
            /* Retrieve Selected Item */
            if (tableParts.getSelectionModel().getSelectedItem() == null) {
                e.consume();
                return;
            }
                
            Part originalPart = (Part)tableParts.getSelectionModel().getSelectedItem();
            
            /* Display alert to ask for confirmation */
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Are you sure?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setContentText(String.format("You are about to remote item %d (%s) from your parts collection...", originalPart.getPartID(), originalPart.getName()));
            
            /* Process Confirmation Result */
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.getInstance().deletePart(originalPart);
            else
                e.consume();
        });
        
        actionsBarGridPane.add(btnActionAdd, 0, 0);
        actionsBarGridPane.add(btnActionModify, 1, 0);
        actionsBarGridPane.add(btnActionDelete, 2, 0);

        if (super.isStyled())
            btnActionDelete.getStyleClass().add("darkblue-button");
        
        /**********************************************
         * Table View
         **********************************************/
        tableParts = new TableView<>(Inventory.getInstance().getAllParts());
        tableParts.setEditable(false);
        
        /* Define Columns */

        /* Column 01: Part ID */
        TableColumn<Part, String> tColPartID = new TableColumn<>("Part ID");
        tColPartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        tColPartID.setMinWidth(55);

        /* Column 02: Part Name */
        TableColumn<Part, String> tColPartName = new TableColumn<>("Part Name");
        tColPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColPartName.setMinWidth(95);
        
        /* Column 03: Inventory Level */
        TableColumn<Part, Integer> tColInventoryLevel = new TableColumn<>("Inventory Level");
        tColInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tColInventoryLevel.setMinWidth(130);

        /* Column 04: Price/Cost per Unit */
        TableColumn<Part, Double> tColPriceCost = new TableColumn<>("Price/Cost per Unit");
        tColPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        tColPriceCost.setMinWidth(150);
        
        tableParts.getColumns().addAll(tColPartID,
                                       tColPartName,
                                       tColInventoryLevel,
                                       tColPriceCost);
        
        /**********************************************
         * Finalize (Add all objects together)
         **********************************************/
        partsPane.setTop(searchBarGridPane);
        partsPane.setCenter(tableParts);
        partsPane.setBottom(actionsBarGridPane);
        
        return partsPane;
    }
    
}
