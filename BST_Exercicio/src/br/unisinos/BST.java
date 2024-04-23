package br.unisinos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BST {
    private TreeNode root;

    public BST() {
        this.root = null;
    }
//O código abaixo escreve o preOrdem:
    private void escreverPreOrdemDot(TreeNode root, BufferedWriter out) throws IOException {
        if (root != null) {
            // Escreve o nó atual
            out.write("  " + root.val + ";\n");

            // Chamada recursiva para o filho esquerdo
            if (root.left != null) {
                out.write("  " + root.val + " -> " + root.left.val + " [label=\"left\"];\n");
                escreverPreOrdemDot(root.left, out);
            }

            // Chamada recursiva para o filho direito
            if (root.right != null) {
                out.write("  " + root.val + " -> " + root.right.val + " [label=\"right\"];\n");
                escreverPreOrdemDot(root.right, out);
            }
        }
    }

    
    
    //Código usado para gerar o arquivo no dot.
    public void gerarArqDot(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write("digraph ArvoreBin {\n");


            escreverPreOrdemDot(root, out);

            out.write("}\n");
            out.close();
            System.out.println("Arquivo DOT gerado com sucesso: " + filename);
        } catch (IOException e) {
            System.err.println("Erro ao gerar arquivo DOT: " + e.getMessage());
        }
    }
    //FUnção para inserir na árvore
    public void insert(int val) {
        root = insertNode(root, val);
    }
    //FUnção para inserir na árvore

    private TreeNode insertNode(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertNode(root.left, val);
        } else if (val > root.val) {
            root.right = insertNode(root.right, val);
        }
        return root;
    }
    //Pesquisa dentro da BST
    public boolean search(int val) {
        return searchNode(root, val);
    }
    //Pesquisa dentro da BST

    private boolean searchNode(TreeNode root, int val) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            return true;
        } else if (val < root.val) {
            return searchNode(root.left, val);
        } else {
            return searchNode(root.right, val);
        }
    }
//Traz a preorder
    public void preorderTraversal() {
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }
    //Mostra em ordem

    public void inorderTraversal() {
        inorder(root);
    }
    //Mostra em ordem
    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }
    //Mostra em pos ordem

    public void postorderTraversal() {
        postorder(root);
    }
    //Mostra em pos ordem

    private void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");
        }
    }
    //Remove um nodo da BST

    public void remove(int val) {
        root = removeNode(root, val);
    }
    //Remove um nodo da BST

    private TreeNode removeNode(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val < root.val) {
            root.left = removeNode(root.left, val);
        } else if (val > root.val) {
            root.right = removeNode(root.right, val);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.val = minValue(root.right);
            root.right = removeNode(root.right, root.val);
        }
        return root;
    }
//Traz o valor minimo encontrado
    private int minValue(TreeNode root) {
        int minVal = root.val;
        while (root.left != null) {
            minVal = root.left.val;
            root = root.left;
        }
        return minVal;
    }
    //Main, onde é rodado o programa e é colocado conteúdo na BST

    public static void main(String[] args) {
    	//Criação da BST e inserção de nodos
        BST bst = new BST();
        bst.insert(50);
        bst.insert(10);
        bst.insert(30);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(70);
        bst.insert(100);
        bst.insert(90);

    	//Mostra em diferentes orders

        System.out.println("Inorder traversal:");
        bst.inorderTraversal();
        System.out.println("\nPreorder traversal:");
        bst.preorderTraversal();
        System.out.println("\nPostorder traversal:");
        bst.postorderTraversal();
    	//Pesquisa dentro da BST

        int searchValue = 50;
        System.out.println("\nSearching for value " + searchValue + ": " + bst.search(searchValue));
    	//Remoção

        int removeValue = 190;
        
        bst.remove(removeValue);
        System.out.println("After removing " + removeValue + ":");
        bst.inorderTraversal();
        // Criar o arquivo em dot. Nome pode ser alterado para o nome desejado do arquivo.
        bst.gerarArqDot("arvoreBinGerado5.dot");
    }
}
