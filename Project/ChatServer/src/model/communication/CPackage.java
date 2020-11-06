package model.communication;

import java.io.Serializable;

import model.ValidModel;

//Standard type will be send throught socket
public final class CPackage implements ValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Type type;
	private Request request;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public CPackage(Type type, Request request) {
		super();
		this.type = type;
		this.request = request;
	}

	@Override
	public boolean isValid() {
		return type != null && request.isValid();
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
