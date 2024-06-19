package com.search.autocomplete.controller;

import com.search.autocomplete.entity.Pessoa;
import com.search.autocomplete.service.IPessoaService;
import com.search.autocomplete.service.PessoaService;
import com.search.autocomplete.utils.BinarySearchTree;
import com.search.autocomplete.utils.MatchedItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PessoaController {

    private final IPessoaService pessoaService;
    private static final Logger logger = LogManager.getLogger(PessoaService.class);
    private final BinarySearchTree bst;

    @Autowired
    public PessoaController(PessoaService pessoaService, BinarySearchTree bst) {
        this.pessoaService = pessoaService;
        this.bst = bst;
    }

    // Endpoint para buscar todos os registros
    @GetMapping("/binarySearchTree")
    public ResponseEntity<List<MatchedItem>> getAllItemsFromBST() {
        Instant start = Instant.now();
        logger.info("Buscando todas as pessoas...");

        List<MatchedItem> allItems = pessoaService.getAllItemsFromBST();

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        logger.info("Tempo de execução: " + duration + " milissegundos");
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }

    // Endpoint para buscar uma nova pessoa por nome
    @GetMapping("/pessoas/{nome}")
    public ResponseEntity<List<MatchedItem>> getPessoasByName(@PathVariable String nome) {
        Instant start = Instant.now();
        logger.info("Buscando todas as pessoas...");

        // Buscar a árvore de busca automática do serviço de pessoa
        BinarySearchTree searchTree = pessoaService.getPessoasFromCache(); // Obtém a árvore do cache

        // Realizar a pesquisa automática com base no nome fornecido
        List<MatchedItem> matchedIds = searchTree.autoComplete(nome); // Realiza a busca na árvore

        Instant end = Instant.now();
        long duration = Duration.between(start, end).toMillis();
        logger.info("Tempo de execução: " + duration + " milissegundos");

        return new ResponseEntity<>(matchedIds, HttpStatus.OK);
    }



    // Endpoint para criar uma nova pessoa
    @PostMapping("/pessoas")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa createdPessoa = pessoaService.createPessoa(pessoa);


        return new ResponseEntity<>(createdPessoa, HttpStatus.CREATED);
    }

    // Endpoint para atualizar uma pessoa existente
    @PutMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Integer id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoa);
        if (updatedPessoa != null) {
            return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para excluir uma pessoa
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Integer id) {
        boolean deleted = pessoaService.deletePessoa(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
