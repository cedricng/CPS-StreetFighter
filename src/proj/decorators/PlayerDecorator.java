package proj.decorators;

import proj.service.Character;
import proj.service.Player;

public class PlayerDecorator implements Player {
	Player delegate;
	
	public PlayerDecorator(Player delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public Character getChar() {
		return delegate.getChar();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

	@Override
	public void init(Character c) {
		delegate.init(c);
	}
	
	

}
