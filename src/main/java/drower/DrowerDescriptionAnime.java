package main.java.drower;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.getter.GetterFilesInFolder;

public class DrowerDescriptionAnime {
	public String fileDescription;

	private ArrayList<File> folderList = new ArrayList<File>();
	private String separator = "\\";
	private String FolderToSave;

	private DrowerImage drowerImage = new DrowerImage();

	private Scanner sc;

	public static void main(String[] args) {
		DrowerDescriptionAnime drower = new DrowerDescriptionAnime();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please, write filename.txt file with text to image");
			drower.setFileDescription(scanner.nextLine());
			System.out.println("Please, write path to save file");
			drower.setFolderToSave(scanner.nextLine());
			
			System.out.println("Please, write path to directory with folders with text to image");
			drower.getFilesInFolder(scanner.nextLine());
		}

		drower.goAllFolder();
	}

	public void getFilesInFolder(String folderString) {
		File folder = new File(folderString);
		folderList = GetterFilesInFolder.getFilesInFolderAsList(folder);
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
			sc = new Scanner(new File(folder.getAbsoluteFile() + separator + fileDescription));
			while (sc.hasNextLine()) {
				text += sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return text;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public void setFolderToSave(String folderToSave) {
		FolderToSave = folderToSave;
		new File(folderToSave).mkdirs();
	}

}
