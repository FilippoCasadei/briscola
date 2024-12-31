package model;

import java.util.ArrayList;
import java.util.List;

import model.state.SetupState;

/*
 * TODO: DA ELIMINARE LA LIBRERIA JAVAfx
 */
public class Briscola {
	public static void main(String[] args) {
		CpuStrategy easyStrategy = new EasyStrategy();
		CpuStrategy mediumStrategy = new MediumStrategy();
		CpuStrategy hardStrategy = new HardStrategy();

		Player p1 = new Cpu("cpu1", easyStrategy);
		Player p2 = new Cpu("cpu2", easyStrategy);
		Player p3 = new Cpu("cpu3", mediumStrategy);
		Player p4 = new Cpu("cpu4", hardStrategy);
		
		List<Player> players = new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);

        // Inizializza il contesto con lo stato iniziale (SetupState)
        GameContext gameContext = new GameContext(new SetupState(), players);

        // Simula il ciclo di esecuzione degli stati
        gameContext.play();
        
    }
}
