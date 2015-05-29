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
			if(ligne.isEmpty()){
				continue;
			}
			
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
	
	public int nbPion(){
		int value=0;
		for(byte[] ligne:this.pion){
			for(byte b:ligne){
				value+=b;
			}
		}
		return value;
	}
	public int getPoint(int ligne,char colonne){
		colonne=Character.toUpperCase(colonne);
		int col=colonne-'A';
		return getPoint(ligne,col);
	}
	public int getPoint(int ligne,int colonne){
		return map.get(ligne).get(colonne);
	}
	public boolean canPose(int x,int y){
		return pion[x][y]==0;
	}
	public int whoIsTheCase(int x,int y){
		int value=0;
		if(pion[x][y]==1){
			value-=1;
		}else if(pion[x][y]==2){
			value+=1;
		}
		
		
		if(pion[x+1][y]==1){
			value-=1;
		}else if(pion[x+1][y]==2){
			value+=1;
		}
		
		
		if(pion[x][y+1]==1){
			value-=1;
		}else if(pion[x][y+1]==2){
			value+=1;
		}
		
		if(pion[x+1][y+1]==1){
			value-=1;
		}else if(pion[x+1][y+1]==2){
			value+=1;
		}
		
		return value==0?0:(value<0?1:2);
	}
	
	public int getTotalPointOfPlayer(int id){
		int totalPoint=0;
		for(int i=0;i<map.size();i++){
			for(int j=0;j<map.get(0).size();j++){
				if(this.whoIsTheCase(i,j)==id){
					totalPoint+=getPoint(i,j);
				}
			}
		}
		return totalPoint;
	}
	
	public int getLigne(){
		return map.size();
	}
	public int getColonne(){
		return map.get(0).size();
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
