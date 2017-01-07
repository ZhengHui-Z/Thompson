package nfa;

import java.util.HashSet;

public class Nfa {
	private State state1, state2, start, end;

	public Nfa(Character character) {
		state2 = new State(null, null);
		HashSet<State> set = new HashSet<State>();
		set.add(state2);
		state1 = new State(character, set);
		this.start = state1;
		this.end = state2;
	}

	public Nfa(Nfa nfa1, Nfa nfa2, int juge) {
		State start1 = nfa1.Get_start();
		State end1 = nfa1.Get_end();
		State start2 = nfa2.Get_start();
		State end2 = nfa2.Get_end();
		if (juge == 1) {// And
			HashSet<State> hashSet = new HashSet<State>();
			hashSet.add(start2);
			end1.set_map('ε', hashSet);
			this.start = start1;
			this.end = end2;
		} else if (juge == 2) {// Or
			HashSet<State> hashSet1 = new HashSet<State>();
			HashSet<State> hashSet2 = new HashSet<State>();
			hashSet1.add(start1);
			hashSet1.add(start2);
			state1 = new State('ε', hashSet1);
			state2 = new State(null, null);
			hashSet2.add(state2);
			end1.set_map('ε', hashSet2);
			end2.set_map('ε', hashSet2);
			this.start = state1;
			this.end = state2;
		}

	}

	public Nfa(Nfa nfa) {
		State start1 = nfa.Get_start();
		State end1 = nfa.Get_end();
		HashSet<State> hashset = new HashSet<>();
		state2 = new State(null, null);
		hashset.add(start1);
		hashset.add(state2);
		end1.set_map('ε', hashset);
		hashset.clear();
		hashset.add(start1);
		hashset.add(state2);
		state1 = new State('ε', hashset);
		this.start = state1;
		this.end = state2;
	}

	public State Get_start() {
		return start;
	}

	public State Get_end() {
		return end;
	}
}
