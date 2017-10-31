/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import inventorysystem.FXGUIHelper;
import inventorysystem.exceptions.FXFormInputException;
import inventorysystem.models.Inventory;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
 *
 * @author java
 */
public class FXProductSetupScreen extends FXMultiModeScreen {
    
    /* The default name for this form */
    private static final String DEFAULTTITLE = "Product Management";
    
    /***************************************************************************
     * Shared UI Fields (to be used by multiple methods)
     **************************************************************************/
    /* Field: ID */
    Label lblField_ID = new Label();
    TextField txtField_ID = new TextField();

    /* Field: Name */
    Label lblField_Name = new Label();
    TextField txtField_Name = new TextField();

    /* Field: Inventory */
    Label lblField_Inv = new Label();
    TextField txtField_Inv = new TextField();

    /* Field: Price/Cost */
    Label lblField_PriceCost = new Label();
    TextField txtField_PriceCost = new TextField();

    /* Field: Inventory Maximum */
    Label lblField_InvMax = new Label();
    TextField txtField_InvMax = new TextField();

    /* Field: Inventory Minimum */
    Label lblField_InvMin = new Label();
    TextField txtField_InvMin = new TextField();
    
    /* Table Views */
    TableView<Part> tableAllParts = new TableView();
    TableView<Part> tableProductParts = new TableView();
    
    /* Lists */
    ObservableList<Part> availableParts;
    
    /***************************************************************************
     * Properties
     **************************************************************************/
    protected final Product _originalProduct;
    
    /**
     * Returns the Original Product
     * @return The Original Product
     */
    public Product getOriginalProduct() {
        return _originalProduct;
    }

    private Product _modifiedProduct;
    
    /**
     * Returns the Modified Product
     * @return The Modified Product
     */
    public Product getModifiedProduct() {
        return _modifiedProduct;
    }
    
