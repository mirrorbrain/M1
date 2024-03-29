/**
 * Type checking operations (NOTE: this class must be implemented by the
 * student; the methods indicated here can be seen as suggestions; note that
 * some minor checks can still be performed directly in VSLTreeParser.g).
 */
public class TypeCheck {

	/**
	 * Type checking for a binary operation - in VSL+: integer operations only!.
	 *
	 * @param t1
	 *            the t 1
	 * @param t2
	 *            the t 2
	 * @return the type
	 */
	public static Type checkBinOp(Type t1, Type t2) {
		if (t1 == Type.INT && t2 == Type.INT)
			return Type.INT;
		else
			return Type.ERROR;
	}

}
