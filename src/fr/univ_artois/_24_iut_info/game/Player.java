package fr.univ_artois._24_iut_info.game;

import java.io.IOException;

public abstract class Player {
	
	protected int id;
	
	protected int nbTwist;
	
	protected Game game;
	
	public boolean isEnnemy = false;
	
	
	public Player(int nbTwist, Game game, int id){
		this.id = id;
		
		this.game = game;
		
		this.nbTwist = nbTwist;
		
		
	}
	
	
	protected boolean trySendTurn(int ligne, int colonne){
		
		if(!game.getMap().canPose(ligne, colonne))
			return false;
		
		
		game.getMap().poser(ligne, colonne, this.id);
		
		int width = game.getMap().getColonne();
		int height = game.getMap().getLigne();
		
		boolean haut = (ligne < height);
		boolean gauche = (colonne < width);
		
		int coin = (haut && gauche) ? 1 :
			(haut && !gauche) ? 2 :
				(!haut && !gauche) ? 3 : 4;
		
		if (!haut)
			ligne--;
		if (!gauche)
			colonne--;
			
		try {
			if (!(this instanceof EnemyPlayer))
				game.getConnection().sendTwistLock(ligne+1, (char) ('A'+colonne), coin);
			this.nbTwist--;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return true;
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
