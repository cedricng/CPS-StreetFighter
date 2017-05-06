package proj.decorators;

import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Hitbox;
import proj.service.Tech;

public class CharacterDecorator implements Character{
	Character delegate;

	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public Engine getEngine() {
		return delegate.getEngine();
	}

	@Override
	public Hitbox getCharBox() {
		return delegate.getCharBox();
	}

	@Override
	public int getLife() {
		return delegate.getLife();
	}

	@Override
	public int getSpeed() {
		return delegate.getSpeed();
	}

	@Override
	public boolean faceRight() {
		return delegate.faceRight();
	}

	@Override
	public boolean isDead() {
		return delegate.isDead();
	}

	@Override
	public void init(int l, int s, boolean f, Engine e,int x,int y) {
		delegate.init(l, s, f, e,x,y);	
	}

	public CharacterDecorator(Character delegate) {
		super();
		this.delegate = delegate;
	}

	@Override
	public void moveLeft() {
		delegate.moveLeft();
		
	}

	@Override
	public void moveRight() {
		delegate.moveRight();
		
	}

	@Override
	public void switchSide() {
		delegate.switchSide();
		
	}

	@Override
	public void step(Commande c) {
		delegate.step(c);
	}

	@Override
	public void setLife(int lif) {
		delegate.setLife(lif);
		
	}

	@Override
	public int getNum() {
		return delegate.getNum();
	}

	@Override
	public boolean isBlocking() {
		return delegate.isBlocking();
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
	public boolean isTeching() {
		
		return delegate.isTeching();
	}

	@Override
	public void startTech(Tech t) {
		delegate.startTech(t);
	}

	@Override
	public Tech getTech() {
		return delegate.getTech();
	}

	@Override
	public boolean techHasAlreadyHit() {
		return delegate.techHasAlreadyHit();
	}

	@Override
	public void stun(int bstun,boolean blocking) {
		delegate.stun(bstun,blocking);
	}

	@Override
	public Hitbox getTechHitbox() {
		return delegate.getTechHitbox();
	}

	@Override
	public boolean isBlockStunned() {
		return delegate.isBlockStunned();
	}

	@Override
	public boolean isHitStunned() {
		return delegate.isHitStunned();
	}

	@Override
	public int getInitHeight() {
		return delegate.getInitHeight();
	}

	@Override
	public boolean isDown() {
		return delegate.isDown();
	}

	@Override
	public int startFrameCounter() {
		// TODO Auto-generated method stub
		return delegate.startFrameCounter();
	}

	@Override
	public int hitFrameCounter() {
		// TODO Auto-generated method stub
		return delegate.hitFrameCounter();
	}

	@Override
	public int recoveryFrameCounter() {
		// TODO Auto-generated method stub
		return delegate.recoveryFrameCounter();
	}
	
	

}
