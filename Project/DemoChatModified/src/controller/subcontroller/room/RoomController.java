package controller.subcontroller.room;

import java.io.File;

import model.sendmodel.Message;
import model.sendmodel.Person;
import model.sendmodel.Room;
import controller.Controller;
import controller.subcontroller.MessageTagController;
import controller.subcontroller.RoomHomeController;
import controller.subcontroller.RoomTagController;
import controller.subcontroller.mediaelement.MediaElementController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class RoomController {
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   ATTRIBUTES    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private Parent roomTag, roomHome, messageTag;
	
	private RoomTagController roomTagController;
	
	private RoomHomeController roomHomeController;
	
	private MessageTagController messageTagController;
	
	private Room room;

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   CONSTRUCTORS    <---------------------------
	*
	*-----------------------------------------------------------------------------------*/
	public RoomController(Room room){
		this.room = room;
		init();
		setParent();
		setInfo();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   GETTER/SETTER    <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Get room tag ********************************************************************/
	public Parent getRoomTag(){
		return this.roomTag;
	}
	
	/** Get room home *******************************************************************/
	public Parent getRoomHome(){
		return this.roomHome;
	}
	
	/** Get message tag *****************************************************************/
	public Parent getMessageTag(){
		return this.messageTag;
	}
	
	/** Get room tag controller *********************************************************/
	public RoomTagController getRoomTagController(){
		return this.roomTagController;
	}
	
	/** Get room home controller ********************************************************/
	public RoomHomeController getRoomHomeController(){
		return this.roomHomeController;
	}
	
	/** Get message tag controller ******************************************************/
	public MessageTagController getMessageTagController(){
		return this.messageTagController;
	}
	
	/** Set room ************************************************************************/
	public void setRoom(Room room){
		this.room = room;
	}
	
	/** Get room ************************************************************************/
	public Room getRoom(){
		return this.room;
	}
	
	/** Get room id* ********************************************************************/
	public int getId(){
		return this.room.getId();
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   METHODS    <--------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Call update when data have changed **********************************************/
	public void update(){
		setInfo();
	}
	
	/** Set message text for room home **************************************************/
	public boolean setMessage(Message message){
		int userId = message.getSender().getId();
		if(!memberInRoom(userId))
				return false;
		
		String modifyMess = message.getContent().getName();
		
		if(userId!=Controller.getInstance().getUserId())
			modifyMess = message.getSender().getName()+":\n"+modifyMess;
		
		Label label = new Label(modifyMess);
		label.setWrapText(true);
		label.setFont(Font.font(24));
		label.setStyle("-fx-background-color:#2ecc71; -fx-padding:10; -fx-background-radius:20");
		label.setMaxWidth(350);
//		Tooltip dateTime = new Tooltip(message.getSendDate().toString());
//		label.setTooltip(dateTime);
		
		HBox hbox = new HBox();
		hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
		
		if(userId==Controller.getInstance().getUserId()){
			hbox.setAlignment(Pos.BASELINE_RIGHT);
			hbox.setStyle("-fx-padding: 0 15 0 5");
		}
		else {
			hbox.setAlignment(Pos.BASELINE_LEFT);
			hbox.setStyle("-fx-padding: 0 5 0 15");
			//TODO
//		Circle circle = new Circle(10,10,20);
	//			circle.setFill(getExternalImage(userId));
	//		hbox.getChildren().add(circle);
		}
		hbox.getChildren().add(label);
		
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth()-2);
		
		roomHomeController.getChatBox().widthProperty().addListener(listener->{
			hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
			anchorPane.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth()-2);
			refreshFrame();
		});
		roomHomeController.getChatBox().heightProperty().addListener(listener->{
			hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
			refreshFrame();
		});
		
		AnchorPane.setBottomAnchor(hbox,0.0);
		AnchorPane.setTopAnchor(hbox,0.0);
		AnchorPane.setRightAnchor(hbox,0.0);
		AnchorPane.setLeftAnchor(hbox,0.0);
		
		anchorPane.getChildren().add(hbox);
		
		Platform.runLater(()->roomHomeController.upMessage(anchorPane));
		refreshFrame();
		messageTagController.updateCurrentMessage(getMemberById(userId).getName()+":"+message.getContent().getName());
		return true;
	}
	
	/** Set message text for room home **************************************************/
	public boolean setMedia(Message message){
		int userId = message.getSender().getId();
		if(!memberInRoom(userId))
				return false;
		if(message.getContent().getFile("tmp/")){
			Label label = new Label();
			try{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mediaelement/mediaelement.fxml"));
				Parent parent = loader.load();
				MediaElementController mediaElementController = loader.getController();
				mediaElementController.setFile(message.getContent());
				label.setGraphic(parent);
			}catch(Exception e){}
			label.setWrapText(true);
			label.setFont(Font.font(24));
			label.setStyle("-fx-background-color:#2ecc71; -fx-padding:10; -fx-background-radius:20");
			label.setMaxWidth(350);
			Tooltip dateTime = new Tooltip(message.getSendDate().toString());
			label.setTooltip(dateTime);
			
			HBox hbox = new HBox();
			hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
			
			if(userId==Controller.getInstance().getUserId()){
				hbox.setAlignment(Pos.BASELINE_RIGHT);
				hbox.setStyle("-fx-padding: 0 15 0 5");
			}
			else {
				hbox.setAlignment(Pos.BASELINE_LEFT);
				hbox.setStyle("-fx-padding: 0 5 0 15");
				//TODO
		//		Circle circle = new Circle(10,10,20);
		//		circle.setFill(getExternalImage(userId));
		//		hbox.getChildren().add(circle);
			}
			hbox.getChildren().add(label);
			
			AnchorPane anchorPane = new AnchorPane();
			anchorPane.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth()-2);
			
			roomHomeController.getChatBox().widthProperty().addListener(listener->{
				hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
				anchorPane.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth()-2);
				refreshFrame();
			});
			roomHomeController.getChatBox().heightProperty().addListener(listener->{
				hbox.setPrefWidth(roomHomeController.getScrollMessage().getViewportBounds().getWidth());
				refreshFrame();
			});
			
			AnchorPane.setBottomAnchor(hbox,0.0);
			AnchorPane.setTopAnchor(hbox,0.0);
			AnchorPane.setRightAnchor(hbox,0.0);
			AnchorPane.setLeftAnchor(hbox,0.0);
			
			anchorPane.getChildren().add(hbox);
			
			Platform.runLater(()->roomHomeController.upMessage(anchorPane));
			refreshFrame();
			messageTagController.updateCurrentMessage(getMemberById(userId).getName()+": Đã gửi 1 file!");
			return true;
		}
		return false;
	}
	
	/** Show this room when user clicked on message tag or room tag *********************/
	public void showThisRoom(){
		Controller.getInstance().showRoomCaller(room.getId());
	}
	
	/*----------------------------------------------------------------------------------
	*
	*------------------------------------->    MODULES   <------------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Initialize tag, controller ******************************************************/
	private void init(){
		try{
			FXMLLoader roomHomeLoader = new FXMLLoader(getClass().getResource("/view/roomhome/roomhome.fxml"));
			FXMLLoader roomTagLoader= new FXMLLoader(getClass().getResource("/view/tag/roomtag/roomtag.fxml"));
			FXMLLoader messageTagLoader = new FXMLLoader(getClass().getResource("/view/tag/messagetag/messagetag.fxml"));
			
			roomHome = roomHomeLoader.load();
			roomTag = roomTagLoader.load();
			messageTag = messageTagLoader.load();
			
			roomHomeController = roomHomeLoader.getController();
			roomTagController = roomTagLoader.getController();
			messageTagController = messageTagLoader.getController();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** Testing that is a member in this room? ******************************************/
	private boolean memberInRoom(int userId){
		if(room.getMembers()==null) return false;
		for (Person person : room.getMembers())
			if(person.getId()==userId)
				return true;
		return false;
	}
	
	/** Get image pattern by member id **************************************************/
	private ImagePattern getExternalImage(int id){
		return new ImagePattern(new Image(readFileFromPerson(id).toURI().toString()));
	}
	
	/** Set image for room **************************************************************/
	private Image setImage(){
		return new Image(readFileFromRoom().toURI().toString());
	}
	
	/** Get member by id ****************************************************************/
	private Person getMemberById(int id){
		for (Person person : room.getMembers())
			if(person.getId()==id)
				return person;
		return null;
	}
	
	/** Get avatar file of room *********************************************************/
	private File readFileFromRoom(){
		if(room.getAvatar().getFile("tmp/"))
			return new File("tmp/"+room.getAvatar().getName());
		return new File("tmp/user_icon.png");
	}
	/** Get avatar file of member *******************************************************/
	private File readFileFromPerson(int id){
		Person tmp = getMemberById(id);
		if(tmp!=null)
			if(tmp.getAvatar().getFile("tmp/"))
				return new File("tmp/"+tmp.getAvatar().getName());
		return new File("tmp/user_icon.png");
	}
	
	/** Refresh the screen when have input message **************************************/
	private void refreshFrame() {
		Thread stream = new Thread(() -> {
			try {
				Thread.sleep(80);
				setFrame();
			} catch (Exception e) {}
		});
		stream.start();
	}
	
	/** Force scroll pane always in the bottom ******************************************/
	private void setFrame(){
		roomHomeController.getScrollMessage().vvalueProperty().setValue(1.0);
	}
	
	/** Set information for children controller *****************************************/
	private void setInfo(){
		roomTagController.callUpdate(room.getName(), setImage());
		roomHomeController.callUpdate(room.getName(),room.getMembers().size(),setImage());
		messageTagController.callUpdate(room.getName(), setImage());
	}
	
	/** Set parent controller for children controller ***********************************/
	private void setParent(){
		roomTagController.setParent(this);
		roomHomeController.setParent(this);
		messageTagController.setParent(this);
	}
}
