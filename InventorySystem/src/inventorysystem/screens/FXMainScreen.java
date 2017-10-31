/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import inventorysystem.FXGUIHelper;
import inventorysystem.models.InHousePart;
import inventorysystem.models.Inventory;
import inventorysystem.models.OutsourcedPart;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    TableView<Product> tableProducts = new TableView();
    
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
        lblHeader_Title.getStyleClass().add("label-header");

        /* WGU Logo */
        Image image = new Image("inventorysystem/wguLogo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);

        /* Grid to Display Title and Logo */
        GridPane gridHeader = new GridPane();
        gridHeader.setAlignment(Pos.CENTER);
        gridHeader.setGridLinesVisible(true);
        
        /* Create Column Constraints */
        ColumnConstraints[] colConstraintsGridHeader = new ColumnConstraints[2];
        
        colConstraintsGridHeader[0] = new ColumnConstraints();
        colConstraintsGridHeader[0].setPercentWidth(70);
        colConstraintsGridHeader[0].setHalignment(HPos.LEFT); 
        
        colConstraintsGridHeader[1] = new ColumnConstraints();
        colConstraintsGridHeader[1].setPercentWidth(30);
        colConstraintsGridHeader[1].setHalignment(HPos.RIGHT); 
        
        gridHeader.getColumnConstraints().addAll(colConstraintsGridHeader);
        
        gridHeader.add(lblHeader_Title, 0, 0);
        gridHeader.add(imageView, 1, 0);
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(gridHeader);
        hBoxHeader.setMinHeight(40);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(15));
        hBoxHeader.getStyleClass().add("hbox-header");

        /***********************************************************************
         * Bottom
         **********************************************************************/

        /* Button to Load Default Values */
        Button btnBottom_LoadData = new Button();
        btnBottom_LoadData.setText("LOAD TEST DATA");
        btnBottom_LoadData.setPrefSize(170, 25);
        btnBottom_LoadData.getStyleClass().add("button-type2");        
        btnBottom_LoadData.setOnAction((ActionEvent e) -> { 
        
            /* Calls the event handler */
            handleLoadTestDataButtonAction(e);
        });

        /* Button to Exit */
        Button btnBottom_Exit = new Button();
        btnBottom_Exit.setText("EXIT");
        btnBottom_Exit.setPrefSize(100, 25);
        btnBottom_Exit.getStyleClass().add("button-type2");        
        
        btnBottom_Exit.setOnAction(e -> Platform.exit());
        
        /* Box to wrap the Button */
        HBox hBoxBottom = new HBox(btnBottom_LoadData, btnBottom_Exit);
        hBoxBottom.setPrefHeight(57);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
        hBoxBottom.setPadding(new Insets(0, 20, 0, 0));
        hBoxBottom.setSpacing(6);
        hBoxBottom.getStyleClass().add("hbox-bottom");
        
        /***********************************************************************
         * Center
         **********************************************************************/

        /***************************************
         * Left Panel
         **************************************/
        BorderPane paneParts = FXGUIHelper.createTitledPanel("Parts", 
                                                             this.createPartsPaneDetails(), 
                                                             "ctitledpane");

        /***************************************
         * Right Panel
         **************************************/
        BorderPane paneProducts = FXGUIHelper.createTitledPanel("Products", 
                                                                this.createProductsPaneDetails(), 
                                                                "ctitledpane");

        /***************************************
         * GridPane for Both Panels
         **************************************/
        GridPane gridCenter = new GridPane();
        gridCenter.setHgap(16);
        gridCenter.setPadding(new Insets(16));
        
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
        colConstraints[0].setPercentWidth(14);
        colConstraints[0].setHalignment(HPos.RIGHT);
        
        colConstraints[1] = new ColumnConstraints();
        colConstraints[1].setPercentWidth(72);
        
        colConstraints[2] = new ColumnConstraints();
        colConstraints[2].setPercentWidth(14);
        
        searchBarGridPane.getColumnConstraints().addAll(colConstraints);
        
        /* Add Search Bar Contents */
        Label lblSearch = new Label();
        lblSearch.setText("Search:");
        
        TextField txtSearch = new TextField();
        
        Button btnSearch = new Button();
        btnSearch.setText("Go");
        btnSearch.getStyleClass().add("button-type1");
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
            else
            {
                /* Show alert that no data was found */
                FXGUIHelper.ErrorBox(DEFAULTTITLE, "No Parts were found", String.format("No Parts could be found with the given criteria ('%s').", searchName));
            }
        });
        
        /* Add Objects to the GridPane */
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
        btnActionAdd.getStyleClass().add("button-type3");
        btnActionAdd.setOnAction((ActionEvent e) -> {
            /* Create the FXPartSetupScreen at Add Mode and show it */
            FXPartSetupScreen newForm = new FXPartSetupScreen(getCssPath());
            
            /* Show screen and, if user clicked ok, save it */
            if (newForm.show(getCurrentStage()) == FXScreenResult.OK)
                Inventory.getInstance().addPart(newForm.getModifiedPart());
            else
                e.consume();
        });              

        Button btnActionModify = new Button();
        btnActionModify.setText("Modify");
        btnActionModify.setPrefSize(130, 20);        
        btnActionModify.getStyleClass().add("button-type3");
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

        Button btnActionDelete = new Button();
        btnActionDelete.setText("Delete");
        btnActionDelete.setPrefSize(130, 20);
        btnActionDelete.getStyleClass().add("button-type3");
        btnActionDelete.setOnAction((ActionEvent e) -> { 
        
            /* Retrieve Selected Item */
            if (tableParts.getSelectionModel().getSelectedItem() == null) {
                e.consume();
                return;
            }
                
            Part originalPart = (Part)tableParts.getSelectionModel().getSelectedItem();
            
            /* Check if part is not in use by any product */
            ArrayList<String> productsWhereUsed = Inventory.getInstance().partExistsInProducts(originalPart);
            
            if (!productsWhereUsed.isEmpty()) {
                /* The part is in use by products, display list */
                String listOfProducts = "\n - " + String.join("\n - ", productsWhereUsed);
                FXGUIHelper.ErrorBox(DEFAULTTITLE, 
                                     "Part is in use", 
                                     String.format("The part you are trying to delete is in use by: %s ", listOfProducts));
                
                /* Stop Event Chain */
                e.consume();
                return;
            }
            
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

    /**
     * Creates the Products Border Pane (the screen where the parts will be displayed).
     *      * 
     * @return      A BorderPane with all JavaFX objects composing the Products display.
     */
    private BorderPane createProductsPaneDetails()
    {
        /**********************************************
         * BorderPane to display the contents 
         **********************************************/
        BorderPane productsPane = new BorderPane();
        
        /**********************************************
         * Search Bar 
         **********************************************/
        GridPane searchBarGridPane = new GridPane();
        searchBarGridPane.setHgap(8);
        searchBarGridPane.setPadding(new Insets(6, 6, 6, 6));

        ColumnConstraints[] colConstraints = new ColumnConstraints[3];

        colConstraints[0] = new ColumnConstraints();
        colConstraints[0].setPercentWidth(14);
        colConstraints[0].setHalignment(HPos.RIGHT);
        
        colConstraints[1] = new ColumnConstraints();
        colConstraints[1].setPercentWidth(72);
        
        colConstraints[2] = new ColumnConstraints();
        colConstraints[2].setPercentWidth(14);
        
        searchBarGridPane.getColumnConstraints().addAll(colConstraints);
        
        /* Add Search Bar Contents */
        Label lblSearch = new Label();
        lblSearch.setText("Search:");
        
        TextField txtSearch = new TextField();
        
        Button btnSearch = new Button();
        btnSearch.setText("Go");  
        btnSearch.getStyleClass().add("button-type1");
        btnSearch.setOnAction((ActionEvent event) -> { 
        
            /* Do nothing if there is nothing on the search textfield */
            if (txtSearch.getText().trim().equals("")) return;
            
            /* Define Variables */
            int searchID = 0;
            String searchName = "";
            Product product;
            
            /* Check if the search criteria is numeric */
            try
            {
                /* Try to parse it to integer */
                searchID = Integer.parseInt(txtSearch.getText());
                
                /* Search for the Part ID */
                product = Inventory.getInstance().lookupProduct(searchID);
                if (product != null) {
                    /* Select it on the TableView and exit */
                    tableProducts.getSelectionModel().select(product);
                    tableProducts.scrollTo(tableProducts.getSelectionModel().getSelectedIndex());
                    return;
                }
            }
            catch (NumberFormatException e)
            {
                /* This is not a numeric field, do nothing */
            }
            
            /* Search for text */
            searchName = txtSearch.getText().trim();
            
            product = Inventory.getInstance().lookupProduct(searchName);
            if (product != null) 
                /* Select it on the TableView */
                tableProducts.getSelectionModel().select(product);
            else
            {
                /* Show alert that no data was found */
                FXGUIHelper.ErrorBox(DEFAULTTITLE, "No Parts were found", String.format("No Parts could be found with the given criteria ('%s').", searchName));
            }
        });
        
        /* Add Objects to the GridPane */
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
        btnActionAdd.getStyleClass().add("button-type3");
        btnActionAdd.setOnAction((ActionEvent e) -> {
            /* Create the FXPartSetupScreen at Add Mode and show it */
            FXProductSetupScreen newForm = new FXProductSetupScreen(getCssPath());
            
            /* Show screen and, if user clicked ok, save it */
            if (newForm.show(getCurrentStage()) == FXScreenResult.OK)
                Inventory.getInstance().addProduct(newForm.getModifiedProduct());
            else
                e.consume();
        });
        
        Button btnActionModify = new Button();
        btnActionModify.setText("Modify");
        btnActionModify.setPrefSize(130, 20);        
        btnActionModify.getStyleClass().add("button-type3");
        btnActionModify.setOnAction((ActionEvent e) -> {
            
            /* Retrieve Selected Item */
            if (tableProducts.getSelectionModel().getSelectedItem() == null) {
                e.consume();
                return;
            }
                
            Product originalProduct = (Product)tableProducts.getSelectionModel().getSelectedItem();
            
            /* Create the FXProductSetupScreen at Modify Mode and show it */            
            FXProductSetupScreen newForm = new FXProductSetupScreen(originalProduct,
                                                                    getCssPath());
            
            /* Show screen and, if user clicked ok, change it on the collection */
            if (newForm.show(getCurrentStage()) == FXScreenResult.OK)
                Inventory.getInstance().replaceProduct(originalProduct, newForm.getModifiedProduct());
            else
                e.consume();
        });
        
        Button btnActionDelete = new Button();
        btnActionDelete.setText("Delete");
        btnActionDelete.setPrefSize(130, 20);
        btnActionDelete.getStyleClass().add("button-type3");
        btnActionDelete.setOnAction((ActionEvent e) -> { 
        
            /* Retrieve Selected Item */
            if (tableProducts.getSelectionModel().getSelectedItem() == null) {
                e.consume();
                return;
            }
                
            /* Get Product to be deleted */
            Product originalProduct = (Product)tableProducts.getSelectionModel().getSelectedItem();
            
            /* Validate number of child parts */
            if (!originalProduct.getAssociatedParts().isEmpty()) {
                FXGUIHelper.ErrorBox("Delete Product", "Unable to Delete Product", "The product you are trying to delete has parts inside it.");
                e.consume();
                return;
            }
            
            /* Display alert to ask for confirmation */
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Are you sure?");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setContentText(String.format("You are about to remote item %d (%s) from your product collection...", originalProduct.getProductID(), originalProduct.getName()));
            
            /* Process Confirmation Result */
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
                Inventory.getInstance().removeProduct(originalProduct);
            else
                e.consume();
        });
        
        actionsBarGridPane.add(btnActionAdd, 0, 0);
        actionsBarGridPane.add(btnActionModify, 1, 0);
        actionsBarGridPane.add(btnActionDelete, 2, 0);

        /**********************************************
         * Table View
         **********************************************/
        tableProducts = new TableView<>(Inventory.getInstance().getProducts());
        tableProducts.setEditable(false);
        
        /* Define Columns */

        /* Column 01: Product ID */
        TableColumn<Product, String> tColProductID = new TableColumn<>("ID");
        tColProductID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        tColProductID.setPrefWidth(35);

        /* Column 02: Product Name */
        TableColumn<Product, String> tColProductName = new TableColumn<>("Product Name");
        tColProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColProductName.setPrefWidth(115);
        
        /* Column 03: Inventory Level */
        TableColumn<Product, Integer> tColInventoryLevel = new TableColumn<>("Inventory Level");
        tColInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        tColInventoryLevel.setPrefWidth(130);

        /* Column 04: Price/Cost per Unit */
        TableColumn<Product, Double> tColPriceCost = new TableColumn<>("Price/Cost per Unit");
        tColPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        tColPriceCost.setPrefWidth(150);
        
        tableProducts.getColumns().addAll(tColProductID,
                                          tColProductName,
                                          tColInventoryLevel,
                                          tColPriceCost);
        
        /**********************************************
         * Finalize (Add all objects together)
         **********************************************/
        productsPane.setTop(searchBarGridPane);
        productsPane.setCenter(tableProducts);
        productsPane.setBottom(actionsBarGridPane);
        
        return productsPane;
    }
    
    /**
     * Handler for the Load Default Data Button
     * This method generates Parts and Products to use for testing the program
     * @param event The ActionEvent to control the event handling
     */
    private void handleLoadTestDataButtonAction(ActionEvent event) {
        
        /* Clear Existing Data */
        Inventory.resetInstance();
        
        /***********************************************************************
         * Add Parts
         **********************************************************************/
        
        /* Now add Parts to it */
        Part[] parts = new Part[10];
        
        for (int i = 1; i <= 10; i++) {
            
            if (i <= 5)
            {
                /* Create an Inouse Part */
                parts[i-1] = new InHousePart();
                ((InHousePart)parts[i-1]).setMachineID(i * 10 + 1000);
            }
            else
            {
                /* Create an Inouse Part */
                parts[i-1] = new OutsourcedPart();
                ((OutsourcedPart)parts[i-1]).setCompanyName(String.format("Company %d", i));
            }
            
            parts[i-1].setPartID(Inventory.getInstance().getNextPartID());
            parts[i-1].setName(String.format("Part %d", parts[i-1].getPartID()));
            parts[i-1].setInStock(i * 10);
            parts[i-1].setMax(i * 10 + 10);
            parts[i-1].setMin(i * 10);
            parts[i-1].setPrice(i * 10);
            
            /* Add to the Inventory */
            Inventory.getInstance().addPart(parts[i-1]);
        }
        
        /***********************************************************************
         * Add Products
         **********************************************************************/
        String[] natoAlphabet = new String[] { "Alpha", "Bravo", "Charlie", "Delta", "Echo",
                                                "Foxtrot", "Golf", "Hotel", "India", "Juliet",
                                                "Kilo", "Lima", "Mike", "November", "Oscar",
                                                "Papa", "Quebec", "Romeo", "Sierra", "Tango", 
                                                "Uniform", "Victor", "Whiskey", "Xray",
                                                "Yankee", "Zulu" };
        
        Product[] products = new Product[30];
        
        for (int i = 1; i <= 26; i++) { 
            
            /* Initialize Product */
            products[i-1] = new Product();
            
            products[i-1].setProductID(Inventory.getInstance().getNextProductID());
            products[i-1].setName(String.format("Product %s", natoAlphabet[i-1]));
            products[i-1].setInStock(i * 10);
            products[i-1].setMax(i * 10 + 10);
            products[i-1].setMin(i * 10);
            
            /* Define Number of Child Parts */
            int randomCountOfChildParts = ThreadLocalRandom.current().nextInt(1, 9);
            
            /* Assign Random Parts to the Collection */
            for (int r = 1; r <= randomCountOfChildParts; r++) {
                
                int indexOfPartToAdd = ThreadLocalRandom.current().nextInt(0, 9);
                products[i-1].addAssociatedPart(parts[indexOfPartToAdd]);
            }
            
            /* Set Price */
            products[i-1].setPrice(products[i-1].getTotalComponentsPrice() + 100);

            /* Add to Inventory */
            Inventory.getInstance().addProduct(products[i-1]);
        }
        
        /* Add Empty Products */        
        for (int i = 27; i <= 30; i++) {
            /* Initialize Product */
            products[i-1] = new Product();
            
            products[i-1].setProductID(Inventory.getInstance().getNextProductID());
            products[i-1].setName(String.format("Product Empty %d", i - 26));
            products[i-1].setInStock(i * 10);
            products[i-1].setMax(i * 10 + 10);
            products[i-1].setMin(i * 10);
            products[i-1].setPrice(100);

            /* Add to Inventory */
            Inventory.getInstance().addProduct(products[i-1]);
        }

        /* Stop event handling */
        event.consume();
    }
}
