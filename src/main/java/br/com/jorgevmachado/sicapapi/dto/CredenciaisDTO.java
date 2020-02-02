package br.com.jorgevmachado.sicapapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CredenciaisDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String senha;
}
