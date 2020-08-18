import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GetterImages {
	static final int MAX_FILE_TO_RENAME=200;
	
	private static String FILENAME = "d://anime";
	private Random random = new Random();
	private ArrayList<File> folderList = new ArrayList<File>();
	private static String folderName="anime";
	private int MAX_FILE_IN_ONE_FOLDER = 100;
	private int temp;
	

	public static void main(String[] args) {

		GetterImages getterImages = new GetterImages();

		getterImages.getAllFolder(new File(FILENAME));
		for (int i = 1; i < MAX_FILE_TO_RENAME; i++)
			getterImages.renameRandomFiles(folderName + i);

	}

	private void renameRandomFiles(String toFolder) {
		new File(toFolder).mkdirs();
		System.out.println(toFolder);
		for (int i = 0; i < MAX_FILE_IN_ONE_FOLDER; i++) {
			temp = random.nextInt(folderList.size());

			File folder = folderList.get(temp);
			System.out.println(i + "\t " + folder.getName());

			File[] folderEntries = folder.listFiles();
			for (File entry : folderEntries) {

				entry.renameTo(new File(toFolder + "//" + folder.getName() + ".jpg"));
				System.out.println(toFolder + "//" + folder.getName() + ".jpg");
				break;
			}
			if (folder.listFiles().length < 1) {
				folder.delete();
				i--;
			}
			folderList.remove(temp);
		}

	}
// получить все папки и занести их в список
	public void getAllFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (entry.isDirectory()) {
				folderList.add(entry);
				continue;
			}
		}
	}

}
