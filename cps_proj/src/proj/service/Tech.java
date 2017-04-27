package proj.service;

public interface Tech {
	int getDamage();
	int getHstun();
	int getBstun();
	int getHframe();
	int getSframe();
	int getRFrame();
	Hitbox getHitbox(int x,int y);
	
}
