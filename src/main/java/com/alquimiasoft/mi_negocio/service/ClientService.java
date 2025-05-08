package com.alquimiasoft.mi_negocio.service;

import com.alquimiasoft.mi_negocio.dto.*;
import java.util.*;

public interface ClientService {
    List<ClientDTO> search(String term);
    ClientDTO create(ClientDTO dto);
    ClientDTO update(Long id, ClientDTO dto);
    void delete(Long id);
    AddressDTO addAddress(Long clientId, AddressDTO dto);
    List<AddressDTO> listAddresses(Long clientId);
}
