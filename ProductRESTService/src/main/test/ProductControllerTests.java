

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void noParamgetProductsShouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get("/categories/600001506/productsWithReducedPrice")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("products"));
    }

    @Test
    public void paramgetProductsShouldReturnTailoredMessage() throws Exception {

        this.mockMvc.perform(get("/categories/600001506/productsWithReducedPrice").param("labelType", "ShowWasNow"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("products"));
    }

}
