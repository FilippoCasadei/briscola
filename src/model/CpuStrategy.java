package model;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public interface CpuStrategy {
	
	Card chooseCard(GameContext context, Cpu cpu);

//    // Strategia semplice: gioca la prima carta
//    static CpuStrategy easyStrategy() {
//        return hand -> hand.get(0);
//    }
//
//    // Strategia media: gioca una carta casuale
//    static CpuStrategy mediumStrategy() {
//        return hand -> hand.get(new Random().nextInt(hand.size()));
//    }
//    
// 
//    // Strategia avanzata: logica più complessa
//    static CpuStrategy hardStrategy() {
//        return hand -> {
//            // Esempio di logica avanzata (gioca la carta con il rank maggiore)
//            return hand.stream()
//                       .max(Comparator.comparingInt(card -> card.getRank().ordinal()))
//                       .orElseThrow(() -> new IllegalStateException("La mano è vuota"));
//        };
//    }
}

