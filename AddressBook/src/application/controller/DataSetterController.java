package application.controller;

import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DataSetterController {
    @FXML
    private TextField selectedDataSizeField;
    @FXML
    private TextField dataSizeField;

    private Stage dialogStage;
    private TableOverviewController tableOverviewController;
    private ObservableList<Person> currentData;
    private ObservableList<Person> selectedData = FXCollections.observableArrayList();
    private int selectedDataSize = 0;
    private int startIndex = 0;
    private int endIndex = 0;

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setCurrentData(ObservableList<Person> currentData){
        this.currentData = currentData;
        if (selectedDataSize > currentData.size()) selectedDataSize = currentData.size();
        if (endIndex > currentData.size()) endIndex = currentData.size();
        if (startIndex > currentData.size() && startIndex - selectedDataSize > 0) startIndex -= selectedDataSize;

        if (selectedDataSize == 0){selectedDataSize = currentData.size();
            showFirstPage();
        } else selectData();

        dataSizeField.setText(String.valueOf(currentData.size()));
        selectedDataSizeField.setText(String.valueOf(selectedDataSize));
    }

    private void selectData(){
        if (startIndex == endIndex && endIndex != 0) showPrevPage();
        selectedData.setAll(currentData.subList(startIndex, endIndex));
        selectedDataSizeField.setText(String.valueOf(selectedDataSize));
        initTable();
    }

    public void setTableOverviewController(TableOverviewController tableOverviewController){
        this.tableOverviewController = tableOverviewController;
    }

    private void initTable(){
        System.out.print(startIndex);
        System.out.print(endIndex + " ");
        tableOverviewController.removeAllTableItems();
        tableOverviewController.setTableData(selectedData);
    }

    @FXML
    private void showFirstPage(){
        startIndex = 0;
        endIndex = selectedDataSize;

        selectData();
    }

    @FXML
    private void showLastPage(){
        endIndex = currentData.size();
        startIndex = currentData.size() - (currentData.size()%selectedDataSize);

        if (startIndex == endIndex) startIndex = currentData.size() - selectedDataSize;
        selectData();
    }

    @FXML
    private void showPrevPage(){
        if(startIndex >= selectedDataSize - 1) {
            endIndex = startIndex;
            startIndex -= selectedDataSize;
            selectData();
        }
        else showFirstPage();
    }

    @FXML
    public void showNextPage(){
        if(currentData.size() - endIndex >= selectedDataSize) {
            startIndex = endIndex;
            endIndex += selectedDataSize;

            selectData();
        }
        else showLastPage();
    }

    @FXML
    private void changeSelectedDataSize(){
        String errorMessage = "";
        try {
            selectedDataSize = Integer.parseInt(selectedDataSizeField.getText());
        } catch (NumberFormatException e) {
            errorMessage += "No valid select size (must be an integer)!\n";
        }

        if (errorMessage.length() != 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            if (selectedDataSize > currentData.size()) selectedDataSize = currentData.size();
          //  if (selectedDataSize + startIndex + 1 <= currentData.size()) endIndex = startIndex + selectedDataSize;
          //  selectData();
            showFirstPage();
        }
    }
}
