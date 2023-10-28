package com.encibra.usuarios;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Getter
@NoArgsConstructor
public class Usuario {
    @Id
    private Long id;
    private String nome;
}
