package proj.impl;



import proj.service.Hitbox;
import proj.service.RectHitbox;

public class RectHitboxImpl implements RectHitbox {

	
	private int height,width,x,y;

	@Override
	public int getPositionX() {
		return x;
	}

	@Override
	public int getPositionY() {
	

		return y;
	}

	@Override
	public boolean belongsTo(int x, int y) {
		return x>=getPositionX() && x<=getPositionX()+getWidth() && y>=getPositionY() && y<=getPositionY()+getHeight();
	}

	@Override
	public boolean collidesWith(Hitbox h) {

		RectHitbox rh=(RectHitbox)h;

		return !(getPositionX()+getWidth()<rh.getPositionX() 
				||rh.getPositionX()+rh.getWidth()<getPositionX() 
				|| getPositionY()+getHeight()<rh.getPositionY()
				||rh.getPositionY()+rh.getHeight()<getPositionY())
				;

	}

	@Override
	public boolean equalsTo(Hitbox h) {
		if(h instanceof RectHitbox){
			RectHitbox rh=(RectHitbox)h;
			return(getHeight()==rh.getHeight() 
					&& getWidth()==rh.getWidth() 
					&& getPositionX()==rh.getPositionX() 
					&& getPositionY()==rh.getPositionY());
		}
		return false;
	}

	@Override
	public void init(int x, int y) {
		
		
		height=10;
		width=10;
		this.x=x;
		this.y=y;
	}

	@Override
	public void MoveTo(int x, int y) {
		
		this.x=x;
		this.y=y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void init(int x, int y, int h, int w) {
		
		height=h;
		width=w;
		this.x=x;
		this.y=y;
	}

	@Override
	public void resize(int h, int w) {
		/*System.out.println(getPositionY());
		System.out.println(r.y);
		
		r.setSize(w, h);
		//r.setLocation(r.x,r.y-h);
		System.out.println(getPositionY());
		System.out.println(r.y);*/
		
		height=h;
		width=w;



	}

}
