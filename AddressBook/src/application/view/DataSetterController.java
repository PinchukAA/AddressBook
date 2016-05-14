package application.view;

import application.MainApp;
import application.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DataSetterController {
    @FXML
    private TextField selectedDataSizeField;
    @FXML
    private TextField dataSizeField;

    private MainApp mainApp;
    private TableOverviewController tableOverviewController;
    private ObservableList<Person> currentData;
    private ObservableList<Person> selectedData = FXCollections.observableArrayList();;
    private int selectedDataSize = 0;
    private int startIndex = 0;
    private int endIndex = 0;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setCurrentData(ObservableList<Person> currentData){
        this.currentData = currentData;
        if (selectedDataSize > currentData.size()) selectedDataSize = currentData.size();
        if (endIndex > currentData.size() + 1) endIndex = currentData.size() + 1;                       // ???? +1

        if (selectedDataSize == 0){
            selectedDataSize = currentData.size();
            showFirstPage();
        } else selectData();

        dataSizeField.setText(String.valueOf(currentData.size()));
        selectedDataSizeField.setText(String.valueOf(selectedDataSize));
    }

    private void selectData(){
        selectedData.setAll(currentData.subList(startIndex, endIndex));
        selectedDataSizeField.setText(String.valueOf(selectedDataSize));
        initTable();
    }

    public void setTableOverviewController(TableOverviewController tableOverviewController){
        this.tableOverviewController = tableOverviewController;
    }

    private void initTable(){
        tableOverviewController.removeTableItems();
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
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
        } else {
            if (selectedDataSize > currentData.size()) selectedDataSize = currentData.size();
            if (endIndex > currentData.size() + 1) endIndex = currentData.size() + 1;
            selectData();
        }
    }
}
