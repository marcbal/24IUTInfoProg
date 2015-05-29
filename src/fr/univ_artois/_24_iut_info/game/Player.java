package fr.univ_artois._24_iut_info.game;

public abstract class Player {
	
	

	private static int ID_COUNT = 1;
	
	protected int id;
	
	private int nbTwist;
	
	private Game game;
	
	
	
	public Player(int nbTwist, Game game){
		this.id = this.ID_COUNT;
		this.ID_COUNT ++ ;
		
		this.nbTwist = nbTwist;
		
		
	}
	
	
	private void sendTurn(int x, int y){
		
	}
	
	public int getNbTwist(){
		return this.nbTwist;
	}
	
	public abstract void play();

}
