package br.com.jorgevmachado.sicapapi.services;

import br.com.jorgevmachado.sicapapi.domain.enumerations.Perfil;
import br.com.jorgevmachado.sicapapi.securities.UserSpringSecurity;
import br.com.jorgevmachado.sicapapi.services.esceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.jorgevmachado.sicapapi.domain.Cliente;
import br.com.jorgevmachado.sicapapi.repositories.IClienteRepository;
import br.com.jorgevmachado.sicapapi.services.esceptions.DataIntegrityException;
import br.com.jorgevmachado.sicapapi.services.esceptions.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private IClienteRepository repository;


    public List<Cliente> getAll(){
        List<Cliente> obj = repository.findAll();
        if(obj.isEmpty()) {
            throw new ObjectNotFoundException("Tabela vazia, Tipo: " + Cliente.class.getName());
        }
        return obj;
    }

    public Cliente findById(Integer id) {
        UserSpringSecurity user = UserService.authenticated();
        if (user == null ||  !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(
                () -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id: " +
                                id +
                                ", Tipo: " +
                                Cliente.class.getName()
                )
        );
    }

    @Transactional
    public Cliente save(Cliente obj) {
        obj.setId(null);
        obj.setDataCriacao(LocalDateTime.now());
        return repository.save(obj);
    }

    @Transactional
    public Cliente update(Cliente obj, Integer id) {
        Cliente newObj = findById(id);
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setCpfCnpj(obj.getCpfCnpj());
        newObj.setTipo(obj.getTipo());
        newObj.setSenha(obj.getSenha());
        newObj.setDataAtualizacao(LocalDateTime.now());
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Ocorreu um erro");
        }
    }
}
