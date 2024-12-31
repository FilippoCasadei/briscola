package model.state;

import model.GameContext;

public interface GameState {
	
	void handle(GameContext context);

}
