package model.communication;

import java.io.Serializable;

import model.ValidModel;

public final class Request implements ValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Name name;
	private Object content;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public Request(Name name, Object content) {
		super();
		this.name = name;
		this.content = content;
	}

	@Override
	public boolean isValid() {
		return name != null;
	}
	
	public Object unpack()
	{
		return content;
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public void setName(Name name) {
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getContent() {
		return content;
	}
}
