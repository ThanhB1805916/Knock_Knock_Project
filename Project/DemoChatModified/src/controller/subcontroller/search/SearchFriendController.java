package controller.subcontroller.search;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.Controller;
import model.sendmodel.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class SearchFriendController implements Initializable{
	@FXML
		private TextField searchTxt;
	
	@FXML
		private Button searchBtn;
	
	@FXML
		private FlowPane personFlow;
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->   INITIALIZE    <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchBtn.setOnMousePressed(value->{
			personFlow.getChildren().clear();
			if(searchTxt.getText().trim().length()>0)
				Controller.getInstance().callSearchFriend(searchTxt.getText());
		});
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     MODULES     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Set flow friend for search data *************************************************/
	private void getMember(Person person){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/searchfriend/searchfriendtag.fxml"));
			Parent parent = loader.load();
			SearchFriendTagController searchFriendTagController = loader.getController();
			searchFriendTagController.setInfo(person);
			personFlow.getChildren().add(parent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*----------------------------------------------------------------------------------
	*
	*----------------------------------->     METHODS     <-----------------------------
	*
	*-----------------------------------------------------------------------------------*/
	/** Update data flow ****************************************************************/
	public void updateData(ArrayList<Person> personList){
		for(Person person : personList)
			getMember(person);
	}
}
