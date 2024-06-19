package com.search.autocomplete;

import com.search.autocomplete.utils.BinarySearchTree;
import com.search.autocomplete.utils.MatchedItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    @Test
    public void testInserirEObterItems() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert("banana", 1);
        bst.insert("apple", 2);
        bst.insert("orange", 3);

        List<MatchedItem> items = bst.getAllItems();

        assertEquals(3, items.size());
        // Verificar se os itens inseridos estão corretos na árvore
        assertEquals("apple", items.get(0).getWord());
        assertEquals("banana", items.get(1).getWord());
        assertEquals("orange", items.get(2).getWord());
    }
}
