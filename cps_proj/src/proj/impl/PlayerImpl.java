package proj.impl;

import proj.service.Character;
import proj.service.Player;

public class PlayerImpl implements Player {
	Character ch;

	@Override
	public Character getChar() {
		return ch;
	}

	@Override
	public boolean isDead() {
		return ch.isDead();
	}

	@Override
	public void init(Character c) {
		ch=c;
	}

}
