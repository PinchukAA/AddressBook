package application.controller;

import application.model.Person;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class TableOverviewController {
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> FIOColumn;
    @FXML
    private TableColumn<Person, String> countryColumn;
    @FXML
    private TableColumn<Person, String> regionColumn;
    @FXML
    private TableColumn<Person, String> cityColumn;
    @FXML
    private TableColumn<Person, String> streetColumn;
    @FXML
    private TableColumn<Person, Integer> houseNumberColumn;
    @FXML
    private TableColumn<Person, Integer> pavilionNumberColumn;
    @FXML
    private TableColumn<Person, Integer> flatNumberColumn;

    private ObservableList<Person> tableData;

    @FXML
    private void initialize() {
        FIOColumn.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().countryProperty());
        regionColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().regionProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().cityProperty());
        streetColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().streetProperty());
        houseNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().houseNumberProperty().asObject());
        pavilionNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().pavilionNumberProperty().asObject());
        flatNumberColumn.setCellValueFactory(cellData -> cellData.getValue().getAddress().flatNumberProperty().asObject());
    }

    public void setTableData(ObservableList<Person> tableData){
        this.tableData = tableData;
        setTableItems();
    }

    public void setTableItems(){
       tableView.setItems(tableData);
    }

    public Person getSelectedPerson(){
        return tableView.getSelectionModel().getSelectedItem();
    }

    public void removeAllTableItems(){
        if (tableData != null) tableView.getItems().removeAll();
    }
}