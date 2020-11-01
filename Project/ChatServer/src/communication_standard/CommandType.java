package communication_standard;

import java.io.Serializable;

import model.IValidModel;


public final class CommandType implements IValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object command;
	private Object content;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public CommandType(Object command, Object content) {
		super();
		this.command = command;
		this.content = content;
	}

	@Override
	public boolean isValid() {
		return command != null && content != null;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public Object getCommand() {
		return command;
	}

	public void setCommand(Object command) {
		this.command = command;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}
