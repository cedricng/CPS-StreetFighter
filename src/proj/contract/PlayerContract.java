package proj.contract;

import proj.decorators.PlayerDecorator;
import proj.service.Player;
import proj.service.Character;

public class PlayerContract extends PlayerDecorator {

	public PlayerContract(Player delegate) {
		super(delegate);
		// TODO Auto-generated constructor stub
	}
	void checkInvariant(){
		
	}
	@Override
	public void init(Character c){
		super.init(c);
		//postinv
		checkInvariant();
	}

}
