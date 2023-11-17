package map.project.MihaiStupyMAPSpring.data.baseClasses;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Client")
public class Client {
    @Setter
    @Getter
    @Id
    private int clientID;

    @Setter
    @Getter
    @Column(name = "firstName")
    private String firstName;

    @Setter
    @Getter
    @Column(name = "lastName")
    private String lastName;

    public Client() {
        this.assignments = new ArrayList<>();
    }

    public Client(int clientID, String firstName,String lastName, String emailAddress, int phoneNumber) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.assignments = new ArrayList<>();
    }

    @Setter
    @Getter
    @Column(name = "emailAddress")
    private String emailAddress;

    @Setter
    @Getter
    @Column(name = "phoneNumber")
    private int phoneNumber;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Assignments> assignments = new ArrayList<>();
}
