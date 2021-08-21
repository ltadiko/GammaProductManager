package nl.intergamma.repositories;

import nl.intergamma.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
    List<Store> findAll();
}
