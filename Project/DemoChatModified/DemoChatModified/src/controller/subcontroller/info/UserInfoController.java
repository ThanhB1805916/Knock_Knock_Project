package controller.subcontroller.info;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;





import controller.Controller;
import model.sendmodel.FileInfo;
import model.sendmodel.Person;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInfoController implements Initializable{
	@FXML
	private ImageView userImage;

	@FXML
	private TextField month;

	@FXML
	private Button userUpdate, changePassword;

	@FXML
	private TextField userBannerName;

	@FXML
	private TextField year;

	@FXML
	private TextField userGender;

	@FXML
	private Label userName;

	@FXML
	private Button userChange;

	@FXML
	private Label userID;

	@FXML
	private TextField day;

	@FXML
	private TextField userPhone;

	@FXML
	private AnchorPane parentPane;

	@FXML
	private Button userChangeAvatar;

	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     ATTRIBUTES     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	private boolean status = false;

	private String currentName, currentDay, currentMonth, currentYear,
			currentPhone;

	private boolean currentGender;

	private FileInfo file, currentFile;
	   
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     INITIALIZE     <--------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setButton();
		if(Controller.getInstance().getUserId()!=null)
			getId(Controller.getInstance().getUserId());
		else Controller.getInstance().alertNotify(AlertType.ERROR,"Tài khoản chưa đăng nhập!","Guess",null);
	}
	
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set button for this controller **************************************************/
	private void setButton(){
		userChange.setOnMouseClicked(value->{
			changeStatus();
			if(status)
				userChange.setText("Hủy");
			else 
				userChange.setText("Sửa");
		});
		
		userBannerName.setOnKeyPressed(value->{
			String old = userBannerName.getText();
			Platform.runLater(()->{
				if(value.getText().matches("[0-9]"))
					userBannerName.setText(old);
			});
			updateAvalid();
		});
		
		userPhone.setOnKeyPressed(value->{
			if(status){
				String old = userPhone.getText();
				Platform.runLater(()->{
					if(value.getText().matches("[^0-9]"))
						userPhone.setText(old);
				});
			}
		});
		
		day.setOnKeyPressed(value->{
			if(status){
				String old = day.getText();
				Platform.runLater(()->{
					if(value.getText().matches("[^0-9]"))
						day.setText(old);
					if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
						userUpdate.setDisable(true);
					else updateAvalid();
				});
			}
		});
		
		month.setOnKeyPressed(value->{
			if(status){
				String old = day.getText();
				Platform.runLater(()->{
					if(value.getText().matches("[^0-9]"))
						month.setText(old);
					if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
						userUpdate.setDisable(true);
					else updateAvalid();
				});
			}
		});
		year.setOnKeyPressed(value->{
			if(status){
				String old = year.getText();
				Platform.runLater(()->{
					if(value.getText().matches("[^0-9]"))
						year.setText(old);
					if(day.getText().equals("") || month.getText().equals("") || year.getText().equals(""))
						userUpdate.setDisable(true);
					else updateAvalid();
				});
			}
		});
		
		userBannerName.setOnKeyPressed(value->{
			if(status){
				updateAvalid();
			}
		});
		
		userPhone.setOnKeyPressed(value->{
			if(status){
				String old = userPhone.getText();
				Platform.runLater(()->{
					if(value.getText().matches("[^0-9]") || userPhone.getText().trim().length()>10)
						userPhone.setText(old);
					updateAvalid();
				});
			}
		});
		
		userGender.setOnKeyReleased(value->{
			if(status)
			updateAvalid();
		});
		
		userUpdate.setOnMousePressed(value->{
			//TODO
			currentDay = day.getText();
			currentMonth = month.getText();
			currentYear = year.getText();
			
			currentPhone = userPhone.getText();
			currentName = userBannerName.getText();
			
			if(userGender.getText().equalsIgnoreCase("nam"))
				currentGender =  true;
			else currentGender = false;
			
			changeStatus();
			Controller.getInstance().updateUserInfo(currentDay, currentMonth, currentYear,currentGender,currentPhone, currentName,currentFile);
			userChange.setText("Sửa");
		});
		
		userChangeAvatar.setOnMouseClicked(value->{
			Stage stage = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(
				      new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				      new FileChooser.ExtensionFilter("PNG", "*.png"));
			
			fileChooser.setTitle("Open Resource File");
			File tmp = fileChooser.showOpenDialog(stage);
			if(tmp!=null) {
				currentFile = new FileInfo(tmp.getPath());
				if(currentFile.getFile("tmp/"))
					userImage.setImage(new Image(tmp.toURI().toString()));
				userUpdate.setDisable(false);
			}
		});
		
		changePassword.setOnMouseClicked(value->{
			Controller.getInstance().showChangePassword();
		});
	}
	
	/** Change status of something ******************************************************/
	private void changeStatus(){
		status=!status;
		day.setEditable(status);
		month.setEditable(status);
		year.setEditable(status);
		userBannerName.setEditable(status);
		userGender.setEditable(status);
		userPhone.setEditable(status);
		userChangeAvatar.setDisable(!status);
		userUpdate.setDisable(true);
		update();
	}
	
	/** Checking that update is valid ***************************************************/
	private void updateAvalid() {
		calucateDate();
		boolean current = false;
		if(userGender.getText().equals("nam"))
			current = true;
		if (userBannerName.getText().equalsIgnoreCase(currentName) && userPhone.getText().equalsIgnoreCase(currentPhone)
				&& day.getText().equalsIgnoreCase(currentDay) && month.getText().equals(currentMonth)
				&& year.getText().equals(currentYear)&& currentGender ==current
				&& file.equals(currentFile))
			userUpdate.setDisable(true);
		else if (userName.getText().trim().length() > 0 && userPhone.getText().trim().length() > 0)
			userUpdate.setDisable(false);
	}
	
	/** Calculate date is valid *********************************************************/
	private void calucateDate() {
		if(Integer.parseInt(month.getText())>12 ||Integer.parseInt(month.getText()) <=0)
			month.setText("1");
		int max[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		if ((Integer.parseInt(year.getText()) % 400 == 0) || ((Integer.parseInt(year.getText()) % 4 == 0) && (Integer.parseInt(year.getText()) % 100 != 0)))
			max[2] = 29;
		if (Integer.parseInt(day.getText()) > max[Integer.parseInt(month.getText())]) {
			day.setText("1");
			month.setText(Integer.toString(Integer.parseInt(month.getText())+1));
			if(Integer.parseInt(month.getText()) > 12){
				month.setText("1");
				year.setText(Integer.toString(Integer.parseInt(year.getText())+1));
			}
		}
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set information for this controller by input id *********************************/
	public void getId(int id){
		Person person = Controller.getInstance().getPersonById(id);
		currentName = person.getName();
		currentGender = person.getMale();
		currentDay = Integer.toString(person.getDateofbirth().getDayOfMonth());
		currentMonth = Integer.toString(person.getDateofbirth().getMonthValue());
		currentYear = Integer.toString(person.getDateofbirth().getYear());
		currentPhone = person.getPhonenumber();
		
		file =  currentFile = person.getAvatar();
		userImage.setImage(new Image(new File("tmp/"+file.getName()).toURI().toString()));
		userID.setText(Integer.toString(person.getId()));
		userName.setText(person.getUsername());
		update();
	}
	
	/** Update the data for this controller *********************************************/
	public void update(){
		userImage.setImage(new Image(new File("tmp/"+currentFile.getName()).toURI().toString()));
		userBannerName.setText(currentName);
		day.setText(currentDay);
		month.setText(currentMonth);
		year.setText(currentYear);
		if(currentGender)
			userGender.setText("Nam");
		else userGender.setText("Nữ");
		userPhone.setText(currentPhone);
	}
}
