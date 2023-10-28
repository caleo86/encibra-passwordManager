package com.encibra.senhas;

import com.encibra.comum.ExcecaoGenerica;
import com.encibra.util.Crypto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SenhaService {

    private SenhaRepository senhaRepository;

    public SenhaService(SenhaRepository senhaRepository) {
        this.senhaRepository = senhaRepository;
    }

    @Transactional
    public Senha salvar(Senha senha) throws ExcecaoGenerica {
        if(Boolean.FALSE.equals(validarQuantidadeSenhas(senha.getUsuario().getId()))) {
            throw new ExcecaoGenerica(HttpStatus.BAD_REQUEST, "Limite de 20 senhas por usuário!");
        }
        senha.setValor(Crypto.criptografar(senha.getValor()));
        return senhaRepository.save(senha);
    }
    public Optional<Senha> recuperar(Long id) {
        Optional<Senha> senha = senhaRepository.findById(id);
        if (senha.isPresent()) {
            senha.get().setValor(Crypto.descriptografar(senha.get().getValor()));
        }
        return senha;
    }
    public List<Senha> listarPorUsuarioId(Long idUsuario)  {
        return senhaRepository.findAllByUsuarioId(idUsuario).stream().map(senha -> {
                           senha.setValor(Crypto.descriptografar(senha.getValor()));
                           return senha;
                }).toList();
    }
    @Transactional
    public Boolean excluir(Long id) throws ExcecaoGenerica {
        try {
            Optional<Senha> senha = senhaRepository.findById(id);
            if (senha.isEmpty())
                throw new ExcecaoGenerica(HttpStatus.NOT_FOUND, "Senha não encontrada!");
            senhaRepository.delete(senha.get());
        } catch (Exception e) {
            throw new ExcecaoGenerica(HttpStatus.BAD_REQUEST, "A senha não pode ser excluida! " + e.getMessage());
        }
        return true;
    }

    private Boolean validarQuantidadeSenhas(Long idUsuario) {
        if (senhaRepository.findCountByUsuarioId(idUsuario) > 20) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
