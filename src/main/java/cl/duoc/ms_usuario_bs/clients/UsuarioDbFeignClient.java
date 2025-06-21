package cl.duoc.ms_usuario_bs.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.duoc.ms_usuario_bs.model.dto.UsuarioDTO;

@FeignClient(name = "ms-usuario-db", url = "http://localhost:8081")
public interface UsuarioDbFeignClient {

    @GetMapping("/usuarios")
    public List<UsuarioDTO> selectAllUsuario();

    @GetMapping("/usuarios/{id}")
    UsuarioDTO getUsuarioById(@PathVariable("id") Long id);

    @PostMapping("/usuarios")
    UsuarioDTO createUsuario(@RequestBody UsuarioDTO usuarioDTO);

    @PutMapping("/usuarios/{id}")
    UsuarioDTO updateUsuario(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO);

    @DeleteMapping("/usuarios/{id}")
    String deleteUsuario(@PathVariable("id") Long id);

}
