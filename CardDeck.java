import java.util.*;
/**
 * Class CardDeck models a standard deck of cards and has methods
 * to shuffle, deal and sort cards.
 */
public class CardDeck{
    /**
     * The deck of card per se, contains all the cards currently on the deck.
     */
    LinkedList<Card> cards = new LinkedList<Card>();
    /**
     * A linked list of all cards which have already been dealt.
     */
    LinkedList<Card> toShuffle = new LinkedList<Card>();
    /**
     * Constructs the class.
     */
    public CardDeck(){
        cards.add(new Card("AS",1,11));
        cards.add(new Card("2S",2,2));
        cards.add(new Card("3S",3,3));
        cards.add(new Card("4S",4,4));
        cards.add(new Card("5S",5,5));
        cards.add(new Card("6S",6,6));
        cards.add(new Card("7S",7,7));
        cards.add(new Card("8S",8,8));
        cards.add(new Card("9S",9,9));
        cards.add(new Card("10S",10,10));
        cards.add(new Card("JS",11,10));
        cards.add(new Card("QS",12,10));
        cards.add(new Card("KS",13,10));
        cards.add(new Card("AD",14,11));
        cards.add(new Card("2D",15,2));
        cards.add(new Card("3D",16,3));
        cards.add(new Card("4D",17,4));
        cards.add(new Card("5D",18,5));
        cards.add(new Card("6D",19,6));
        cards.add(new Card("7D",20,7));
        cards.add(new Card("8D",21,8));
        cards.add(new Card("9D",22,9));
        cards.add(new Card("10D",23,10));
        cards.add(new Card("JD",24,10));
        cards.add(new Card("QD",25,10));
        cards.add(new Card("KD",26,10));
        cards.add(new Card("AH",27,11));
        cards.add(new Card("2H",28,2));
        cards.add(new Card("3H",29,3));
        cards.add(new Card("4H",30,4));
        cards.add(new Card("5H",31,5));
        cards.add(new Card("6H",32,6));
        cards.add(new Card("7H",33, 7));
        cards.add(new Card("8H",34, 8));
        cards.add(new Card("9H",35, 9));
        cards.add(new Card("10H",36, 10));
        cards.add(new Card("JH",37, 10));
        cards.add(new Card("QH",38, 10));
        cards.add(new Card("KH",39, 10));
        cards.add(new Card("AC",40, 11));
        cards.add(new Card("2C",41, 2));
        cards.add(new Card("3C",42, 3));
        cards.add(new Card("4C",43, 4));
        cards.add(new Card("5C",44, 5));
        cards.add(new Card("6C",45, 6));
        cards.add(new Card("7C",46, 7));
        cards.add(new Card("8C",47, 8));
        cards.add(new Card("9C",48, 9));
        cards.add(new Card("10C",49, 10));
        cards.add(new Card("JC",50, 10));
        cards.add(new Card("QC",51, 10));
        cards.add(new Card("KC",52, 10));
        shuffle();
    }
    /**
     * Shuffles the deck of cards 7 times.
     * @return a shuffled deck of cards
     */
    public LinkedList<Card> shuffle(){
        for(int i = 0; i < 7; i++){
            for (int j = 0;  j < cards.size(); j++) {
                int index = (int) Math.floor(Math.random() * (j + 1));
                Card temp = cards.get(j);
                cards.set(j, cards.get(index));
                cards.set(index, temp);
            }
        }
        return cards;
    }
    /**
     * Sorts a deck of cards.
     */
   public void sort(){ 
       Collections.sort(cards);
   }
   /**
    * Returns the face value of each card currently on the deck.
    * @return the face value of every card currently on the deck.
    */
    public String toString(){
        String toReturn = "";
        for(int i = 0; i < cards.size() - 1; i++){
            toReturn += cards.get(i).getFaceValue() + ",  ";
        }
        toReturn += cards.get(cards.size() - 1);
        return toReturn;
    }
    /**
     * Deals the card on top of the deck to the user and removes it  from the deck of cards. 
     * Adds the card to a list of cards which have already been dealt. If all cards have been 
     * dealt it sorts the deck of cards (representing getting a brand new deck of cards) and
     * shuffles it.
     * @return an Object of type Card, representing the card which was on top of the deck of cards.
     */
    public Card dealCard(){
        Card card = cards.peek();
        toShuffle.add(cards.remove());
        if(toShuffle.size() == 52){
            Collections.sort(toShuffle);
            cards = toShuffle;
            shuffle();
            toShuffle = new LinkedList<>();
        }
        return card;
    }
    /**
     * Deals a new card when the user presses any key.
     */
    public static void main(String[] args) {
        CardDeck test = new CardDeck();
        Scanner userInput = new Scanner(System.in);
        while(true) {
            System.out.println("Type anything to be dealt a new card");
            userInput.nextLine();
            System.out.println("Your card is "+test.dealCard());
        }
    }
}