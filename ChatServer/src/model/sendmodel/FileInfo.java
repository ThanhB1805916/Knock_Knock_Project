package model.sendmodel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import data_model.ValidModel;

public class FileInfo implements Serializable, ValidModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//----------------------------------------------------------------------------------
	//-----------------------------    Attributes     ----------------------------------
	//----------------------------------------------------------------------------------
	private String name;
	private byte[] data;

	//----------------------------------------------------------------------------------
	//-----------------------------    Constructors     --------------------------------
	//----------------------------------------------------------------------------------
	//*****************************   None parameter    ********************************
	public FileInfo() {
	}

	//*****************************        URL          ********************************
	public FileInfo(String url) {
		this.name = new String(new File(url).getName());
		this.data = changeToByte(url);
	}
	
	//*****************************        Copy         ********************************
	public FileInfo(FileInfo fileInfo) {
		this.name = new String(fileInfo.name);
		this.data = fileInfo.data;
	}
	
	@Override
	public boolean isValid() {
		return data != null;
	}
	
	//----------------------------------------------------------------------------------
	//-----------------------------      GETTER/SETTER      ----------------------------
	//----------------------------------------------------------------------------------
	//*****************************        S/G name         ****************************
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	//*****************************        S/G file         ****************************
	public boolean setFile(String url){
		this.name = new String(new File(url).getName());
		this.data = changeToByte(url);
		return data.length>0;
	}
	
	public boolean getFile(String directory){
		if(this.data!=null && this.data.length>0){
			File file = new File(directory);
			file.mkdirs();
			File fileReader = new File(directory+"/"+this.name);
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(fileReader);
				fileOutputStream.write(this.getData());
				fileOutputStream.flush();
				fileOutputStream.close();
				return true;
			} catch (IOException e) {
				return false;
			} 
		}
		return false;
	}
	
	//----------------------------------------------------------------------------------
	//-----------------------------    Internal methods     ----------------------------
	//----------------------------------------------------------------------------------
	private byte[] changeToByte(String url) {
		File file = new File(url);
		byte[] dataFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(dataFile);
			fileInputStream.close();
			return dataFile;
		} catch (Exception e1) {
			System.err.println("Failure to read file in: " + url);
			return null;
		}
	}

	private byte[] getData() {
		return this.data;
	}
}
