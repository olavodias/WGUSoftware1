/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import inventorysystem.FXGUIHelper;
import inventorysystem.exceptions.FXFormInputException;
import inventorysystem.models.InHousePart;
import inventorysystem.models.Inventory;
import inventorysystem.models.OutsourcedPart;
import inventorysystem.models.Part;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * Represents the screen to Add/Modify Parts
 * 
 * @author Olavo Henrique Dias
 */
public class FXPartSetupScreen extends FXMultiModeScreen {
    
    /* The default name for this form */
    private static final String DEFAULTTITLE = "Part Management";
    
    /***************************************************************************
     * Shared UI Fields (to be used by multiple methods)
     **************************************************************************/

    /* Radio Button - InHouse / Outsourced */
    final ToggleGroup groupPartType = new ToggleGroup();

    RadioButton rbInHouse = new RadioButton();
    RadioButton rbOutsourced = new RadioButton();
    
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

    /* Machine ID */
    Label lblField_MachineID = new Label();
    TextField txtField_MachineID = new TextField();

    /* Company Name */
    Label lblField_CompanyName = new Label();
    TextField txtField_CompanyName = new TextField();

    /***************************************************************************
     * Properties
     **************************************************************************/
    protected final Part _originalPart;
    
    /**
     * Returns the Original Part
     * @return The Original Part
     */
    public Part getOriginalPart() {
        return _originalPart;
    }

    private Part _modifiedPart;
    
    /**
     * Returns the Modified Part
     * @return The Modified Part
     */
    public Part getModifiedPart() {
        return _modifiedPart;
    }
    
    /**
     * Sets the Modified Part
     * @param modifiedPart   The Modified Part
     */
    protected void setModifiedPart(Part modifiedPart) {
        _modifiedPart = modifiedPart;
    }
    
    /***************************************************************************
     * Constructors
     **************************************************************************/
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     */
    public FXPartSetupScreen()
    {
        /* Initialize the screen in Add Mode */
        this("");
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     * @param cssPath       The CSS File Path
     */
    public FXPartSetupScreen(String cssPath)
    {
        /* Initialize the screen in Add Mode */
        this(cssPath, DEFAULTTITLE);
    }

    /**
     * Initializes a new instance of the FXPartSetup Screen in Add Mode
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    public FXPartSetupScreen(String cssPath, String title)
    {
        /* Initialize the screen in Add Mode */
        this(FXMode.ADD, null, cssPath, title);
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param part          The part to edit
     */
    public FXPartSetupScreen(Part part)
    {
        /* Initialize the screen in Modify Mode */
        this(part, "");
    }
    
    
    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param part          The part to edit
     * @param cssPath       The CSS File Path
     */
    public FXPartSetupScreen(Part part, String cssPath)
    {
        /* Initialize the screen in Modify Mode */
        this(part, cssPath, DEFAULTTITLE);
    }

    /**
     * Initializes a new instance of the FXPartSetup Screen in Modify Mode
     * @param part          The part to edit
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    public FXPartSetupScreen(Part part, String cssPath, String title)
    {
        /* Initialize the screen in Modify Mode */
        this(FXMode.MODIFY, part, cssPath, title);
    }
    
