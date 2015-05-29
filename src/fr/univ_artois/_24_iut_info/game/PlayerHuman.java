package fr.univ_artois._24_iut_info.game;

public class PlayerHuman extends Player {

	

	
	public PlayerHuman(int nbTwist, Game game, int id) {
		super(nbTwist, game, id);
	}

	@Override
	public void play() {
		System.out.println("10-A vous de jouer ("+this.id+")");
		

	}

}
