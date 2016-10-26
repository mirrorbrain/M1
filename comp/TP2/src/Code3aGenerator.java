/**
 * This class implements all the methods for 3a code generation (NOTE: this
 * class must be coded by the student; the methods indicated here can be seen as
 * a suggestion, but are not actually necessary).
 *
 * @author MLB
 */
public class Code3aGenerator {

	/**
	 * Generate code for a binary operation.
	 *
	 * @param op
	 *            must be a code op: Inst3a.TAC.XXX
	 * @param temp
	 *            the temporary variable for the result
	 * @param exp1
	 *            the expression 1
	 * @param exp2
	 *            the expression 2
	 * @return the code 3 a
	 */
	public static Code3a genBinOp(Inst3a.TAC op, Operand3a temp, ExpAttribute exp1, ExpAttribute exp2) {
		Code3a cod = exp1.code;
		cod.append(exp2.code);
		cod.append(genVar(temp));
		cod.append(new Inst3a(op, temp, exp1.place, exp2.place));
		return cod;
	}

	/**
	 * Generate code for an assign operation.
	 *
	 * @param o
	 *            the variable
	 * @param exp
	 *            the expression
	 * @return the code 3 a
	 */
	public static Code3a genAssign(Operand3a o, ExpAttribute exp) {
		Code3a cod = new Code3a();
		if (exp.place instanceof ConstSymbol) {
			ConstSymbol cs = (ConstSymbol) exp.place;
			if (cs.value >= 0)
				cod.append(new Inst3a(Inst3a.TAC.COPY, o, cs, null));
			else
				cod.append(new Inst3a(Inst3a.TAC.NEG, o, cs, null));
		} else {
			cod.append(new Inst3a(Inst3a.TAC.COPY, o, exp.place, null));
		}
		return cod;
	}

	/**
	 * Generate code for an assign operation on an array.
	 *
	 * @param a
	 *            the a
	 * @param e
	 *            the e
	 * @return the code 3 a
	 */
	public static Code3a genAssignArray(ArrayAttributes a, ExpAttribute e) {
		Code3a cod = new Code3a();
		cod.append(new Inst3a(Inst3a.TAC.VARTAB, a.tab, a.e.place, e.place));
		return cod;
	}

	/**
	 * Generates the 3a statement: VAR t.
	 *
	 * @param t
	 *            the variable
	 * @return the code 3 a
	 */
	public static Code3a genVar(Operand3a t) {
		Inst3a i = new Inst3a(Inst3a.TAC.VAR, t, null, null);
		return new Code3a(i);
	}

	/**
	 * Instantiates a new code 3 a generator.
	 */
	// Constructor not needed
	private Code3aGenerator() {}

} // Code3aGenerator ***
