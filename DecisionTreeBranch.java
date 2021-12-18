/**
 * models a node present in a decision tree. It contains the value to be
 * reached/compared (key), and  a value associated with every key
 * representing a condition associated with with it.
 */
public class DecisionTreeBranch {
    /**
     * The value of the users hand. (the amount you get when you sum the ingame
     * value of each card of the user's hand)
     */
    private Integer sumHand; 
    /**
     * The value of the maximum card count on a blackjack table when it is
     * still worth to drawing a new card
     */
    private Integer cardCount;
    /**
     * The left child of the current node
     */
    private DecisionTreeBranch leftBranch; 
    /**
     * The right child of the current node
     */
    private DecisionTreeBranch rightBranch;         
    /**
     * Constructs the decision tree. Sets the values of each of 
     * the branches to null.
     */
    public DecisionTreeBranch() {
        leftBranch = null;
        rightBranch = null;
    }
    /**
     * Constructs the decision tree, assigns values to variables sumHand and
     * cardCount.
     * @param sumHand an integer greater than 0 representing the total value of a blackjack hand
     * @param cardCount an integer representing the maximum card count value on a blackjack table when it is still worth betting
     */
    public DecisionTreeBranch(int sumHand, int cardCount) { 
        this.leftBranch = null;
        this.rightBranch = null;
        this.sumHand = sumHand;
        this.cardCount = cardCount;
    }
    /**
     * Gets the maximum card count when it is worth drawing a new card
     * @return an integer representing the maximum card count when it is still worth drawing a new card
     */
    public int getCardCount() { 
        return this.cardCount;
    }
    /**
     * Gets the total value of a blackjack hand
     * @return an integer representing the total value of a blackjack hand
     */
    public int getSumHand() {
        return this.sumHand;
    }
    /**
     * Sets the maximum card count when it is worth drawing a new card
     * @param cardCount an integer representing the maximum card count when it is still worth drawing a new card
     */
    public void setCardCount(int cardCount) {
        this.cardCount = cardCount;
    }
    /**
     * Sets the total value of a blackjack hand
     * @param sumHand an integer representing the total value of a blackjack hand
     */
    public void setSumHand(int sumHand) { 
        this.sumHand = sumHand; 
    }
    /**
     * Gets the left child of the current node of the tree
     * @return an object of type DecisionTreeBranch representing left child of the current node of the tree; null if it does not exist
     */
    public DecisionTreeBranch getLeftBranch() { 
        return this.leftBranch; 
    }
    /**
     * Sets the left child of the current node of the tree
     * @param leftBranch an object of type DecisionTreeBranch representing left child of the current node of the tree; null if it does not exist
     */
    public void setLeftBranch(DecisionTreeBranch leftBranch) { 
         this.leftBranch = leftBranch; 
    }
    /**
     * Gets the right child of the current node of the tree
     * @param rightBranch an object of type DecisionTreeBranch representing right child of the current node of the tree; null if it does not exist
     */
    public DecisionTreeBranch getRightBranch() { 
        return this.rightBranch; 
    }
    /**
     * Sets the right child of the current node of the tree
     * @param rightBranch an object of type DecisionTreeBranch representing left child of the current node of the tree; null if it does not exist
     */
    public void setRightBranch(DecisionTreeBranch rightBranch) { 
        this.rightBranch = rightBranch; 
    }
    /**
     * Returns true if the current node is a leaf of the tree and false otherwise
     * @return true if the current node is a leaf of the tree and false otherwise
     */
    public boolean isLeaf() { 
        return (leftBranch == null) && (rightBranch == null); 
    }
}
