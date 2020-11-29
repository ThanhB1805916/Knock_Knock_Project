package controller.subcontroller.mediaelement;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.sendmodel.FileInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MediaElementController implements Initializable{
	
    @FXML
    private Button downloadFile;

    @FXML
    private Button seeFile;

    @FXML
    private Label nameFile;

    @FXML
    private Label typeMedia;
	
    private FileInfo file;
	
    private ArrayList<String> listExtension = new ArrayList<>();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] list = new String[]{".png",".gif", ".jpg",".mp3",".wav", ".aac",".ogg",".doc",".docx", ".exls",".pptx",".pdf",".txt",".html",".mp4",".mov", ".wmv",".flv",".avi",".mkv",};
		for (String string : list)
			listExtension.add(string);
		setButtonListener();
	}
	
	public void setFile(FileInfo file){
		this.file = file;
		typeMedia.setText("["+getExtension(file.getName())+"]");
		nameFile.setText(getName(file.getName()));
		if (!isFormalFile())
			seeFile.setVisible(false);
	}
	
	private String getExtension(String text){
		return text.substring(text.lastIndexOf(".")).toUpperCase();
	}
	
	private String getName(String text){
		return text.substring(0,text.lastIndexOf("."));
	}
	
	private void setButtonListener(){
		seeFile.setOnMouseClicked(value->{
			file.getFile("tmp/");
			try {
				Desktop.getDesktop().open(new File("tmp/"+file.getName()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		downloadFile.setOnMouseClicked(value->{
			Stage stage = new Stage();
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Choose location");
			File tmp = directoryChooser.showDialog(stage);
			if(tmp!=null)
				file.getFile(tmp.getPath());
		});
	}
	
	private boolean isFormalFile(){
		return listExtension.contains(getExtension(file.getName()).toLowerCase());
	}
}
