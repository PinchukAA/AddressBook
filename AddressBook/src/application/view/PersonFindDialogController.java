package application.view;

import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Iterator;

public class PersonFindDialogController {
    private TableOverviewController findTableOverviewController;
    private DataSetterController findDataSetterController;
    private DataBaseController dataBaseController;
    private DataEnterComponentController findDataEnterComponentController;

    private Stage dialogStage;
    private boolean okClicked = false;

    private ObservableList<Person> findData = FXCollections.observableArrayList();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFindDataSetterController(DataSetterController findDataSetterController){
        this.findDataSetterController = findDataSetterController;
    }

    public void setDataBaseController(DataBaseController dataBaseController){
        this.dataBaseController = dataBaseController;
    }

    public void setFindDataEnterComponentController(DataEnterComponentController findDataEnterComponentController){
        this.findDataEnterComponentController = findDataEnterComponentController;
    }

    public void setFindTableOverviewController(TableOverviewController findTableOverviewController){
        this.findTableOverviewController = findTableOverviewController;
    }

    public boolean isFindClicked() {
        return okClicked;
    }

    @FXML
    public void handleFindPerson(){
        findData.remove(0, findData.size());

        for (Iterator<Person> iteratorPerson = (dataBaseController.getData()).iterator(); iteratorPerson.hasNext();){
            boolean a = false;
            boolean b = true;
            Person tempPerson = iteratorPerson.next();
            if(findDataEnterComponentController.getFirstNameCheck())
                if ((tempPerson.getFirstName()).equals(findDataEnterComponentController.getFirstNameText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getLastNameCheck())
                if ((tempPerson.getLastName()).equals(findDataEnterComponentController.getLastNameText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getSurNameCheck())
                if ((tempPerson.getSurName()).equals(findDataEnterComponentController.getSurNameText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getCountryCheck())
                if ((tempPerson.getCountry()).equals(findDataEnterComponentController.getCountryText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getRegionCheck())
                if ((tempPerson.getRegion()).equals(findDataEnterComponentController.getRegionText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getCityCheck())
                if ((tempPerson.getCity()).equals(findDataEnterComponentController.getCityText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getStreetCheck())
                if ((tempPerson.getStreet()).equals(findDataEnterComponentController.getStreetText())) a = true;
                else b = false;
            if(findDataEnterComponentController.getHouseNumberCheck())
                if ((tempPerson.getHouseNumber()) == findDataEnterComponentController.getHouseNumberText()) a = true;
                else b = false;
            if(findDataEnterComponentController.getPavilionNumberCheck())
                if ((tempPerson.getPavilionNumber()) == findDataEnterComponentController.getPavilionNumberText()) a = true;
                else b = false;
            if(findDataEnterComponentController.getFlatNumberCheck())
                if ((tempPerson.getFlatNumber()) == findDataEnterComponentController.getFlatNumberText()) a = true;
                else b = false;
            if (a && b) findData.add(tempPerson);
        }
        findDataSetterController.setCurrentData(findData);
    }

    @FXML
    public void handleDeletePerson(){
        Person delPerson = findTableOverviewController.getSelectedPerson();
        if (delPerson != null) {
            findData.remove(delPerson);
            findDataSetterController.setCurrentData(findData);
            dataBaseController.deletePerson(delPerson);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(dialogStage);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }
}
