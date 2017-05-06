package proj.service;

public interface RectHitbox extends /*include*/ Hitbox{
	
	int getHeight();
	int getWidth();
	//pre:h>0 && w>0
	
	//post: getPositionX()==x
	//post: getPositionY()==y
	//post: getHeight()==h
	//post: getWidth()==h
	void init(int x,int y,int h, int w);
	
	//pre:h>0 && w>0
	//post: getHeight()==h
	//post: getWidth()==h
	void resize(int h, int w);
	
	
}
