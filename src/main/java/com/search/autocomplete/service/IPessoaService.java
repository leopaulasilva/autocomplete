package com.search.autocomplete.service;

import com.search.autocomplete.entity.Pessoa;
import com.search.autocomplete.utils.BinarySearchTree;
import com.search.autocomplete.utils.MatchedItem;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface IPessoaService {


    List<Pessoa> getAllPessoas();

    Pessoa createPessoa(Pessoa pessoa);

    Pessoa updatePessoa(Integer id, Pessoa pessoa);

    boolean deletePessoa(Integer id);

    BinarySearchTree getPessoasFromCache();

    void invalidateCache();

    List<MatchedItem> getAllItemsFromBST();
}
