package com.search.autocomplete.config;

import com.search.autocomplete.entity.Pessoa;
import com.search.autocomplete.repository.PessoaRepository;
import com.search.autocomplete.service.PessoaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DadosIniciaisLoader implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private static final Logger logger = LogManager.getLogger(PessoaService.class);
    private final CacheManager cacheManager;
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    public DadosIniciaisLoader(PessoaRepository pessoaRepository, CacheManager cacheManager, PessoaService pessoaService) {
        this.pessoaRepository = pessoaRepository;
        this.cacheManager = cacheManager;
        this.pessoaService = pessoaService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Carregar dados iniciais no banco de dados H2
        List<Pessoa> pessoas = criarExemplosDePessoas();

        for (Pessoa pessoa : pessoas) {
            pessoaRepository.save(pessoa);
        }

        logger.info("Dados iniciais carregados no banco de dados H2.");


        for (Pessoa pessoa : pessoas) {
            pessoaService.inserirPessoa(pessoa.getNome(), pessoa.getId());
        }
        logger.info("Dados iniciais colocados no cache Caffeine.");
    }

    private List<Pessoa> criarExemplosDePessoas() {
        // Criar exemplos de pessoas
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João");
        pessoa1.setIdade(30);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria");
        pessoa2.setIdade(25);

        Pessoa pessoa3 = new Pessoa();
        pessoa3.setNome("Pedro");
        pessoa3.setIdade(35);

        Pessoa pessoa4 = new Pessoa();
        pessoa4.setNome("Ana");
        pessoa4.setIdade(28);

        Pessoa pessoa5 = new Pessoa();
        pessoa5.setNome("Carlos");
        pessoa5.setIdade(40);

        Pessoa pessoa6 = new Pessoa();
        pessoa6.setNome("Juliana");
        pessoa6.setIdade(22);

        Pessoa pessoa7 = new Pessoa();
        pessoa7.setNome("Maraia");
        pessoa7.setIdade(45);

        Pessoa pessoa8 = new Pessoa();
        pessoa8.setNome("Ana Silva");
        pessoa8.setIdade(28);

        return Arrays.asList(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5, pessoa6, pessoa7, pessoa8);

    }
}
