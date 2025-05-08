package com.alquimiasoft.mi_negocio.controller;

import com.alquimiasoft.mi_negocio.dto.ClientDTO;
import com.alquimiasoft.mi_negocio.dto.AddressDTO;
import com.alquimiasoft.mi_negocio.service.ClientService;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {

    private final ClientService svc;

    public ClientController(ClientService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<ClientDTO> search(@RequestParam(defaultValue = "") String q) {
        return svc.search(q);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO create(@Valid @RequestBody ClientDTO dto) {
        return svc.create(dto);
    }

    @PutMapping("/{id}")
    public ClientDTO update(
        @PathVariable Long id,
        @Valid @RequestBody ClientDTO dto
    ) {
        return svc.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        svc.delete(id);
    }

    @PostMapping("/{id}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO addAddress(
        @PathVariable("id") Long clientId,
        @Valid @RequestBody AddressDTO dto
    ) {
        return svc.addAddress(clientId, dto);
    }

    @GetMapping("/{id}/addresses")
    public List<AddressDTO> listAddresses(@PathVariable("id") Long clientId) {
        return svc.listAddresses(clientId);
    }
}
