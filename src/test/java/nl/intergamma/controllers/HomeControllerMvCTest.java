package nl.intergamma.controllers;

import info.solidsoft.mockito.java8.api.WithBDDMockito;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(value = "classpath:application.yml")
@AutoConfigureMockMvc
public class HomeControllerMvCTest implements WithBDDMockito {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("SHOULD return home page")
    void getHomePage_200() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc
                .perform(get("/"))
                .andReturn()
                .getResponse();
        // then
        assertEquals(200, response.getStatus());
        assertNotNull(response.getContentAsString());
    }
}
