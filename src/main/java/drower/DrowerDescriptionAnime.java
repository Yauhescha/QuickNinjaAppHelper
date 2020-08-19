package main.java.drower;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DrowerDescriptionAnime {
	public static final String FOLDER_WITH_FOLREDS = "D:\\anime-description";
	public static String fileDescription = "description.txt";
	private ArrayList<File> folderList = new ArrayList<File>();
	private String separator = "\\";
	private String FolderToSave = "description";
	private DrowerImage drowerImage = new DrowerImage();

	public static void main(String[] args) {
		DrowerDescriptionAnime drower = new DrowerDescriptionAnime();
		drower.getFilesInFolder(FOLDER_WITH_FOLREDS);

		drower.goAllFolder();
	}

	public void getFilesInFolder(String folderString) {
		File folder = new File(folderString);
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (entry.isDirectory()) {
				folderList.add(entry);
			}
		}
	}

	private void goAllFolder() {
		for (int i = 0; i < folderList.size(); i++) {
			File folder = folderList.get(i);
			String readText = readText(folder);
			BufferedImage image = drowerImage.draw(readText);
			DrowerImage.saveImage(image, FolderToSave + separator + folder.getName(), "png");
			
			System.out.println("Step: " + i);
		}
	}

	private String readText(File folder) {
		String text = "";
		try {
			Scanner sc = new Scanner(new File(folder.getAbsoluteFile() + separator + fileDescription));
			while (sc.hasNextLine()) {
				text += sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return text;
	}

}
