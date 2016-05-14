package application;

import application.controller.*;
import application.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import application.model.Person;

import java.io.IOException;

public class MainApp {
    private Stage primaryStage;

    private BorderPane rootLayout;
    private AnchorPane tableOverview;
    private AnchorPane dataSetter;
    private AnchorPane personAddDialog;
    private AnchorPane personDeleteDialog;
    private AnchorPane personFindDialog;
    private AnchorPane dataEnterComponent;

    private RootLayoutController rootLayoutController;
    private TableOverviewController tableOverviewController;
    private DataSetterController dataSetterController;
    private PersonAddDialogController personAddDIalogController;
    private PersonDeleteDialogController personDeleteDialogController;
    private PersonFindDialogController personFindDialogController;
    private DataEnterComponentController dataEnterComponentController;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.setResizable(false);

        loadFXML();

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void loadFXML(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
        try {
            rootLayout = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        rootLayoutController = loader.getController();

        loader.setLocation(MainApp.class.getResource("view/TableOverview.fxml"));
        try {
            tableOverview = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tableOverviewController = loader.getController();

        loader.setLocation(MainApp.class.getResource("view/DataSetter.fxml"));
        try {
            dataSetter = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSetterController= loader.getController();

        loader.setLocation(MainApp.class.getResource("view/PersonAddDialog.fxml"));
        try {
            personAddDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personAddDIalogController = loader.getController();

        loader.setLocation(MainApp.class.getResource("view/PersonDeleteDialog.fxml"));
        try {
            personDeleteDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personDeleteDialogController = loader.getController();

        loader.setLocation(MainApp.class.getResource("view/PersonFindDialog.fxml"));
        try {
            personFindDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personFindDialogController = loader.getController();

        loader.setLocation(MainApp.class.getResource("view/DataEnterComponent.fxml"));
        try {
            dataEnterComponent = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataEnterComponentController = loader.getController();
    }

    public void initDataEnterComponent(){
        dataEnterComponentController = new DataEnterComponentController();
    }














}
