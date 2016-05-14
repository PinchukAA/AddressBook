package application;

import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import java.io.IOException;

public class MainApp extends Application{
    private Stage primaryStage;

    private BorderPane rootLayout;
    private AnchorPane tableOverview;
    private AnchorPane findTableOverview;
    private AnchorPane dataSetter;
    private AnchorPane findDataSetter;
    private AnchorPane personAddDialog;
    private AnchorPane personDeleteDialog;
    private AnchorPane personFindDialog;
    private AnchorPane findDataEnterComponent;
    private AnchorPane dataEnterComponent;

    private RootLayoutController rootLayoutController;
    private TableOverviewController tableOverviewController;
    private DataSetterController dataSetterController;
    private PersonAddDialogController personAddDIalogController;
    private PersonDeleteDialogController personDeleteDialogController;
    private PersonFindDialogController personFindDialogController;
    private DataEnterComponentController dataEnterComponentController;

    private DataBaseController dataBaseController;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.setResizable(false);

        loadFXML();
        initRootLayout();

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

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TableOverview.fxml"));
        try {
            tableOverview = (AnchorPane) loader.load();
 //           findTableOverview = (AnchorPane) loader.load();
        } catch (IOException e) {
            System.out.print(1);
            e.printStackTrace();
        }
        tableOverviewController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataSetter.fxml"));
        try {
            dataSetter = (AnchorPane) loader.load();
   //         findDataSetter = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSetterController = loader.getController();
/*
        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonAddDialog.fxml"));
        try {
            personAddDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personAddDialogController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonDeleteDialog.fxml"));
        try {
            personDeleteDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personDeleteDialogController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonFindDialog.fxml"));
        try {
            personFindDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personFindDialogController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataEnterComponent.fxml"));
        try {
            findDataEnterComponent = (AnchorPane) loader.load();
            dataEnterComponent = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataEnterComponentController = loader.getController();*/
    }

    public void initDataEnterComponentController(){
        dataEnterComponentController = new DataEnterComponentController();
    }

    public void initRootLayout(){
        AnchorPane.setLeftAnchor(tableOverview, 0.0);
        AnchorPane.setTopAnchor(tableOverview, 0.0);
        dataSetter.getChildren().add(tableOverview);

        rootLayout.setRight(dataSetter);
        rootLayoutController.setMainApp(this);

        dataBaseController = new DataBaseController(dataSetterController);
        dataSetterController.setTableOverviewController(tableOverviewController);
        rootLayoutController.setDataBaseController(dataBaseController);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
