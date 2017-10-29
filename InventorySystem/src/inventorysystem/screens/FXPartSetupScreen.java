/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.screens;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * Represents the screen to Add/Modify Parts
 * 
 * @author Olavo Henrique Dias
 */
public class FXPartSetupScreen extends FXMultiModeScreen {
    
    /* The default name for this form */
    private static final String DEFAULTTITLE = "Part Management";
    
    public FXPartSetupScreen(String cssPath)
    {
        /* Initialize the screen in Modify Mode */
        this(FXMode.NONE, cssPath, DEFAULTTITLE);
    }

    public FXPartSetupScreen(String cssPath, String title)
    {
        /* Initialize the screen in Modify Mode */
        this(FXMode.NONE, cssPath, title);
    }
    
    public FXPartSetupScreen(FXMode mode)
    {
        /* Initialize Base */
        this(mode, "");
        
        /* Create the Scene */
        this.createScene();
    }

    public FXPartSetupScreen(FXMode mode, String cssPath)
    {
        /* Initialize Base */
        this(mode, cssPath, DEFAULTTITLE);
        
        /* Create the Scene */
        this.createScene();
    }

    public FXPartSetupScreen(FXMode mode, String cssPath, String title)
    {
        /* Initialize Base */
        super(mode, cssPath, title);
        super.setSize(400, 350);
        
        /* Create the Scene */
        this.createScene();
    }
    
    
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
            /* Create the FXPartSetupScreen at Add Mode and show it */
            this.getCurrentStage().close();
            e.consume();
        });

        Button btnActionCancel = new Button();
        btnActionCancel.setText("Cancel");
        btnActionCancel.getStyleClass().add("darkblue-button");
        btnActionCancel.setPrefSize(100, 20);
        btnActionCancel.setOnAction((ActionEvent e) -> {
            /* Close Current Stage */
            this.getCurrentStage().close();
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
        
        /* Radio Button - InHouse / Outsourced */
        final ToggleGroup groupPartType = new ToggleGroup();
        
        RadioButton rbInHouse = new RadioButton();
        rbInHouse.setToggleGroup(groupPartType);
        rbInHouse.setText("In-House");
        
        RadioButton rbOutsourced = new RadioButton();
        rbOutsourced.setToggleGroup(groupPartType);
        rbOutsourced.setText("Outsourced");
        
        HBox rbContainer = new HBox();
        rbContainer.setSpacing(10);
        rbContainer.setAlignment(Pos.CENTER);
        rbContainer.getChildren().addAll(rbInHouse, rbOutsourced);

        /* ****** Fields ****** */
        
        /* Field: ID */
        Label lblField_ID = new Label();
        lblField_ID.setText("ID");
        TextField txtField_ID = new TextField();
        txtField_ID.setEditable(false);

        /* Field: Name */
        Label lblField_Name = new Label();
        lblField_Name.setText("Name");
        TextField txtField_Name = new TextField();

        /* Field: Inventory */
        Label lblField_Inv = new Label();
        lblField_Inv.setText("Inventory");
        TextField txtField_Inv = new TextField();

        /* Field: Price/Cost */
        Label lblField_PriceCost = new Label();
        lblField_PriceCost.setText("Price/Cost");
        TextField txtField_PriceCost = new TextField();

        /* Field: Inventory Maximum */
        Label lblField_InvMax = new Label();
        lblField_InvMax.setText("Max");
        TextField txtField_InvMax = new TextField();

        /* Field: Inventory Minimum */
        Label lblField_InvMin = new Label();
        lblField_InvMin.setText("Min");
        TextField txtField_InvMin = new TextField();

        /* Field: Machine ID (For In-House Parts) */
        Label lblField_MachineID = new Label();
        lblField_MachineID.setText("Machine ID");
        TextField txtField_MachineID = new TextField();

        /* Field: Company Name (For Outsourced Parts) */
        Label lblField_CompanyName = new Label();
        lblField_CompanyName.setText("Company Name");
        //TextField txtField_CompanyName = new TextField();
        
        
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
    }
}
