package nl.intergamma.service;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import nl.intergamma.domain.exception.StoreNotFoundException;
import nl.intergamma.entities.Store;
import nl.intergamma.repositories.StoreRepository;
import nl.intergamma.services.StoreService;
import nl.intergamma.services.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class StoreServiceImplTest implements WithBDDMockito {

    @Mock
    private StoreRepository storeRepository;
    private StoreService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StoreServiceImpl(storeRepository);
    }

    @Test
    @DisplayName("Should return list of stores")
    void getStoreData() {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        List<Store> stores = Collections.singletonList(store);
        given(storeRepository.findAll()).willReturn(Collections.singletonList(store));

        // when
        List<Store> result = underTest.getStores();

        // then
        assertEquals(result, stores);
        verify(storeRepository).findAll();
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should pass the unhandled exception")
    void getStoreData_failure() {
        // given
        given(storeRepository.findAll()).willThrow(RuntimeException.class);

        // when
        assertThrows(RuntimeException.class, () -> underTest.getStores());

        // then
        verify(storeRepository).findAll();
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should return list of stores")
    void getAllStores_success() {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        List<Store> stores = Collections.singletonList(store);
        given(storeRepository.findAll()).willReturn(Collections.singletonList(store));

        // when
        List<Store> result = underTest.getStores();

        // then
        assertEquals(result, stores);
        verify(storeRepository).findAll();
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should return store based on uuid WHEN uuid is exist in repository")
    void getStore_success() {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        given(storeRepository.findById("uuid")).willReturn(Optional.of(store));

        // when
        Store result = underTest.getStore("uuid");

        // then
        assertEquals(result, store);
        verify(storeRepository).findById("uuid");
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should throw Store not found exception WHEN uuid is not exist in repository")
    void getStore_when_not_found() {
        // given
        given(storeRepository.findById("uuid")).willReturn(Optional.empty());

        // when
        assertThrows(StoreNotFoundException.class, () -> underTest.getStore("uuid"));

        // then
        verify(storeRepository).findById("uuid");
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should add Store to repository")
    void addStore() {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);

        // when
        underTest.addStore(store);
        // then
        verify(storeRepository).save(store);
        verifyNoMoreInteractions(storeRepository);
    }

    @Test
    @DisplayName("Should delete Store from repository")
    void deleteStore() {
        // given

        // when
        underTest.deleteStore("uuid");
        // then
        verify(storeRepository).deleteById("uuid");
        verifyNoMoreInteractions(storeRepository);
    }


}
