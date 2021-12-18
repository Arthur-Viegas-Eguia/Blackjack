import java.util.*;
/**
 * Class GamblingGame models a casino card game where users can gamble and get
 * cards from a deck of cards. It has methods to deal hands to the user 
 * and to methods to gamble. It also implements a simple game that both the user
 * and the computer are given a number of cards, whoever has the highest value in 
 * their hands wins.
 */
public class GamblingGame extends CardDeck{
    /**
     * The total amount of money the user currently has.
     */
    private float money = 0;
    /**
     * The total bet which the user has put on the table.
     */
    private float bet = 0;
    /**
     * The scanner used to get user input throughout the game.
     */
    Scanner userInput = new Scanner(System.in);
    /**
     * Constructs the class. 
     * @param money the money the user has upon their arrival at the game.
     */
    public GamblingGame(int money){
        super();
        this.money = money;
    }
    /**
     * Gets the amount of money the user currently  has.
     * @return the amount of money the user currently  has.
     */
    public float getMoney(){
        return money;
    }
    /**
     * Gets the amount of bet which is currently on the table.
     * @return the amount of bet which is currently on the table
     */
    public float getBet(){
        return bet;
    }
    /**
     * Adds money to the total amount of the user currently has.
     * @param money the amount of money which will be added to the total amount the user currently has
     */
    public void addMoney(float money){
        if(money > 0){
            this.money += money;
        }
    }
    /**
     * Bets the amount of money the user indicated.
     * @param bet the total amount of money the user wants to bet.
     * @return true if it was possible to make the bet; false otherwise.
     */
    public boolean placeBet(float bet){
        if(bet > this.money || bet <= 0){
            return false;
        }
        this.money -= bet;
        this.bet = bet;
        return true;
    }
    /**
     * Sums the ingame values for each of the cards in a hand and returns it.
     * @param handCards the hand which will have the value of all its cards summed
     * @return the sum of the ingame value of all cards in handCards
     */
    public int sum(LinkedList<Card> handCards){
        int count = 0;
        for(int i = 0; i < handCards.size(); i++){
            count += handCards.get(i).getValue();
        }
        return count;
    }
    /**
     * Tests if it is possible to make the bet the user currently wants to make.
     * @param bet the amount of money the user wants to bet
     * @return true if it is possible to make the bet; false otherwise.
     */
    public boolean testBet(float bet){
        if(bet > this.money || bet <= 0){
            return false;
        }
        return true;
    }
    /**
     * Adds twice the amount of bet on the table to the total amount of
     * money the user has. Resets the value of bet.
     */
    public void winBet(){
        addMoney(2*bet);
        bet = 0;
    }
    /**
     * Sets the value of bet to 0. Makes the user lose the bet.
     */
    public void loseBet(){
        bet = 0;
    }
    /**
     * Deals a hand of number cards from the top of the deck to the user.
     * @param number the amount of the cards that will be dealt
     * @return a list with number objects of type card, representing a hand within a game.
     */
    public LinkedList<Card> dealCards(int number){
        LinkedList<Card> cards = new LinkedList<Card>();
        for(int i = 0; i < number; i++){
            cards.add(super.dealCard());
        }
        return cards;
    }
    /**
     * Deals the card on top of the deck to the user and removes it card from the deck. 
     * @return an Object of type Card, representing the card on top of the deck.
     */
    public Card getOnlyOneCard(){
        return super.dealCard();
    }
    /**
     * Menu where the user decides how much they want to bet. Prints
     * all instructions and validates the user's input.
     * @return the amount of money the user wants to bet
     */
    public int betMenu(){
        Integer moneyBet = 0;
        while(true){
            System.out.println("How much money do you want to bet for this hand");
            moneyBet = Integer.parseInt(userInput.nextLine());
            if(testBet(moneyBet)){
                break;
            } else {
                System.out.println("The amount of money you bet must be a float number greater than 0 and less than the total amount of money you have!");
            }
        }
        return moneyBet;
    }
    /**
     * Starts a simple game where the user chooses the amount of cards to be
     * dealt. The user and the computer get dealt these many cards. Whoever
     * has the greatest sum of the ingame value of the cards wins.
     */
    public void playGame(){
        int numberOfCards;
        LinkedList<Card> user = new LinkedList<Card>();
        LinkedList<Card> computer = new LinkedList<Card>();
        placeBet(betMenu());
        while(true) {
            System.out.println("How many cards do you want each player to have");
            numberOfCards = userInput.nextInt();
            userInput.nextLine();
            if(numberOfCards > 0){
                break;
            } else {
                System.out.println("The number of cards must be a positive integer and not 0");
            }
        }
        user = dealCards(numberOfCards);
        computer = dealCards(numberOfCards);
        System.out.println("Your cards "+user);
        System.out.println("Computer's cards "+computer);
        if(sum(user) > sum(computer)) {
            winBet();
            System.out.println("You have won the game, now you have "+getMoney());
        } else {
            loseBet();
            System.out.println("You have lost the game, now you have "+getMoney());
        }

    }
    /**
     * Asks the user for the amount of money they have upon arriving at the
     * table, and runs the game.
     * @param args the amount of money the user arrives with on the table
     */
    public static void main(String[] args) {
        Scanner userAnswer = new Scanner(System.in);
        Integer totalMoney = 0;
        GamblingGame game;
        String answer;
        boolean playing = true;
        if(args.length == 0){
            System.out.println("You need money to join the gambling game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        try{
            totalMoney = Integer.parseInt(args[0]);
        } catch(Exception e){
            System.out.println("You need money to join the gambling game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        if(totalMoney <= 0){
            System.out.println("You need money to join the gambling game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        game = new GamblingGame(totalMoney);
        while(game.getMoney() > 0 && playing){
            game.playGame();
            while(true){
                System.out.println("Type continue to keep playing, or type exit to leave the game");
                answer = userAnswer.nextLine();
                if(answer.equals("continue")){
                    break;
                } else if(answer.equals("exit")){
                    playing = false;
                    break;
                } else {
                    System.out.println("Please type either continue if you want ot keep playing or exit if you want to leave the game");
                }
            }
        }
        System.out.println("You have left the game, now you have "+game.getMoney()+"$");
        userAnswer.close();
    }
}