    /**
     * Initializes a new instance of the FXPartSetup Screen
     * @param mode          The Form Mode (ADD or MODIFY)
     * @param part          The part to edit
     * @param cssPath       The CSS File Path
     * @param title         The Screen Title
     */
    protected FXPartSetupScreen(FXMode mode, Part part, String cssPath, String title)
    {
        /* Initialize Base */
        super(mode, cssPath, title);
        super.setSize(400, 350);
        
        /* Set the Part Number */
        _originalPart = part;
        
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
            lblHeader_Title.setText("Add Parts");
        else 
            lblHeader_Title.setText("Modify Parts");
        
        lblHeader_Title.getStyleClass().add("label-header-small");
        
        /* Horizontal Box */
        HBox hBoxHeader = new HBox();
        hBoxHeader.getChildren().add(lblHeader_Title);
        hBoxHeader.setMinHeight(30);
        hBoxHeader.setAlignment(Pos.CENTER_LEFT);
        hBoxHeader.setPadding(new Insets(12));
        hBoxHeader.getStyleClass().add("hbox-header-small");

        /******************************************
         * Bottom
         *****************************************/
        
        /* Add Action Bar Contents */
        Button btnActionSave = new Button();
        btnActionSave.setText("Save");
        btnActionSave.getStyleClass().add("button-type2");
        btnActionSave.setPrefSize(100, 20);
        btnActionSave.setOnAction((ActionEvent e) -> {
            /* Sets the result to OK and close screen */
            handleSaveButtonAction(e);
        });

        Button btnActionCancel = new Button();
        btnActionCancel.setText("Cancel");
        btnActionCancel.getStyleClass().add("button-type2");
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
        hBoxFooter.getStyleClass().add("hbox-bottom-small");
        hBoxFooter.setPadding(new Insets(10));
        
        hBoxFooter.getChildren().addAll(btnActionSave, btnActionCancel);

        /******************************************
         * Center
         *****************************************/
        
        /* Radio Button - InHouse / Outsourced */
        rbInHouse = new RadioButton();
        rbInHouse.setToggleGroup(groupPartType);
        rbInHouse.setText("In-House");
        rbInHouse.setOnAction((ActionEvent e) -> { 
            setPartMode(FXPartMode.INHOUSE);
        });
        
        rbOutsourced = new RadioButton();
        rbOutsourced.setToggleGroup(groupPartType);
        rbOutsourced.setText("Outsourced");
        rbOutsourced.setOnAction((ActionEvent e) -> { 
            setPartMode(FXPartMode.OUTSOURCED);
        });
        
        HBox rbContainer = new HBox();
        rbContainer.setSpacing(10);
        rbContainer.setAlignment(Pos.CENTER);
        rbContainer.getChildren().addAll(rbInHouse, rbOutsourced);

        /* ****** Fields ****** */
        
        /* Field: ID */
        lblField_ID = new Label();
        lblField_ID.setText("ID");
        txtField_ID = new TextField();
        txtField_ID.setEditable(false);
        txtField_ID.setDisable(true);

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

        /* Field: Machine ID (For In-House Parts) */
        lblField_MachineID = new Label();
        lblField_MachineID.setText("Machine ID");
        txtField_MachineID = new TextField();

        /* Field: Company Name (For Outsourced Parts) */
        lblField_CompanyName = new Label();
        lblField_CompanyName.setText("Company Name");
        txtField_CompanyName = new TextField();
        
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
        
        /* InHouse / Outsourced Radio Buttons */
        gridCenter.add(rbContainer, 0, iGridCenterRow, 4, 1);
        
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
        
        /* Machine ID (For InHouse Parts) */
        iGridCenterRow++;
        gridCenter.add(lblField_MachineID, 0, iGridCenterRow);
        gridCenter.add(txtField_MachineID, 1, iGridCenterRow, 3, 1);

        /* Company Name (For Outsourced Parts) */
        gridCenter.add(lblField_CompanyName, 0, iGridCenterRow);
        gridCenter.add(txtField_CompanyName, 1, iGridCenterRow, 3, 1);
        
        /***********************************************************************
         * Border Pane
         **********************************************************************/
        BorderPane border = new BorderPane();
        border.setTop(hBoxHeader);
        border.setCenter(gridCenter);
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
            txtField_ID.setText(Integer.toString(_originalPart.getPartID()));
            txtField_Name.setText(_originalPart.getName());
            txtField_Inv.setText(Integer.toString(_originalPart.getInStock()));
            txtField_PriceCost.setText(Double.toString(_originalPart.getPrice()));
            txtField_InvMax.setText(Integer.toString(_originalPart.getMax()));
            txtField_InvMin.setText(Integer.toString(_originalPart.getMin()));
            
            if (_originalPart.getClass() == InHousePart.class) 
            {
                txtField_MachineID.setText(Integer.toString(((InHousePart)_originalPart).getMachineID()));
                groupPartType.selectToggle(rbInHouse);
                setPartMode(FXPartMode.INHOUSE);
            }
            else 
            {
                txtField_CompanyName.setText(((OutsourcedPart)_originalPart).getCompanyName());
                groupPartType.selectToggle(rbOutsourced);
                setPartMode(FXPartMode.OUTSOURCED);
            }
        }
        else
        {
            txtField_ID.setText(Integer.toString(Inventory.getInstance().getNextPartID(false)));
            groupPartType.selectToggle(rbInHouse);
            setPartMode(FXPartMode.INHOUSE);
        }
        
