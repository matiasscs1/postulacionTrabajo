package com.alquimiasoft.mi_negocio.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String line;

    @Column(name = "is_main", nullable = false)
    private boolean main;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProvince() { return province; }
    public void setProvince(String p) { this.province = p; }

    public String getCity() { return city; }
    public void setCity(String c) { this.city = c; }

    public String getLine() { return line; }
    public void setLine(String l) { this.line = l; }

    public boolean isMain() { return main; }
    public void setMain(boolean m) { this.main = m; }

    public Client getClient() { return client; }
    public void setClient(Client c) { this.client = c; }
}
