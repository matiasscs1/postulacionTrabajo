package com.alquimiasoft.mi_negocio.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_type", nullable = false)
    private String identificationType;

    @Column(name = "identification_number", nullable = false, unique = true)
    private String identificationNumber;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentificationType() { return identificationType; }
    public void setIdentificationType(String t) { this.identificationType = t; }

    public String getIdentificationNumber() { return identificationNumber; }
    public void setIdentificationNumber(String n) { this.identificationNumber = n; }

    public String getName() { return name; }
    public void setName(String n) { this.name = n; }

    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }

    public String getPhone() { return phone; }
    public void setPhone(String p) { this.phone = p; }

    public List<Address> getAddresses() { return addresses; }
    public void setAddresses(List<Address> list) { this.addresses = list; }
}
