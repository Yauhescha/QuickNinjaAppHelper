package main.java.getter;

import java.io.File;
import java.util.ArrayList;

public class GetterFilesInFolder {
	private static ArrayList<File> list = new ArrayList<File>();

	public static ArrayList<File> getFilesInFolderAsList(File folder) {
		list.clear();
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			list.add(entry);
		}
		return list;
	}
}
