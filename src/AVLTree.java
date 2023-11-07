import Bst.BST;1 public class AVLTree<E> extends BST<E> {
 2 /** Create an empty AVL tree using a natural comparator*/
         3 public AVLTree() { // super() is implicitly called

        4 }
 5
         6 /** Create a BST with a specified comparator */
         7 public AVLTree(java.util.Comparator<E> c) {
        8 super(c);
        9 }
 10
         11 /** Create an AVL tree from an array of objects */
         12 public AVLTree(E[] objects) {
        13 super(objects);
        14 }
 15
         16 @Override /** Override createNewNode to create an AVLTreeNode */
 17 protected AVLTreeNode<E> createNewNode(E e) {
        18 return new AVLTreeNode<E>(e);
        19 }
 20
         21 @Override /** Insert an element and rebalance if necessary */
 22 public boolean insert(E e) {
        23 boolean successful = super.insert(e);
        24 if (!successful)
            25 return false; // e is already in the tree
        26 else {
            27 balancePath(e); // Balance from e to the root if necessary
            28 }
        29
        30 return true; // e is inserted
        31 }
 32
         33 /** Update the height of a specified node */
         34 private void updateHeight(AVLTreeNode<E> node) {
        35 if (node.left == null && node.right == null) // node is a leaf
            36 node.height = 0;
        37 else if (node.left == null) // node has no left subtree
            38 node.height = 1 + ((AVLTreeNode<E>)(node.right)).height;
        39 else if (node.right == null) // node has no right subtree
            40 node.height = 1 + ((AVLTreeNode<E>)(node.left)).height;
        41 else
        42 node.height = 1 +
                43 Math.max(((AVLTreeNode<E>)(node.right)).height,
                44 ((AVLTreeNode<E>)(node.left)).height);
        45 }

         50 private void balancePath(E e) {
        51 java.util.ArrayList<TreeNode<E>> path = path(e);
        52 for (int i = path.size() − 1; i >= 0; i−−) {
            53 AVLTreeNode<E> A = (AVLTreeNode<E>)(path.get(i));
            54 updateHeight(A);
            55 AVLTreeNode<E> parentOfA = (A == root) ? null :
                    56 (AVLTreeNode<E>)(path.get(i − 1));
            57
            58 switch (balanceFactor(A)) {
                59 case −2:
                    60 if (balanceFactor((AVLTreeNode<E>)A.left) <= 0) {
                        61 balanceLL(A, parentOfA); // Perform LL rotation
                        62 }
                    63 else {

                    64 balanceLR(A, parentOfA); // Perform LR rotation
                    65 }
                66 break;
                67 case +2:
                    68 if (balanceFactor((AVLTreeNode<E>)A.right) >= 0) {
                        69 balanceRR(A, parentOfA); // Perform RR rotation
                        70 }
                    71 else {
                    72 balanceRL(A, parentOfA); // Perform RL rotation
                    73 }
                74 }
            75 }
        76 }
 77
         78 /** Return the balance factor of the node */
         79 private int balanceFactor(AVLTreeNode<E> node) {
        80 if (node.right == null) // node has no right subtree
            81 return −node.height;
        82 else if (node.left == null) // node has no left subtree
            83 return +node.height;
        84 else
        85 return ((AVLTreeNode<E>)node.right).height −
        86 ((AVLTreeNode<E>)node.left).height;
        87 }
 88
         89 /** Balance LL (see Figure 26.3) */
         90 private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        91 TreeNode<E> B = A.left; // A is left-heavy and B is left-heavy
        92
        93 if (A == root) {
            94 root = B;
            95 }
        96 else {
            97 if (parentOfA.left == A) {
                98 parentOfA.left = B;
                99 }
            100 else {
                101 parentOfA.right = B;
                102 }
            103 }
        104
        105 A.left = B.right; // Make T2 the left subtree of A
        106 B.right = A; // Make A the left child of B
        107 updateHeight((AVLTreeNode<E>)A);
        108 updateHeight((AVLTreeNode<E>)B);
        109 }
