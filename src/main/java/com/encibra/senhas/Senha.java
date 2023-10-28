package com.encibra.senhas;

import com.encibra.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SENHA_SEQ_GENERATOR")
    @SequenceGenerator(name = "SENHA_SEQ_GENERATOR", sequenceName = "SENHA_SEQ", allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    private String descricao;
    private String tags;
    private String valor;
}
