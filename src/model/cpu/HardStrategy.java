//package model.cpu;
//
//import java.util.Comparator;
//import java.util.List;
//
//import model.Card;
//
//public class HardStrategy implements CpuStrategy {
//    @Override
//    public Card chooseCard(List<Card> hand) {
//        // Logica avanzata: blocca il giocatore o gioca in modo ottimale
////        return hand.stream()
////            .filter(card -> card.getRank().ordinal() > 5)
////            .max(Comparator.comparingInt(card -> card.getRank().getIndex()))
////            .orElse(hand.get(0));
//    	return hand.get(0);
//    }
//
//}
