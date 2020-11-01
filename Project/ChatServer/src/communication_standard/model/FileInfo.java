package communication_standard.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

public class FileInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private byte[] data;

	public FileInfo() {

	}

	public FileInfo(String url) {
		this.name = new String(new File(url).getName());
		this.data = changeToByte(url);
	}

	public FileInfo(FileInfo fileInfo) {
		this.name = new String(fileInfo.name);
		this.data = fileInfo.data;
	}

	private byte[] changeToByte(String url) {
		File file = new File(url);
		byte[] dataFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(dataFile);
			fileInputStream.close();
			return dataFile;
		} catch (Exception e1) {
			System.out.println("The file fail in directory!");
			dataFile = null;
		}
		return dataFile;
	}

	public String getName() {
		return this.name;
	}

	public byte[] getData() {
		return this.data;
	}
}
