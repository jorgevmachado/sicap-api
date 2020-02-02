package br.com.jorgevmachado.sicapapi.services;

import br.com.jorgevmachado.sicapapi.securities.UserSpringSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    public static UserSpringSecurity authenticated() {
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
