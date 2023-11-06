1 public class TestAVLTree {
 2 public static void main(String[] args) {
        3 // Create an AVL tree
        4 AVLTree<Integer> tree = new AVLTree<Integer>(new Integer[]{25,
                5 20, 5});
        6 System.out.print("After inserting 25, 20, 5:");
        7 printTree(tree);
        8
        9 tree.insert(34);
        10 tree.insert(50);
        11 System.out.print("\nAfter inserting 34, 50:");
        12 printTree(tree);
        13
        14 tree.insert(30);
        15 System.out.print("\nAfter inserting 30");
        16 printTree(tree);
        17
        18 tree.insert(10);
        19 System.out.print("\nAfter inserting 10");
        20 printTree(tree);
        21
        22 tree.delete(34);
        23 tree.delete(30);
        24 tree.delete(50);
        25 System.out.print("\nAfter removing 34, 30, 50:");
        26 printTree(tree);
        27
        28 tree.delete(5);
        29 System.out.print("\nAfter removing 5:");
        30 printTree(tree);
        31
        32 System.out.print("\nTraverse the elements in the tree: ");
        33 for (int e: tree) {
            34 System.out.print(e + " ");
            35 }
        36 }
37
        38 public static void printTree(BST tree) {
        39 // Traverse tree
        40 System.out.print("\nInorder (sorted): ");
        41 tree.inorder();
        42 System.out.print("\nPostorder: ");
        43 tree.postorder();
        44 System.out.print("\nPreorder: ");
        45 tree.preorder();
        46 System.out.print("\nThe number of nodes is " + tree.getSize());
        47 System.out.println();
        48 }
49 }