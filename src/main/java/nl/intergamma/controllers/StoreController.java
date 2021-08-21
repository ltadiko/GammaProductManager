package nl.intergamma.controllers;

import nl.intergamma.entities.Store;
import nl.intergamma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
public class StoreController {
    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping(path = "/stores", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Store> getStores() {
        return storeService.getStores();
    }

    @GetMapping(path = "/stores/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Store getStore(@PathVariable String storeId) {
        return storeService.getStore(storeId);
    }

    @PostMapping(path = "/stores", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStore(@RequestBody @Valid Store store) {
        storeService.addStore(store);
    }

    @DeleteMapping(path = "/stores/{storeId}")
    public void deleteStore(@PathVariable @NotBlank String storeId) {
        storeService.deleteStore(storeId);
    }

}
