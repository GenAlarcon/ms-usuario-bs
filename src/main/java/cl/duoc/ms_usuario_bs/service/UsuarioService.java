package cl.duoc.ms_usuario_bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.ms_usuario_bs.clients.UsuarioDbFeignClient;
import cl.duoc.ms_usuario_bs.model.dto.UsuarioDTO;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDbFeignClient usuarioDbFeignClient;

    public List<UsuarioDTO> selectAllUsuario(){
        List<UsuarioDTO> listaClientes = usuarioDbFeignClient.selectAllUsuario();
        return listaClientes; 
    }

    // Busca
    public UsuarioDTO getUsuarioById(Long id) {
        return usuarioDbFeignClient.getUsuarioById(id);
    }

    // Crea
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDbFeignClient.createUsuario(usuarioDTO);
    }

    // Actualiza
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        return usuarioDbFeignClient.updateUsuario(id, usuarioDTO);
    }

    // Elimina
    public String deleteUsuario(Long id) {
        return usuarioDbFeignClient.deleteUsuario(id);
    }

}
