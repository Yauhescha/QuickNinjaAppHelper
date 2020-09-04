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


	private String folderWithFiles;
	private StringBuilder builder = new StringBuilder();

	private String filename;
	private String quest, answer;
	private final String STANDART_QUEST = "?";
	private static Scanner sc;

	private static final String QUEST_SELECTOR = "span.question input";
	private static final String ANSWER_SELECTOR = "span.answer input";
	private final int ANSWER_LETTER_COUNT=14;
	private final int QUEST_LETTER_COUNT=25;

	public SeleniumHelper(String folder) {
		this.folderWithFiles =folder;
	}

	public static void main(String[] args) {
		WebDriver driver = init();
		while (true) {
			try {
				System.out.println("Please, write path folder with images to getting answers and quests");
				String folderToParse = sc.nextLine();
				if (folderToParse.equals("break"))
					break;

				SeleniumHelper helper = new SeleniumHelper(folderToParse);
				helper.run();

				System.out.println("Press any key to write quests and answers");
				sc.nextLine();
				sendQuest(helper, driver);

				sendAnswer(helper, driver);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private static WebDriver init() {
		System.setProperty("webdriver.chrome.driver", "chromedriver84.exe");

		WebDriver driver = new ChromeDriver();
		sc = new Scanner(System.in);

		driver.navigate().to("https://quickappninja.com/");
		return driver;
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
		for (Iterator<File> iterator = listFiles.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			filename = file.getName().replace(".png", "").trim();
			getQuestAndAnswer();
			listQuest.add(quest);
			listAnswer.add(answer);
		}

	}

	private void getQuestAndAnswer() {
		if (filename.length() <= ANSWER_LETTER_COUNT) {
			quest = STANDART_QUEST;
			answer = filename;
		} else {
			builder.setLength(0);
			builder.append(filename);
			answer = builder.substring(builder.length() - ANSWER_LETTER_COUNT, builder.length());
			builder.delete(builder.length() - ANSWER_LETTER_COUNT, builder.length());
			if (builder.length() <= QUEST_LETTER_COUNT)
				quest = builder.toString();
			else
				quest = builder.substring(builder.length() - QUEST_LETTER_COUNT, builder.length());
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
