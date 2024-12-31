package model;

public class EasyStrategy implements CpuStrategy{

	@Override
	public Card chooseCard(GameContext context, Cpu cpuPlayer) {
		return cpuPlayer.getHand().getCards().get(0);
	}
}