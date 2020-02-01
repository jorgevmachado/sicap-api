package br.com.jorgevmachado.sicapapi.controllers;

import br.com.jorgevmachado.sicapapi.domain.Cliente;
import br.com.jorgevmachado.sicapapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

//    @RequestMapping(method= RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Cliente>> index() {
        List<Cliente> obj = service.getAll();
        return ResponseEntity.ok().body(obj);
    }

//    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> show(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Cliente> store(@Valid @RequestBody Cliente obj) {
        Cliente newObj = service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(newObj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id , @Valid @RequestBody Cliente obj) {
        service.update(obj, id);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete( @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
