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
	 *            the temp
	 * @param exp1
	 *            the exp 1
	 * @param exp2
	 *            the exp 2
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
	 * Generates the 3a statement: VAR t.
	 *
	 * @param t
	 *            the t
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
