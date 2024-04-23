package br.unisinos;

//Classe da árvore em si. 

class TreeNode {
    int val;
    TreeNode left, right;
// Incializada como null para os nodos serem adicionados posteriormente na classe BST que contém o main.
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

