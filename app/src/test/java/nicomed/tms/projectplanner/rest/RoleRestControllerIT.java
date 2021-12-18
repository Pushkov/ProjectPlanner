package nicomed.tms.projectplanner.rest;

import nicomed.tms.projectplanner.controller.rest.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest
class RoleRestControllerIT extends BaseIT {

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/planner/api/v1/roles"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRole() throws Exception {
        mockMvc.perform(delete("/planner/api/v1/roles/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteRole_pushkov() throws Exception {
        mockMvc.perform(delete("/planner/api/v1/roles/1")
                .header("Api-Key", "pushkov")
                .header("Api-Secret", "pan")
        )
                .andExpect(status().isOk());
    }

    @Test
    void deleteRole_pushkov2() throws Exception {
        mockMvc.perform(delete("/planner/api/v1/roles/1")
                .header("Api-Key", "pushkov")
                .header("Api-Secret", "pan1")
        )
                .andExpect(status().isOk());
    }


    @Test
    void deleteRole_wrongPwd() throws Exception {
        mockMvc.perform(delete("/planner/api/v1/roles/1")
                .header("Api-Key", "admin")
                .header("Api-Secret", "usr")
        )
                .andExpect(status().isUnauthorized());
    }
}