import java.util.LinkedList;
import java.util.Scanner;
/**
 * Models a simplified game of blackjack, without splits and insurance.
 * Gives the user the standard operations of the game and plays against
 * the user with simple strategies.
 */
class Blackjack extends GamblingGame{
    /**
     * The hand of the user.
     */
    private LinkedList<Card> hand = new LinkedList<Card>();
    /**
     * The hand of the computer.
     */
    private LinkedList<Card> computer = new LinkedList<Card>();
    /**
     * A decision tree which tells the computer whether or not to draw cards
     */
    private DecisionTree computerDecisions;
    /**
     * The scanner responsible to ingame user interactions
     */
    private Scanner userInput;
    /**
     * The card count of the table.
     */
    private int cardCount = 0;
    /**
     * Initiates the class.
     * @param money the amount of money the user has in the beginning of the game
     */
    public Blackjack(int money){
        super(money);
        computerDecisions = new DecisionTree();
        computerDecisions.addLeave(15, 6);
        computerDecisions.addLeave(13, 15);
        computerDecisions.addLeave(17, 2);
        computerDecisions.addLeave(12, 20);
        computerDecisions.addLeave(14, 12);
        computerDecisions.addLeave(16, 4);
        computerDecisions.addLeave(18, -15);
        userInput = new Scanner(System.in);
    }
    /**
     * Counts the cards which are currently on the table using HiLo
     * @return the card count of the cards which are on the table
     */
    private int hiLo(){
        int currentCount = cardCount;
        for(int i = 0; i < hand.size(); i++){
            if(hand.get(i).getValue() < 7){
                currentCount += 1;
            } else if (hand.get(i).getValue() < 10){
                currentCount += 0;
            } else {
                currentCount -= 1;
            }
        }
        for(int i = 0; i < computer.size(); i++){
            if(computer.get(i).getValue() < 7){
                currentCount += 1;
            } else if (computer.get(i).getValue() < 10){
                currentCount += 0;
            } else {
                currentCount -= 1;
            }
        }
        return currentCount;
    }
    /**
     * Checks if the user has an ace, if so changes the value of the first
     * ace in the user's hand from 11 to 1.
     * @param handCard the hand which is going to have the value of its aces checked and replaced
     * @return true if the value of an ace was replaced; false otherwise
     */
    private boolean checkValueOfAce(LinkedList<Card> handCard){
        for(int i = 0; i < handCard.size(); i++){
            if(handCard.get(i).getValue() == 11){
                handCard.get(i).setValue(1);
                return true;
            }
        }   
        return false;
    }
    /**
     * Decides whether the computer should draw another card or not based on 
     * the current card count.
     * @param currentCount the current card count
     * @return true if the computer should draw another card; false otherwise
     */
    private boolean makeDecisionsForComputer(int currentCount){
        int handValue = sum(computer);
        if(handValue < 12){
            return true;
        } else if(handValue > 18){
            return false;
        } else {
            return currentCount <= computerDecisions.get(handValue);
        }
    }
    /**
     * Makes the moves of the computer. 
     * Checks if it has more than 21.
     */
    private void adversaryMoves(){
        while(makeDecisionsForComputer(hiLo())){
            displayCards(true);
            computer.add(super.dealCard());
            checkStatus(computer);
        }
    }
    /**
     * Finalizes the game: prints all cards, defines who won the game, pays
     * the user double the amount they bet in case of a win or makes
     * them lose the bet otherwise.
     */
    private void finalizeGame(){
        displayCards(true);
        if((sum(hand) > sum(computer) && sum(hand) <= 21) || sum(computer) > 21){
            super.winBet();
            System.out.println("You have won the game, now with the money you got you have "+super.getMoney()+"$");
        } else {
            super.loseBet();
            System.out.println("Oh no, you have lost the game, now you have "+super.getMoney()+"$");
        }
    }
    /**
     * Returns true if the value of hand is below 21 and false otherwise.
     * Makes the necessary adjustments if necessary
     * @param handCard the hand which is being tested
     * @return true if the hand is above 21 and false otherwise
     */
    private boolean checkStatus(LinkedList<Card> handCard){
        int count = sum(handCard);
        while(count > 21){  
            if(!checkValueOfAce(handCard)){
                return false;
            }
            count = sum(handCard);
        }
        return true;
    }
    /**
     * Starts the game, sets up the bet, deals the original cards to the user
     * and the computer, prints the cards and checks whether the value
     * of the player's hand is above 21.
     * @param bet the amount the user wants to bet
     */
    private void startGame(int bet){
        super.placeBet(bet);
        hand = super.dealCards(2);
        computer = super.dealCards(2);
        boolean test = true;
        while(test){
            displayCards(false);
            if(!checkStatus(hand)){
                break;
            } else {
                test = userOperations();
            }
        }
    }
    /**
     * Runs the game, starts it, calls all the functions used during its
     * execution, offer user the operations he can call during the game.
     * updates the value of the card count and finalizes the game.
     * @param bet the amount the user wants to bet
     */
    public void playGame(int bet){
        startGame(bet);
        adversaryMoves();
        cardCount += hiLo();
        if(sum(hand) > 21){
            super.loseBet();
            System.out.println("Oh no, you have lost the game, now you have "+super.getMoney()+"$");
        } else {
            finalizeGame();
        }
    }
    /**
     * Shows and executes the possible ingame operations.
     * Takes the user input and validates it.
     * @return true if the user got a new card; false otherwise
     */
    private boolean userOperations(){
        boolean test = true;
        boolean toReturn = false;
        String input;
        while(test){
            System.out.println("If you would like a new card type new card, if you would like to hold type hold");
            input = userInput.nextLine();
            switch(input){
                case "new card":
                test = false;
                toReturn = true;
                hand.add(super.getOnlyOneCard());
                break;
                case "hold":
                toReturn = false;
                test = false;
                break;
                default:
                System.out.println("Invalid input, please type new card if you want a new card and hold if you dont");
                break;
            }
        }
        return toReturn;
    }
    /**
     * Prints all cards on the table, replaces the hidden dealer's second card
     * with an X if necessary.
     * @param dealer a boolean. If true prints all the dealers cards, otherwise prints only the dealer's first card.
     */
    public void displayCards(boolean dealer){
        System.out.println("---------------------------------------------------------------");
        if(dealer){
            for(int i = 0; i < computer.size(); i++){
                System.out.println(computer.get(i).getFaceValue());
            }
        } else{
            System.out.println(computer.peek().getFaceValue());
            System.out.println("X");
        }
        System.out.println("\n\n\n\n");
        for(int i = 0; i < hand.size(); i++){
            System.out.println(hand.get(i).getFaceValue());
        }
    }
    /**
     * Asks the user for the amount of money they have upon arriving at the
     * table, initializes and runs the entire game. Finishes the game and 
     * print the end results (the amount of money the user has) upon
     * leaving the table on the screen.
     * @param args the amount of money the user arrives with on the table
     */
    public static void main(String[] args) {
        Scanner userAnswer = new Scanner(System.in);
        Integer totalMoney = 0;
        Blackjack game;
        String answer;
        boolean playing = true;
        if(args.length == 0){
            System.out.println("You need money to join the blackjack game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        try{
            totalMoney = Integer.parseInt(args[0]);
        } catch(Exception e){
            System.out.println("You need money to join the blackjack game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        if(totalMoney <= 0){
            System.out.println("You need money to join the blackjack game. Please type a positive float value as the amount of money you have the next time.");
            System.exit(1);
        }
        game = new Blackjack(totalMoney);
        while(game.getMoney() > 0 && playing){
            game.playGame(game.betMenu());
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
