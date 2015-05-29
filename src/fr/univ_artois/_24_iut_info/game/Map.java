package fr.univ_artois._24_iut_info.game;

import java.util.ArrayList;
import java.util.List;

public class Map implements Cloneable{
	private List<List<Integer>> map=new ArrayList<List<Integer>>();
	private byte[][] pion;
	Map(){
		
	}
	
	
	public void decode(String str){
		String[] lignes=str.split("|");
		
		for(String ligne : lignes){
			String[] cases = ligne.split(":");
			List<Integer> list=new ArrayList<Integer>();
			map.add(list);
			for(String cas:cases){
				list.add(Integer.parseInt(cas)); 
			}
		}
		
		pion=new byte[map.size()+1][map.get(0).size()+1];
		for(int i=0;i<this.pion.length;i++){
			for(int j=0;j<this.pion[0].length;j++){
				pion[i][j]=0;
			}
		}
		
	}
	/**
	 * 
	 * @param ligne ligne du tableau principal
	 * @param colonne colonne du tableau principal
	 * @param coin coin de la case
	 * @param id id du joueur 
	 */
	public void poser(int ligne,char colonne,int coin, int id){
		colonne=Character.toUpperCase(colonne);
		
		int col=colonne-'A';
		
		if(coin==1){
			poser(ligne-1,col,id);
		}else if(coin==2){
			poser(ligne-1,col+1,id);
		}else if(coin==3){
			poser(ligne,col+1,id);
		}
		else{
			poser(ligne,col,id);
		}
		
	
	}
	/**
	 * 
	 * @param ligne ligne du tableau pion
	 * @param colonne colonne du tableau pion
	 * @param id id du joueur
	 */
	public void poser(int ligne,int colonne,int id){
		pion[ligne][colonne]=(byte)id;
	}
	
	
	public boolean canPose(int x,int y){
		return pion[x][y]==0;
	}
	public byte whoIsTheCase(int x,int y){
		return 0;
	}
	@Override
	public Map clone(){
		Map m = new Map();
	    
		m.map=new ArrayList<List<Integer>>();
		for(List<Integer> i:this.map){
			m.map.add(new ArrayList<Integer>(i));
		
		}
		
		m.pion=new byte[map.size()+1][map.get(0).size()+1];
		for(int i=0;i<m.pion.length;i++){
			for(int j=0;j<m.pion[0].length;j++){
				m.pion[i][j]=this.pion[i][j];
			}
		}
		
		// on renvoie le clone
	    return m;
	}
	
	
}
