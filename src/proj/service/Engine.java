package proj.service;

public interface Engine {
	int getHeight();
	int getWidth();
	
	//pre: i in{1,2}
	Character getChar(int i);
	
	//pre: i in{1,2}
	Player getPlayer(int i);
	
	boolean isGameOver();
	
	//inv: isGameOver()= exist i in 1,2 getPlayer(i).isDead()
	
	//pre:h>0 && w>0 && s>0 && w>s && p1!=p2
	//post:getHeight()==h
	//post:getWidth()==w
	//post:getPlayer(1)==p1
	//post:getPlayer(2)==p2
	//post:getChar(1).getPositionX()=w/2-s/2
	//post:getChar(2).getPositionX()=w/2+s/2
	//post:getChar(1).getPositionY()=0
	//post:getChar(2).getPositionY()=0
	//post:getChar(1).faceRight()
	//post:!getChar(2).faceRight()
	void init(int h,int w,int s,Player p1,Player p2 );
	
	//pre:!isGameOver();
	//post:step(c1,c2)=getChar(1).step(c1)
	//post:step(c1,c2)=getChar(2).step(c2)
	void step(Commande c1,Commande c2);

	
	
	

}
