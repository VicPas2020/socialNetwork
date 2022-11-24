package meow.soft.socialnetwork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class SocialNetworkApplicationTests {
    @Autowired
    ApplicationContext context;
    @Test
    void contextLoads() {
        Assertions.assertNotNull(context);
    }

}
