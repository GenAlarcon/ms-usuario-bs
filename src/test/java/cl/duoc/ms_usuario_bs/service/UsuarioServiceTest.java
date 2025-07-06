package cl.duoc.ms_usuario_bs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.ms_usuario_bs.clients.UsuarioDbFeignClient;
import cl.duoc.ms_usuario_bs.model.dto.UsuarioDTO;

public class UsuarioServiceTest {

    @Mock
    private UsuarioDbFeignClient usuarioDbFeignClient;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsuarioByIdTest() {
        Long usuarioId = 1L;
        UsuarioDTO test = new UsuarioDTO(
            usuarioId, "Genara", "Alarcon", "Coloma",
            "g.alarcon@gmail.com", "912345678", "Av. Siempre Viva 123"
        );

        when(usuarioDbFeignClient.getUsuarioById(usuarioId)).thenReturn(test);

        UsuarioDTO actualUsuario = usuarioService.getUsuarioById(usuarioId);

        assertNotNull(actualUsuario);
        assertEquals(test.getIdUsuario(), actualUsuario.getIdUsuario());
        assertEquals(test.getNombreUsuario(), actualUsuario.getNombreUsuario());

        verify(usuarioDbFeignClient, times(1)).getUsuarioById(usuarioId);
    }

    @Test
    void selectAllUsuarioTest() {
        List<UsuarioDTO> testList = Arrays.asList(
            new UsuarioDTO(1L, "Genara", "Alarcon", "Coloma", 
            "g.alarcon@gmail.com", "912345678", "Av. Siempre Viva 123"),
            new UsuarioDTO(2L, "Javiera", "Ramirez", "Contreras", 
            "j.ramirez@gmail.com", "923456789", "Los flamencos 456")
        );
        when(usuarioDbFeignClient.selectAllUsuario()).thenReturn(testList);
        List<UsuarioDTO> actualList = usuarioService.selectAllUsuario();

        assertNotNull(actualList);
        assertEquals(2, actualList.size());
        assertEquals("Genara", actualList.get(0).getNombreUsuario());
        verify(usuarioDbFeignClient, times(1)).selectAllUsuario();
    }

    @Test
    void createUsuarioTest() {
        UsuarioDTO usuarioToCreate = new UsuarioDTO(
            null, "Nuevo Usuario", "Paterno",
            "Materno", "nuevo@email.com", "111111111", "Nueva Calle"
        );
        UsuarioDTO createdUsuarioFromClient = new UsuarioDTO(
            1L, "Nuevo Usuario", "Paterno",
            "Materno", "nuevo@email.com", "111111111", "Nueva Calle"
        );

        when(usuarioDbFeignClient.createUsuario(usuarioToCreate)).thenReturn(createdUsuarioFromClient);
        UsuarioDTO actualCreatedUsuario = usuarioService.createUsuario(usuarioToCreate);

        assertNotNull(actualCreatedUsuario);
        assertNotNull(actualCreatedUsuario.getIdUsuario());
        assertEquals(createdUsuarioFromClient.getIdUsuario(), actualCreatedUsuario.getIdUsuario());
        assertEquals(usuarioToCreate.getNombreUsuario(), actualCreatedUsuario.getNombreUsuario());
        assertEquals(usuarioToCreate.getCorreo(), actualCreatedUsuario.getCorreo());
        verify(usuarioDbFeignClient, times(1)).createUsuario(usuarioToCreate);
    }


}
