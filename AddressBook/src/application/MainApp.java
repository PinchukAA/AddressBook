package application;

import application.controller.*;
import application.model.DataBaseController;
import application.model.Person;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Modality;
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
    private TableOverviewController findTableOverviewController;
    private DataSetterController dataSetterController;
    private DataSetterController findDataSetterController;
    private PersonAddDialogController personAddDialogController;
    private PersonDeleteDialogController personDeleteDialogController;
    private PersonFindDialogController personFindDialogController;
    private DataEnterComponentController dataEnterComponentController;
    private DataEnterComponentController findDataEnterComponentController;

    private DataBaseController dataBaseController;
    private DataSaver dataSaver;


    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.setResizable(false);

        initRootLayout();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void initRootLayout(){
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
        } catch (IOException e) {
            System.out.print(1);
            e.printStackTrace();
        }
        tableOverviewController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataSetter.fxml"));
        try {
            dataSetter = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSetterController = loader.getController();

        //////////////////////////////////////////////////////////////////////
        AnchorPane.setLeftAnchor(tableOverview, 0.0);
        AnchorPane.setTopAnchor(tableOverview, 0.0);
        dataSetter.getChildren().add(tableOverview);
        rootLayout.setRight(dataSetter);

        //////////////////////////////////////////////////////////////////////
        rootLayoutController.setMainApp(this);

        dataSetterController.setTableOverviewController(tableOverviewController);
        dataBaseController = new DataBaseController(dataSetterController);
        rootLayoutController.setDataBaseController(dataBaseController);
        rootLayoutController.setTableOverviewController(tableOverviewController);

        dataSaver = new DataSaver();
        dataSaver.setMainApp(this);
        dataSaver.setPrimaryStage(primaryStage);
        dataSaver.setDataBaseController(dataBaseController);
        rootLayoutController.setDataSaver(dataSaver);

        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean showPersonAddDialog(Person person){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonAddDialog.fxml"));
        try {
            personAddDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personAddDialogController = loader.getController();

        //////////////////////////////////////////////////////////////////////
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(personAddDialog);
        dialogStage.setScene(scene);

        personAddDialogController.setDialogStage(dialogStage);
        personAddDialogController.setPerson(person);

        dialogStage.showAndWait();

        return personAddDialogController.isOkClicked();
    }


    public boolean showPersonFindDialog(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonFindDialog.fxml"));
        try {
            personFindDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personFindDialogController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataSetter.fxml"));
        try {
            findDataSetter = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        findDataSetterController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/TableOverview.fxml"));
        try {
            findTableOverview = (AnchorPane) loader.load();
        } catch (IOException e) {
            System.out.print(1);
            e.printStackTrace();
        }
        findTableOverviewController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataEnterComponent.fxml"));
        try {
            findDataEnterComponent = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        findDataEnterComponentController = loader.getController();

        //////////////////////////////////////////////////////////////////
        AnchorPane.setLeftAnchor(findTableOverview, 0.0);
        AnchorPane.setTopAnchor(findTableOverview, 0.0);
        findDataSetter.getChildren().add(findTableOverview);

        AnchorPane.setLeftAnchor(findDataSetter, 8.0);
        AnchorPane.setTopAnchor(findDataSetter, 0.0);

        AnchorPane.setLeftAnchor(findDataEnterComponent, 8.0);
        AnchorPane.setBottomAnchor(findDataEnterComponent, 5.0);

        personFindDialog.getChildren().add(findDataSetter);
        personFindDialog.getChildren().add(findDataEnterComponent);

        //////////////////////////////////////////////////////////////////
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Find Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setResizable(false);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(personFindDialog);
        dialogStage.setScene(scene);

        findDataSetterController.setTableOverviewController(findTableOverviewController);

        findDataSetterController.setDialogStage(dialogStage);
        personFindDialogController.setDialogStage(dialogStage);
        personFindDialogController.setDataBaseController(dataBaseController);
        personFindDialogController.setFindDataSetterController(findDataSetterController);
        personFindDialogController.setFindDataEnterComponentController(findDataEnterComponentController);
        personFindDialogController.setFindTableOverviewController(findTableOverviewController);

        dialogStage.showAndWait();

        return personFindDialogController.isFindClicked();
    }

    public void showPersonDeleteDialog(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonDeleteDialog.fxml"));
        try {
            personDeleteDialog = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        personDeleteDialogController = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/DataEnterComponent.fxml"));
        try {
            dataEnterComponent = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataEnterComponentController = loader.getController();

        //////////////////////////////////////////////////////////////////
        AnchorPane.setLeftAnchor(dataEnterComponent, 8.0);
        AnchorPane.setTopAnchor(dataEnterComponent, 5.0);
        personDeleteDialog.getChildren().add(dataEnterComponent);

        //////////////////////////////////////////////////////////////////
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Delete Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setResizable(false);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(personDeleteDialog);
        dialogStage.setScene(scene);

        personDeleteDialogController.setDialogStage(dialogStage);
        personDeleteDialogController.setDataEnterComponentController(dataEnterComponentController);
        personDeleteDialogController.setDataBaseController(dataBaseController);

        dialogStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
