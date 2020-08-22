package main.java.getter;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GetterRandomImagesFrolFolders {

	private Random random = new Random();
	private static Scanner sc = new Scanner(System.in);
	private ArrayList<File> folderList = new ArrayList<File>();

	private int temp;
	private String folderToSave;

	private String folderWithImages;
	private int maxFilesInOneFolder;
	private boolean inFolderOnlyFiles;

	public static void main(String[] args) {

		GetterRandomImagesFrolFolders getter = new GetterRandomImagesFrolFolders();

		init(getter);

		getter.start();

	}

	private static void init(GetterRandomImagesFrolFolders getter) {
		System.out.println("Please, write path to folder with folders with images \nor path to folder with images");
		getter.setFolderWithImages(sc.nextLine());

		System.out.println("Is it folder with images (type 'true') or with folders (type 'false')");
		getter.setInFolderOnlyFiles(sc.nextBoolean());

		System.out.println("Please, write foldername to save images");
		getter.setFolderToSave(sc.nextLine());

		System.out.println("Please, write max count files in one folder.");
		getter.setMaxFilesInOneFolder(sc.nextInt());
	}

	public void start() {
		getAllFolder(new File(folderWithImages));
		renameAllFiles();
	}

	private void renameAllFiles() {
		for (int folderNumber = 1; folderList.size() > 0; folderNumber++)
			renameRandomFiles(folderWithImages + "\\" + folderToSave + folderNumber);
	}

	private void renameRandomFiles(String toFolder) {

		CreateFolderToRename(toFolder);

		for (int i = 0; i < maxFilesInOneFolder; i++) {
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
		if (!inFolderOnlyFiles)
			if (folder.listFiles().length < 1) {
				folder.delete();
				i--;
			}
		return i;
	}

	private void renameFileInFolder(String toFolder, File folder, File[] folderEntries) {
		for (File entry : folderEntries) {
			String name = toFolder + "\\" + folder.getName();
			name = replaceBadWords(name);
			entry.renameTo(new File(name));
			System.out.println(name);
			break;
		}
	}

	private String replaceBadWords(String name) {
		name = name.replace("ОВА 1", "").replace("ОВА 2", "").replace("ОВА 3", "").replace("ОВА 4", "")
				.replace("ОВА", "").replace("первый сезон", "").replace("второй сезон", "")
				.replace("третий сезон", "").replace("четвертый сезон", "").replace("пятый сезон", "")
				.replace("фильм второй", "").replace("фильм третий", "").replace("фильм четвертый", "")
				.replace("фильм пятый", "").replace("фильм", "").replace("спэшл", "").replace("  ", " ").trim();
		return name;
	}

	private File[] getOneFolderFrolmList(File folder) {
		File[] folderEntries;
		if (!inFolderOnlyFiles)
			folderEntries = folder.listFiles();
		else
			folderEntries = new File[] { folder };
		return folderEntries;
	}

// получить все папки и занести их в список
	public void getAllFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (inFolderOnlyFiles) {
				if (!entry.isDirectory())
					folderList.add(entry);
			} else if (entry.isDirectory()) {
				folderList.add(entry);
			}
		}
	}

	public void setFolderToSave(String folderToSave) {
		this.folderToSave = folderToSave;
	}

	public void setFolderWithImages(String folderWithImages) {
		this.folderWithImages = folderWithImages;
	}

	public void setMaxFilesInOneFolder(int maxFilesInOneFolder) {
		this.maxFilesInOneFolder = maxFilesInOneFolder;
	}

	public void setInFolderOnlyFiles(boolean inFolderOnlyFiles) {
		this.inFolderOnlyFiles = inFolderOnlyFiles;
	}

}
