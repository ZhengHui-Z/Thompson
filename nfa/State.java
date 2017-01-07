package nfa;

import java.util.HashMap;
import java.util.HashSet;

public class State {
	private static int node = 0;
	private int id;
	private HashMap<Character, HashSet<State>> State_map;
	
	public State(Character character,HashSet<State> hashSet) {
		this.id = node++;
		State_map = new HashMap<>();
		State_map.put(character, hashSet);
	}
	
	public void set_map(Character character,HashSet<State> hashSet) {
		State_map.put(character, hashSet);
	}
	
	public int Get_id() {
		return this.id;
	}
	public HashMap<Character, HashSet<State>> Get_State_map(){
		return this.State_map;
	}

}
