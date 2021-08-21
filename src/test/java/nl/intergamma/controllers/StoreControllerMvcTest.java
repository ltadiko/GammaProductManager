package nl.intergamma.controllers;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import nl.intergamma.entities.Store;
import nl.intergamma.services.StoreService;
import nl.intergamma.services.UserService;
import nl.intergamma.util.JsonConverterUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(value = "classpath:application.yml")
@AutoConfigureMockMvc
public class StoreControllerMvcTest implements WithBDDMockito {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StoreService storeService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Captor
    private ArgumentCaptor<Store> storeArgumentCaptor;

    @Test
    @DisplayName("SHOULD return list of stores json")
    void getStores_200() throws Exception {
        // given

        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        given(storeService.getStores()).willReturn(Collections.singletonList(store));
        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/stores")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
        assertEquals("[{\"storeId\":\"uuid\",\"city\":\"city\",\"postalCode\":\"postalCode\",\"street\":\"street\",\"address_number\":\"23-3\",\"todayOpen\":\"8.0\",\"todayClose\":\"22:00\",\"collectionPoint\":true}]", response.getContentAsString());
        verify(storeService).getStores();
        verifyNoMoreInteractions(storeService);
    }


    @Test
    @DisplayName("SHOULD return store based on uuid")
    void getStore_200() throws Exception {
        // given
        Store store = new Store("1", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);
        given(storeService.getStore("1")).willReturn(store);

        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/stores/{storeId}", "1")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
        assertEquals("{\"storeId\":\"1\",\"city\":\"city\",\"postalCode\":\"postalCode\",\"street\":\"street\",\"address_number\":\"23-3\",\"todayOpen\":\"8.0\",\"todayClose\":\"22:00\",\"collectionPoint\":true}", response.getContentAsString());
        verify(storeService).getStore("1");
        verifyNoMoreInteractions(storeService);
    }


    @Test
    @DisplayName("SHOULD add store to repository when all validation are passed")
    void addStore_200() throws Exception {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);

        // when

        String accessToken = obtainAccessToken("admin", "admin");
        MockHttpServletResponse response = mockMvc
                .perform(post("/stores")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(store)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(201, response.getStatus());
        verify(storeService).addStore(storeArgumentCaptor.capture());
        assertEquals(JsonConverterUtil.convertToJson(store), JsonConverterUtil.convertToJson(storeArgumentCaptor.getValue()));
        verifyNoMoreInteractions(storeService);
    }

    @Test
    @DisplayName("SHOULD add store to repository when all validation are passed")
    void addStore_unauthorized() throws Exception {
        // given
        Store store = new Store("uuid", "city", "postalCode", "street", "23-3", "8.0", "22:00", true);

        // when

        String accessToken = obtainAccessToken("user", "user");
        MockHttpServletResponse response = mockMvc
                .perform(post("/stores")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(store)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(403, response.getStatus());
        verifyNoMoreInteractions(storeService);
    }

    @Test
    @DisplayName("SHOULD be able to delete from store by the admin")
    void deleteStore_200() throws Exception {
        // given

        // when

        String accessToken = obtainAccessToken("admin", "admin");
        MockHttpServletResponse response = mockMvc
                .perform(delete("/stores/{storeId}", 1)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)

                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
        verify(storeService).deleteStore("1");
        verifyNoMoreInteractions(storeService);
    }

    @Test
    @DisplayName("SHOULD not be able to delete from store by the user")
    void deleteStore_unauthorized() throws Exception {
        // given

        // when

        String accessToken = obtainAccessToken("user", "user");
        MockHttpServletResponse response = mockMvc
                .perform(delete("/stores/{storeId}", 2)
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(403, response.getStatus());
        verifyNoMoreInteractions(storeService);
    }


    public String obtainAccessToken(String userName, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "clientid");
        params.add("username", userName);
        params.add("password", password);

        // @formatter:off

        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("clientid", "client-secret"))
                .accept(MediaType.APPLICATION_JSON_VALUE));
        // @formatter:on

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        System.out.println(jsonParser.parseMap(resultString).get("access_token").toString());
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }


}
