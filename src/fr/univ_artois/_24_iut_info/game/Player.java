package fr.univ_artois._24_iut_info.game;

public abstract class Player {
	
	

	private static int ID_COUNT = 1;
	
	protected int id;
	
	protected int nbTwist;
	
	protected Game game;
	
	private String couleur;
	
	
	@SuppressWarnings("static-access")
	public Player(int nbTwist, Game game){
		this.id = this.ID_COUNT;
		this.ID_COUNT ++ ;
		
		this.nbTwist = nbTwist;
		
		
	}
	
	
	protected void sendTurn(int x, int y){
		
		if(game.getMap().canPose(x, y)){
			game.getMap().poser(x, y, this.id);
			//game.getConnection().send(); //TODO
		}
		else{
			//gestion erreur
		}
		
		this.nbTwist--;
		
	}
	
	public int getNbTwist(){
		return this.nbTwist;
	}
	

	public String getCouleur(){
		return this.couleur;
	}
	
	public abstract void play();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
