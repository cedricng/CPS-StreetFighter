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
	boolean isDown();
	Tech getTech();
	int startFrameCounter();
	int hitFrameCounter();
	int recoveryFrameCounter();
	int stunCounter();
	//pre :l>0 && s>0
	
	//post:getLife()==l && getSpeed()==s && faceRight()==f && getEngine()==e
	
	//post:getCharBox()!=null
	void init(int l,int s,boolean f, Engine e,int x, int y);
	
	Hitbox getTechHitbox();
	
	//inv:getPositionX()>0 && getPositionX()<=getEngine().getWidth() 
	
	//inv:getPositionY()>0 && getPositionY()<=getEngine().getHeight() 
	
	//inv:(isDead())==(getLife()!=0)
	
	//inv:isBlockStunned =>isBlocking
	
	//inv:stunCounter>0 <=> isBlockStunned || isHitStunned
	
	
	
	//post: exists i getEngine()@pre.getChar(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
		//=>getPositionX()@pre=getPositionX()
	
	//post: (getPositionX()@pre<=getSpeed() 
		//&& (forall i getEngine().getChar(i) != this => 
		//!(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==0
	
	//post:(getPositionX()@pre>getSpeed() 
		//&& (forall i getEngine().getChar(i) != this => 
	
	//!(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getPositionX@pre-getSpeed();
	
	//post:!faceRight() && getLife()==getLife()@pre
	
	//post:getPositionY()==getPositionY()@pre
	void moveLeft();
	
	//post: exists i getEngine()@pre.getPlayer(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
		//=>getPositionX()@pre=getPositionX()
	
	//post: (getPositionX()@pre>=getEngine.getWidth()-getSpeed() 
		//&& (forall i getEngine().getPlayer(i) != this => 
	//!(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getEngine().getWidth()
	
	//post:(getPositionX()@pre<getEngine.getWidth()-getSpeed() 
		//&& (forall i getEngine().getPlayer(i) != this => 
	//!(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getPositionX@pre+getSpeed();
	
	//post:faceRight() && getLife()==getLife()@pre
	//post:getPositionY()==getPositionY()@pre
	void moveRight();
	
	//post: faceRight()!=faceRight@pre
	//post: positionX()=positionX@pre
	void switchSide();
	
	//pre:!dead()
	
	//post:!isTeching@pre && !isBlockStunned@pre && !isHitStunned@pre=> step(Commande.LEFT)=moveLeft()
	
	//post:!isTeching@pre&& !isBlockStunned@pre && !isHitStunned@pre=> 
		//step (Commande.RIGHT)=moveRight()
	
	//POST:!isBlockStunned@pre()=>c!=Commande.GUARD=>!isBlocking()
	
	//post:!isTeching@pre&& !isHitStunned@pre &&!isBlockStunned@pre=> (c=Commande.GUARD)=>isBlocking()
	
	//post:!isTeching@pre && !isHitStunned@pre &&!isBlockSunned@pre=>step(Commande.PUNCH)=startTech(new Punch());
	
	//post:!isTeching@pre&& !isBlockStunned@pre && !isHitStunned@pre && !isDown@pre=> 
		//c=Commande.DOWN=>getHeight=getInitHeight/2 && isDown
	
	//post:!isTeching@pre && !isBlockStunned@pre && !isHitStunned@pre && isDown@pre => 
		//c=Commande.UP=>getHeight=getInitHeight && !isDown
	
	//post:startFrameCounter@pre>0 =>startFrameCounter==startFrameCounter@pre-1
	
	//post:startFrameCounter@pre==0 && hitFrameCounter@pre>0 =>hitFrameCounter==hitFrameCounter@pre-1 
			//&& getTech!=null &&getTechHitbox!=null && getTech().getDamage()>0 && 
			//getTech().getBStun()>0 &&getTech().getHStun()>0 && getTech().getgetSframe()>0 
			//&&getTech().getHframe()>0 getTech().getRframe()>0
	
	//post:recoveryFrameCounter@pre>0 && hitFrameCounter@pre==0 =>
			//recoveryFrameCounter==recoveryFrameCounter@pre-1 && getTech=null && getTechHitbox=null
	
	//post:stunCounter_at_pre>0=>stunCounter=stunCounter_at_pre-1
	
	//post:exists i getEngine()@pre.getChar(i) != this && !techHasAlreadyHit@pre=>
	//(techHasAlreadyHit=>getEngine.getChar(i).life<getEngine.getChar(i).life@pre)
	void step(Commande c);
	
	//pre:!isTeching()
	
	//post:isTeching()
	//post:SframeCounter==t.sframe-1
	//post:HframeCounter==t.hframe
	//post:RframeCounter==t.rframe
	void startTech(Tech t);
	
	
	//post:stunCounter()==bstun
	//post:isBlocking()==isBlockStunned() && isBlocking()!=isHitStunned()
	void stun(int bstun,boolean blocking);
	
	
	
	
	
	
	

}
