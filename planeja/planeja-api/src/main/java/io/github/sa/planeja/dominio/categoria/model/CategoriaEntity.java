package io.github.sa.planeja.dominio.categoria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "Categoria")
@Getter@Setter
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Boolean ativo;

    @PrePersist
    public void prePersist(){
        setAtivo(true);
    }
}
