package proj.decorators;

import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Player;

public class EngineDecorator implements Engine{
	Engine delegate;
	

	public EngineDecorator(Engine delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public Character getChar(int i) {
		return delegate.getChar(i);
	}



	@Override
	public boolean isGameOver() {
		return delegate.isGameOver();
	}

	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		delegate.init(h, w, s, p1, p2);
	}


	@Override
	public Player getPlayer(int i) {
		return delegate.getPlayer(i);
	}

	@Override
	public void step(Commande c1, Commande c2) {
		delegate.step(c1, c2);
	}

}
