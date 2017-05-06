package proj.contract;

import proj.decorators.EngineDecorator;
import proj.impl.RectHitboxImpl;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Character;
import proj.service.Player;
import proj.service.RectHitbox;

public class EngineContract extends EngineDecorator {

	
	public EngineContract(Engine delegate) {
		super(delegate);

	}
	void checkInvariant(){
		//inv: isGameOver()= exist i in 1,2 getPlayer(i).isDead()
		if(!( (isGameOver()) == (getChar(1).isDead() || getChar(2).isDead()) )){
			throw new InvariantError("isGameOver()= exist i in 1,2 getPlayer(i).isDead()");
		}
	}

	public void init(int h,int w,int s,Player p1, Player p2){


		//pre:h>0 && w>0 && s>0 && w>s && p1!=p2
		if(!(h>0 && w>0 && s>0 && w>s && p1!=p2)){
			throw new PreconditionError("h>0 && w>0 && s>0 && w>s && p1!=p2");
		}


		//traitement
		super.init(h,w, s, p1, p2);

		//post-inv
		checkInvariant();

		//post:getHeight()==h
		if(!(getHeight()==h)){
			throw new PostconditionError("getHeight()==h");
		}
		//post:getWidth()==w
		if(!(getWidth()==w)){
			throw new PostconditionError("getWidth()==w");
		}

		//post:getPlayer(1)==p1
		if(!(getPlayer(1)==p1)){
			throw new PostconditionError("getChar(1)==p1");
		}
		//post:getPlayer(2)==p2
		if(!(getPlayer(2)==p2)){
			throw new PostconditionError("getChar(2)==p2");
		}
		//post:getChar(1).getPositionX()=w/2-s/2
		if(!(getChar(1).getPositionX()==w/2-s/2)){
			throw new PostconditionError("getChar(1).getPositionX()=w/2-s/2");
		}
		//post:getChar(2).getPositionX()=w/2+s/2
		if(!(getChar(2).getPositionX()==w/2+s/2)){
			throw new PostconditionError("getChar(2).getPositionX()=w/2+s/2");
		}
		//post:getChar(1).getPositionY()==0
		if(!(getChar(1).getPositionY()==0)){
			throw new PostconditionError("getChar(1).getPositionY()==0"+"->"+getChar(1).getPositionY());
		}
		//post:getChar(2).getPositionY()==0
		if(!(getChar(2).getPositionY()==0)){
			throw new PostconditionError("getChar(2).getPositionY()==1");
		}
		//post:getChar(1).faceRight()
		if(!(getChar(1).faceRight())){
			throw new PostconditionError("getChar(1).faceRight()");
		}
		//post:!getChar(2).faceRight()
		if(getChar(2).faceRight()){
			throw new PostconditionError("!getChar(2).faceRight()");
		}
		
	}
	
	public void step(Commande c1, Commande c2){
		//pre-inv
		checkInvariant();
		
		//pre:!isGameOver();
		if(isGameOver()){
			throw new PreconditionError("!isGameOver()");
		}
		
		//captures
		int positionX1_at_pre=getChar(1).getPositionX();
		int positionY1_at_pre=getChar(1).getPositionY();
		boolean faceRight1_at_pre=getChar(1).faceRight();
		int positionX2_at_pre=getChar(2).getPositionX();
		int positionY2_at_pre=getChar(2).getPositionY();
		boolean faceRight2_at_pre=getChar(2).faceRight();
		RectHitbox charbox_at_pre1=new RectHitboxContract(new RectHitboxImpl());
		charbox_at_pre1.init(getChar(1).getCharBox().getPositionX(), 
				getChar(1).getCharBox().getPositionY(), 
				((RectHitbox)getChar(1).getCharBox()).getHeight(), ((RectHitbox)getChar(1).getCharBox()).getWidth());
		RectHitbox charbox_at_pre2=new RectHitboxContract(new RectHitboxImpl());
		charbox_at_pre2.init(getChar(2).getCharBox().getPositionX(), 
				getChar(2).getCharBox().getPositionY(), 
				((RectHitbox)getChar(2).getCharBox()).getHeight(), ((RectHitbox)getChar(2).getCharBox()).getWidth());
		boolean isTeching_at_pre1=getChar(1).isTeching();
		boolean isTeching_at_pre2=getChar(2).isTeching();
		boolean isHitStunned_at_pre1=getChar(1).isHitStunned();
		boolean isHitStunned_at_pre2=getChar(2).isHitStunned();
		boolean isBlockStunned_at_pre1=getChar(1).isBlockStunned();
		boolean isBlockStunned_at_pre2=getChar(2).isBlockStunned();
		int getHeight_at_pre1=getChar(1).getHeight();
		int getHeight_at_pre2=getChar(2).getHeight();
		int getInitHeight_at_pre1=getChar(1).getInitHeight();
		int getInitHeight_at_pre2=getChar(2).getInitHeight();
		boolean isDown_at_pre1=getChar(1).isDown();
		boolean isDown_at_pre2=getChar(2).isDown();
		int startFrameCounter_at_pre1=getChar(1).startFrameCounter();
		int hitFrameCounter_at_pre1=getChar(1).hitFrameCounter();
		int recoveryFrameCounter_at_pre1=getChar(1).recoveryFrameCounter();
		int startFrameCounter_at_pre2=getChar(2).startFrameCounter();
		int hitFrameCounter_at_pre2=getChar(2).hitFrameCounter();
		int recoveryFrameCounter_at_pre2=getChar(2).recoveryFrameCounter();
		super.step(c1, c2);
		
		//post-inv
		checkInvariant();
		

		
		//post:step(c1,c2)=getChar(1).step(c1)
		
		CharacterContract cc= new CharacterContract(getChar(1));
		
		cc.postStep(positionX1_at_pre,
				positionY1_at_pre,
				faceRight1_at_pre,
				c1,charbox_at_pre1,
				isTeching_at_pre1,
				isHitStunned_at_pre1,
				isBlockStunned_at_pre1,
				getHeight_at_pre1,
				getInitHeight_at_pre1,isDown_at_pre1,startFrameCounter_at_pre1,
				hitFrameCounter_at_pre1,recoveryFrameCounter_at_pre1);
		
		//post:step(c1,c2)=getChar(2).step(c2)
		cc=new CharacterContract(getChar(2));
		cc.postStep(positionX2_at_pre, 
				positionY2_at_pre, 
				faceRight2_at_pre, c2,
				charbox_at_pre2,
				isTeching_at_pre2,
				isHitStunned_at_pre2,
				isBlockStunned_at_pre2,
				getHeight_at_pre2,
				getInitHeight_at_pre2,
				isDown_at_pre2,startFrameCounter_at_pre2,
				hitFrameCounter_at_pre2,recoveryFrameCounter_at_pre2);
		
		
	}





}
