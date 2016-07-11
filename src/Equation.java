public class Equation {
	public static String answer;

	public Equation() {
		// TODO Auto-generated constructor stub
	}

	public String solve(String eq) {
		CharSequence openpren = "(";
		CharSequence closepren = ")";
		CharSequence multi = "*";
		CharSequence divide = "/";
		CharSequence addition = "+";
		CharSequence sub = "-";
		if ((eq.contains(openpren) || eq.contains(closepren))) {
			if ((eq.contains(openpren) && eq.contains(closepren))) {
				String[] spliteqfirst = eq.split("\\(");
				String[] spliteqsecond = spliteqfirst[spliteqfirst.length-1].split("\\)");
				if (spliteqsecond.length >= 2) {
					solve(spliteqfirst[0] + solve(spliteqsecond[0])
							+ spliteqsecond[1]);
				} else {
					int idx =0;
					while (spliteqfirst[idx].equals("")){
						idx++;
					}
					solve(spliteqfirst[idx] + solve(spliteqsecond[0]));
				}
			} else {
				answer = "ERROR";
				return answer;
			}
		} else {
			if (eq.contains(multi) || eq.contains(divide)) {
				if (eq.contains(multi) && eq.contains(divide)) {
					if (checkplacement(multi, eq) && checkplacement(divide, eq)) {
						int idxmulti = eq.indexOf(multi.toString());
						int idxdivide = eq.indexOf(divide.toString());
						if (idxmulti == idxdivide - 1
								|| idxmulti == idxdivide + 1) {
							answer = "ERROR";
							return answer;
						} else if (idxmulti < idxdivide) {
							solve(multiply(eq, multi));
						} else {
							solve(division(eq, divide));
						}
					}
				} else if (eq.contains(multi) && checkplacement(multi, eq)) {
					solve(multiply(eq, multi));
				} else if (eq.contains(divide) && checkplacement(divide, eq)) {
					solve(division(eq, divide));
				} else {
					answer = "ERROR";
					return answer;
				}
			} else if (eq.contains(addition) || eq.contains(sub)) {
				if (eq.contains(addition) && eq.contains(sub)) {
					int idxadd = eq.indexOf(addition.toString());
					int idxminus = eq.indexOf(sub.toString());
					if (idxadd == idxminus - 1 || idxadd == idxminus + 1) {
						answer = "ERROR";
						return answer;
					} else if (idxadd < idxminus) {
						solve(add(eq, addition));
					} else {
						solve(minus(eq, sub));
					}
				} else if (eq.contains(sub) && checkplacement(sub, eq)) {
					solve(minus(eq, sub));
				} else if (eq.contains(addition)
						&& checkplacement(addition, eq)) {
					solve(add(eq, addition));
				} else {
					answer = "ERROR";
					return answer;
				}
			} else {
				char[] charanswer = eq.toCharArray();
				for (int idx = 0; idx < eq.length() - 1; idx++) {
					if (Character.toString(charanswer[idx]).equals("-")
							&& idx == 0) {

					} else if (!(Character.isDigit(charanswer[idx]) || Character
							.toString(charanswer[idx]).equals("."))) {
						answer = "ERROR";
						return answer;
					}
				}
				answer = eq;
				return answer;
			}
		}
		return answer;
	}

	public String add(String eq, CharSequence addition) {
		String firstnumber = firstnum(eq.indexOf(addition.toString()), eq);
		String secondnumber = secondnum(eq.indexOf(addition.toString()), eq);
		if (checkdouble(firstnumber) || checkdouble(secondnumber)) {
			double ans = Double.parseDouble(firstnumber)
					+ Double.parseDouble(secondnumber);
			answer = eq.replace(firstnumber + "+" + secondnumber,
					String.valueOf(ans));
			return answer;
		} else {
			int ans = Integer.parseInt(firstnumber)
					+ Integer.parseInt(secondnumber);
			answer = eq.replace(firstnumber + "+" + secondnumber,
					String.valueOf(ans));
			return answer;
		}
	}

	public String minus(String eq, CharSequence sub) {
		String firstnumber = firstnum(eq.indexOf(sub.toString()), eq);
		String secondnumber = secondnum(eq.indexOf(sub.toString()), eq);
		if (checkdouble(firstnumber) || checkdouble(secondnumber)) {
			double ans = Double.parseDouble(firstnumber)
					- Double.parseDouble(secondnumber);
			answer = eq.replace(firstnumber + "-" + secondnumber,
					String.valueOf(ans));
			return answer;
		} else {
			int ans = Integer.parseInt(firstnumber)
					- Integer.parseInt(secondnumber);
			answer = eq.replace(firstnumber + "-" + secondnumber,
					String.valueOf(ans));
			return answer;
		}
	}

	public String division(String eq, CharSequence divide) {
		String firstnumber = firstnum(eq.indexOf(divide.toString()), eq);
		String secondnumber = secondnum(eq.indexOf(divide.toString()), eq);
		if (secondnumber.equals("0")) {
			answer = "ERROR DIVIDING BY 0";
			return answer;
		}
		if (checkdouble(firstnumber) || checkdouble(secondnumber)) {
			double ans = Double.parseDouble(firstnumber)
					/ Double.parseDouble(secondnumber);
			answer = eq.replace(firstnumber + "/" + secondnumber,
					String.valueOf(ans));
			return answer;
		} else {
			int ans = Integer.parseInt(firstnumber)
					/ Integer.parseInt(secondnumber);
			answer = eq.replace(firstnumber + "/" + secondnumber,
					String.valueOf(ans));
			return answer;
		}
	}

	public String multiply(String eq, CharSequence multi) {
		String firstnumber = firstnum(eq.indexOf(multi.toString()), eq);
		String secondnumber = secondnum(eq.indexOf(multi.toString()), eq);
		if (firstnumber.equals("ERROR") || secondnumber.equals("ERROR")) {
			return answer;
		}
		if (checkdouble(firstnumber) || checkdouble(secondnumber)) {
			double ans = Double.parseDouble(firstnumber)
					* Double.parseDouble(secondnumber);
			answer = eq.replace(firstnumber + "*" + secondnumber,
					String.valueOf(ans));
			return answer;
		} else {
			int ans = Integer.parseInt(firstnumber)
					* Integer.parseInt(secondnumber);
			answer = eq.replace(firstnumber + "*" + secondnumber,
					String.valueOf(ans));
			return answer;
		}
	}

	public boolean checkdouble(String num) {
		CharSequence dot = ".";
		if (num.contains(dot)) {
			return true;
		} else {
			return false;
		}
	}

	public String firstnum(int opidx, String eq) {
		String num = "";
		char[] testeq = eq.toCharArray();
		for (int idx = opidx - 1; idx >= 0; idx--) {
			if (Character.isDigit(testeq[idx])
					|| Character.toString(testeq[idx]).equals(".")) {
				num = Character.toString(testeq[idx]) + num;
			} else {
				if (Character.toString(testeq[idx]).equals("-")) {
					if (idx == 0) {
						num = Character.toString(testeq[idx]) + num;
					} else if (!Character.isDigit(testeq[idx - 1])) {
						num = Character.toString(testeq[idx]) + num;
					} else {
						return num;
					}
				}
				return num;
			}
		}
		return num;
	}

	public String secondnum(int opidx, String eq) {
		String num = "";
		char[] testeq = eq.toCharArray();
		for (int idx = opidx + 1; idx <= eq.length() - 1; idx++) {
			if (Character.isDigit(testeq[idx])
					|| Character.toString(testeq[idx]).equals("\\.")) {
				num = num + Character.toString(testeq[idx]);
			} else {
				if (Character.toString(testeq[idx]).equals("-")) {
					if (idx == eq.length() - 1) {
						answer = "Error";
						return answer;
					} else if (!Character.isDigit(testeq[idx - 1])) {
						num = Character.toString(testeq[idx]) + num;
					} else {
						return num;
					}
				}
				return num;
			}
		}
		return num;
	}

	public boolean checkplacement(CharSequence operator, String eq) {
		if (eq.indexOf(operator.toString()) == 0
				|| eq.indexOf(operator.toString()) == eq.length() - 1) {
			return false;
		} else {
			return true;
		}
	}
}
