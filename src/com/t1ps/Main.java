package com.t1ps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int number_bots = 50;
        int i = 0;
        while (i <= number_bots-1) {
            i++;
            new Thread( () -> {
                try {
                    String game = "3805046";
                    String botName = "LilWilly";
                    game = "https://kahoot.it/?pin=" + game + "&refer_method=link";

                    launch_bot(botName, game);
                }
                catch (Exception ignored) {}
            }).start();
            Thread.sleep(125);
        }

    }

    public static void launch_bot(String botName, String game) throws InterruptedException {
        try {
            Random r = new Random();
            int low = 1000;
            int high = 9999;
            int result = r.nextInt(high-low) + low;
            botName = botName + result;
            System.out.println("Connecting to " + game + " as " + botName);
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\T1ps\\CustomCMD\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            WebDriver driver = new ChromeDriver(options);

            WebDriverWait wait = new WebDriverWait(driver, 5);
            // Join Game
            driver.get(game);
            Thread.sleep(2);
            WebElement nickname_input = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"nickname\"]"))); //driver.findElement(By.xpath("//*[@id=\"nickname\"]"));

            nickname_input.sendKeys(botName);
            nickname_input.sendKeys(Keys.ENTER);
            System.out.println("Joined Game as " + botName);
            Thread.sleep(2000);
            try {
                while (Objects.equals(driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/main/h1/div/p[1]")).getAttribute("innerText"), "You're in!")) {
                }
            } catch (Exception ignored) {}

            System.out.println("Game starting!");
            while (true) {
                try {

                    List<WebElement> answers = driver.findElements(By.xpath("//*[@id=\"root\"]/div[1]/main/div[2]/div/div/button[*]"));
                    Random rand = new Random();
                    int low_ = 0;
                    int high_ = answers.size();
                    int result_ = r.nextInt(high_-low_) + low_;
                    answers.get(result_).click();
                } catch (Exception ignored) {}
                if (Objects.equals(driver.getCurrentUrl(), "https://kahoot.it/ranking")) {
                    driver.close();
                    System.out.println("Game over!");
                    break;
                }

            }
        } catch (Exception ignored) {}

    }


}
