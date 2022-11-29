package com.example.storegamesonline.controller;

import com.example.storegamesonline.model.Categoria;
import com.example.storegamesonline.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    private Categoria categoria;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> ListarTudo() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> ListarPorId(@PathVariable Long id) {
        return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Categoria>> ListarPorTipo(@PathVariable String tipo) {
            return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
    }

    @PostMapping
    public ResponseEntity<Categoria> CriarCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> AtualizarCategoria(@Valid @RequestBody Categoria categoria) {
        if (categoria.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else
            return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeletarCategoria(@PathVariable Long id) {
        try {
            categoriaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
