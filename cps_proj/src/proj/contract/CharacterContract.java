package proj.contract;

import proj.decorators.CharacterDecorator;
import proj.impl.RectHitboxImpl;
import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.RectHitbox;
import proj.service.Tech;

public class CharacterContract extends CharacterDecorator {

	public CharacterContract(Character delegate) {
		super(delegate);

	}
	void checkInvariant(){

		//inv:getPositionX()>=0 && getPositionX()<=getEngine().getWidth()
		if(!(getPositionX()>=0 && getPositionX()<=getEngine().getWidth())){
			throw new InvariantError("getPositionX()>=0 && getPositionX()<=getEngine().getWidth()");
		}

		//inv:getPositionY()>=0 && getPositionY()<=getEngine().getHeight()
		if(!(getPositionY()>=0 && getPositionY()<=getEngine().getHeight())){
			throw new InvariantError("getPositionY()>=0 && getPositionY()<=getEngine().getHeight()");
		}

		//inv:(isDead())==(getLife()!=0)
		if(!( isDead() == (getLife()==0) )){
			throw new InvariantError("(isDead())==(getLife()!=0)");
		}



	}
	@Override
	public void init(int l, int s, boolean f,Engine e,int x,int y){

		//pre :l>0 && s>0
		if(!(l>0 && s>0)){
			throw new PreconditionError("l>0 && s>0");
		}

		//traitement
		super.init(l, s, f, e,x,y);

		//post-inv
		checkInvariant();

		//post:getLife()==l && getSpeed()==s && faceRight()==f && getEngine()==e
		if(!( getLife() ==l && getSpeed()==s && faceRight()==f && getEngine()==e)){
			throw new PostconditionError("getLife()==l && getSpeed()==s && faceRight()==f && getEngine()==e");
		}
		//post:getCharBox()!=null
		if(!(getCharBox()!=null)){
			throw new PostconditionError("getCharBox()!=null");
		}
	}

	@Override
	public void moveLeft(){
		//pre-inv
		checkInvariant();

		//capture
		int positionX_at_pre= getPositionX();
		boolean faceRight_at_pre=faceRight();
		int positionY_at_pre= getPositionY();
		RectHitbox charbox_at_pre=new RectHitboxContract(new RectHitboxImpl());
		charbox_at_pre.init(getCharBox().getPositionX(), getCharBox().getPositionY(), ((RectHitbox)getCharBox()).getHeight(),((RectHitbox)getCharBox()).getWidth() );

		//traitement
		super.moveLeft();

		//post-inv
		checkInvariant();

		//post
		postMoveLeft(positionX_at_pre, positionY_at_pre, faceRight_at_pre,charbox_at_pre);


	}

	public void moveRight(){
		//pre-inv
		checkInvariant();

		//capture
		int positionX_at_pre= getPositionX();
		boolean faceRight_at_pre=faceRight();
		int positionY_at_pre= getPositionY();
		RectHitbox charbox_at_pre=new RectHitboxContract(new RectHitboxImpl());
		charbox_at_pre.init(getCharBox().getPositionX(), getCharBox().getPositionY(), ((RectHitbox)getCharBox()).getHeight(),((RectHitbox)getCharBox()).getWidth() );

		//traitement
		super.moveRight();

		//post-inv
		checkInvariant();

		//post
		postMoveRight(positionX_at_pre, positionY_at_pre, faceRight_at_pre,charbox_at_pre);

	}

