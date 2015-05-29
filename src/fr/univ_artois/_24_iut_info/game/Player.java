package fr.univ_artois._24_iut_info.game;

public abstract class Player {
	
	protected int id;
	
	protected int nbTwist;
	
	protected Game game;
	
	
	public Player(int nbTwist, Game game, int id){
		this.id = id;
		
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
		return (id == 1)?"Rouge":"Vert";
	}
	
	public abstract void play();


	public int getId() {
		return this.id;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
