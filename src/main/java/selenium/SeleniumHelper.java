package main.java.selenium;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHelper {
	private ArrayList<File> listFiles = new ArrayList<File>();

	private ArrayList<String> listQuest = new ArrayList<String>();
	private ArrayList<String> listAnswer = new ArrayList<String>();

	private String folderWithFiles = "D:\\anime-description\\";
	private String folderWithFilesSTANDART = "D:\\anime-description\\";
	private StringBuilder builder = new StringBuilder();

	private String filename, temp;
	private String quest, answer;
	private final String STANDART_QUEST = "Название?";
	private static Scanner sc;

	private static final String QUEST_SELECTOR = "span.question input";
	private static final String ANSWER_SELECTOR = "span.answer input";

	public SeleniumHelper(String folder) {
		this.folderWithFiles =folderWithFilesSTANDART+ folder;
	}

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "chromedriver84.exe");

		WebDriver driver = new ChromeDriver();
		sc = new Scanner(System.in);

		driver.navigate().to("https://quickappninja.com/");
		while (true) {
			try {
				System.out.println("write folder to getting answers and quests");
				String folderToParse = sc.nextLine();
				if (folderToParse.equals("break"))
					break;

				SeleniumHelper helper = new SeleniumHelper(folderToParse);
				helper.run();

				System.out.println("write to push");
				sc.nextLine();
				sendQuest(helper, driver);

				sendAnswer(helper, driver);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private static void sendAnswer(SeleniumHelper helper, WebDriver driver) {
		List<WebElement> findElements = driver.findElements(By.cssSelector(helper.ANSWER_SELECTOR));
		for (int i = 0; i < findElements.size(); i++) {
			findElements.get(i).sendKeys(helper.listAnswer.get(i));
		}
	}

	private static void sendQuest(SeleniumHelper helper, WebDriver driver) {
		List<WebElement> findElements = driver.findElements(By.cssSelector(helper.QUEST_SELECTOR));
		for (int i = 0; i < findElements.size(); i++) {
			findElements.get(i).sendKeys(helper.listQuest.get(i));
		}
	}

	private void run() {
		getAllFilesFromFolder(new File(folderWithFiles));

		readAllFiles();
	}

	private void readAllFiles() {
		for (Iterator iterator = listFiles.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			filename = file.getName().replace(".png", "").trim();
			getQuestAndAnswer();
			listQuest.add(quest);
			listAnswer.add(answer);
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
				listFiles.add(entry);
		}
	}

}
