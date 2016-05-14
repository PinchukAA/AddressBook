package application.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;


@XmlRootElement(name = "persons")
@XmlSeeAlso({Person.class})
public class PersonListWrapper implements java.io.Serializable{

    private List persons;

    @XmlElement(name = "person")
    public List getPersons() {
        return persons;
    }

    public void setPersons(List persons) {
        this.persons = persons;
    }
}