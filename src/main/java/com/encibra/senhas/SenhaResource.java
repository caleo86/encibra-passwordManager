package com.encibra.senhas;

import com.encibra.comum.ExcecaoGenerica;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/")
public class SenhaResource {

    private SenhaService senhaService;

    public SenhaResource(SenhaService senhaService) {
        this.senhaService = senhaService;
    }

    @PostMapping
    public ResponseEntity<Senha> salvarSenha(Senha senha) throws ExcecaoGenerica {
        return ok(senhaService.salvar(senha));
    }
    @GetMapping
    public ResponseEntity<List<Senha>> recuperarSenhaPorIdUsuario(Long idUsuario) {
        return ok(senhaService.listarPorUsuarioId(idUsuario));
    }
    @DeleteMapping
    public ResponseEntity<Boolean> excluirSenha(Long id) throws ExcecaoGenerica {
        return ok(senhaService.excluir(id));
    }
}
