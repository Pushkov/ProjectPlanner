package nicomed.tms.projectplanner.controller.rest;

import nicomed.tms.projectplanner.pdf.PdfService;
import nicomed.tms.projectplanner.rest.*;
import nicomed.tms.projectplanner.services.*;
import nicomed.tms.system.services.SystemOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles({"test"})
@WebMvcTest
class ApplicationControllerTest {

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;
    @MockBean
    PdfService pdfService;
    @MockBean
    ApplicationRestController applicationRestController;
    @MockBean
    DepartmentRestController departmentRestController;
    @MockBean
    DocumentFormatRestController documentFormatRestController;
    @MockBean
    DocumentRestController documentRestController;
    @MockBean
    DocumentSignedRestController documentSignedRestController;
    @MockBean
    EngineerRestController engineerRestController;
    @MockBean
    FormatRestController formatRestController;
    @MockBean
    MemoRestController memoRestController;
    @MockBean
    PermissionRestController permissionRestController;
    @MockBean
    PlanPointRestController planPointRestController;
    @MockBean
    PlanRestController planRestController;
    @MockBean
    ProjectRestController projectRestController;
    @MockBean
    RoleRestController roleRestController;
    @MockBean
    SystemRestController systemRestController;
    @MockBean
    TechnicalTaskRestController technicalTaskRestController;
    @MockBean
    TitleListRestController titleListRestController;
    @MockBean
    WorkshopRestController workshopRestController;
    @MockBean
    PlanProjectRestController planProjectRestController;
    @MockBean
    SystemOptionService systemOptionService;
    @MockBean
    AddressService addressService;
    @MockBean
    AppSearchService appSearchService;
    @MockBean
    DepartmentService departmentService;
    @MockBean
    DocumentService documentService;
    @MockBean
    DocumentFormatService documentFormatService;
    @MockBean
    DocumentSignedService documentSignedService;
    @MockBean
    DepartmentStructureService departmentStructureService;
    @MockBean
    EngineerService engineerService;
    @MockBean
    MemoService memoService;
    @MockBean
    PermissionService permissionService;
    @MockBean
    PlanPointService planPointService;
    @MockBean
    PlanProjectService planProjectService;
    @MockBean
    ProjectService projectService;
    @MockBean
    PlanService planService;
    @MockBean
    RoleService roleService;
    @MockBean
    SheetFormatService sheetFormatService;
    @MockBean
    TechnicalTaskService technicalTaskService;
    @MockBean
    TitleListService titleListService;
    @MockBean
    WorkshopService workshopService;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

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
    void getOverviewPage() throws Exception {
        mockMvc.perform(get("/planner/overview").with(httpBasic("usr", "usr")))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}