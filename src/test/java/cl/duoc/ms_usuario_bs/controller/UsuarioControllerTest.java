package cl.duoc.ms_usuario_bs.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.ms_usuario_bs.model.dto.UsuarioDTO;
import cl.duoc.ms_usuario_bs.service.UsuarioService;

public class UsuarioControllerTest {

    private MockMvc mockMvc; //Simula peticiones de HTTP al controlador

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }


@Test
void selectAllUsuarioTestController() throws Exception {
    UsuarioDTO usuario1 = new UsuarioDTO(1L, "Genara", "Alarcon", "Coloma", 
                                         "g.alarcon@gmail.com", "912345678", "Av. Siempre Viva 123");
    UsuarioDTO usuario2 = new UsuarioDTO(2L, "Javiera", "Ramirez", "Contreras", 
                                         "j.ramirez@gmail.com", "923456789", "Los flamencos 456");
    List<UsuarioDTO> mockUsuarioList = Arrays.asList(usuario1, usuario2);

    when(usuarioService.selectAllUsuario()).thenReturn(mockUsuarioList);
    mockMvc.perform(get("/api/usuarios/usuarios")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].idUsuario").value(usuario1.getIdUsuario()))
            .andExpect(jsonPath("$[0].nombreUsuario").value(usuario1.getNombreUsuario()))
            .andExpect(jsonPath("$[1].idUsuario").value(usuario2.getIdUsuario()))
            .andExpect(jsonPath("$[1].nombreUsuario").value(usuario2.getNombreUsuario()));
    verify(usuarioService, times(1)).selectAllUsuario();
}

    @Test
    void getUsuarioByIdTest() throws Exception {
        Long usuarioId = 1L;
        UsuarioDTO testUsuario = new UsuarioDTO(
            usuarioId, "Genara", "ApellidoPaterno", "ApellidoMaterno",
            "g.alarcon@gmail.com", "123456789", "Calle Falsa 123"
        );

        when(usuarioService.getUsuarioById(usuarioId)).thenReturn(testUsuario);
        mockMvc.perform(get("/api/usuarios/{id}", usuarioId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(usuarioId))
                .andExpect(jsonPath("$.nombreUsuario").value("Genara"));
        verify(usuarioService, times(1)).getUsuarioById(usuarioId);
    }


    @Test
    void createUsuarioTestController() throws Exception {
        UsuarioDTO usuarioToCreate = new UsuarioDTO(
            null, "Usuario Creado", "ApellidoP", "ApellidoM",
            "creado@email.com", "999999999", "Calle Nueva"
        );
        UsuarioDTO createdUsuarioFromService = new UsuarioDTO(
            3L, "Usuario Creado", "ApellidoP", "ApellidoM",
            "creado@email.com", "999999999", "Calle Nueva"
        );

        when(usuarioService.createUsuario(usuarioToCreate)).thenReturn(createdUsuarioFromService);
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuarioToCreate)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idUsuario").value(3))
                .andExpect(jsonPath("$.nombreUsuario").value("Usuario Creado"));
        verify(usuarioService, times(1)).createUsuario(usuarioToCreate);
    }

}