package fr.univ_artois._24_iut_info.game;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private List<List<Integer>> map=new ArrayList<List<Integer>>();
	private byte[][] pion;
	Map(String str){
		decode(str);
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
		for(byte[] ligne:pion){
			for(byte p:ligne){
				p=(byte)0;
			}
		}
		
	}
	public void poser(int x,int y, int id){
		pion[x][y]=(byte)id;
	}
	
	public boolean canPose(int x,int y){
		return pion[x][y]==0;
	}
	
	
}
