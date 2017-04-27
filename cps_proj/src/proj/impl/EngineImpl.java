package proj.impl;

import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Player;

public class EngineImpl implements Engine{
	private int height;
	private int width;
	
	private Player[] players=new Player[2];
	

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Character getChar(int i) {
		return getPlayer(i).getChar();
	}

	

	@Override
	public boolean isGameOver() {
		return getChar(1).isDead()||getChar(2).isDead();
	}
	public Player getPlayer(int i){
		return players[i-1];
	}
	@Override
	public void init(int h, int w, int s, Player p1, Player p2) {
		height=h;
		width=w;
		
		players[0]=p1;
		players[1]=p2;
		players[0].getChar().init(100, 7, true, this, w/2-s/2, 0);
		players[1].getChar().init(100, 7, false, this, w/2+s/2, 0);
		
	}

	@Override
	public void step(Commande c1, Commande c2) {
		getChar(1).step(c1);
		getChar(2).step(c2);
	}


}
