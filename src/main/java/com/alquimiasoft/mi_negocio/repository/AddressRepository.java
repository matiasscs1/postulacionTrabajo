package com.alquimiasoft.mi_negocio.repository;

import com.alquimiasoft.mi_negocio.entity.Address;
import org.springframework.data.jpa.repository.*;
import java.util.*;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByClientId(Long clientId);

    @Modifying
    @Query("UPDATE Address a SET a.main = false WHERE a.client.id = :clientId")
    void clearMainForClient(Long clientId);
}
