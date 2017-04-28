package proj.service;

public interface Character {
	int getPositionX();
	int getPositionY();
	Engine getEngine();
	Hitbox getCharBox();
	int getLife();
	void setLife(int lif);
	int /*const*/ getSpeed();
	boolean faceRight();
	boolean isDead();
	int getNum();
	boolean isBlocking();
	int getHeight();
	int getWidth();
	boolean isTeching();
	boolean techHasAlreadyHit();
	boolean isBlockStunned();
	boolean isHitStunned();
	int getInitHeight();
	//pre:!isTeching()
	Tech getTech();
	//pre :l>0 && s>0
	//post:getLife()==l && getSpeed()==s && faceRight()==f && getEngine()==e
	//post:getCharBox()!=null
	void init(int l,int s,boolean f, Engine e,int x, int y);
	
	//pre:isTeching()
	Hitbox getTechHitbox();
	
	//inv:getPositionX()>0 && getPositionX()<=getEngine().getWidth() 
	//inv:getPositionY()>0 && getPositionY()<=getEngine().getHeight() 
	//inv:(isDead())==(getLife()!=0)
	
	
	//post: exists i getEngine()@pre.getPlayer(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
	//=>getPositionX()@pre=getPositionX()
	
	//post: (getPositionX()@pre<=getSpeed() 
	//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
	//=>getPositionX()==0
	
	//post:(getPositionX()@pre>getSpeed() 
	//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
	//=>getPositionX()==getPositionX@pre-getSpeed();
	
	//post:!faceRight() && getLife()==getLife()@pre
	//post:getPositionY()==getPositionY()@pre
	void moveLeft();
	
	//post: exists i getEngine()@pre.getPlayer(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
	//=>getPositionX()@pre=getPositionX()
	
	//post: (getPositionX()@pre>=getEngine.getWidth()-getSpeed() 
	//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
	//=>getPositionX()==getEngine().getWidth()
	
	//post:(getPositionX()@pre<getEngine.getWidth()-getSpeed() 
	//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
	//=>getPositionX()==getPositionX@pre+getSpeed();
	
	//post:faceRight() && getLife()==getLife()@pre
	//post:getPositionY()==getPositionY()@pre
	void moveRight();
	
	//post: faceRight()!=faceRight@pre
	//post: positionX()=positionX@pre
	void switchSide();
	
	//pre:!dead()
	//post:!isTeching@pre&& !isBlockStunned@pre && !isHitStunned@pre=> step(Commande.LEFT)=moveLeft() && step(Commande.LEFT)=>isBlocking()=false
	//post:!isTeching@pre&& !isBlockStunned@pre && !isHitStunned@pre=> step (Commande.RIGHT)=moveRight() && step(Commande.RIGHT)=>isBlocking()=false
	//POST:!isBlockStunned@pre()=>step(Commande.NEUTRAL)=>isBlocking()=false
	//post:!isTeching@pre&& !isHitStunned@pre=> step(Commande.GUARD)=>isBlocking()==true
	//post:!isTeching@pre=>step(Commande.PUNCH)=startTech(new Punch());
	//post:!isTeching@pre&& !isBlockStunned@pre && !isHitStunned@pre=> c=Commande.DOWN=>getHeight=getInitHeight@pre/2
	void step(Commande c);
	
	//pre:!isTeching()
	//post:isTeching()
	void startTech(Tech t);
	
	
	
	void stun(int bstun);
	
	
	
	
	
	
	

}
