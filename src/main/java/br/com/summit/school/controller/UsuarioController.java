package br.com.summit.school.controller;

import br.com.summit.school.domain.usuario.Usuario;
import br.com.summit.school.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioRepository.save(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findall(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    usuarioExistente.setPerfil(usuarioAtualizado.getPerfil());
                    usuarioExistente.setFoto(usuarioAtualizado.getFoto());

                    Usuario usuarioSalvo = usuarioRepository.save(usuarioExistente);
                    return ResponseEntity.ok(usuarioSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
