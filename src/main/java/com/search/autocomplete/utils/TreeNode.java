package com.search.autocomplete.utils;

public class TreeNode {
    Integer id;
    String word;
    TreeNode left;
    TreeNode right;

    public TreeNode(String word, int id) {
        this.id = id;
        this.word = word;
        this.left = null;
        this.right = null;
    }
}
