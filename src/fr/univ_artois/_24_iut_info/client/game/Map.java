package fr.univ_artois._24_iut_info.client.game;

import java.util.ArrayList;
import java.util.List;

public class Map implements Cloneable{
	private List<List<Integer>> map=new ArrayList<List<Integer>>();
	private int[][] pion;
	public Map(){
		
	}
	
	
	public void decode(String str){
		String[] lignes=str.split("\\|");
		
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
		
		pion=new int[map.size()+1][map.get(0).size()+1];
		for(int i=0;i<this.pion.length;i++){
			for(int j=0;j<this.pion[0].length;j++){
				pion[i][j]=0;
			}
		}
		
	}
	/**
	 * 
	 * @param ligne ligne du tableau pion
	 * @param colonne colonne du tableau pion
	 * @param id id du joueur
	 */
	public void poser(int ligne,int colonne,int id){
		pion[ligne][colonne] = id;
	}
	
	public int nbPion(){
		int value=0;
		for(int[] ligne:this.pion){
			for(int b:ligne){
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
	
	
	
	public boolean canPose(int ligne,int colonne){
		try {
			return pion[ligne][colonne]==0;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	
	public int whoIsTheCase(int ligne,int colonne){
		int value=0;
		
		//System.out.println("valeur pion : " + ligne + " : " + colonne  + " : "+ pion[ligne][colonne]);
		if(pion[ligne][colonne]==1){
			value-=1;
		}else if(pion[ligne][colonne]==2){
			value+=1;
		}
		
		
		if(pion[ligne+1][colonne]==1){
			value-=1;
		}else if(pion[ligne+1][colonne]==2){
			value+=1;
		}
		
		
		if(pion[ligne][colonne+1]==1){
			value-=1;
		}else if(pion[ligne][colonne+1]==2){
			value+=1;
		}
		
		if(pion[ligne+1][colonne+1]==1){
			value-=1;
		}else if(pion[ligne+1][colonne+1]==2){
			value+=1;
		}
		
		//System.out.println("value : " + value);
		return (value==0)?0:value<0?1:2;
	}
	
	public int getTotalPointOfPlayer(int id){
		int totalPoint=0;
		for(int i=0;i<map.size();i++){
			for(int j=0;j<map.get(0).size();j++){
				if(this.whoIsTheCase(i,j)==id){
					//System.out.println("je suis a : " + this.whoIsTheCase(i,j));
					totalPoint+=getPoint(i,j);
				}
			}
		}
		//System.out.println(("total point : " + totalPoint));
		return totalPoint;
	}
	
	public int getLigne(){
		return map.size();
	}
	public int getColonne(){
		return map.get(0).size();
	}
	public boolean isLocked(int x,int y, byte id){
		int value=0;
		if(pion[x][y]==id){
			value+=1;
		}
		if(pion[x+1][y]==id){
			value+=1;
		}
		if(pion[x][y+1]==id){
			value+=1;
		}
		
		if(pion[x+1][y+1]==id){
			value+=1;
		}
		return value>=3;
	}
	
	@Override
	public Map clone(){
		Map m = new Map();
	    
		m.map=this.map;
		
		m.pion=new int[map.size()+1][map.get(0).size()+1];
		for(int i=0;i<m.pion.length;i++){
			for(int j=0;j<m.pion[0].length;j++){
				//System.out.println("Clone : x:" + i + " : y:" + j + " " + this.pion[i][j]);
				m.pion[i][j]=this.pion[i][j];
			}
		}
		
		// on renvoie le clone
	    return m;
	}
	

	
	@Override
	public String toString(){
		
		String out = "";
		
		for (int i = 0; i< map.size(); i++)
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
			for (int p : map.get(i)) {
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
		
		
		
		


	public int [] getFormat(int x, int y, byte p){
		int [] format = {0,0};
		byte e= (byte) ((p%2)+1);
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				if(pion[x+i][y+j]==p)
					format[0]++;
				else if (pion[x+i][y+j]== e)
					format[1]++;
				
		return format;

	}
	public int nbTroue(){
		int value=0;
		for(int i=0;i<pion.length;i++){
			for(int j=0;j<pion[0].length;j++){
				if(pion[i][j]==0){
					value++;
				}
			}
		}
		return value;
	}
	
}
