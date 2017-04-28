package proj.impl;

import java.awt.Rectangle;

import proj.service.Hitbox;
import proj.service.RectHitbox;

public class RectHitboxImpl implements RectHitbox {

	Rectangle r;

	@Override
	public int getPositionX() {
		return r.x;
	}

	@Override
	public int getPositionY() {
		
		return -r.y+getHeight();
	}

	@Override
	public boolean belongsTo(int x, int y) {
		return r.contains(x,y);
	}

	@Override
	public boolean collidesWith(Hitbox h) {

		RectHitbox rh=(RectHitbox)h;

		return !(getPositionX()+getWidth()<rh.getPositionX() 
				||rh.getPositionX()+rh.getWidth()<getPositionX() 
				|| getPositionY()+getWidth()<rh.getPositionY()
				||rh.getPositionY()+rh.getWidth()<getPositionY())
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
		r=new Rectangle(x,y,10,10);

	}

	@Override
	public void MoveTo(int x, int y) {
		r.setLocation(x, getHeight()-y);
	}

	@Override
	public int getHeight() {
		return r.height;
	}

	@Override
	public int getWidth() {
		return r.width;
	}

	@Override
	public void init(int x, int y, int h, int w) {
		r=new Rectangle(x,h-y,w,h);
	}

	@Override
	public void resize(int h, int w) {
		System.out.println(getPositionY());
		System.out.println(r.y);
		
		r.setSize(w, h);
		System.out.println(getPositionY());
		System.out.println(r.y);



	}

}