    /***************************************************************************
     * Constructors
     **************************************************************************/
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     */
    public FXProductSetupScreen()
    {
        /* Initialize the screen in Add Mode */
        this("");
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     * @param cssPath       The CSS File Path
     */
    public FXProductSetupScreen(String cssPath)
    {
        /* Initialize the screen in Add Mode */
        this(cssPath, DEFAULTTITLE);
    }

    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    public FXProductSetupScreen(String cssPath, String title)
    {
        /* Initialize the screen in Add Mode */
        this(FXMode.ADD, null, cssPath, title);
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param product          The part to edit
     */
    public FXProductSetupScreen(Product product)
    {
        /* Initialize the screen in Modify Mode */
        this(product, "");
    }
    
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param product          The part to edit
     * @param cssPath       The CSS File Path
     */
    public FXProductSetupScreen(Product product, String cssPath)
    {
        /* Initialize the screen in Modify Mode */
        this(product, cssPath, DEFAULTTITLE);
    }

    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param product       The product to edit
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    public FXProductSetupScreen(Product product, String cssPath, String title)
    {
        /* Initialize the screen in Modify Mode */
        this(FXMode.MODIFY, product, cssPath, title);
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen
     * @param mode          The Form Mode (ADD or MODIFY)
     * @param product       The product to edit
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    protected FXProductSetupScreen(FXMode mode, Product product, String cssPath, String title)
    {
        /* Initialize Base */
        super(mode, cssPath, title);
        super.setSize(900, 650);
        
        /* Set the Product */
        _originalProduct = product;

        if (_originalProduct != null)
            _modifiedProduct = (Product)_originalProduct.clone();
        else
            _modifiedProduct = new Product();
        
        /* Initialize List */
        availableParts = FXCollections.observableArrayList();
        
        /* Create the Scene */
        this.createScene();
    }
    
    /**
     * Creates the Part Setup Screen
     */
    @Override
    public final void createScene()
    {
        /******************************************
         * Header
         *****************************************/
        
        /* Label for Title */
        Label lblHeader_Title = new Label();
        
        if (super.getMode() == FXMode.ADD)
            lblHeader_Title.setText("Add Products");
        else 
            lblHeader_Title.setText("Modify Products");
        
        lblHeader_Title.getStyleClass().add("darkblue-windowsmall-title-text");
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(lblHeader_Title);
        hBoxHeader.setMinHeight(30);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(12));
        hBoxHeader.getStyleClass().add("darkblue-windowsmall-title");

        /******************************************
         * Bottom
         *****************************************/
        
        /* Add Action Bar Contents */
        Button btnActionSave = new Button();
        btnActionSave.setText("Save");
        btnActionSave.getStyleClass().add("darkblue-button");
        btnActionSave.setPrefSize(100, 20);
        btnActionSave.setOnAction((ActionEvent e) -> {
            /* Sets the result to OK and close screen */
            handleSaveButtonAction(e);
        });

        Button btnActionCancel = new Button();
        btnActionCancel.setText("Cancel");
        btnActionCancel.getStyleClass().add("darkblue-button");
        btnActionCancel.setPrefSize(100, 20);
        btnActionCancel.setOnAction((ActionEvent e) -> {
            
            /* Sets the result to CANCEL and close screen */
            if (FXGUIHelper.ConfirmationBox(DEFAULTTITLE, "Are you sure?", "All your changes will be lost").get() == ButtonType.OK) {
                setResult(FXScreenResult.CANCEL);
                getCurrentStage().close();
            }
            else
                e.consume();
        });
        
        /* Container for the buttons */
        HBox hBoxFooter = new HBox();
        hBoxFooter.setAlignment(Pos.CENTER_RIGHT);
        hBoxFooter.setSpacing(10);
        hBoxFooter.setPadding(new Insets(10));
        
        hBoxFooter.getChildren().addAll(btnActionSave, btnActionCancel);

        /******************************************
         * Center
         *****************************************/
        
        /* Grid to split screen into two columns */
        GridPane gridSplit = new GridPane();
        gridSplit.setAlignment(Pos.CENTER);
        gridSplit.setPadding(new Insets(6, 6, 6, 6));
        
        /* Create column constraints */
        ColumnConstraints[] colConstraintsGridSplit = new ColumnConstraints[2];
        
        colConstraintsGridSplit[0] = new ColumnConstraints();
        colConstraintsGridSplit[0].setPercentWidth(40);
        colConstraintsGridSplit[0].setHalignment(HPos.CENTER);        
        
        colConstraintsGridSplit[1] = new ColumnConstraints();
        colConstraintsGridSplit[1].setPercentWidth(60);
        colConstraintsGridSplit[1].setHalignment(HPos.CENTER);
        colConstraintsGridSplit[1].setHgrow(Priority.ALWAYS);
        
        gridSplit.getColumnConstraints().addAll(colConstraintsGridSplit);
        
        /* Create row constraints */
        RowConstraints rowConstraintGridSplit = new RowConstraints();
        rowConstraintGridSplit.setVgrow(Priority.ALWAYS);
        
        gridSplit.getRowConstraints().add(rowConstraintGridSplit);
        
        /******************************************
         * Center - Column 1
         *****************************************/
        
        /* ****** Fields ****** */
        
        /* Field: ID */
        lblField_ID = new Label();
        lblField_ID.setText("ID");
        txtField_ID = new TextField();
        txtField_ID.setEditable(false);

        /* Field: Name */
        lblField_Name = new Label();
        lblField_Name.setText("Name");
        txtField_Name = new TextField();

        /* Field: Inventory */
        lblField_Inv = new Label();
        lblField_Inv.setText("Inventory");
        txtField_Inv = new TextField();

        /* Field: Price/Cost */
        lblField_PriceCost = new Label();
        lblField_PriceCost.setText("Price/Cost");
        txtField_PriceCost = new TextField();

        /* Field: Inventory Maximum */
        lblField_InvMax = new Label();
        lblField_InvMax.setText("Max");
        txtField_InvMax = new TextField();

        /* Field: Inventory Minimum */
        lblField_InvMin = new Label();
        lblField_InvMin.setText("Min");
        txtField_InvMin = new TextField();

        /* ****** Grid ****** */
        int iGridCenterRow = 0;
        GridPane gridCenter = new GridPane();
        gridCenter.setPrefWidth(400);
        gridCenter.setAlignment(Pos.TOP_CENTER);
        gridCenter.setHgap(4);
        gridCenter.setVgap(8);
        
        /* Define Columns */
        ColumnConstraints[] columns = new ColumnConstraints[4];
        
        columns[0] = new ColumnConstraints(80);
        columns[1] = new ColumnConstraints(70);

        columns[2] = new ColumnConstraints(65);
        columns[2].setHalignment(HPos.RIGHT);

        columns[3] = new ColumnConstraints(70);
        
        gridCenter.getColumnConstraints().addAll(columns);
        
        /* ID */
        iGridCenterRow++;
        gridCenter.add(lblField_ID, 0, iGridCenterRow);
        gridCenter.add(txtField_ID, 1, iGridCenterRow, 3, 1);

        /* Name */
        iGridCenterRow++;
        gridCenter.add(lblField_Name, 0, iGridCenterRow);
        gridCenter.add(txtField_Name, 1, iGridCenterRow, 3, 1);

        /* Inventory */
        iGridCenterRow++;
        gridCenter.add(lblField_Inv, 0, iGridCenterRow);
        gridCenter.add(txtField_Inv, 1, iGridCenterRow, 3, 1);

        /* Price/Cost */
        iGridCenterRow++;
        gridCenter.add(lblField_PriceCost, 0, iGridCenterRow);
        gridCenter.add(txtField_PriceCost, 1, iGridCenterRow, 3, 1);

        /* Inventory - Max / Min */
        iGridCenterRow++;
        gridCenter.add(lblField_InvMax, 0, iGridCenterRow);
        gridCenter.add(txtField_InvMax, 1, iGridCenterRow);

        gridCenter.add(lblField_InvMin, 2, iGridCenterRow);
        gridCenter.add(txtField_InvMin, 3, iGridCenterRow);
        
        /* Add objects to the GridSplit */
        gridSplit.add(gridCenter, 0, 0);
        
        /******************************************
         * Center - Column 2
         *****************************************/
        /* Create the grid to allocate two table views */
        GridPane gridCenterParts = new GridPane();
        gridCenterParts.setAlignment(Pos.CENTER);
        gridCenterParts.setPadding(new Insets(6, 6, 6, 6));
        gridCenterParts.setVgap(6);
        
        /* Make sure contains inside it will grow accordingly to the column */
        ColumnConstraints colConstraintsGridSplitParts = new ColumnConstraints();
        colConstraintsGridSplitParts.setHgrow(Priority.ALWAYS);
        
        gridCenterParts.getColumnConstraints().add(colConstraintsGridSplitParts);
        
        /* Setup Two Rows for this grid */
        RowConstraints[] rowConstraintsGridSplitParts = new RowConstraints[2];
        
        rowConstraintsGridSplitParts[0] = new RowConstraints();
        rowConstraintsGridSplitParts[0].setPercentHeight(50);
        rowConstraintsGridSplitParts[0].setValignment(VPos.CENTER);

        rowConstraintsGridSplitParts[1] = new RowConstraints();
        rowConstraintsGridSplitParts[1].setPercentHeight(50);
        rowConstraintsGridSplitParts[1].setValignment(VPos.CENTER);        
        
        gridCenterParts.getRowConstraints().addAll(rowConstraintsGridSplitParts);        
        
        /* Create Grid for All Parts */
        BorderPane paneAllParts = FXGUIHelper.createTitledPanel("Available Parts", 
                                                                this.createAllPartsPaneDetails(), 
                                                                (super.isStyled() ? "darkblue-titledpane" : ""));
        
        
        
        /* Create Grid for Product Parts */
        BorderPane paneProductParts = FXGUIHelper.createTitledPanel("Product Parts", 
                                                                    this.createProductPartsPaneDetails(), 
                                                                    (super.isStyled() ? "darkblue-titledpane" : ""));
        
        gridCenterParts.add(paneAllParts, 0, 0);
        gridCenterParts.add(paneProductParts, 0, 1);
        
        gridSplit.add(gridCenterParts, 1, 0);
        
        /***********************************************************************
         * Border Pane
         **********************************************************************/
        BorderPane border = new BorderPane();
        border.setTop(hBoxHeader);
        border.setCenter(gridSplit);
        border.setBottom(hBoxFooter);

        /***********************************************************************
         * Create the Scene
         **********************************************************************/
        super.scene = new Scene(border, super.getWidth(), super.getHeight());
        super.applyCss();

        /***********************************************************************
         * Fulfill list of Available Parts
         **********************************************************************/
        availableParts.addAll(Inventory.getInstance().getAllParts());

        /***********************************************************************
         * Load Original Product when Mode is Modify
         **********************************************************************/
        
        if (super.getMode() == FXMode.MODIFY) {
            txtField_ID.setText(Integer.toString(_originalProduct.getProductID()));
            txtField_Name.setText(_originalProduct.getName());
            txtField_Inv.setText(Integer.toString(_originalProduct.getInStock()));
            txtField_PriceCost.setText(Double.toString(_originalProduct.getPrice()));
            txtField_InvMax.setText(Integer.toString(_originalProduct.getMax()));
            txtField_InvMin.setText(Integer.toString(_originalProduct.getMin()));
            
            /* Remove Product Parts from Available Parts */
            for (Part productPart : _originalProduct.getAssociatedParts())
                availableParts.remove(productPart);
        }
        else
        {
            txtField_ID.setText(Integer.toString(Inventory.getInstance().getNextProductID(false)));
        }
        
        /* Focus on the Name Field */
        txtField_Name.requestFocus();
    }
    
    /**
     * Handler for the Save Button Action
     * @param event The ActionEvent to control the event handling
     */
    private void handleSaveButtonAction(ActionEvent event) {
        
        try
        {
            /* Perform Input Validation and Set Values */

            /* Name */
            if (txtField_Name.getText().trim().equals(""))
                throw new FXFormInputException("The Product Name cannot be blank.", 
                                               "Invalid Product Name",
                                               txtField_Name);
            
            _modifiedProduct.setName(txtField_Name.getText().trim());
            
            /* Inventory */
            try
            {
                _modifiedProduct.setInStock(Integer.parseInt(txtField_Inv.getText()));
            }
            catch (NumberFormatException e)
            {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("Inventory quantity must be a numeric value.\n\n A value of '%s' is not valid.", txtField_Inv.getText()), 
                                               "Invalid In Stock Quantity",
                                               txtField_Inv);
            }
            
            /* Price */
            try
            {
                _modifiedProduct.setPrice(Double.parseDouble(txtField_PriceCost.getText()));
            }
            catch (NumberFormatException e)
            {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("Price must be a numeric value.\n\n A value of '%s' is not valid.", txtField_PriceCost.getText()), 
                                               "Invalid Price / Cost",
                                               txtField_PriceCost);
            }

            /* Maximum Inventory */
            try
            {
                _modifiedProduct.setMax(Integer.parseInt(txtField_InvMax.getText()));
            }
            catch (NumberFormatException e)
            {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("Maximum Inventory quantity must be a numeric value.\n\n A value of '%s' is not valid.", txtField_InvMax.getText()), 
                                               "Invalid Maximum Inventory Quantity",
                                               txtField_InvMax);
            }

            /* Minimum Inventory */
            try
            {
                _modifiedProduct.setMin(Integer.parseInt(txtField_InvMin.getText()));
            }
            catch (NumberFormatException e)
            {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("Minimum Inventory quantity must be a numeric value.\n\n A value of '%s' is not valid.", txtField_InvMin.getText()), 
                                               "Invalid Minimum Inventory Quantity",
                                               txtField_InvMin);
            }
            
