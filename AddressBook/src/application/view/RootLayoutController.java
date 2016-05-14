package application.view;

import application.MainApp;
import application.model.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Iterator;

public class RootLayoutController {
    private MainApp mainApp;
    private DataSaver dataSaver;
    private DataBaseController dataBaseController;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public void setDataSaver(DataSaver dataSaver){
        this.dataSaver = dataSaver;
    }
    public void setDataBaseController(DataBaseController dataBaseController){
        this.dataBaseController = dataBaseController;
    }

    @FXML
    public void handleNewPerson() {

    }

    @FXML
    public void handleEditPerson() {

    }

    @FXML
    public void handleFindPerson(){

    }

    @FXML
    public void handleDeletePerson() {

    }

    @FXML
    public void handleFindAndDeletePerson() {

    }

    @FXML
    private void handleNewFile() {
        dataSaver.setPersonFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            dataSaver.loadPersonDataFromFile(file);
        }
        dataBaseController.setData(dataSaver.getData());
        for (Iterator<Person> iteratorPerson = dataSaver.getData().iterator(); iteratorPerson.hasNext();){
            iteratorPerson.next().setFIO();
        }
    }


    @FXML
    private void handleSave() {
        dataSaver.setData(dataBaseController.getData());
        File personFile = dataSaver.getPersonFilePath();
        if (personFile != null) {
            dataSaver.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        dataSaver.setData(dataBaseController.getData());
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            dataSaver.savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
