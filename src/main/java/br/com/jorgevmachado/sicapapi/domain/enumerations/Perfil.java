package br.com.jorgevmachado.sicapapi.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum  Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_CLIENTE"),
    ANONYMOUS(3, "ROLE_ANONYMOUS");

    private int codigo;
    private String descricao;

    public static Perfil toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for(Perfil item : Perfil.values()) {
            if (codigo.equals(item.getCodigo())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
