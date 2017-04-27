package proj.service;

public interface Hitbox {

	int getPositionX();
	int getPositionY();
	boolean belongsTo(int x, int y);
	boolean collidesWith(Hitbox h);
	boolean equalsTo(Hitbox h);
	
	//post: getPositionX()==x
	//post: getPositionY()==y
	void init(int x,int y);
	
	//post: getPositionX()==x
	//post: getPositionY()==y
	//post: forall u,v belongsTo(u,v)=belongsTo(u-(x-getPositionX()), v-(y-getPositionY()))
	void MoveTo(int x,int y);
	

}
