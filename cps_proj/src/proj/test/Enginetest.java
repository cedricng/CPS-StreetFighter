package proj.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import proj.contract.CharacterContract;
import proj.contract.EngineContract;
import proj.contract.PreconditionError;
import proj.impl.CharacterImpl;
import proj.impl.EngineImpl;
import proj.impl.PlayerImpl;
import proj.service.Character;
import proj.service.Engine;

public class Enginetest extends AbstractEngineTest{

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub
		eng=new EngineContract(new EngineImpl());
		p1=new PlayerImpl();
		p2=new PlayerImpl();
		c1=new CharacterImpl();
		c2=new CharacterImpl();
		p1.init(c1);
		p2.init(c2);

	}

}
