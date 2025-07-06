package cl.duoc.ms_usuario_bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.ms_usuario_bs.model.dto.UsuarioDTO;
import cl.duoc.ms_usuario_bs.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
<<<<<<< HEAD
@RequestMapping("/api/usuarios")
=======
@RequestMapping("/usuarios")
>>>>>>> b4bf8b75e24038b114fcfb79e7c4a1ec7aea6092
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("")
    public List<UsuarioDTO> selectAllUsuario() {
        return usuarioService.selectAllUsuario();
    }

<<<<<<< HEAD
        // Busca 
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findUsuarioById(@PathVariable("id") Long id) {
        UsuarioDTO usuario = usuarioService.getUsuarioById(id);
        return (usuario != null) ? new ResponseEntity<>(usuario, HttpStatus.OK)
                                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
=======
    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioDTO usuario = usuarioService.getUsuarioById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

>>>>>>> b4bf8b75e24038b114fcfb79e7c4a1ec7aea6092
    // Crea
    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.createUsuario(usuarioDTO);
        return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
    }

    // Actualiza
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioDTO);
        if (updatedUsuario != null) {
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        String responseMessage = usuarioService.deleteUsuario(id);
        if ("Usuario eliminado".equals(responseMessage)) {
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        } else if ("Usuario no encontrado".equals(responseMessage)) {
            return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


