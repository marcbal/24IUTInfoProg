package fr.univ_artois._24_iut_info.game;

public abstract class Player {
	
	

	private static int ID_COUNT = 1;
	
	protected int id;
	
	public Player(){
		this.id = this.ID_COUNT;
		this.ID_COUNT ++ ;
		
		
	}
	
	public abstract void play();

}
