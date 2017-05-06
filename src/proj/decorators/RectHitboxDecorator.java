package proj.decorators;


import proj.service.Hitbox;
import proj.service.RectHitbox;

public class RectHitboxDecorator extends HitboxDecorator implements RectHitbox {

	public RectHitboxDecorator(Hitbox delegate) {
		super(delegate);
	}

	@Override
	public void init(int x, int y, int h, int w) {
		((RectHitbox)delegate).init(x, y, h, w);
	}

	@Override
	public void resize(int h, int w) {
		((RectHitbox)delegate).resize(h, w);

	}

	@Override
	public int getHeight() {
		return ((RectHitbox)delegate).getHeight();
	}

	@Override
	public int getWidth() {
		return ((RectHitbox)delegate).getWidth();
	}

}
