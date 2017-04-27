package proj.service;

public interface Player  {
	
	Character getChar();
	boolean isDead();
	
	void init(Character c);
}