            /* Perform Data Validation */
            
            /* Inventory cannot be greater than the maximum value or lower than the minimum value */
            if (_modifiedProduct.getInStock() > _modifiedProduct.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be greater than Maximum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }

            if (_modifiedProduct.getInStock() < _modifiedProduct.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be less than Minimum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }
            
            /* Minimum cannot be greater than Maximum */
            if (_modifiedProduct.getMin() > _modifiedProduct.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Minimum quantity cannot be greater than the Maximum Quantity.", 
                                               "Minimum Quantity Validation",
                                               txtField_InvMin);
            }

            /* Maximum cannot be less than Maximum */
            if (_modifiedProduct.getMax() < _modifiedProduct.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Maximum quantity cannot be less than the Minimum Quantity.", 
                                               "Maximum Quantity Validation",
                                               txtField_InvMax);
            }
            
            /* Check Product Price greater than Components Price */
            if (_modifiedProduct.getPrice() < _modifiedProduct.getTotalComponentsPrice()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("The Product Price is %f , which is lower than the total components price of %f", _modifiedProduct.getPrice(), _modifiedProduct.getTotalComponentsPrice()), 
                                               "Price Validation",
                                               txtField_PriceCost);
            }
            
            /* Check if there are parts inside the product */
            if (_modifiedProduct.getAssociatedParts().size() == 0) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("A product must have at least one part.", 
                                               "Product Parts Validation",
                                               txtField_Name);                
            }
            
            /* Sets the Result to "OK" and close screen */

            /* Product ID */
            if (super.getMode() == FXMode.ADD)
                _modifiedProduct.setProductID(Inventory.getInstance().getNextProductID()); /* Do not use the one on the screen */
            else
                _modifiedProduct.setProductID(_originalProduct.getProductID());
            
            setResult(FXScreenResult.OK);
            getCurrentStage().close();
        }
        catch (FXFormInputException e)
        {
            /* Display the Alert */

            /* Alert Box to Display Errors */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data Input");
            alert.setHeaderText(e.getHeaderText());
            alert.setContentText(e.getMessage());
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            
            /* Focus on the TextField */
            if (e.getTextField() != null)
                e.getTextField().requestFocus();
                
            /* Consume the event so no further event handlers will be called */
            event.consume();
        }
    }
    
    /**
     * Creates the Pane with All Available Parts.
     *      * 
     * @return      A BorderPane with all JavaFX objects composing the All Available Parts display.
     */
    private BorderPane createAllPartsPaneDetails()
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
                    tableAllParts.getSelectionModel().select(part);
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
                tableAllParts.getSelectionModel().select(part);
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
        
        ColumnConstraints[] colConstraintsActionBar = new ColumnConstraints[1];
        
        colConstraintsActionBar[0] = new ColumnConstraints();
        colConstraintsActionBar[0].setPercentWidth(100);
        colConstraintsActionBar[0].setHalignment(HPos.RIGHT);
        
        actionsBarGridPane.getColumnConstraints().addAll(colConstraintsActionBar);
        
        /* Add Action Bar Contents */
        Button btnActionAdd = new Button();
        btnActionAdd.setText("Add");
        btnActionAdd.setPrefSize(130, 20);
        btnActionAdd.setOnAction((ActionEvent e) -> {
            /* Include Part on the Grid */
        });
        
        if (super.isStyled())
            btnActionAdd.getStyleClass().add("darkblue-button");

        actionsBarGridPane.add(btnActionAdd, 0, 0);

        /**********************************************
         * Table View
         **********************************************/
        tableAllParts = new TableView<>(Inventory.getInstance().getAllParts());
        tableAllParts.setEditable(false);
        
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
        
        tableAllParts.getColumns().addAll(tColPartID,
                                          tColPartName,
                                          tColInventoryLevel,
                                          tColPriceCost);
        
        /**********************************************
         * Finalize (Add all objects together)
         **********************************************/
        partsPane.setTop(searchBarGridPane);
        partsPane.setCenter(tableAllParts);
        partsPane.setBottom(actionsBarGridPane);
        
        return partsPane;
    }

    /**
     * Creates the Pane with the Parts Assigned to the Product.
     *      * 
     * @return      A BorderPane with all JavaFX objects composing the Product Parts display.
     */
    private BorderPane createProductPartsPaneDetails()
    {
        /**********************************************
         * BorderPane to display the contents 
         **********************************************/
        BorderPane partsPane = new BorderPane();
        
        /**********************************************
         * Actions Bar 
         **********************************************/
        GridPane actionsBarGridPane = new GridPane();
        actionsBarGridPane.setPadding(new Insets(6, 6, 6, 6));
        
        ColumnConstraints[] colConstraintsActionBar = new ColumnConstraints[1];
        
        colConstraintsActionBar[0] = new ColumnConstraints();
        colConstraintsActionBar[0].setPercentWidth(100);
        colConstraintsActionBar[0].setHalignment(HPos.RIGHT);
        
        actionsBarGridPane.getColumnConstraints().addAll(colConstraintsActionBar);
        
        /* Add Action Bar Contents */
        Button btnActionDelete = new Button();
        btnActionDelete.setText("Delete");
        btnActionDelete.setPrefSize(130, 20);
        btnActionDelete.setOnAction((ActionEvent e) -> {
            /* Delete Part from the Product */
        });
        
        if (super.isStyled())
            btnActionDelete.getStyleClass().add("darkblue-button");

        actionsBarGridPane.add(btnActionDelete, 0, 0);

        /**********************************************
         * Table View
         **********************************************/
        tableProductParts = new TableView<>(_modifiedProduct.getAssociatedParts());
        tableProductParts.setEditable(false);
        
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
        
        tableProductParts.getColumns().addAll(tColPartID,
                                              tColPartName,
                                              tColInventoryLevel,
                                              tColPriceCost);
        
        /**********************************************
         * Finalize (Add all objects together)
         **********************************************/
        partsPane.setCenter(tableProductParts);
        partsPane.setBottom(actionsBarGridPane);
        
        return partsPane;
    }
}
