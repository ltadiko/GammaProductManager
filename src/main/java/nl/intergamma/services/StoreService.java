package nl.intergamma.services;


import nl.intergamma.entities.Store;

import java.util.List;

public interface StoreService {

    /**
     * Service to retrieve all stores from database
     *
     * @return list of stores near to longitude and latitude
     */
    List<Store> getStores();

    /**
     * Service returns store data based on uuid
     *
     * @param storeId unique id of the store
     * @return store data
     */
    Store getStore(String storeId);


    /**
     * service is used to delete store from database
     *
     * @param storeId unique id of the store
     */
    void deleteStore(String storeId);

    /**
     * service add new store to database
     *
     * @param storeId new store object
     * @return
     */
    void addStore(Store storeId);
}
