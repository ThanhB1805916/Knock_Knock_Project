package communication_standard;

import java.io.Serializable;

import communication_standard.manage_type.EManageType;
import model.IValidModel;

//Standard type will be send throught socket
public final class CommunicationPackage implements IValidModel, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EManageType manageType;
	private CommandType commandType;

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Constructors
	// -------------------------------
	// ---------------------------------------------------------------------------

	public CommunicationPackage(EManageType manageType, CommandType commandType) {
		super();
		this.manageType = manageType;
		this.commandType = commandType;
	}

	@Override
	public boolean isValid() {
		return manageType != null && commandType.isValid();
	}

	// ---------------------------------------------------------------------------
	// -------------------------------
	// ------------------------------- Setters - Getters
	// -------------------------------
	// ---------------------------------------------------------------------------

	public EManageType getManageType() {
		return manageType;
	}

	public void setManageType(EManageType manageType) {
		this.manageType = manageType;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

}
