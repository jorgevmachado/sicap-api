package br.com.jorgevmachado.sicapapi.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum TipoCliente {
    PESSSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int codigo;
    private String descricao;

    public static TipoCliente toEnum (Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for(TipoCliente item : TipoCliente.values()) {
            if (codigo.equals(item.getCodigo())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}
