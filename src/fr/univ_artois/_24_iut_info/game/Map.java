package fr.univ_artois._24_iut_info.game;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private List<List<Integer>> map=new ArrayList<List<Integer>>();
	
	Map(String str){
		decode(str);
	}
	private void decode(String str){
		String[] lignes=str.split("|");
		
		for(String ligne : lignes){
			String[] cases = ligne.split(":");
			List<Integer> list=new ArrayList<Integer>();
			map.add(list);
			for(String cas:cases){
				list.add(Integer.parseInt(cas));
			}
		}
	}
}
