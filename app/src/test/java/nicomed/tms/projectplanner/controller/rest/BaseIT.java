package nicomed.tms.projectplanner.controller.rest;

import nicomed.tms.projectplanner.entity.Engineer;
import nicomed.tms.projectplanner.entity.Permission;
import nicomed.tms.projectplanner.entity.Role;
import nicomed.tms.projectplanner.mapper.*;
import nicomed.tms.projectplanner.pdf.PdfService;
import nicomed.tms.projectplanner.qualifier.*;
import nicomed.tms.projectplanner.repository.EngineerRepository;
import nicomed.tms.projectplanner.rest.*;
import nicomed.tms.projectplanner.security.JpaUserDetailsService;
import nicomed.tms.projectplanner.security.jwt.JwtTokenProvider;
import nicomed.tms.projectplanner.services.*;
import nicomed.tms.system.services.SystemOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

public abstract class BaseIT {


    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @MockBean
    JwtTokenProvider jwtTokenProvider;
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
    @MockBean
    DepartmentMapper departmentMapper;
    @MockBean
    DocumentFormatMapper documentFormatMapper;
    @MockBean
    DocumentSignedMapper documentSignedMapper;
    @MockBean
    DocumentMapper documentMapper;
    @MockBean
    EngineerMapper engineerMapper;
    @MockBean
    MemoMapper memoMapper;
    @MockBean
    TechnicalTaskMapper technicalTaskMapper;
    @MockBean
    TitleListMapper titleListMapper;
    @MockBean
    ProjectMapper projectMapper;
    @MockBean
    RoleMapper roleMapper;
    @MockBean
    WorkshopMapper workshopMapper;
    @MockBean
    DepartmentQualifier departmentQualifier;
    @MockBean
    DocumentFormatQualifier documentFormatQualifier;
    @MockBean
    DocumentQualifier documentQualifier;
    @MockBean
    EngineerQualifier engineerQualifier;
    @MockBean
    FormatQualifier formatQualifier;
    @MockBean
    MemoQualifier memoQualifier;
    @MockBean
    PlanQualifier planQualifier;
    @MockBean
    ProjectQualifier projectQualifier;
    @MockBean
    RoleQualifier roleQualifier;
    @MockBean
    PlanPointQualifier planPointQualifier;
    @MockBean
    TechnicalTaskQualifier technicalTaskQualifier;
    @MockBean
    TitleListQualifier titleListQualifier;
    @MockBean
    WorkshopQualifier workshopQualifier;
    @MockBean
    JpaUserDetailsService jpaUserDetailsService;
    @MockBean
    EngineerRepository engineerRepository;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();

        Engineer puh = Engineer.builder()
                .login("pushkov")
//                .lastName("Pushkov")
                .password("pan")
                .role(Role.builder()
                        .name("ENGINEER")
                        .permissions(Arrays.asList(
                                Permission.builder().name("DOC_READ").build(),
                                Permission.builder().name("DOC_WRITE").build(),
                                Permission.builder().name("PRJ_READ").build(),
                                Permission.builder().name("ENGINEER_READ").build()))
                        .build())
                .build();

        when(engineerRepository.findByLogin("pushkov")).thenReturn(Optional.of(puh));
        when(engineerService.findByLogin("pushkov")).thenReturn(Optional.of(puh));


    }

}
