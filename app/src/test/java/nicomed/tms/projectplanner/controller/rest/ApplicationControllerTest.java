package nicomed.tms.projectplanner.controller.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles({"test"})
@WebMvcTest
class ApplicationControllerTest extends BaseIT {


    @DisplayName("Index GET")
    @Nested
    class IndexEndPoint_GET {

        @WithAnonymousUser
        @Test
        void getPlannerIndexPage_GET() throws Exception {
            mockMvc.perform(get("/planner/index"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
        }

        @Test
        void getPlannerIndexPage_GET_AUTH() throws Exception {
            mockMvc.perform(get("/planner/index").with(httpBasic("pushkov", "pan")))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
        }
    }

    @DisplayName("Index POST")
    @Nested
    class IndexEndPoint_POST {

        @WithAnonymousUser
        @Test
        void getPlannerIndexPage_POST() throws Exception {
            mockMvc.perform(post("/planner/index"))
                    .andExpect(status().isForbidden());
        }

        @Test
        void getPlannerIndexPage_POST_AUTH() throws Exception {
            mockMvc.perform(post("/planner/index").with(httpBasic("pushkov", "pan")))
                    .andExpect(status().isForbidden());
        }
    }


    @DisplayName("Login GET")
    @Nested
    class LoginEndPoint_GET {

        @WithAnonymousUser
        @Test
        void getPlannerIndexPage_GET_LOGIN_PAGE() throws Exception {
            mockMvc.perform(get("/planner/auth/login"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("index"));
        }
    }

    @DisplayName("Login POST")
    @Nested
    class LoginEndPoint_POST {
        @WithAnonymousUser
        @Test
        void getPlannerIndexPage_POST_LOGIN_PAGE() throws Exception {
            mockMvc.perform(post("/planner/auth/login"))
                    .andExpect(status().isForbidden());
        }
    }


//    @WithAnonymousUser
//    @Test
//    void getOverviewPage_Anonymous_401() throws Exception {
//        mockMvc.perform(get("/planner/overview"))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void getOverviewPage_pushkov() throws Exception {
//        mockMvc.perform(get("/planner/overview").with(httpBasic("pushkov", "pan")))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));
//    }
//
//    @Test
//    void getOverviewPage_kupreev() throws Exception {
//        mockMvc.perform(get("/planner/overview").with(httpBasic("kupreev", "kmp")))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));
//    }
//
//    @Test
//    void getOverviewPage_kupreev_wrong_pwd() throws Exception {
//        mockMvc.perform(get("/planner/overview").with(httpBasic("kupreev", "kmp2")))
//                .andExpect(status().isUnauthorized());
//    }
//
//    @Test
//    void getOverviewPage_wrong_login() throws Exception {
//        mockMvc.perform(get("/planner/overview").with(httpBasic("kupreev1", "kmp")))
//                .andExpect(status().isUnauthorized());
//    }
}