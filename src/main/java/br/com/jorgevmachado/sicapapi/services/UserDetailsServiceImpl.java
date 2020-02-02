package br.com.jorgevmachado.sicapapi.services;

import br.com.jorgevmachado.sicapapi.domain.Cliente;
import br.com.jorgevmachado.sicapapi.repositories.IClienteRepository;
import br.com.jorgevmachado.sicapapi.securities.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IClienteRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = repository.findByEmail(email);
        if (cliente == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSpringSecurity(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getSenha(),
                cliente.getPerfis()
        );
    }
}
