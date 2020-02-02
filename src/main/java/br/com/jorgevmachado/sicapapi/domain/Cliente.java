package br.com.jorgevmachado.sicapapi.domain;

import br.com.jorgevmachado.sicapapi.domain.enumerations.Perfil;
import br.com.jorgevmachado.sicapapi.domain.enumerations.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CLIENTE", nullable = false)
    private Integer id;

    @Column(name = "NO_NOME", nullable = false)
    private String nome;

    @Column(name = "DS_EMAIL", nullable = false)
    private String email;

    @Column(name = "NR_CPF_CNPJ", nullable = false)
    private String cpfCnpj;

    @Column(name = "TI_TIPO", nullable = false)
    private Integer tipo;

    @Column(name = "DS_SENHA", nullable = false)
    private String senha;

    @Column(name = "TS_CRIADO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "TS_ATUALIZADO")
    private LocalDateTime dataAtualizacao;

    @Column(name = "TS_REMOVIDO")
    private LocalDateTime dataRemocao;

    @ElementCollection
    @CollectionTable(name="TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    public Cliente() {
        addPerfil(Perfil.USER);
    }

    public Cliente(
            Integer id,
            String nome,
            String email,
            String cpfCnpj,
            TipoCliente tipo,
            String senha,
            LocalDateTime dataCriacao
    ) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.tipo = (tipo==null) ? null : tipo.getCodigo();
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        addPerfil(Perfil.USER);
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCodigo());
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }
}
