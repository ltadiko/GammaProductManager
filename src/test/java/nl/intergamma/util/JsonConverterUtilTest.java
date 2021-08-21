package nl.intergamma.util;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import nl.intergamma.entities.Store;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JsonConverterUtilTest implements WithBDDMockito {

    @Test
    @DisplayName("Should convert Name to Json")
    void convertToJson() {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        // when
        String json = JsonConverterUtil.convertToJson(store);

        // then
        assertEquals("{\"storeId\":\"uuid\",\"city\":\"city\",\"postalCode\":\"postalCode\",\"street\":\"street\",\"address_number\":\"23-3\",\"todayOpen\":\"8.0\",\"todayClose\":\"22:00\",\"collectionPoint\":true}", json);
    }

    @Test
    @DisplayName("Should throw JsonConversionException while converting mock object")
    void exceptionConvToJson() {
        Store notSerilizableObj = mock(Store.class);

        assertThrows(JsonConversionException.class, () -> JsonConverterUtil.convertToJson(notSerilizableObj));
    }

    @Test
    @DisplayName("Should instantiate json to given object")
    void convertFromJson() {
        // given
        String json = "{\"storeId\":\"uuid\",\"city\":\"city\",\"postalCode\":\"postalCode\",\"street\":\"street\",\"address_number\":\"23-3\",\"todayOpen\":\"8.0\",\"todayClose\":\"22:00\",\"collectionPoint\":true}";

        // when
        Store store = JsonConverterUtil.convertFromJson(json, Store.class);

        // then
        assertEquals("city", store.getCity());
        assertEquals("uuid", store.getStoreId());
    }

    @Test
    @DisplayName("Should throw JsonConversionException on converting invalid json")
    void exceptionTestFromJson() {
        String input = "invalid json";

        assertThrows(JsonConversionException.class, () -> JsonConverterUtil.convertFromJson(input, Store.class));
    }

}
