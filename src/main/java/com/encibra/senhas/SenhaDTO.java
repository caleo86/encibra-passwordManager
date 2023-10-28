package com.encibra.senhas;

import com.encibra.usuarios.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaDTO {

    private Long id;
    private Usuario usuario;
    private String descricao;
    private String tags;
    private String valor;
}
