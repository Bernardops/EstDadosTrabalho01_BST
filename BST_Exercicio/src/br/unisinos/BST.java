package br.unisinos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BST {
    private TreeNode root;

    public BST() {
        this.root = null;
    }

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
    
    public void insert(int val) {
        root = insertNode(root, val);
    }

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

    public boolean search(int val) {
        return searchNode(root, val);
    }

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

    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public void postorderTraversal() {
        postorder(root);
    }

    private void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + " ");
        }
    }

    public void remove(int val) {
        root = removeNode(root, val);
    }

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

    private int minValue(TreeNode root) {
        int minVal = root.val;
        while (root.left != null) {
            minVal = root.left.val;
            root = root.left;
        }
        return minVal;
    }

    public static void main(String[] args) {
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


        System.out.println("Inorder traversal:");
        bst.inorderTraversal();
        System.out.println("\nPreorder traversal:");
        bst.preorderTraversal();
        System.out.println("\nPostorder traversal:");
        bst.postorderTraversal();

        int searchValue = 50;
        System.out.println("\nSearching for value " + searchValue + ": " + bst.search(searchValue));

        int removeValue = 190;
        
        bst.remove(removeValue);
        System.out.println("After removing " + removeValue + ":");
        bst.inorderTraversal();
        
        bst.gerarArqDot("arvoreBinGerado5.dot");
    }
}
