package dca0120.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextEditLimit extends PlainDocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 440956376482683039L;

	private int limit;

	public JTextEditLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextEditLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}

}
