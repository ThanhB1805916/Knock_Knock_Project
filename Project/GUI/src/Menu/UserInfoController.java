package Menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import MainChatRoom.FrameController;
import Models.FileInfo;
import Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
public class UserInfoController implements Initializable {
	
	@FXML AnchorPane superPane;
	
	@FXML TextField name, phone;
	
	@FXML ComboBox<Integer> day, month, year;
	
	@FXML RadioButton male, female;
	
	@FXML Button cancel, update, buttonChangeImg;
	
	@FXML ImageView userAvatar;
	
	private FileInfo imageFile;
	
	private User user;
	
	private FrameController controller;

	private boolean current;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Integer> days 	= FXCollections.observableArrayList();
		ObservableList<Integer> months	= FXCollections.observableArrayList();
		ObservableList<Integer> years	= FXCollections.observableArrayList();
		for(int i =1; i<=31; i++)
			days.add(i);
		for(int i =1; i<=12; i++)
			months.add(i);
		for(int i =1970; i<=2020; i++)
			years.add(i);
		day.getItems().addAll(days);
		day.setCellFactory(cell->new ListCell<Integer>(){
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);

	            if (empty || item == null) {
	                setText(null);
	            } else {
	                setText(item.toString());
	                setFont(Font.font(20));
	            }
			};
		});
		
		month.getItems().addAll(months);
		month.setCellFactory(cell->new ListCell<Integer>(){
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);

	            if (empty || item == null) {
	                setText(null);
	            } else {
	                setText(item.toString());
	                setFont(Font.font(20));
	            }
			};
		});
		
		year.getItems().addAll(years);
		year.setCellFactory(cell->new ListCell<Integer>(){
			@Override
			protected void updateItem(Integer item, boolean empty) {
				super.updateItem(item, empty);

	            if (empty || item == null) {
	                setText(null);
	            } else {
	                setText(item.toString());
	                setFont(Font.font(20));
	            }
			};
		});
	}
//*
  //*		Các hàm sự kiện
	//-------------chọn ngày----------------
	public void dayClicked(MouseEvent event) {
		updateAvalid();
	}
	
	//-------------chọn tháng----------------
	public void monthClicked(MouseEvent event) {
		updateAvalid();
	}
	
	//-------------chọn năm----------------
	public void yearClicked(MouseEvent event) {
		updateAvalid();
	}
	
	//----kiểm tra mỗi khi chuột di chuyển------
	public void backgroundTest(MouseEvent event) {
		calucateDate();
		updateAvalid();
	}
	
	//--------cập nhật thông tin------------------
	public void updateProfile(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("CẬP NHẬT THÔNG TIN");
		alert.setHeaderText("Bạn có chắc muốn cập nhật thông tin không ?");
		Optional<ButtonType> type = alert.showAndWait();
		if(type.get()==ButtonType.OK) {
			//Gửi thông tin lên máy chủ tại đây
			//if true
			user.set_avatar(imageFile);
			user.set_name(name.getText());
			user.set_phone(phone.getText());
			user.set_male(current);
			user.set_dateofbirth(year.getValue()+"-"+month.getValue()+"-"+day.getValue());
			Alert subAlert = new Alert(AlertType.INFORMATION);
			subAlert.setTitle("THÔNG BÁO");
			subAlert.setHeaderText("Cập nhật thông tin thàng công!");
			controller.getBackgroundPane().getChildren().remove(superPane);
			subAlert.show();
			controller.updateAvatar();
		}	
	}
	
	//----Tắt panel thông tin cá nhân------------
	public void canceling(MouseEvent event) {
		controller.getBackgroundPane().getChildren().remove(superPane);
	}
	
	//--------cập nhật khi tên có thay đổi--------
	public void showBtnName(KeyEvent event) {
		updateAvalid();
	}
	
	//--------cập nhật khi sdt có thay đổi--------
	public void showBtnPhone(KeyEvent event) {
		updateAvalid();
	}

	//--------cập nhật khi giới tính có thay đổi--------
	public void malePressed(MouseEvent event) {
		current = true;
		updateAvalid();
	}
	public void femalePressed(MouseEvent event) {
		current = false;
		updateAvalid();
	}
	
	//----------------- chọn ảnh đại diện------------
	public void chooseAnother(MouseEvent event) {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(
			      new FileChooser.ExtensionFilter("JPG", "*.jpg"),
			      new FileChooser.ExtensionFilter("PNG", "*.png"));
		
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(stage);
		if(file!=null) {
			Image image = new Image(file.toURI().toString());
			userAvatar.setImage(image);
			imageFile = new FileInfo(file.getPath());
		}
		System.out.println(imageFile==user.get_avatar());
		updateAvalid();
	}

//****************Kết thúc các hàm sự kiện********************
	
// **************GET/SET********************************
	//****
	//------Hàm lấy thông tin tài khoản ----------------
	public void getUser(User user) {
		this.user = user;
		updateInfo();
	}
	
	//------Hàm lấy controller context ------------------	
	public void getController(FrameController controller) {
		this.controller = controller;
	}
//********************************************************************
	
//********Các nội hàm*****************************************
	//---------Hàm tính toán thời gian----------------------
	private void calucateDate() {
		int max[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if ((year.getValue() % 400 == 0) || ((year.getValue() % 4 == 0) && (year.getValue() % 100 != 0)))
			max[2] = 29;
		if (day.getValue() > max[month.getValue()]) 
			day.setValue(1);
	}
	
	//-----Hàm cập nhật thông tin tài khoản-----
	private void updateInfo() {
		name.setText(this.user.get_name());
		phone.setText(this.user.get_phone());
		day.setValue(this.user.get_day());
		month.setValue(this.user.get_month());
		year.setValue(this.user.get_year());
		imageFile = user.get_avatar();
		if(this.user.is_male()) {
			current = true;
			male.setSelected(true);
		}
		else {
			female.setSelected(true);
			current  = false;
		};
		updateAvatar();
	}
	
	//-------------------Hàm cập nhật ảnh đại diện--------------------System.getProperty("user.dir")+"//src
	private void updateAvatar() {
		File file = new File(System.getProperty("user.dir")+"//src//tmp//"+user.get_avatar().getName());	
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(user.get_avatar().getData());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		Image imageLoader = new Image(file.toURI().toString());
		userAvatar.setImage(imageLoader);
		userAvatar.setPreserveRatio(true);
	}
	
	//----------------Hàm kiểm tra thông tin để có thể update hay không----------------
	private void updateAvalid() {
		if (name.getText().equalsIgnoreCase(user.get_name()) && phone.getText().equalsIgnoreCase(user.get_phone())
				&& day.getValue() == user.get_day() && month.getValue() == user.get_month()
				&& year.getValue() == user.get_year() && current == user.is_male()
				&& imageFile.equals(user.get_avatar()))
			update.setDisable(true);
		else if (name.getText().trim().length() > 0 && phone.getText().trim().length() > 0)
			update.setDisable(false);
	}
}
