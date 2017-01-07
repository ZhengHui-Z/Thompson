package nfa;

import java.util.Stack;

public class Rpn {
	Nfa nfa;

	public Rpn() {
		// TODO Auto-generated constructor stub
	}

	public boolean isOperator1(String e) {
		if (null == e || "".equals(e)) {
			return false;
		}
		return ".".equals(e) || "*".equals(e) || "|".equals(e) || "+".equals(e);
	}

	public Nfa count(String string) {
		Stack<Nfa> stack = new Stack<Nfa>();
		for (int i = 0; i < string.length(); ++i) {
			String temp = string.charAt(i) + "";
			if (isOperator1(temp)) {
				if (temp.equals("." + "")) {
					Nfa nfa1 = stack.pop();
					Nfa nfa2 = stack.pop();
					Nfa nfa3 = new Nfa(nfa2, nfa1, 1);
					stack.push(nfa3);
				} else if (temp.equals("|" + "")) {
					Nfa nfa1 = stack.pop();
					Nfa nfa2 = stack.pop();
					Nfa nfa3 = new Nfa(nfa1, nfa2, 2);
					stack.push(nfa3);
				} else if (temp.equals("*" + "")) {
					Nfa nfa1 = stack.pop();
					Nfa nfa2 = new Nfa(nfa1);
					stack.push(nfa2);
				}
			} else {
				Nfa nfa = new Nfa(string.charAt(i));
				stack.push(nfa);
			}

		}
		return stack.pop();
	}

	// 是否是左括号
	public boolean isLeft(String s) {
		return "(".equals(s);
	}

	// 是否是右括号
	public boolean isRight(String s) {
		return ")".equals(s);
	}

	public boolean isOperator(String e) {
		if (null == e || "".equals(e)) {
			return false;
		}
		return ".".equals(e) || "|".equals(e);
	}

	public String reverse2Rpn(String src) {
		StringBuffer stringBuffer = new StringBuffer();
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < src.length(); i++) {
			String temp = src.charAt(i) + "";
			// System.out.println(temp);
			if (!isOperator(temp) && !isRight(temp) && !isLeft(temp)) {
				stringBuffer.append(temp);
			} else if (isOperator(temp)) {
				if (stack.size() == 0) {
					stack.push(temp);
				} else if ("|".equals(temp)) {
					if (isLeft(stack.peek())) {// 如果栈顶为左括号，则直接入栈
						stack.push(temp);// 直接将操作符入栈
					} else {
						String top = stack.peek();
						boolean next = true;
						while (".".equals(top) && next) {
							top = stack.pop();
							stringBuffer.append(top);
							next = stack.size() == 0 ? false : true;
						}
						stack.push(temp);
					}
					
				} else {
					stack.push(temp);
				}
			} else if (isLeft(temp)) {
				stack.push(temp);
			} else if (isRight(temp)) {
				String top = stack.pop();
				boolean next = true;
				while (!isLeft(top) && next) {
					stringBuffer.append(top);
					top = stack.pop();
					next = stack.size() == 0 ? false : true;
				}
			}
		}
		while (stack.size() > 0) {
			stringBuffer.append(stack.pop());
		}
		return stringBuffer.toString();
	}

}