	public void switchSide(){
		//pre-inv
		checkInvariant();

		//capture
		int positionX_at_pre= getPositionX();
		boolean faceRight_at_pre=faceRight();


		//traitement
		super.switchSide();

		//post-inv
		checkInvariant();

		//post: faceRight()!=faceRight@pre
		if(!(faceRight()!=faceRight_at_pre)){
			throw new PostconditionError("faceRight()!=faceRight@pre");
		}

		//post: positionX()=positionX@pre
		if(!(getPositionX()!=positionX_at_pre)){
			throw new PostconditionError("positionX()=positionX@pre");
		}
	}
	@Override
	public void step(Commande c){
		//pre-inv
		checkInvariant();
		//pre:!dead()
		if(isDead()){
			throw new PreconditionError("!dead()");

		}

		//capture
		int positionX_at_pre= getPositionX();
		boolean faceRight_at_pre=faceRight();
		int positionY_at_pre= getPositionY();
		RectHitbox charbox_at_pre=new RectHitboxContract(new RectHitboxImpl());
		charbox_at_pre.init(getCharBox().getPositionX(), getCharBox().getPositionY(), ((RectHitbox)getCharBox()).getHeight(),((RectHitbox)getCharBox()).getWidth() );
		boolean isTeching_at_pre=isTeching();
		//traitement
		super.step(c);

		//post-inv
		checkInvariant();

		postStep(positionX_at_pre, positionY_at_pre, faceRight_at_pre, c,charbox_at_pre,isTeching_at_pre);

	}
	public void postMoveLeft(int positionX_at_pre, int positionY_at_pre,boolean faceRight_at_pre, RectHitbox charbox_at_pre){
		//post: exists i getEngine()@pre.getPlayer(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
		//=>getPositionX()@pre=getPositionX()
		if(getNum()==2){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){	
				if(!(positionX_at_pre==getPositionX())){
					throw new PostconditionError(" exists i getEngine()@pre.getPlayer(i) != this "
							+ "&& getCharBox().collidesWith(getPlayer(i).getCharBox)"
							+ "=>getPositionX()@pre=getPositionX()");
				}
			}
		}
		else if(getNum()==1 ){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(charbox_at_pre.collidesWith(getEngine().getChar(2).getCharBox())){	
				if(!(positionX_at_pre==getPositionX())){
					throw new PostconditionError(" exists i getEngine()@pre.getPlayer(i) != this "
							+ "&& getCharBox().collidesWith(getPlayer(i).getCharBox)"
							+ "=>getPositionX()@pre=getPositionX()");
				}
			}
		}

