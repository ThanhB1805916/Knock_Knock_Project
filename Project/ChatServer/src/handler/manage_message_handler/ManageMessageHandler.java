//package handler.manage_message_handler;
//
//import communication_standard.CommandType;
//import communication_standard.CommunicationPackage;
//import communication_standard.EManageType;
//import communication_standard.manage_type.command_type.EManageMessages;
//import handler.Handler;
//import socket.IClient;
//
//public class ManageMessageHandler extends Handler {
//
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//	// ---------------------------------------------------------------- Constructor
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//
//	public ManageMessageHandler(IClient client) {
//		super(client);
//		// TODO Auto-generated constructor stub
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//	// ---------------------------------------------------------------- Functions
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//	// -------------------------------------------------------------- Handle Request
//	@Override
//	public void handleRequest(CommandType request) {
//		if (request.isValid()) {
//
//			EManageMessages command = (EManageMessages) request.getCommand();
//
//			switch (command) {
//			case VIEWMESSAGE:
//				ViewMessage(request.getContent());
//				break;
//
//			default:
//				break;
//
//			}
//		}
//	}
//
//	private void ViewMessage(Object content) {
//
////		List<ViewMessage> message 
//	}
//
//	// --------------------------------------------------------------------------------------------------------------------------------------------
//	// ------------------------------------------------------------ Pack And Send
//	@Override
//	public void packAndSend(CommandType commandType) {
//		if (commandType.isValid()) {
//			CommunicationPackage CPackage = new CommunicationPackage(EManageType.MANAGEMESSAGES, commandType);
//			send(CPackage);
//		}
//	}
//
//}
