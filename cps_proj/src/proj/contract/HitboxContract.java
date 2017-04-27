package proj.contract;

import proj.decorators.HitboxDecorator;
import proj.service.Hitbox;

public class HitboxContract extends HitboxDecorator {
	public HitboxContract(Hitbox delegate) {
		super(delegate);
		
	}
	void checkInvariant(){

	}
	@Override
	public void MoveTo(int x, int y){
		checkInvariant();
		/* Capture du centre */
		boolean belongsTo_centre_at_pre = belongsTo(getPositionX(), getPositionY());
		/* Capture du centre + 100 */
		boolean belongsTo_centre_100_at_pre = belongsTo(getPositionX() + 100, getPositionY() + 100);
		/* Capture d�un point absolu */
		int PositionX_at_pre = getPositionX();
		int PositionY_at_pre = getPositionY();
		boolean belongsTo_abs_at_pre = belongsTo(300, 0);
		super.MoveTo(x,y);
		checkInvariant();
		/* Test du centre */
		if(! belongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY()))");}
		/* Test du centre + 100 */
		if(! belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsTo_centre_100_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY())");}
		/* Test d�un point absolu */
		if(! belongsTo(300 + (x - PositionX_at_pre), 0 + (y - PositionY_at_pre)) == belongsTo_abs_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY()))");}
		
		//post: getPositionX()==x
		if(! (getPositionX()==x)){
			throw new PostconditionError("getPositionX()==x");
		}
		//post: getPositionY()==y
		if(! (getPositionY()==y)){
			throw new PostconditionError("getPositionY()==y");
		}

	}
	
	@Override
	public void init(int x,int y){
		checkInvariant();
		super.init(x, y);
		checkInvariant();
		
		//post: getPositionX()==x
		if(! (getPositionX()==x)){
			throw new PostconditionError("getPositionX()==x");
		}
		//post: getPositionY()==y
		if(! (getPositionY()==y)){
			throw new PostconditionError("getPositionY()==y");
		}
	}
}
