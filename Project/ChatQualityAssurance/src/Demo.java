import java.io.File;

public class Demo {

	public static void main(String[] args) {

		deleteFolder("sources/users/admin/avatars");
		deleteFolder("sources/users/admin");
		System.out.println("end");
	}

	public static boolean deleteFolder(String path) {

		boolean success = false;

		try {
			if (path.isEmpty() == false) {
				File userDir = new File(path);

				// Delete all childFiles
				String[] childFiles = userDir.list();
				if (childFiles != null) {
					for (String s : childFiles) {
						File currentFile = new File(userDir.getPath(), s);
						currentFile.delete();
					}
				}

				// Delete folder
				success = userDir.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return success;
	}
}
