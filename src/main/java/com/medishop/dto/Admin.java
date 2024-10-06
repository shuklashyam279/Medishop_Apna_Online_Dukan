package com.medishop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
* Admin class is used to store admin details.
* Admin can register multiple vendors and manage all the vendors registered.
* 
* @author Shyam Shukla
*/
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", 
             message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character")
    private String password;

    @Valid
    @NotNull(message = "Address is required")
    @Embedded
    private Address address;

    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotNull(message = "Phone number is required")
    @Min(value = 1000000000L, message = "Phone number must be 10 digits")
    @Max(value = 9999999999L, message = "Phone number must be 10 digits")
    private Long phone;

    @NotNull(message = "Aadhar number is required")
    @Column(unique = true)
    @Min(value = 100000000000L, message = "Aadhar number must be 12 digits")
    @Max(value = 999999999999L, message = "Aadhar number must be 12 digits")
    private Long aadhar;

    @Lob
    private byte[] image;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Vendor> vendors;
    
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin(String name, String email, String password, Long phone) {
        this(name, email, password);
        this.phone = phone;
    }

    public Admin(String name, String email, String password, Long phone, Address address) {
        this(name, email, password, phone);
        this.address = address;
    }

    public Admin(String name, String email, String password, Long phone, Address address, Long aadhar) {
        this(name, email, password, phone, address);
        this.aadhar = aadhar;
    }

    public Admin(String name, String email, String password, Long phone, Address address,
                 Long aadhar, LocalDate dob) {
        this(name, email, password, phone, address, aadhar);
        this.dob = dob;
    }
}