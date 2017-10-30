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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    
    /**
     * Sets the Modified Product
     * @param modifiedProduct   The Modified Product
     */
    protected void setModifiedProduct(Product modifiedProduct) {
        _modifiedProduct = modifiedProduct;
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
     * @param product          The part to edit
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
     * @param product          The part to edit
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    protected FXProductSetupScreen(FXMode mode, Product product, String cssPath, String title)
    {
        /* Initialize Base */
        super(mode, cssPath, title);
        super.setSize(900, 450);
        
        /* Set the Part Number */
        _originalProduct = product;
        
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
        
        ColumnConstraints[] colConstraintsGridSplit = new ColumnConstraints[2];
        
        colConstraintsGridSplit[0] = new ColumnConstraints();
        colConstraintsGridSplit[0].setPercentWidth(40);
        colConstraintsGridSplit[0].setHalignment(HPos.CENTER);
        
        colConstraintsGridSplit[1] = new ColumnConstraints();
        colConstraintsGridSplit[1].setPercentWidth(60);
        colConstraintsGridSplit[1].setHalignment(HPos.CENTER);
        
        gridSplit.getColumnConstraints().addAll(colConstraintsGridSplit);
        
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
        gridCenter.setAlignment(Pos.CENTER);
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
        
        RowConstraints[] rowConstraintsGridSplitParts = new RowConstraints[2];
        
        rowConstraintsGridSplitParts[0] = new RowConstraints();
        rowConstraintsGridSplitParts[0].setPercentHeight(50);
        rowConstraintsGridSplitParts[0].setValignment(VPos.CENTER);

        rowConstraintsGridSplitParts[1] = new RowConstraints();
        rowConstraintsGridSplitParts[1].setPercentHeight(50);
        rowConstraintsGridSplitParts[1].setValignment(VPos.CENTER);        
        
        gridCenterParts.getRowConstraints().addAll(rowConstraintsGridSplitParts);
        
        Label lbl1 = new Label("Label1");
        Label lbl2 = new Label("Label2");
        
        gridCenterParts.add(lbl1, 0, 0);
        gridCenterParts.add(lbl2, 0, 1);
        
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
         * Load Original Part when Mode is Modify
         **********************************************************************/
        if (super.getMode() == FXMode.MODIFY) {
            txtField_ID.setText(Integer.toString(_originalProduct.getProductID()));
            txtField_Name.setText(_originalProduct.getName());
            txtField_Inv.setText(Integer.toString(_originalProduct.getInStock()));
            txtField_PriceCost.setText(Double.toString(_originalProduct.getPrice()));
            txtField_InvMax.setText(Integer.toString(_originalProduct.getMax()));
            txtField_InvMin.setText(Integer.toString(_originalProduct.getMin()));
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
            /* Defines the Temporary Part Number */
            Product _tempProduct = new Product();
            
            /* Perform Input Validation and Set Values */

            /* Name */
            if (txtField_Name.getText().trim().equals(""))
                throw new FXFormInputException("The Product Name cannot be blank.", 
                                               "Invalid Product Name",
                                               txtField_Name);
            
            _tempProduct.setName(txtField_Name.getText().trim());
            
            /* Inventory */
            try
            {
                _tempProduct.setInStock(Integer.parseInt(txtField_Inv.getText()));
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
                _tempProduct.setPrice(Double.parseDouble(txtField_PriceCost.getText()));
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
                _tempProduct.setMax(Integer.parseInt(txtField_InvMax.getText()));
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
                _tempProduct.setMin(Integer.parseInt(txtField_InvMin.getText()));
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
            if (_tempProduct.getInStock() > _tempProduct.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be greater than Maximum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }

            if (_tempProduct.getInStock() < _tempProduct.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be less than Minimum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }
            
            /* Minimum cannot be greater than Maximum */
            if (_tempProduct.getMin() > _tempProduct.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Minimum quantity cannot be greater than the Maximum Quantity.", 
                                               "Minimum Quantity Validation",
                                               txtField_InvMin);
            }

            /* Maximum cannot be less than Maximum */
            if (_tempProduct.getMax() < _tempProduct.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Maximum quantity cannot be less than the Minimum Quantity.", 
                                               "Maximum Quantity Validation",
                                               txtField_InvMax);
            }
            
            /* Check Product Price greater than Components Price */
            if (_tempProduct.getPrice() < _tempProduct.getTotalComponentsPrice()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException(String.format("The Product Price is %f , which is lower than the total components price of %f", _tempProduct.getPrice(), _tempProduct.getTotalComponentsPrice()), 
                                               "Price Validation",
                                               txtField_PriceCost);
            }
            
            /* Sets the Result to "OK" and close screen */

            /* Part ID */
            if (super.getMode() == FXMode.ADD)
                _tempProduct.setProductID(Inventory.getInstance().getNextProductID()); /* Do not use the one on the screen */
            else
                _tempProduct.setProductID(_originalProduct.getProductID());
            
            setModifiedProduct(_tempProduct);
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
}
