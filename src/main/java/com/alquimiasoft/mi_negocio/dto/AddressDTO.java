package com.alquimiasoft.mi_negocio.dto;

import jakarta.validation.constraints.*;  // <<< aquí
public class AddressDTO {
    private Long id;

    @NotBlank
    private String province;

    @NotBlank
    private String city;

    @NotBlank
    private String line;

    private boolean main;

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getLine() { return line; }
    public void setLine(String line) { this.line = line; }

    public boolean isMain() { return main; }
    public void setMain(boolean main) { this.main = main; }
}
