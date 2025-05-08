package com.alquimiasoft.mi_negocio.repository;

import com.alquimiasoft.mi_negocio.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByIdentificationNumber(String idNum);
    List<Client> findByIdentificationNumberContainingOrNameContainingAllIgnoreCase(String idNum, String name);
}
