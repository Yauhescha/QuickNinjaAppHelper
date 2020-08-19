import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GetterImages {
	static final int MAX_FILE_TO_RENAME = 2000;

	private static String FOLDER_NAME = "D:\\anime-description";
	private Random random = new Random();
	private ArrayList<File> folderList = new ArrayList<File>();
	private static String folderName = "folder";
	private int MAX_FILE_IN_ONE_FOLDER = 100;
	private int temp;

	public final boolean FILES_IN_ONE_FOLDER = true;

	public static void main(String[] args) {

		GetterImages getterImages = new GetterImages();

		getterImages.getAllFolder(new File(FOLDER_NAME));
		getterImages.renameAllFiles();
		

	}

	private void renameAllFiles() {
		for (int folderNumber = 1; folderList.size()>0; folderNumber++)
			renameRandomFiles(FOLDER_NAME + "\\" + folderName + folderNumber);
	}

	private void renameRandomFiles(String toFolder) {
		
		CreateFolderToRename(toFolder);

		for (int i = 0; i < MAX_FILE_IN_ONE_FOLDER; i++) {
			temp = random.nextInt(folderList.size());

			File folder = folderList.get(temp);
			System.out.println(i + "\t " + folder.getName());
			File[] folderEntries;
			
			folderEntries = getOneFolderFrolmList(folder);
			
			renameFileInFolder(toFolder, folder, folderEntries);
			
			i = deleteFolderIfEmpty(i, folder);
			
			folderList.remove(temp);
		}

	}

	private void CreateFolderToRename(String toFolder) {
		new File(toFolder).mkdirs();
	}

	private int deleteFolderIfEmpty(int i, File folder) {
		if (!FILES_IN_ONE_FOLDER)
			if (folder.listFiles().length < 1) {
				folder.delete();
				i--;
			}
		return i;
	}

	private void renameFileInFolder(String toFolder, File folder, File[] folderEntries) {
		for (File entry : folderEntries) {
			String name=toFolder + "\\" + folder.getName();
			name=name.replace("ОВА 1", "").replace("ОВА 2", "").replace("ОВА 3", "").replace("ОВА 4", "").replace("ОВА", "")
					.replace("первый сезон", "").replace("второй сезон", "").replace("третий сезон", "").replace("четвертый сезон", "").replace("пятый сезон", "")
					.replace("фильм второй", "").replace("фильм третий", "").replace("фильм четвертый", "").replace("фильм пятый", "").replace("фильм", "")
					.replace("спэшл", "").replace("  ", " ").trim();
			entry.renameTo(new File(name));
			System.out.println(name);
			break;
		}
	}

	private File[] getOneFolderFrolmList(File folder) {
		File[] folderEntries;
		if (!FILES_IN_ONE_FOLDER)
			folderEntries = folder.listFiles();
		else
			folderEntries = new File[] { folder };
		return folderEntries;
	}

// получить все папки и занести их в список
	public void getAllFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (FILES_IN_ONE_FOLDER) {
				if (!entry.isDirectory())
					folderList.add(entry);
			} else if (entry.isDirectory()) {
				folderList.add(entry);
			}
		}
	}

}
