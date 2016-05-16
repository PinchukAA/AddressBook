package application.controller;

import application.model.Person;
import application.model.DataBaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.Iterator;

public class PersonDeleteDialogController {
    private DataEnterComponentController dataEnterComponentController;
    private DataBaseController dataBaseController;

    private Stage dialogStage;
    private ObservableList<Person> findData = FXCollections.observableArrayList();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDataEnterComponentController(DataEnterComponentController dataEnterComponentController){
        this.dataEnterComponentController = dataEnterComponentController;
    }

    public void setDataBaseController(DataBaseController dataBaseController){
        this.dataBaseController = dataBaseController;
    }

    @FXML
    public void deleteData(){
        findData.remove(0, findData.size());

        for (Iterator<Person> iteratorPerson = (dataBaseController.getData()).iterator(); iteratorPerson.hasNext();){
            boolean a = false;
            boolean b = true;
            Person tempPerson = iteratorPerson.next();
            if(dataEnterComponentController.getFirstNameCheck())
                if ((tempPerson.getFirstName()).equals(dataEnterComponentController.getFirstNameText())) a = true;
                else b = false;
            if(dataEnterComponentController.getLastNameCheck())
                if ((tempPerson.getLastName()).equals(dataEnterComponentController.getLastNameText())) a = true;
                else b = false;
            if(dataEnterComponentController.getSurNameCheck())
                if ((tempPerson.getSurName()).equals(dataEnterComponentController.getSurNameText())) a = true;
                else b = false;
            if(dataEnterComponentController.getCountryCheck())
                if ((tempPerson.getCountry()).equals(dataEnterComponentController.getCountryText())) a = true;
                else b = false;
            if(dataEnterComponentController.getRegionCheck())
                if ((tempPerson.getRegion()).equals(dataEnterComponentController.getRegionText())) a = true;
                else b = false;
            if(dataEnterComponentController.getCityCheck())
                if ((tempPerson.getCity()).equals(dataEnterComponentController.getCityText())) a = true;
                else b = false;
            if(dataEnterComponentController.getStreetCheck())
                if ((tempPerson.getStreet()).equals(dataEnterComponentController.getStreetText())) a = true;
                else b = false;
            if(dataEnterComponentController.getHouseNumberCheck())
                if ((tempPerson.getHouseNumber()) == dataEnterComponentController.getHouseNumberText()) a = true;
                else b = false;
            if(dataEnterComponentController.getPavilionNumberCheck())
                if ((tempPerson.getPavilionNumber()) == dataEnterComponentController.getPavilionNumberText()) a = true;
                else b = false;
            if(dataEnterComponentController.getFlatNumberCheck())
                if ((tempPerson.getFlatNumber()) == dataEnterComponentController.getFlatNumberText()) a = true;
                else b = false;
            if (a && b) findData.add(tempPerson);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);

        if(findData.size() != 0){
            dataBaseController.deletePerson(findData);
            alert.setTitle("Deleted items");
            alert.setHeaderText("Deleted items");
            alert.setContentText(findData.size() + " items have been deleted.");

            alert.showAndWait();
        } else {
            alert.setTitle("Deleted items");
            alert.setHeaderText("No content in the table");
            alert.setContentText(0 + " items have been deleted.");

            alert.showAndWait();
        }
    }

    @FXML
    public void handleCancel() {
        dialogStage.close();
    }
}
