package com.example.storegamesonline.controller;

import com.example.storegamesonline.model.Produto;
import com.example.storegamesonline.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    //private Produto prod;


    @GetMapping                             //getAll
    public ResponseEntity<List<Produto>> ListarTodosOsProdutos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")//getById
    public ResponseEntity<Produto> BuscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")    //getByTituto
    public ResponseEntity<List<Produto>> BuscarPorNome(@PathVariable String nome) {
       // if (prod.getNome().equals(nome)) {
            return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
       // } else
         //   return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping                //postPostagem
    public ResponseEntity<Produto> CriarProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping                //putPostagem
    public ResponseEntity<Produto> AtualizarProduto(@Valid @RequestBody Produto produto) {
        if(produto.getId() == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(produtoRepository.save(produto));
        }
    }

    @DeleteMapping("/{id}") //deletePostagem
    public ResponseEntity<Void> DeletarProduto(@PathVariable Long id) {
        try {
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("preco/{preco}")
    public ResponseEntity<List<Produto>> ListarPorMaiorQue (@PathVariable double preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
    }
    @GetMapping("menor/{preco}")
    public ResponseEntity<List<Produto>> ListarPorMenorQue (@PathVariable double preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
    }


}









