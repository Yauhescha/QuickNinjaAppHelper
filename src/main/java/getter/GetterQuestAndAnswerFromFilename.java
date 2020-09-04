package main.java.getter;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class GetterQuestAndAnswerFromFilename {
	private ArrayList<File> list = new ArrayList<File>();

	private String folderWithFiles = "D:\\anime-description\\folder1";
	private StringBuilder builder = new StringBuilder();

	private String filename, temp;
	private String quest, answer;
	private final String STANDART_QUEST = "Название?";

	public static void main(String[] args) {
		GetterQuestAndAnswerFromFilename getter = new GetterQuestAndAnswerFromFilename();
		getter.run();

	}

	private void run() {
		getAllFilesFromFolder(new File(folderWithFiles));

		readAllFiles();
	}

	private void readAllFiles() {
		for (Iterator<File> iterator = list.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			filename = file.getName().replace(".png", "").trim();
			getQuestAndAnswer();
			System.out.println(filename);
			System.out.println(quest);
			System.out.println(answer);
			System.out.println();
		}

	}

	private void getQuestAndAnswer() {
		if (filename.length() <= 14) {
			quest = STANDART_QUEST;
			answer = filename;
		} else {
			builder.setLength(0);
			builder.append(filename);
			answer = builder.substring(builder.length() - 14, builder.length());
			builder.delete(builder.length() - 14, builder.length());
			if (builder.length() <= 25)
				quest = builder.toString();
			else
				quest = builder.substring(builder.length() - 25, builder.length());
		}
	}

	public void getAllFilesFromFolder(File folder) {
		File[] folderEntries = folder.listFiles();
		for (File entry : folderEntries) {
			if (!entry.isDirectory())
				list.add(entry);
		}
	}
}
