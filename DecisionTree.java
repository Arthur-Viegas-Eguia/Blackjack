/**
 * class DecisionTree models a decision tree and has methods to add and
 * retrieve elements from the tree.
 */
public class DecisionTree{
    private DecisionTreeBranch root;
    /**
     * Adds a new key, value pair to the decision tree.
     * If the key is a duplicate, returns false, otherwise returns true.
     * @param key an integer that is used to determine placement of the node within a tree
     * @param val an integer associated with key which determines which decision the computer should make
     * @return boolean, true if the key/value pair was added successfully; false otherwise
     */
    public boolean addLeave(int key, int val){
        DecisionTreeBranch newNode = new DecisionTreeBranch(key, val);
        if (root == null) {
            root = newNode;
            return true;
        } else {
            return addHelper(root, newNode);
        }

    }

    /**
     * A method for searching the tree for a key and returning its associated value
     * @param key an integer key associated with the value you want
     * @return an integer value associatd with the given key and helps the computer make decisions
     */
    public Integer get(int key) {
        return getHelper(root, key);
        
    }

    /**
     * A recursive helper method for retrieving a value from the tree
     * based on the key
     * @param subRoot the DecisionTreeBranch that is the root of the current subtree
     * @param key the key that the program is looking for
     * @return Integer, the value associated with the given key or null if the key isn't in the tree
     */
    private Integer getHelper(DecisionTreeBranch subRoot, int sumHand) {
        if(subRoot == null){
            return null;
        } else if(sumHand == subRoot.getSumHand()){
            return subRoot.getCardCount();
        } else if(sumHand < subRoot.getSumHand()){
            return getHelper(subRoot.getLeftBranch(), sumHand);
        } else {
            return getHelper(subRoot.getRightBranch(), sumHand);
        }
    }

    /**
     * A recursive helper method for adding a node to the tree
     * Assumes that subRoot is not null
     * @param subRoot the root of current subtree
     * @param newNode the node to add
     * @return a boolean indicating whether the node was added successfully or not
     */
    private boolean addHelper(DecisionTreeBranch subRoot, DecisionTreeBranch newNode) {
        int test = newNode.getSumHand();
        boolean toReturn = false;
        if(subRoot == null){
            subRoot = newNode;
            toReturn = true;
            /**
             * The new node can not be added here because it has to be rigth/left of another node.  this one only sets the first node.
             * If you added it here it would not be connected to the tree, or not connected to any side.
             */
        } else if(test < subRoot.getSumHand()){
            if(subRoot.getLeftBranch() == null){
                subRoot.setLeftBranch(newNode);
            }
            else{
                addHelper(subRoot.getLeftBranch(), newNode);
            }
        } else if(test > subRoot.getSumHand()){
            if(subRoot.getRightBranch() == null){
                subRoot.setRightBranch(newNode);
            }
            else{
                addHelper(subRoot.getRightBranch(), newNode);
            }
        } else if(test == subRoot.getSumHand()){
            toReturn = false;
        }
        return toReturn;
    }
    /**
     * Tests the class and its methods
     */
    public static void main(String[] args) {
        DecisionTree treee = new DecisionTree();
        treee.addLeave(3, 30);
        treee.addLeave(2, 20);
        treee.addLeave(4, 40);
        treee.addLeave(1, 10);
        treee.addLeave(5, 50);
        System.out.println(treee.root.getCardCount());
        System.out.println(treee.root.getLeftBranch().getCardCount());
        System.out.println(treee.root.getRightBranch().getCardCount());
        System.out.println(treee.root.getLeftBranch().getLeftBranch().getCardCount());
        System.out.println(treee.root.getRightBranch().getRightBranch().getCardCount());
    }
}