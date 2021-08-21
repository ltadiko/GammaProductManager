package nl.intergamma.controllers;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import nl.intergamma.entities.Product;
import nl.intergamma.util.JsonConverterUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
public class ProductControllerMvcTest implements WithBDDMockito {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("SHOULD return list of products from a store")
    void getProducts_from_store() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/stores/{storeId}/products", "1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD return list of products of a article")
    void getProducts_from_article() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/articles/{articleId}/products", "1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD return list of products of a article from a store")
    void getProducts_from_store_and_article() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/stores/{storeId}/products/artifacts/{artifactId}", "1", "1")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD be able to add product when user has access")
    void addProduct_when_admin_user() throws Exception {
        // given
        Product product = new Product("10", "name", "description", "1", "1");

        // when

        String accessToken = obtainAccessToken("admin", "admin");
        MockHttpServletResponse response = mockMvc
                .perform(post("/stores/{storeId}/products", "1")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(product)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD not be able to add product when user does not have admin access")
    void addProduct_when_normal_user() throws Exception {
        // given
        Product product = new Product("10", "name", "description", "1", "1");

        // when

        String accessToken = obtainAccessToken("user", "user");
        MockHttpServletResponse response = mockMvc
                .perform(post("/stores/{storeId}/products", "1")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(product)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(403, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD be able to delete product when user have admin access")
    void deleteProduct_when_admin_user() throws Exception {
        // given
        Product product = new Product("10", "name", "description", "1", "1");

        // when

        String accessToken = obtainAccessToken("admin", "admin");
        MockHttpServletResponse response = mockMvc
                .perform(delete("/products/{productId}", "1")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(product)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("SHOULD not be able to delete product when user does not have admin access")
    void deleteProduct_when_normal_user() throws Exception {
        // given
        Product product = new Product("10", "name", "description", "1", "1");

        // when

        String accessToken = obtainAccessToken("user", "user");
        MockHttpServletResponse response = mockMvc
                .perform(delete("/products/{productId}", "1")
                        .header("Authorization", "Bearer " + accessToken)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(JsonConverterUtil.convertToJson(product)))
                .andReturn()
                .getResponse();
        // then
        assertEquals(403, response.getStatus());
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
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}
