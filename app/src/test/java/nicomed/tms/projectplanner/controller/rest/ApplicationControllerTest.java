package nicomed.tms.projectplanner.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles({"test"})
@WebMvcTest
class ApplicationControllerTest extends BaseIT {


    @WithAnonymousUser
    @Test
    void getPlannerIndexPage() throws Exception {
        mockMvc.perform(get("/planner/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @WithAnonymousUser
    @Test
    void getOverviewPage_Anonymous_401() throws Exception {
        mockMvc.perform(get("/planner/overview"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getOverviewPage_pushkov() throws Exception {
        mockMvc.perform(get("/planner/overview").with(httpBasic("pushkov", "pan")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getOverviewPage_kupreev() throws Exception {
        mockMvc.perform(get("/planner/overview").with(httpBasic("kupreev", "kmp")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}