        /* Focus on the Name Field */
        txtField_Name.requestFocus();
    }
    
    /**
     * Method to configure the screen fields based on the mode
     * 
     * @param mode A FXPartMode to define whether the mode in "InHouse" or "Outsourced"
     */
    public void setPartMode(FXPartMode mode)
    {
        if (mode == FXPartMode.INHOUSE)
        {
            /* InHouse Parts - Show Machine ID / Hide Company Name */
            lblField_MachineID.setVisible(true);
            txtField_MachineID.setVisible(true);
            
            lblField_CompanyName.setVisible(false);
            txtField_CompanyName.setVisible(false);
        }
        else
        {
            /* Outsourced Parts - Hide Machine ID / Show Company Name */
            lblField_MachineID.setVisible(false);
            txtField_MachineID.setVisible(false);
            
            lblField_CompanyName.setVisible(true);
            txtField_CompanyName.setVisible(true);
        }
    }
    
    /**
     * Handler for the Save Button Action
     * @param event The ActionEvent to control the event handling
     */
    private void handleSaveButtonAction(ActionEvent event) {
        
        try
        {
            /* Defines the Temporary Part Number */
            Part _tempPart;
            
            /* Creates a new object with the information on the screen */
            if (groupPartType.getSelectedToggle().equals(rbInHouse))
            {
                /* This is an InHouse Part */
                _tempPart = new InHousePart();

                /* Validate Machine ID - It has to be an integer */
                try
                {
                    if (txtField_MachineID.getText().trim().equals(""))
                    {
                        /* There is nothing on the Machine ID, set to Zero */
                        ((InHousePart)_tempPart).setMachineID(0);
                    }
                    else
                    {
                        /* Try to parte the value to an integer */
                        ((InHousePart)_tempPart).setMachineID(Integer.parseInt(txtField_MachineID.getText().trim()));
                    }
                }
                catch (NumberFormatException e)
                {
                    /* Throws the exception to the next catch block */
                    throw new FXFormInputException("Enter a valid MachineID, it has to be a numeric value.",
                                                   "Invalid Machine ID",
                                                   txtField_MachineID);
                }
                catch (Exception e)
                {
                    /* Any random exception */
                    throw e;
                }
            }
            else
            {
                /* This is an Outsourced Part */
                _tempPart = new OutsourcedPart();

                ((OutsourcedPart)_tempPart).setCompanyName(txtField_CompanyName.getText().trim());
            }

            /* Perform Input Validation and Set Values */

            /* Name */
            if (txtField_Name.getText().trim().equals(""))
                throw new FXFormInputException("The Part Name cannot be blank.", 
                                               "Invalid Part Name",
                                               txtField_Name);
            
            _tempPart.setName(txtField_Name.getText().trim());
            
            /* Inventory */
            try
            {
                _tempPart.setInStock(Integer.parseInt(txtField_Inv.getText()));
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
                _tempPart.setPrice(Double.parseDouble(txtField_PriceCost.getText()));
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
                _tempPart.setMax(Integer.parseInt(txtField_InvMax.getText()));
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
                _tempPart.setMin(Integer.parseInt(txtField_InvMin.getText()));
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
            if (_tempPart.getInStock() > _tempPart.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be greater than Maximum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }

            if (_tempPart.getInStock() < _tempPart.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Inventory cannot be less than Minimum Inventory.", 
                                               "Inventory Quantity Validation",
                                               txtField_Inv);
            }
            
            /* Minimum cannot be greater than Maximum */
            if (_tempPart.getMin() > _tempPart.getMax()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Minimum quantity cannot be greater than the Maximum Quantity.", 
                                               "Minimum Quantity Validation",
                                               txtField_InvMin);
            }

            /* Maximum cannot be less than Maximum */
            if (_tempPart.getMax() < _tempPart.getMin()) {
                /* Throws the exception to the next catch block */
                throw new FXFormInputException("Maximum quantity cannot be less than the Minimum Quantity.", 
                                               "Maximum Quantity Validation",
                                               txtField_InvMax);
            }
            
            /* Sets the Result to "OK" and close screen */

            /* Part ID */
            if (super.getMode() == FXMode.ADD)
                _tempPart.setPartID(Inventory.getInstance().getNextPartID()); /* Do not use the one on the screen */
            else
                _tempPart.setPartID(_originalPart.getPartID());
            
            setModifiedPart(_tempPart);
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
