package nicomed.tms.projectplanner.rest;

import nicomed.tms.projectplanner.dto.PermissionDto;
import nicomed.tms.projectplanner.services.PermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PermissionRestControllerTest {

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private PermissionRestController permissionRestController;

    @Test
    void findAll() {
        //given
        Collection<PermissionDto> permissionDtos = mock(Collection.class);
        when(permissionService.findAll()).thenReturn(permissionDtos);

        //when
        Collection<PermissionDto> result = permissionRestController.findAll();

        //then
        assertFalse(result.isEmpty());
        verify(permissionService, times(1)).findAll();

    }

    @Test
    void findDtoById() {
        //given
        Long id = 1L;
        PermissionDto dto = mock(PermissionDto.class);
        when(permissionService.findById(id)).thenReturn(dto);
        when(dto.getId()).thenReturn(id);
        //when
        PermissionDto result = permissionRestController.findById(id);
        //then
        assertFalse(Objects.isNull(result));
        assertEquals(1L, result.getId());
        verify(permissionService, times(1)).findById(id);

    }

    @Test
    void findPermissionByNameContains() {
        //given
        String name = "name";
        List<PermissionDto> permissionDtos = mock(List.class);
        when(permissionService.findAllDtoByNameContains(name)).thenReturn(permissionDtos);
        //when
        Collection<PermissionDto> result = permissionRestController.findPermissionByNameContains(name);
        //then
        assertFalse(result.isEmpty());
        verify(permissionService, times(1)).findAllDtoByNameContains(name);
    }

    @Test
    void findPermissionByNameContains_EmptyRequestParam() {
        //given
        String name = "";
        List<PermissionDto> permissionDtos = mock(List.class);
        when(permissionService.findAll()).thenReturn(permissionDtos);
        //when
        Collection<PermissionDto> result = permissionRestController.findPermissionByNameContains(name);
        //then
        assertFalse(result.isEmpty());
        verify(permissionService, times(1)).findAll();
    }

    @Test
    void findPermissionByNameContains_NullRequestParam() {
        //given
        String name = null;
        List<PermissionDto> permissionDtos = mock(List.class);
        when(permissionService.findAll()).thenReturn(permissionDtos);
        //when
        Collection<PermissionDto> result = permissionRestController.findPermissionByNameContains(name);
        //then
        assertFalse(result.isEmpty());
        verify(permissionService, times(1)).findAll();
    }
}