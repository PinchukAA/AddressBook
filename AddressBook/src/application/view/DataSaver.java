package application.view;

import application.MainApp;
import application.model.Person;
import application.model.PersonListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;

public class DataSaver {
    private MainApp mainApp;
    private Stage primaryStage;
    private ObservableList<Person> data = FXCollections.observableArrayList();
    private String filePath;

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setData(ObservableList<Person> data){
        this.data = data;
    }

    public ObservableList<Person> getData(){
        return data;
    }

    public File getPersonFilePath() {
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }


    }

    public void setPersonFilePath(File file) {
        if(file != null) {
            filePath = file.getAbsolutePath();
            primaryStage.setTitle("AddressApp - " + file.getName());
        }
        else {
            filePath = null;
            primaryStage.setTitle("AddressApp");
        }

    }

    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            PersonListWrapper wrapper = (PersonListWrapper) unmarshaller.unmarshal(file);

            data.clear();
            data.addAll(wrapper.getPersons());
            setPersonFilePath(file);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            e.printStackTrace();
            alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(data);

            marshaller.marshal(wrapper, file);
            setPersonFilePath(file);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            e.printStackTrace();
            alert.showAndWait();
        }
    }

}
