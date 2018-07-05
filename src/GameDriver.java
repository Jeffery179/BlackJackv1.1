// import java.util.Scanner;
//
public class GameDriver {
//
// public static void main(String[] args) {
//
// Scanner scanner = new Scanner(System.in);
// Deck deck = new Deck(1, true);
//
// // deck.printDeck();
//
// Player me = new Player("Jeff");
// Player dealer = new Player("House");
////
//// me.addCard(deck.dealNextCard());
//// dealer.addCard(deck.dealNextCard());
//// me.addCard(deck.dealNextCard());
//// dealer.addCard(deck.dealNextCard());
////
//// System.out.println("Cards dealt!");
//// me.printHand(false);
//// dealer.printHand(true);
//// System.out.println();
////
//// boolean playerDone = false;
//// boolean dealerDone = false;
//// String response;
////
//// // player's turn:
//// while (!playerDone) {
////
//// //check for double down possibility:
//// boolean doubleDownPossible = me.canDoubleDown();
////
//// if(doubleDownPossible) {
//// System.out.println("Stay, Hit, or Double Down? ('S', 'H', or 'D')");
//// } else {
//// System.out.println("Stay or Hit? ('S' or 'H'");
//// }
//// response = scanner.next();
//// System.out.println();
//// if (response.compareToIgnoreCase("H") == 0) {
//// playerDone = !me.addCard(deck.dealNextCard());
//// me.printHand(false);
//// } else if(response.compareToIgnoreCase("D") == 0 && doubleDownPossible) {
//// playerDone = !me.addCard(deck.dealNextCard());
//// me.printHand(false);
//// playerDone = true; //only 1 extra card can be dealt
//// } else {
//// playerDone = true;
//// }
//// }
////
//// // dealer turn:
//// while (!dealerDone) {
//// dealer.printHand(false);
//// if (dealer.getHandSum() < 17) {
//// System.out.println("The dealer hits!");
//// dealerDone = !dealer.addCard(deck.dealNextCard());
//// dealer.printHand(false);
//// } else {
//// dealerDone = true;
//// }
//// }
//// System.out.println();
////
//// scanner.close();
//// me.printHand(false);
//// dealer.printHand(false);
////
//// int playerSum = me.getHandSum();
//// int dealerSum = dealer.getHandSum();
////
//// if ((playerSum > dealerSum && playerSum <= 21) || dealerSum > 21) {
//// System.out.println("Player wins with: " + playerSum + " over dealer's " +
// dealerSum);
//// } else if (playerSum == dealerSum) {
//// System.out.println("Push! Dealer with: " + dealerSum + ", Player with: " +
// playerSum);
//// } else {
//// System.out.println("Dealer wins with: " + dealerSum + " over player's " +
// playerSum);
//// }
}
// }
