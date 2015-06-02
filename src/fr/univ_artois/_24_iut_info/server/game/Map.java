package fr.univ_artois._24_iut_info.server.game;

import java.util.Arrays;
import java.util.Random;

public class Map {
	
	
	private int[][] map;
	private int[][] pion;
	
	
	public Map (int ligne, int colonne, int maxValue) {
		
		map = new int[ligne][colonne];
		pion = new int[ligne+1][colonne+1];

		for (int[] l : pion)
			Arrays.fill(l, 0);
		
		Random r = new Random();
		for (int[] l : map) {
			
			for (int i = 0; i < l.length; i++)
				l[i] = r.nextInt(maxValue+1);
			
		}
		
		
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * @param ligne ligne du tableau pion
	 * @param colonne colonne du tableau pion
	 * @param id id du joueur
	 */
	public void setPion(int ligne,int colonne,int id){
		if (id < 0)
			throw new IllegalArgumentException("id négatif");
		pion[ligne][colonne] = id;
	}
	

	/**
	 * 
	 * @param ligne ligne du tableau pion
	 * @param colonne colonne du tableau pion
	 * @return l'id du joueur, ou 0 si il n'y a aucun pion
	 */
	public int getPion(int ligne,int colonne){
		try {
			return pion[ligne][colonne];
		} catch(ArrayIndexOutOfBoundsException e) {
			return -1;
		}
	}
	
	
	
	
	public boolean containEmptyPion() {
		
		
		
		return true;
	}
	
	
	
	public int getScoreForPlayer(int id) {
		int score = 0;
		
		for (int i = 0; i<map.length; i++) {
			for (int j = 0; j<map[i].length; j++) {
				
				int count = 0;

				count += (pion[i][j] == 1)?-1:(pion[i][j] == 2)?1:0;
				count += (pion[i+1][j] == 1)?-1:(pion[i+1][j] == 2)?1:0;
				count += (pion[i][j+1] == 1)?-1:(pion[i][j+1] == 2)?1:0;
				count += (pion[i+1][j+1] == 1)?-1:(pion[i+1][j+1] == 2)?1:0;
				
				
				if ((id == 1 && count < 0) || (id == 2 && count > 0))
					score += map[i][j];
				
			}
		}
		
		
		
		return score;
	}
	
	
	
	
	public String toPlayerString() {
		String ret = "";
		
		boolean premLigne = true;
		for (int[] l : map) {
			if (!premLigne) ret+="|";
			premLigne = false;
			boolean premC = true;
			for (int c : l) {
				if (!premC) ret += ":";
				premC = false;
				ret+=c;
			}
			
		}
		
		
		return ret;
	}
	
	
	
	

	
	@Override
	public String toString(){
		
		String out = "";
		
		for (int i = 0; i< map.length; i++)
		{
			// ligne de pion au dessus des cases de la ligne courante :
			boolean premier = true;
			for (int c : pion[i]) { // 179 │  196 ─
				if (!premier)
					out += "──";
				premier = false;
				out += (c == 0) ? " " : c;
			}
			out += "\n";

			premier = true;
			for (int p : map[i]) {
				if (premier)
					out += "|";
				premier = false;
				
				String nbAff = (p<10) ? " "+p : ""+p;
				
				out += nbAff+"|";
			}
			out += "\n";
			
		}
		
		boolean premier = true;
		for (int c : pion[pion.length-1]) { // 179 │  196 ─
			if (!premier)
				out += "──";
			premier = false;
			out += (c == 0) ? " " : c;
		}
		
		return out;
	}






}
