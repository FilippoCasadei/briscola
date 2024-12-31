//package model.cpu;
//
//import java.util.Comparator;
//import java.util.List;
//
//import model.Card;
//
//public class MediumStrategy implements CpuStrategy {
//    @Override
//    public Card chooseCard(List<Card> hand) {
//        // Gioca la carta con il valore più alto o una briscola se possibile
//        return hand.stream()
//        		   .max(Comparator.comparingInt(card -> card.getRank().ordinal()))
//        		   .orElse(hand.get(0));
//    }
//
//}

