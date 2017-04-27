package proj.impl;

import proj.contract.RectHitboxContract;
import proj.service.Hitbox;
import proj.service.RectHitbox;
import proj.service.Tech;

public class Punch implements Tech {
	private int damage=15;
	private int hstun=2;
	private int bstun=1;
	private int hframe=1;
	private int sframe=1;
	private int rframe=1;
	private int h=20;
	private int w=30;
	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public int getHstun() {
		return hstun;
	}

	@Override
	public int getBstun() {
		return bstun;
	}

	@Override
	public int getHframe() {
		return hframe;
	}

	@Override
	public int getSframe() {
		return sframe;
	}

	@Override
	public int getRFrame() {
		return rframe;
	}

	@Override
	public Hitbox getHitbox(int x, int y) {
		RectHitbox rh=new RectHitboxContract(new RectHitboxImpl());
		rh.init(x, y, h, w);
		return rh;
	}

}
