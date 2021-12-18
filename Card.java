import java.lang.Comparable;
/**
 * Models a single card of a deck of cards, it saves its face
 * value, numeric value within a game and order it should be inside an ordered
 * deck of cards, it has getter and setter methods as well as saying where this
 * card should be in a deck of cards.
 */
public class Card implements Comparable<Card> {
    /**
     * The face value of a card. 
     */
    String faceValue;
    /**
     * The position of a card inside an ordered deck
     */
    Integer position;
    /**
     * The numeric value of the card within a game.
     */
    Integer value;
    /**
     * Constructs the class. 
     */
    public Card(String faceCard, int positionCard, int value){
        faceValue = faceCard;
        position = positionCard;
        this.value = value;
    }
    /**
     * Gets the actual numeric value of a card within a given game.
     * @return an integer, which represents the ingame value of the card
     */
    public int getValue(){
        return value;
    }
    /**
     * Gets the the position the card should be within a sorted
     * deck of cards
     * @return an integer which represents the position of the card in an ordered deck of cards
     */
    public int getPosition(){
        return position;
    }
    /**
     * Gets the face value of the card.
     * @rerturn a string representing the face value of the card.
     */
    public String getFaceValue(){
        return faceValue;
    }
    /**
     * Sets the face value of the card.
     * @param face a string, representing the face value of the card
     */
    public  void setFaceValue(String face){
        faceValue = face;
    }
    /**
     * Sets the position of the card within a sorted deck of cards.
     * @param position an integer, which represents the position of the card within a sorted deck if cards
     */
    public void setPosition(int position){
        this.position = position;
    }
    /**
     * Sets the ingame numerical value of the card.
     * @return an integer, which represents the ingame value of the card
     */
    public void setValue(int cardValue){
        this.value = cardValue;
    }
    /**
     * Returns the face value of the card.
     * @rerturn a string representing the face value of the card.
     */
    public String toString(){
        return faceValue;
    }
    /**
     * Compares two objects of class card based on their position within
     * an ordered deck of cards. 
     * @param card which represents a card which will have its position within an ordered deck of cards compared to this card.
     * @return -1 if card should be after this object,  1 if this object should be after card ; if they are equals 0
     */
    public int compareTo(Card card){
        return position.compareTo(card.getPosition());
    }
}