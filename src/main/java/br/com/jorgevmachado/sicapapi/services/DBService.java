package br.com.jorgevmachado.sicapapi.services;

import br.com.jorgevmachado.sicapapi.domain.Cliente;
import br.com.jorgevmachado.sicapapi.domain.enumerations.Perfil;
import br.com.jorgevmachado.sicapapi.domain.enumerations.TipoCliente;
import br.com.jorgevmachado.sicapapi.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IClienteRepository clienteRepository;

    public void instantiateTestDatabase() {
        Cliente cliente1 = new Cliente(
                null,
                "admin",
                "jorge.vmachado@hotmail.com",
                "36378912377",
                TipoCliente.PESSSOAFISICA,
                bCryptPasswordEncoder.encode("1234"),
                LocalDateTime.now()
        );
        cliente1.getTelefones().addAll(Arrays.asList("37363323", "10838393"));
        cliente1.addPerfil(Perfil.ADMIN);

        Cliente cliente2 = new Cliente(
                null,
                "cliente",
                "jorge.vmachado@gmail.com",
                "19277229004",
                TipoCliente.PESSSOAFISICA,
                bCryptPasswordEncoder.encode("1234"),
                LocalDateTime.now()
        );
        cliente2.getTelefones().addAll(Arrays.asList("37363323", "10838393"));

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
    }
}
