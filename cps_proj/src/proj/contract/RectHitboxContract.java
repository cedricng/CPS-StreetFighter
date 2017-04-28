package proj.contract;


import proj.decorators.RectHitboxDecorator;
import proj.service.RectHitbox;

public class RectHitboxContract extends RectHitboxDecorator {

	public RectHitboxContract(RectHitbox delegate) {
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
	/*	 Test du centre 
		if(! belongsTo(getPositionX(), getPositionY()) == belongsTo_centre_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY()))");}
		 Test du centre + 100 
		if(! belongsTo(getPositionX() + 100, getPositionY() + 100) == belongsTo_centre_100_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY())");}
		 Test d�un point absolu 
		if(! belongsTo(300 + (x - PositionX_at_pre), 0 + (y - PositionY_at_pre)) == belongsTo_abs_at_pre)
		{throw new PostconditionError(" forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY()))");}
		*/
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
	
	@Override
	public void init(int x,int y,int h, int w){
		checkInvariant();
		//pre:h>0 && w>0
		if(!(h>0 && w>0)){
			throw new PreconditionError("h>0 && w>0");
		}
		super.init(x, y,h,w);
		checkInvariant();
		//post: getPositionX()==x
		if(! (getPositionX()==x)){
				throw new PostconditionError("getPositionX()==x");
		}
		//post: getPositionY()==y
		if(! (getPositionY()==y)){
			throw new PostconditionError("getPositionY()==y"+"->"+getPositionY()+"<>"+y);
		}
		
		//post: getHeight()==h
		if(! (getHeight()==h)){
			throw new PostconditionError("getHeight()==h");
		}
		//post: getWidth()==w
		if(! (getWidth()==w)){
			throw new PostconditionError("getWidth()==w");
		}
	}
	
	@Override
	public void resize(int h, int w){
		//pre:h>0 && w>0
		if(!(h>0 && w>0)){
			throw new PreconditionError("h>0 && w>0");
		}
		super.resize(h, w);
		checkInvariant();
		
		//post: getHeight()==h
		if(! (getHeight()==h)){
			throw new PostconditionError("getHeight()==h");
		}
		//post: getWidth()==w
		if(! (getWidth()==w)){
			throw new PostconditionError("getWidth()==w");
		}
	}

}
