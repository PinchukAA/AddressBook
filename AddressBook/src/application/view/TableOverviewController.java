package application.view;

import application.MainApp;
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

    private MainApp mainApp;
    private ObservableList<Person> tableData;

    TableOverviewController(){

    }

    @FXML
    private void initialize() {
        FIOColumn.setCellValueFactory(cellData -> cellData.getValue().FIOProperty());
        countryColumn.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        regionColumn.setCellValueFactory(cellData -> cellData.getValue().regionProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        streetColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
        houseNumberColumn.setCellValueFactory(cellData -> cellData.getValue().houseNumberProperty().asObject());
        pavilionNumberColumn.setCellValueFactory(cellData -> cellData.getValue().pavilionNumberProperty().asObject());
        flatNumberColumn.setCellValueFactory(cellData -> cellData.getValue().flatNumberProperty().asObject());
    }

    public void setTableData(ObservableList<Person> tableData){
        this.tableData = tableData;
        setTableItems();
    }

    public void setTableItems(){
       tableView.setItems(tableData);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void removeTableItems(){
        if (tableData != null) tableView.getItems().removeAll(tableData);
    }
}