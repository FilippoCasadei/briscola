package model;

import java.util.List;

public class Cpu extends Player{
	
	private CpuStrategy strategy;

	public Cpu(String name, CpuStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	public void setStrategy(CpuStrategy strategy) {
        this.strategy = strategy;
    }
	
	@Override
	public Card chooseCard(GameContext context) {
		// Usa la strategia configurata per selezionare una carta
		return strategy.chooseCard(context, this);
	}

}
