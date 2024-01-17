package nl.novi.techiteasy.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "televisions")

public class Television {
   @Id
   @GeneratedValue
    private Long id;
    private String type;
    private String brand;
    private String name;
    private Double price;
    private Double availableSize;
    private Double refreshRate;
    private String screenType;
    private String screenQuality;
    private Boolean smartTv;
    private Boolean wifi;
    private Boolean voiceControl;
    private Boolean hdr;
    private Boolean bluetooth;
    private Boolean ambiLight;
    private Integer originalStock;
    private Integer sold;
    private LocalDate saleDate;
    private LocalDate purchaseDate;

    @OneToOne
    private RemoteController remoteController;

    @ManyToOne(fetch = FetchType.EAGER)
    private CIModule ciModule;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "televisions_wall_brackets", joinColumns = @JoinColumn(name = "wall_brackets_id"), inverseJoinColumns = @JoinColumn(name = "television_id"))

    private Set<WallBracket> wallBrackets = new HashSet<>();

    public Television() {} // default constructor

    public Television(Long id, String type, String brand, String name, Double price, Double availableSize, Double refreshRate, String screenType, String screenQuality, Boolean smartTv, Boolean wifi, Boolean voiceControl, Boolean hdr, Boolean bluetooth, Boolean ambiLight, Integer originalStock, Integer sold) {
     this.id = id;
     this.type = type;
     this.brand = brand;
     this.name = name;
     this.price = price;
     this.availableSize = availableSize;
     this.refreshRate = refreshRate;
     this.screenType = screenType;
     this.screenQuality = screenQuality;
     this.smartTv = smartTv;
     this.wifi = wifi;
     this.voiceControl = voiceControl;
     this.hdr = hdr;
     this.bluetooth = bluetooth;
     this.ambiLight = ambiLight;
     this.originalStock = originalStock;
     this.sold = sold;
    }
    // Getters en setters via Lombok

   }
