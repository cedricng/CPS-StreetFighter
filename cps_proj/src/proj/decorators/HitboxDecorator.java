package proj.decorators;

import proj.service.Hitbox;

public class HitboxDecorator implements Hitbox {
	protected Hitbox delegate;

	public HitboxDecorator(Hitbox delegate) {
		
		this.delegate = delegate;
	}

	@Override
	public int getPositionX() {
		return delegate.getPositionX();
	}

	@Override
	public int getPositionY() {
		return delegate.getPositionY();
	}

	@Override
	public boolean belongsTo(int x, int y) {
		return delegate.belongsTo(x, y);
	}

	@Override
	public boolean collidesWith(Hitbox h) {
		return delegate.collidesWith(h);
	}

	@Override
	public boolean equalsTo(Hitbox h) {
		return delegate.equalsTo(h);
	}

	@Override
	public void init(int x, int y) {
		delegate.init(x, y);
	}

	@Override
	public void MoveTo(int x, int y) {
		delegate.MoveTo(x, y);
	}

}
