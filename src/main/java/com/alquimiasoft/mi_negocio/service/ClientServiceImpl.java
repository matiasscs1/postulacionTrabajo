package com.alquimiasoft.mi_negocio.service;

import com.alquimiasoft.mi_negocio.dto.AddressDTO;
import com.alquimiasoft.mi_negocio.dto.ClientDTO;
import com.alquimiasoft.mi_negocio.entity.Address;
import com.alquimiasoft.mi_negocio.entity.Client;
import com.alquimiasoft.mi_negocio.exception.BadRequestException;
import com.alquimiasoft.mi_negocio.exception.ResourceNotFoundException;
import com.alquimiasoft.mi_negocio.repository.AddressRepository;
import com.alquimiasoft.mi_negocio.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepo;
    private final AddressRepository addressRepo;

    public ClientServiceImpl(ClientRepository clientRepo,
                             AddressRepository addressRepo) {
        this.clientRepo = clientRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public List<ClientDTO> search(String term) {
        return clientRepo
            .findByIdentificationNumberContainingOrNameContainingAllIgnoreCase(term, term)
            .stream()
            .map(this::toDTO)
            .toList();
    }

    @Override
    public ClientDTO create(ClientDTO dto) {
        if (clientRepo.existsByIdentificationNumber(dto.getIdentificationNumber())) {
            throw new BadRequestException("Ya existe un cliente con esa identificación");
        }
        Client c = new Client();
        c.setIdentificationType(dto.getIdentificationType());
        c.setIdentificationNumber(dto.getIdentificationNumber());
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());

        dto.getAddresses().stream()
           .filter(AddressDTO::isMain)
           .findFirst()
           .ifPresent(adto -> {
               Address a = toEntity(adto);
               a.setMain(true);
               a.setClient(c);
               c.getAddresses().add(a);
           });

        return toDTO(clientRepo.save(c));
    }

    @Override
    public ClientDTO update(Long id, ClientDTO dto) {
        Client c = clientRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (!c.getIdentificationNumber().equals(dto.getIdentificationNumber())
         && clientRepo.existsByIdentificationNumber(dto.getIdentificationNumber())) {
            throw new BadRequestException("Identificación en uso por otro cliente");
        }

        c.setIdentificationType(dto.getIdentificationType());
        c.setIdentificationNumber(dto.getIdentificationNumber());
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());

        return toDTO(c);
    }

    @Override
    public void delete(Long id) {
        if (!clientRepo.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        clientRepo.deleteById(id);
    }

    @Override
    public AddressDTO addAddress(Long clientId, AddressDTO dto) {
        Client c = clientRepo.findById(clientId)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        if (dto.isMain()) {
            addressRepo.clearMainForClient(clientId);
        }

        Address a = toEntity(dto);
        a.setClient(c);
        return toDTO(addressRepo.save(a));
    }

    @Override
    public List<AddressDTO> listAddresses(Long clientId) {
        if (!clientRepo.existsById(clientId)) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
        return addressRepo.findByClientId(clientId)
                          .stream()
                          .map(this::toDTO)
                          .toList();
    }

    // —————————————————————————————————————————————————————
    // Métodos auxiliares para mapear Entidad ⇄ DTO
    // —————————————————————————————————————————————————————

    private ClientDTO toDTO(Client c) {
        ClientDTO dto = new ClientDTO();
        dto.setId(c.getId());
        dto.setIdentificationType(c.getIdentificationType());
        dto.setIdentificationNumber(c.getIdentificationNumber());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        dto.setAddresses(
            c.getAddresses()
             .stream()
             .map(this::toDTO)
             .toList()
        );
        return dto;
    }

    private AddressDTO toDTO(Address a) {
        AddressDTO dto = new AddressDTO();
        dto.setId(a.getId());
        dto.setProvince(a.getProvince());
        dto.setCity(a.getCity());
        dto.setLine(a.getLine());
        dto.setMain(a.isMain());
        return dto;
    }

    private Address toEntity(AddressDTO dto) {
        Address a = new Address();
        a.setProvince(dto.getProvince());
        a.setCity(dto.getCity());
        a.setLine(dto.getLine());
        a.setMain(dto.isMain());
        return a;
    }
}
