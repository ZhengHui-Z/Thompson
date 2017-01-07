package nfa;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dbug {
	public static HashSet<State> hashset_juge = new HashSet<>();
	public static GraphViz gv = new GraphViz();

	private void start() {
		gv.addln(gv.start_graph());
		gv.addln("rankdir = LR;");
		gv.addln("A -> B;");
		gv.addln("A -> C[label=\"#\"];");
		gv.addln(gv.end_graph());
		// System.out.println(gv.getDotSource());
		gv.increaseDpi();
		String type = "png";
		String repesentationType = "dot";
		File out = new File("H:/graph/out.png" + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);
	}

	public void printstate(State state) {
		hashset_juge.add(state);
		HashMap<Character, HashSet<State>> map = new HashMap<>();
		map = state.Get_State_map();
		for (Map.Entry<Character, HashSet<State>> entry : map.entrySet()) {
			if (entry.getKey() != null) {
				HashSet<State> Set = entry.getValue();
				for (State state1 : Set) {
					// System.out.println(state.Get_id() + "->" +
					// state1.Get_id() + "[label=\"" + entry.getKey() + "\"];");
					gv.addln(state.Get_id() + "->" + state1.Get_id() + "[label=\"" + entry.getKey() + "\"];");
				}
				for (State state1 : Set) {
					if (!hashset_juge.contains(state1)) {
						printstate(state1);
					}
				}
			}

		}
	}

	public static void main(String[] args) {
		Dbug debug = new Dbug();
		Dbug.hashset_juge.clear();
		// Scanner scanner = new Scanner(System.in);
		//String s = "((a|a.b).(a|a.b)*)*";
		String s = "a.(b*|(c.d)*)*";
		Rpn rpn = new Rpn();
		Nfa nfa = rpn.count(rpn.reverse2Rpn(s));
		System.out.println(rpn.reverse2Rpn(s));
		gv.addln(gv.start_graph());
		gv.addln("rankdir = LR;");
		debug.printstate(nfa.Get_start());
		gv.addln(gv.end_graph());
		gv.increaseDpi();
		String type = "png";
		String repesentationType = "dot";
		File out = new File(System.getProperty("user.dir") + "/out." + type);
		gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type, repesentationType), out);

	}
}
