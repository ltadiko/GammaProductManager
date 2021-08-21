package nl.intergamma.services;


import lombok.extern.slf4j.Slf4j;
import nl.intergamma.domain.exception.StoreNotFoundException;
import nl.intergamma.entities.Store;
import nl.intergamma.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void deleteStore(String uuid) {
        storeRepository.deleteById(uuid);
    }

    @Override
    public List<Store> getStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStore(String uuid) {
        return storeRepository.findById(uuid).orElseThrow(() -> new StoreNotFoundException("Store with uuid " + uuid + " not found"));
    }

    @Override
    public void addStore(Store store) {
        storeRepository.save(store);
    }
}
