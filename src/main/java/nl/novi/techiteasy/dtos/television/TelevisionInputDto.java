package nl.novi.techiteasy.dtos.television;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class TelevisionInputDto {
    @NotNull(message = "Type is required")
    public String type;
    @NotNull(message = "Brand is required")
    public String brand;
    @Size (max = 20, message = "Name must be between 0-20 characters")
    public String name;
    @Positive(message = "Price must be higher than 0")
    public Double price;
    public Double availableSize;
    public Double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    @AssertTrue(message = "All televisions must be HDR minimum")
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    @PositiveOrZero(message = "Television cannot have negative stock")
    public Integer originalStock;
    public Integer sold;
    @Past
    public LocalDate saleDate;
    public LocalDate purchaseDate;

}
