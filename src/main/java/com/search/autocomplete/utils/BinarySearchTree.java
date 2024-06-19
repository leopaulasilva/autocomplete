package com.search.autocomplete.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BinarySearchTree {
    TreeNode root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(String word, int id) {
        root = insertRec(root, word, id);
    }

    /**
     * Insere um novo nodo na árvore binária de busca (BST).
     * Se a raiz for nula, cria um novo nodo com a palavra e o ID fornecidos.
     * Caso contrário, insere recursivamente à esquerda ou à direita com base na comparação da palavra.
     *
     * @param root Raiz atual da árvore
     * @param word Palavra a ser inserida
     * @param id   ID associado à palavra
     * @return A raiz da árvore atualizada
     */
    private TreeNode insertRec(TreeNode root, String word, int id) {
        if (root == null) {
            return new TreeNode(word, id);
        }

        int comparison = word.compareTo(root.word);

        if (comparison < 0) {
            root.left = insertRec(root.left, word, id); // Insere à esquerda se a palavra for menor
        } else if (comparison > 0) {
            root.right = insertRec(root.right, word, id); // Insere à direita se a palavra for maior
        }

        return root;
    }

    /**
     * Realiza uma busca automática na árvore binária de busca (BST) a partir de um prefixo dado.
     * Retorna uma lista de MatchedItem contendo os itens que começam com o prefixo.
     *
     * @param root   Raiz atual da árvore
     * @param prefix Prefixo a ser pesquisado
     * @return Lista de MatchedItem correspondentes ao prefixo
     */
    private List<MatchedItem> autoCompleteRec(TreeNode root, String prefix) {
        List<MatchedItem> matchedItems = new ArrayList<>();
        if (root == null) {
            return matchedItems;
        }

        // Se a palavra na raiz começa com o prefixo, adiciona à lista de resultados
        if (root.word.startsWith(prefix)) {
            matchedItems.add(new MatchedItem(root.id, root.word));
        }

        // Se o prefixo for menor ou igual à palavra na raiz, busca recursivamente à esquerda
        if (prefix.compareTo(root.word) <= 0) {
            matchedItems.addAll(autoCompleteRec(root.left, prefix));
        }

        // Busca recursivamente à direita independentemente do prefixo
        matchedItems.addAll(autoCompleteRec(root.right, prefix));
        return matchedItems;
    }

    public List<MatchedItem> autoComplete(String prefix) {
        return autoCompleteRec(root, prefix);
    }

    // Método para listar todos os itens da árvore
    public List<MatchedItem> getAllItems() {
        List<MatchedItem> allItems = new ArrayList<>();
        traverseTree(root, allItems);
        return allItems;
    }

    // Método recursivo para percorrer a árvore
    private void traverseTree(TreeNode node, List<MatchedItem> items) {
        if (node != null) {
            traverseTree(node.left, items);
            items.add(new MatchedItem(node.id, node.word));
            traverseTree(node.right, items);
        }
    }


    public void removeFromTree(String word) {
        root = removeNode(root, word);
    }

    private TreeNode removeNode(TreeNode root, String word) {
        if (root == null) {
            return null;
        }

        // Comparar com a chave (word) para encontrar o nó a ser removido
        int comparison = word.compareTo(root.word);

        // Encontrou o nó a ser removido
        if (comparison == 0) {
            // Caso 1: Nó sem filhos (folha)
            if (root.left == null && root.right == null) {
                return null;
            }
            // Caso 2: Nó com um filho
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // Caso 3: Nó com dois filhos
            TreeNode minNode = findMin(root.right);
            root.id = minNode.id;
            root.word = minNode.word;
            root.right = removeNode(root.right, minNode.word);
            return root;
        }

        // Caso contrário, buscar nos subárvores
        if (comparison < 0) {
            root.left = removeNode(root.left, word);
        } else {
            root.right = removeNode(root.right, word);
        }
        return root;
    }

    // Método auxiliar para encontrar o nó mínimo na subárvore
    private TreeNode findMin(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

}
