package MessageWindow;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import MessageWindow.MessageElement.MessageLine;
import Models.FileInfo;
import Models.Room;
import RoomWindow.RoomDetails.RoomExtendController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import roomelement.DivroomController;  

public class ActivityController implements Initializable {
	
	@FXML AnchorPane frameActive;
	
	@FXML Button sendBtn, informationBtn;
	
	@FXML FlowPane flowPane;
	
	@FXML ImageView roomAvatar;
	
	@FXML Label member,roomName;
	
	@FXML ScrollPane centerPane;
	
	@FXML TextField editText;
	
	private DivroomController divControlller;
	
	private Room room;
	
	private VBox vBox;
	
	private FrameController controller;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		centerPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		centerPane.widthProperty().addListener(function->{
			flowPane.setPrefWidth(centerPane.getWidth()-2);
		});
	}
	
	//***********CÁC HÀM LẮNG NGHE*****************
	
	public void showRoomDetail(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomExtend/RoomExtend.fxml"));
			AnchorPane anchorPane = loader.load();
			AnchorPane.setLeftAnchor(anchorPane, 0.0);
			AnchorPane.setRightAnchor(anchorPane, 0.0);
			AnchorPane.setTopAnchor(anchorPane, 0.0);
			AnchorPane.setBottomAnchor(anchorPane, 0.0);
			RoomExtendController roomExtendController = loader.getController();
			roomExtendController.getConText(controller);
			roomExtendController.getRoom(room);
			roomExtendController.getActivity(this);
			controller.getBackgroundPane().getChildren().add(anchorPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendBtnPressed(MouseEvent event) {
		if(editText.getText().toString().trim().length()>0)
			upMessage(editText.getText(),false);
	}
	
	public void editPressEnter(KeyEvent event) {
		if(event.getCode()==KeyCode.ENTER)
			if(editText.getText().toString().trim().length()>0)
				upMessage(editText.getText(),true);
	}
	
	public void pickImage(MouseEvent event) {}
	public void pickAudio(MouseEvent event) {}
	public void pickDocument(MouseEvent event) {}
	public void pickVideo(MouseEvent event) {}
	public void pickAnother(MouseEvent event) {}
	
	
	//----------------------up messge to screen-------------
	public void upMessage(String content, boolean flag/*,ImageView userImage, type*/) {
		AnchorPane anchorPane = new AnchorPane();
		MessageLine messageLine = new MessageLine(content,flag/*,ImageView userImage, type*/);
		
		anchorPane.setPrefWidth(centerPane.getViewportBounds().getWidth()-2);
		
		messageLine.setPrefWidth(centerPane.getViewportBounds().getWidth()-2);
		
		AnchorPane.setRightAnchor(messageLine, 0.0);
		AnchorPane.setBottomAnchor(messageLine, 0.0);
		
		anchorPane.getChildren().add(messageLine);
		
		flowPane.widthProperty().addListener((a,o,n)->{
			anchorPane.setPrefWidth(centerPane.getViewportBounds().getWidth()-2);
			messageLine.setPrefWidth(centerPane.getViewportBounds().getWidth()-2);
			anchorPane.setPrefHeight(messageLine.getHeight());
			setFrame();
		});
		
		flowPane.getChildren().add(anchorPane);
		refeshFrame();
		divControlller.setCurrentText(editText.getText());
		vBox.getChildren().remove(divControlller.getDivPane());
		vBox.getChildren().add(0,divControlller.getDivPane());
		editText.setText("");
	}
	///------------refesh croll pane----------------------------
	private void refeshFrame() {
		Thread stream = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(80);
					setFrame();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		stream.start();
	}
	
	private void setFrame() {
		centerPane.vvalueProperty().setValue(1.0);
	}
	
	public void setImage(FileInfo fileInfo) {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+fileInfo.getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(fileInfo.getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println("Error line 51 InfoUserClas");
		}
		Image imageLoader = new Image(file.toURI().toString());
		roomAvatar.setImage(imageLoader);
		roomAvatar.setPreserveRatio(true);
	}
///-------------------------//I/O Input----------------------------------------------
	public void setMessage(String content,ImageView imageUser/*type*/) {
		upMessage(content, false/*type*/);
	}
	
///***************SET/GET*************************************
	//----------Lấy tên phòng
	public void setRoomName(String name) {
		roomName.setText(name);
	}
	
	
	public void getContext(FrameController controller) {
		this.controller = controller;
	}
	//----------Đếm số thành viên
	public void setMember(String quantiy) {
		member.setText(quantiy);
	}
	
	public void setDiv(DivroomController controller) {
		divControlller = controller;
	}
	
	public void setVBox(VBox vbox) {
		this.vBox = vbox;
	}
	
	public void setRoomModel(Room room) {
		this.room = room;
	}
	
	public void update() {
		setRoomName(room.get_name());
		setMember(Integer.toString(this.room.get_userList().size()));
		setImage(room.get_avatar());
	}
}
