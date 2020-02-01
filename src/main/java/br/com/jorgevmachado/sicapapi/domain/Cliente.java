package br.com.jorgevmachado.sicapapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CLIENTE", nullable = false)
    private Integer id;

    @Column(name = "NO_NOME")
    private String nome;

    @Column(name = "DS_EMAIL")
    private String email;

    @Column(name = "NR_CPF")
    private String cpf;

    @Column(name = "TI_TIPO")
    private Integer tipo;

    @Column(name = "DS_SENHA")
    private String senha;

    @Column(name = "TS_CRIADO")
    private LocalDateTime dataCriacao;

    @Column(name = "TS_ATUALIZADO", nullable = true)
    private LocalDateTime dataAtualizacao;

    @Column(name = "TS_REMOVIDO", nullable = true)
    private LocalDateTime dataRemocao;

}
