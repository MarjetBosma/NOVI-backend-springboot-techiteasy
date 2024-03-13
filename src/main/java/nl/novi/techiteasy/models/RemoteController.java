package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "remote_controllers")
public class RemoteController {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private String compatibleWith;
    private String batteryType;
    private Integer originalStock;

    @OneToOne(mappedBy = "remoteController")
    private Television television;
    public RemoteController(Long id, String name, String brand, Double price, String compatibleWith, String batteryType, Integer originalStock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.originalStock = originalStock;
    }

    public RemoteController() {

    }
}