110
        111 /** Balance LR (see Figure 26.5) */
        112 private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        113 TreeNode<E> B = A.left; // A is left-heavy
        114 TreeNode<E> C = B.right; // B is right-heavy
        115
        116 if (A == root) {
            117 root = C;
            118 }
        119 else {
            120 if (parentOfA.left == A) {
                121 parentOfA.left = C;
                122 }
            123 else {

                124 parentOfA.right = C;
                125 }
            126 }
        127
        128 A.left = C.right; // Make T3 the left subtree of A
        129 B.right = C.left; // Make T2 the right subtree of B
        130 C.left = B;
        131 C.right = A;
        132
        133 // Adjust heights
        134 updateHeight((AVLTreeNode<E>)A);
        135 updateHeight((AVLTreeNode<E>)B);
        136 updateHeight((AVLTreeNode<E>)C);
        137 }
138
        139 /** Balance RR (see Figure 26.4) */
        140 private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        141 TreeNode<E> B = A.right; // A is right-heavy and B is right-heavy
        142
        143 if (A == root) {
            144 root = B;
            145 }
        146 else {
            147 if (parentOfA.left == A) {
                148 parentOfA.left = B;
                149 }
            150 else {
                151 parentOfA.right = B;
                152 }
            153 }
        154
        155 A.right = B.left; // Make T2 the right subtree of A
        156 B.left = A;
        157 updateHeight((AVLTreeNode<E>)A);
        158 updateHeight((AVLTreeNode<E>)B);
        159 }
160
        161 /** Balance RL (see Figure 26.6) */
        162 private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        163 TreeNode<E> B = A.right; // A is right-heavy
        164 TreeNode<E> C = B.left; // B is left-heavy
        165
        166 if (A == root) {
            167 root = C;
            168 }
        169 else {
            170 if (parentOfA.left == A) {
                171 parentOfA.left = C;
                172 }
            173 else {
                174 parentOfA.right = C;
                175 }
            176 }
        177
        178 A.right = C.left; // Make T2 the right subtree of A
        179 B.left = C.right; // Make T3 the left subtree of B
        180 C.left = A;
        181 C.right = B;
        182
        183 // Adjust heights

        184 updateHeight((AVLTreeNode<E>)A);
        185 updateHeight((AVLTreeNode<E>)B);
        186 updateHeight((AVLTreeNode<E>)C);
        187 }
188
          @Override

192 public boolean delete(E element) {
        193 if (root == null)
            194 return false; // Element is not in the tree
        195
        196 // Locate the node to be deleted and also locate its parent node
        197 TreeNode<E> parent = null;
        198 TreeNode<E> current = root;
        199 while (current != null) {
            200 if (c.compare(element, current.element) < 0) {
                201 parent = current;
                202 current = current.left;
                203 }
            204 else if (c.compare(element, current.element) > 0) {
                205 parent = current;
                206 current = current.right;
                207 }
            208 else
            209 break; // Element is in the tree pointed by current
            210 }
        211
        212 if (current == null)
            213 return false; // Element is not in the tree
        214
        215 // Case 1: current has no left children (See Figure 23.6)
        216 if (current.left == null) {
            217 // Connect the parent with the right child of the current node
            218 if (parent == null) {
                219 root = current.right;
                220 }
            221 else {
                222 if (c.compare(element, parent.element) < 0)
                    223 parent.left = current.right;
                224 else
                225 parent.right = current.right;
                226
                227 // Balance the tree if necessary
                228 balancePath(parent.element);
                229 }
            230 }
        231 else {
            232 // Case 2: The current node has a left child
            233 // Locate the rightmost node in the left subtree of
            234 // the current node and also its parent
            235 TreeNode<E> parentOfRightMost = current;
            236 TreeNode<E> rightMost = current.left;
            237
            238 while (rightMost.right != null) {
                239 parentOfRightMost = rightMost;
                240 rightMost = rightMost.right; // Keep going to the right
                241 }
            242
            243 // Replace the element in current by the element in rightMost

            244 current.element = rightMost.element;
            245
            246 // Eliminate rightmost node
            247 if (parentOfRightMost.right == rightMost)
                248 parentOfRightMost.right = rightMost.left;
            249 else
            250 // Special case: parentOfRightMost is current
            251 parentOfRightMost.left = rightMost.left;
            252
            253 // Balance the tree if necessary
            254 balancePath(parentOfRightMost.element);
            255 }

          size--;
          return true;
          }

        262 protected static class AVLTreeNode<E> extends BST.TreeNode<E> {
  protected int height = 0; // New data field
 
        265 public AVLTreeNode(E o) {
            266 super(o);
            267 }
  }
  }
