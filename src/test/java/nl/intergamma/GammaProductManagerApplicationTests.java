package nl.intergamma;

import nl.intergamma.repositories.StoreRepository;
import nl.intergamma.services.StoreService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = {
                GammaProductManagerApplication.class
        },
        properties = {
                "spring.main.allow-bean-definition-overriding=true"
        })
@ExtendWith(SpringExtension.class)
public class GammaProductManagerApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext.getBean("storeServiceImpl", StoreService.class));
        assertNotNull(applicationContext.getBean("storeRepository", StoreRepository.class));
    }

    @Test
    @DisplayName("SHOULD run without exceptions WHEN application is started")
    void main() {
        GammaProductManagerApplication.main(new String[]{});
    }

}
