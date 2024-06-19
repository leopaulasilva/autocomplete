package com.search.autocomplete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableScheduling // Habilita o suporte a agendamento
public class AutocompleteApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutocompleteApplication.class, args);
	}

	/*public static void main(String[] args) {


		Pessoa ana = new Pessoa(1,"Ana Caroline", 18);
		Pessoa carla = new Pessoa(2,"Carla Santos", 20);
		Pessoa felipe = new Pessoa(3,"Felipe Silva", 24);
		Pessoa patricia = new Pessoa(4,"Patricia Pinto", 23);
		Pessoa rafael = new Pessoa(5,"Rafael Colto", 20);
		Pessoa carla2 = new Pessoa(6,"Carla Silva", 20);

		List<Pessoa> pessoaList = new ArrayList<>();
		pessoaList.add(ana);
		pessoaList.add(carla);
		pessoaList.add(felipe);
		pessoaList.add(patricia);
		pessoaList.add(rafael);
		pessoaList.add(carla2);



		BinarySearchTree autoCompleteTree = new BinarySearchTree();
		for(Pessoa pessoa :  pessoaList){
			autoCompleteTree.insert(pessoa.getNome(), pessoa.getId());
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a prefix for auto-complete: ");
		String prefix = scanner.nextLine();

		Instant start = Instant.now();
		System.out.println("Auto-complete suggestions:");
		List<MatchedItem> matchedIds = autoCompleteTree.autoComplete(prefix);

		for (MatchedItem matchedItem : matchedIds) {
			System.out.println(matchedItem.getId()+ " -" +matchedItem.getWord());
		}
		scanner.close();
		Instant end = Instant.now();
		long duration = Duration.between(start, end).toMillis();  // Tempo em milissegundos
		System.out.println("Tempo de execução: " + duration + " milissegundos");
	}*/

}
