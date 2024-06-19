package com.search.autocomplete.service;

import com.search.autocomplete.entity.Pessoa;
import com.search.autocomplete.repository.PessoaRepository;
import com.search.autocomplete.utils.BinarySearchTree;
import com.search.autocomplete.utils.MatchedItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService implements IPessoaService {

    private final PessoaRepository pessoaRepository;
    private BinarySearchTree bst;
    private final CacheManager cacheManager;
    private static final Logger logger = LogManager.getLogger(PessoaService.class);

    @Autowired
    public PessoaService(PessoaRepository pessoaRepository, BinarySearchTree bst, CacheManager cacheManager) {
        this.pessoaRepository = pessoaRepository;
        this.bst = bst;
        this.cacheManager = cacheManager;
        populateBST(); // Popula a BST quando o serviço é inicializado

    }

    private void populateBST() {
        for (Pessoa p : pessoaRepository.findAll()) {
            bst.insert(p.getNome(), p.getId());
        }
    }


    @Cacheable(value = "pessoasCache", key = "'todasAsPessoas'")
    public List<Pessoa> getAllPessoas() {
        // Retorna todas as pessoas do banco de dados
        return pessoaRepository.findAll();
    }

    @CacheEvict(value = "pessoasCache", allEntries = true)
    public void limparCachePessoas() {
        // Método para limpar o cache quando necessário
    }


    @CachePut(value = "pessoasCache", key = "#result.id")
    public Pessoa createPessoa(Pessoa pessoa) {
        Pessoa savedPessoa = pessoaRepository.save(pessoa); // Salva a pessoa no banco de dados
        bst.insert(pessoa.getNome(), pessoa.getId()); // Insere na BinarySearchTree
        return savedPessoa;
    }


    @CachePut(value = "pessoasCache", key = "#id")
    public Pessoa updatePessoa(Integer id, Pessoa pessoa) {
        Optional<Pessoa> pessoaAtual = pessoaRepository.findById(id);
        if (pessoaAtual.isPresent()) {
            Pessoa pessoaAntiga = pessoaAtual.get();

            // Remove a pessoa antiga da BinarySearchTree
            bst.removeFromTree(pessoaAntiga.getNome());

            // Atualiza os dados da pessoa
            pessoa.setId(id);
            pessoaRepository.save(pessoa);

            // Insere a pessoa atualizada na BinarySearchTree
            bst.insert(pessoa.getNome(), pessoa.getId());

            return pessoa;
        }
        return null;
    }


    @CacheEvict(value = "pessoasCache", key = "#id")
    public boolean deletePessoa(Integer id) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            pessoaRepository.deleteById(id); // Exclui a pessoa do banco de dados
            bst.removeFromTree(pessoa.getNome()); // Remove da BinarySearchTree
            return true;
        }
        return false;
    }




    // Método para buscar e construir a árvore de busca automaticamente
    @Override
    @Cacheable(value = "pessoasCache", key = "'autoCompleteTree'")
    public BinarySearchTree getPessoasFromCache() {
        BinarySearchTree autoCompleteTree = new BinarySearchTree();
        for(Pessoa p : pessoaRepository.findAll()){
            autoCompleteTree.insert(p.getNome(), p.getId()); // adiciona da BinarySearchTree
        }

        return autoCompleteTree;
    }

    @Override
    @CacheEvict(value = "pessoasCache", allEntries = true)
    public void invalidateCache() {
        logger.info("Cache 'pessoasCache' invalidated.");
    }

    @CacheEvict(value = "pessoasCache", allEntries = true)
    public void inserirPessoa(String palavra, int id) {
        bst.insert(palavra, id);
    }

    public List<MatchedItem> getAllItemsFromBST() {
        return bst.getAllItems();
    }

}