		//post: (getPositionX()@pre<=getSpeed() 
		//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==0
		if(positionX_at_pre<=getSpeed() && 
				getNum()==2 ){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){	
				if(!(getPositionX()==0)){
					throw new PostconditionError(" (getPositionX()@pre<=getSpeed()  "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==0");
				}
			}
		}
		else if(positionX_at_pre<=getSpeed() && 
				getNum()==1 ){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(2).getCharBox())){	
				if(!(getPositionX()==0)){
					throw new PostconditionError(" (getPositionX()@pre<=getSpeed()  "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==0");
				}
			}
		}

		//post:(getPositionX()@pre>getSpeed()
		//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getPositionX@pre-getSpeed();
		if(positionX_at_pre>getSpeed() && 
				getNum()==2 ){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){
				if(!(getPositionX()==positionX_at_pre-getSpeed())){
					throw new PostconditionError(" (getPositionX()@pre>getSpeed() "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getPositionX@pre-getSpeed();");
				}
			}
		}
		else if(positionX_at_pre>getSpeed() && 
				getNum()==1 ){
			charbox_at_pre.MoveTo(positionX_at_pre-getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){
				if(!(getPositionX()==positionX_at_pre-getSpeed())){
					throw new PostconditionError(" (getPositionX()@pre>getSpeed() "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getPositionX@pre-getSpeed();");
				}
			}
		}

		//post:!faceRight() && getLife()==getLife()@pre
		if(faceRight() ){
			throw new PostconditionError("!faceRight()");
		}

		//post:getPositionY()==getPositionY()@pre
		if(!(getPositionY()==positionY_at_pre)){
			throw new PostconditionError("positionY()=positionY@pre");
		}

	}
	public void postMoveRight(int positionX_at_pre, int positionY_at_pre,boolean faceRight_at_pre,RectHitbox charbox_at_pre){
		//post: exists i getEngine()@pre.getPlayer(i) != this && getCharBox().collidesWith(getPlayer(i).getCharBox)
		//=>getPositionX()@pre=getPositionX()
		if(getNum()==2 ){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){	
				if(!(positionX_at_pre==getPositionX())){
					throw new PostconditionError(" exists i getEngine()@pre.getPlayer(i) != this "
							+ "&& getCharBox().collidesWith(getPlayer(i).getCharBox)"
							+ "=>getPositionX()@pre=getPositionX()");
				}
			}
		}
		else if(getNum()==1 ){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(charbox_at_pre.collidesWith(getEngine().getChar(2).getCharBox())){	
				if(!(positionX_at_pre==getPositionX())){
					throw new PostconditionError(" exists i getEngine()@pre.getPlayer(i) != this "
							+ "&& getCharBox().collidesWith(getPlayer(i).getCharBox)"
							+ "=>getPositionX()@pre=getPositionX()");
				}
			}
		}

		//post: (getPositionX()@pre>=getEngine.getWidth()-getSpeed() 
		//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getEngine().getWidth()
		if(positionX_at_pre>=getEngine().getWidth()-getSpeed() && 
				getNum()==2){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){	
				if(!(getPositionX()==getEngine().getWidth())){
					throw new PostconditionError("(getPositionX()@pre>=getEngine.getWidth()-getSpeed()"
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getEngine().getWidth()");
				}
			}
		}
		if(positionX_at_pre>=getEngine().getWidth()-getSpeed() && 
				getNum()==1){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(2).getCharBox())){	
				if(!(getPositionX()==getEngine().getWidth())){
					throw new PostconditionError("(getPositionX()@pre>=getEngine.getWidth()-getSpeed()"
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getEngine().getWidth()");
				}
			}
		}
		//post:(getPositionX()@pre<getEngine.getWidth()-getSpeed() 
		//&& (forall i getEngine().getPlayer(i) != this => !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))
		//=>getPositionX()==getPositionX@pre+getSpeed()
		if(positionX_at_pre<getEngine().getWidth()-getSpeed() && 
				getNum()==2){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(1).getCharBox())){
				if(!(getPositionX()==positionX_at_pre+getSpeed())){
					throw new PostconditionError(" (getPositionX()@pre<getEngine.getWidth()-getSpeed()  "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getPositionX@pre+getSpeed()");
				}
			}
		}
		if(positionX_at_pre<getEngine().getWidth()-getSpeed() && 
				getNum()==1){
			charbox_at_pre.MoveTo(positionX_at_pre+getSpeed(),positionY_at_pre);
			if(!charbox_at_pre.collidesWith(getEngine().getChar(2).getCharBox())){
				if(!(getPositionX()==positionX_at_pre+getSpeed())){
					throw new PostconditionError(" (getPositionX()@pre<getEngine.getWidth()-getSpeed()  "
							+ "&& (forall i getEngine().getPlayer(i) != this "
							+ "=> !(getCharBox().collidesWith(getCharBox(),getPlayer(i).getCharBox))))"
							+ "=>getPositionX()==getPositionX@pre+getSpeed()");
				}
			}
		}
		//post:faceRight()==faceRight@pre && getLife()==getLife()@pre
		if(!faceRight()){
			throw new PostconditionError("faceRight()");
		}

		//post:getPositionY()==getPositionY()@pre
		if(!(getPositionY()==positionY_at_pre)){
			throw new PostconditionError("positionY()=positionY@pre");
		}
	}

	public void postStep(int positionX_at_pre, int positionY_at_pre, boolean faceRight_at_pre, Commande c,RectHitbox charbox_at_pre,boolean isTeching_at_pre){
		//post: :!isTeching()=>step(Commande.LEFT)=moveLeft() && step(Commande.LEFT)=>isBlocking()=false
		if(!isTeching_at_pre){
			if(c==Commande.LEFT){
				postMoveLeft(positionX_at_pre, positionY_at_pre, faceRight_at_pre,charbox_at_pre);
				if(!isBlocking()==false){
					throw new PostconditionError("step(Commande.LEFT)=>isBlocking()=false");
				}
			}
		}
		//post:!isTeching()=>step (Commande.RIGHT)=moveRight()  && step(Commande.RIGHT)=>isBlocking()=false
		if(!isTeching_at_pre){
			if(c==Commande.RIGHT){
				postMoveRight(positionX_at_pre, positionY_at_pre, faceRight_at_pre,charbox_at_pre);
				if(!isBlocking()==false){
					throw new PostconditionError("step(Commande.RIGHT)=>isBlocking()=false");
				}

			}
		}
		//post:!isBlockStunned()=>step(Commande.NEUTRAL)=>isBlocking()=false
		if(!isBlockStunned()){
		if(c==Commande.NEUTRAL){
			if(!isBlocking()==false){
				throw new PostconditionError("step(Commande.NEUTRAL)=>isBlocking()=false");

			}
		}
		}

		//post:!isTeching&& !isHitStunned=> step(Commande.GUARD)=>isBlocking()==true
		if(!isTeching_at_pre && !isHitStunned())
		if(c==Commande.GUARD){
			if(!isBlocking()==true){
				throw new PostconditionError("step(Commande.GUARD)=>isBlocking()=true");

			}
		}

		//post:!isTeching@pre=>step(Commande.PUNCH)=startTech()
		if(!isTeching_at_pre){
			if(c==Commande.PUNCH){
				if(!isTeching()){
					throw new PostconditionError("isTeching()");
				}
			}
		}

	}

	@Override
	public void startTech(Tech t){
		//pre inv
		checkInvariant();
		//pre:!isTeching()
		if(isTeching()){
			throw new PreconditionError("!isTeching()");
		}
		//traitement
		super.startTech(t);

		//post:isTeching
		if(!isTeching()){
			throw new PreconditionError("isTeching()");
		}
	}


}
