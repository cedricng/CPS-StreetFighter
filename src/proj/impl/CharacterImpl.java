package proj.impl;


import proj.contract.RectHitboxContract;
import proj.service.Character;
import proj.service.Commande;
import proj.service.Engine;
import proj.service.Hitbox;
import proj.service.RectHitbox;
import proj.service.Tech;

public class CharacterImpl implements Character {
	private Character opp;
	private int life;
	private int speed;
	private boolean face_right;
	private int num;
	private boolean block;

	private Engine eng;
	private RectHitbox hb;
	private boolean teching;
	private int sframe;
	private int hframe;
	private int rframe;
	private Tech tech;
	private RectHitbox th=null;
	private boolean hashit;
	private int tstun;
	private boolean bstun;
	private boolean hstun;
	private int init_height;
	private boolean down;
	@Override
	public int getPositionX() {

		return hb.getPositionX();
	}

	@Override
	public int getPositionY() {

		return hb.getPositionY();
	}

	@Override
	public Engine getEngine() {

		return eng;
	}

	@Override
	public Hitbox getCharBox() {

		return hb;
	}

	@Override
	public int getLife() {

		return life;
	}

	@Override
	public int getSpeed() {

		return speed;
	}

	@Override
	public boolean faceRight() {

		return face_right;
	}

	@Override
	public boolean isDead() {

		return life<=0;
	}

	@Override
	public void init(int l, int s, boolean f, Engine e,int x,int y) {

		life=l;
		speed=s;
		face_right=f;
		eng=e;
		block=false;
		teching=false;
		hashit=false;
		hb=new RectHitboxContract(new RectHitboxImpl());
		hb.init(x, y,100,40);
		sframe=0;
		rframe=0;
		hframe=0;
		tstun=0;
		init_height=100;
		down=false;
		if(f){
			opp=e.getChar(2);
			num=1;
		}
		else{
			opp=e.getChar(1);
			num=2;
		}
	}

	@Override
	public void moveLeft() {
		int orig_positionX=getPositionX();

		if(getPositionX()<=getSpeed() && opponent()!=null){

			getCharBox().MoveTo(0, getPositionY()); //on deplace
			if((getCharBox().collidesWith(opponent().getCharBox()))){
				getCharBox().MoveTo(orig_positionX, getPositionY());//si il ya collision, on remet à la place d'origine
			}
		}


		else if( getPositionX()>getSpeed() && opponent()!=null){

			getCharBox().MoveTo(getPositionX()-getSpeed(), getPositionY());
			if((getCharBox().collidesWith(opponent().getCharBox()))){
				getCharBox().MoveTo(orig_positionX, getPositionY());
			}

		}

		if(faceRight()){
			switchSide();
		}

	}

	@Override
	public void moveRight() {

		int positionX_at_pre=getPositionX();

		if(positionX_at_pre>=getEngine().getWidth()-getSpeed() 
				&& opponent()!=null){

			getCharBox().MoveTo(getEngine().getWidth(), getPositionY());
			if((getCharBox().collidesWith(opponent().getCharBox()))){
				getCharBox().MoveTo(positionX_at_pre, getPositionY());
			}
		}

		else if(positionX_at_pre<getEngine().getWidth()-getSpeed() && opponent()!=null){
			System.out.println("getchar1<>null");
			getCharBox().MoveTo(getPositionX()+getSpeed(), getPositionY());

			if(getCharBox().collidesWith(opponent().getCharBox())){
				getCharBox().MoveTo(positionX_at_pre, getPositionY());
			}

		}

		if(!faceRight()){
			switchSide();
		}
	}

	@Override
	public void switchSide() {
		face_right=!face_right;

	}

	@Override
	public void step(Commande c) {
		if(tstun>0){					
			System.out.println("tsun");

			tstun--;
			if(tstun==0){
				bstun=false;
				hstun=false;
			}
		}
		else{
			
			if(isTeching()){
				System.out.println("isTeching");
				if(sframe>0){
					sframe--;
				}
				else if(hframe>0){
					hframe--;
					if(th==null){
						if(faceRight()){
						th=(RectHitbox)tech.getHitbox(getPositionX()+getWidth(), getPositionY()+2*(getHeight()/3)/*+(getHeight()/2)*/);
						}
						else{
							System.out.println("chimpl:y "+getPositionY()+(getHeight()/2));
						th=(RectHitbox)tech.getHitbox(getPositionX()-30,getPositionY()+2*(getHeight()/3)/*+(getHeight()/2)*/ );
						}
					}
					if(!techHasAlreadyHit()){
						hitTech();
					}

				}
				else if(rframe>0){
					rframe--;
				}
				else{
					teching=false;
					th=null;
					tech=null;
					hashit=false;
				}
			}
			else{
				switch(c){
				case LEFT:
					moveLeft();
					block=false;
					break;
				case RIGHT:
					moveRight();
					block=false;
					break;
				case NEUTRAL:
					block=false;
					break;
				case GUARD:
					block=true;
					System.out.println("charimpl:guard");
					break;
				case PUNCH:
					System.out.println("case punch");
					startTech(new Punch());
					
					break;
				case DOWN:
					hb.resize(init_height/2, getWidth());
					down=true;
					break;
				case UP:
					hb.resize(init_height, getWidth());
					down=false;
				default:
					break;
				}
			}
		}
	}
	Character opponent(){
		return opp;
	}

	@Override
	public void setLife(int lif) {
		this.life=lif;

	}

	@Override
	public int getNum() {
		return num;
	}

	@Override
	public boolean isBlocking() {
		return block;
	}

	@Override
	public int getHeight() {
		return hb.getHeight();
	}

	@Override
	public int getWidth() {
		return hb.getWidth();
	}

	@Override
	public boolean isTeching() {

		return teching;
	}



	@Override
	public void startTech(Tech t) {
		teching=true;
		tech=t;
		sframe=t.getSframe();
		hframe=t.getHframe();
		rframe=t.getRFrame();
		sframe--;
	}

	@Override
	public Tech getTech() {

		return tech;
	}

	public void hitTech(){
		if(th.collidesWith(opponent().getCharBox())){
			if(!opponent().isBlocking()){
				opponent().setLife(opponent().getLife()-tech.getDamage());
				opponent().stun(tech.getHstun(),false);
				hashit=true;
				
			}
			else{
				opponent().stun(tech.getBstun(),true);
				hashit=true;
				
			}
		}
	}

	@Override
	public boolean techHasAlreadyHit() {
		return hashit;
	}
	@Override
	public void stun(int x,boolean blocking){
		tstun=x;
		if(blocking){
			bstun=true;
			block=true;
		}
		else{
			hstun=true;
			
		}
	}

	@Override
	public Hitbox getTechHitbox() {
		return th;
	}

	@Override
	public boolean isBlockStunned() {
		return bstun;
	}

	@Override
	public boolean isHitStunned() {
		return hstun;
	}

	@Override
	public int getInitHeight() {
		return init_height;
	}

	@Override
	public boolean isDown() {
		return down;
	}

	@Override
	public int startFrameCounter() {
		return sframe;
	}

	@Override
	public int hitFrameCounter() {
		return hframe;
	}

	@Override
	public int recoveryFrameCounter() {
		return rframe;
	}

	@Override
	public int stunCounter() {
		return tstun;
	}

}
