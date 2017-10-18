/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Olavo Henrique Dias
 */
public class InventorySystem extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
        /***********************************************************************
         * Create the Main Screen
         **********************************************************************/
        
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
        hBoxBottom.setMinHeight(50);
        hBoxBottom.setAlignment(Pos.CENTER_RIGHT);
        hBoxBottom.setPadding(new Insets(20));
        
        /***********************************************************************
         * Center
         **********************************************************************/

        /***************************************
         * Left Panel
         **************************************/
        Label lblTeste = new Label();
        lblTeste.setText("Testing");
        
        StackPane paneParts = GUIHelper.createPanel(lblTeste);

        /***************************************
         * Right Panel
         **************************************/
        StackPane paneProducts = GUIHelper.createPanel(lblTeste);

        /***************************************
         * GridPane for Both Panels
         **************************************/
        GridPane gridCenter = new GridPane();
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        
        gridCenter.getColumnConstraints().addAll(col1, col1);
        
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        
        gridCenter.getRowConstraints().add(row1);
        
        gridCenter.add(paneParts, 0, 0);
        gridCenter.add(paneProducts, 1, 0);
        
        /***********************************************************************
         * Border Pane
         **********************************************************************/
        BorderPane border = new BorderPane();
        border.setTop(hBoxHeader);
        border.setBottom(hBoxBottom);
        border.setCenter(gridCenter);
        
        Scene scene = new Scene(border, 800, 400);
        scene.getStylesheets().add("inventorysystem/InventorySystem.css");
        
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
