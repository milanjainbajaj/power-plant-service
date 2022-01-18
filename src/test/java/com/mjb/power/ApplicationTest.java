package com.mjb.power;

import com.mjb.power.controller.PlantController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
class ApplicationTest {
    @Autowired
    private PlantController plantController;

    @Test
    void contextLoads() {
        assertNotNull(plantController);
    }

    @Test
    public void applicationContextTest() {
        Application.main(new String[]{});
    }
}
