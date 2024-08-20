package Scraping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class FoodInfoScraper {

    private static final String FOODBASICS_URL = "https://www.foodbasics.ca";
    private static final String METRO_URL = "https://www.metro.ca/en";
    private static final String ZEHRS_URL = "https://www.zehrs.ca/food/c/27985";

    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        try {
            List<Map<String, String>> firstDataSource = firstSourceDataScraper(driver);
            List<Map<String, String>> secondDataSource = secondSourceDataScraper(driver);
            List<Map<String, String>> thirdDataSource = thirdSourceDataScraper(driver);
            List<Map<String, String>> collectedData = collaborateData(firstDataSource, secondDataSource, thirdDataSource);
            storeDataAsJSON(collectedData, "Food_Info_Data.json");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static List<Map<String, String>> firstSourceDataScraper(WebDriver driver) {
        driver.get(FOODBASICS_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content__head")));
        List<Map<String, String>> listForFirstSource = new ArrayList<>();
        List<WebElement> headingData = driver.findElements(By.className("head__title"));
        List<WebElement> contentData = driver.findElements(By.className("pricing__secondary-price"));

        for (int i = 0; i < headingData.size(); i++) {
            Map<String, String> productData = new HashMap<>();
            String productName = headingData.get(i).getText();
            String productPrice = contentData.get(i).getText();

            if (!productName.isEmpty() && !productPrice.isEmpty()) {
                productData.put("Store name", "Foodbasics");
                productData.put("Product name", productName);
                productData.put("Product price", productPrice);
                listForFirstSource.add(productData);
            }
        }
        return listForFirstSource;
    }

    private static List<Map<String, String>> secondSourceDataScraper(WebDriver driver) {
        driver.get(METRO_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("content__head")));
        List<Map<String, String>> listForSecondSource = new ArrayList<>();
        List<WebElement> headingData = driver.findElements(By.className("head__title"));
        List<WebElement> contentData = driver.findElements(By.className("pricing__secondary-price"));

        for (int i = 0; i < headingData.size(); i++) {
            Map<String, String> productData = new HashMap<>();
            String productName = headingData.get(i).getText();
            String productPrice = contentData.get(i).getText();

            if (!productName.isEmpty() && !productPrice.isEmpty()) {
                productData.put("Store name", "Metro");
                productData.put("Product name", productName);
                productData.put("Product price", productPrice);
                listForSecondSource.add(productData);
            }
        }
        return listForSecondSource;
    }

    private static List<Map<String, String>> thirdSourceDataScraper(WebDriver driver) {
        driver.get(ZEHRS_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-testid='product-title']")));
        List<Map<String, String>> listForThirdSource = new ArrayList<>();
        List<WebElement> headingData = driver.findElements(By.xpath("//h3[@data-testid='product-title']"));
        List<WebElement> contentData = driver.findElements(By.xpath("//p[@data-testid='product-package-size']"));

        for (int i = 0; i < headingData.size(); i++) {
            Map<String, String> productData = new HashMap<>();
            String productName = headingData.get(i).getText();
            String productPrice = contentData.get(i).getText();

            if (!productName.isEmpty() && !productPrice.isEmpty()) {
                productData.put("Store name", "Zehrs");
                productData.put("Product name", productName);
                productData.put("Product price", productPrice);
                listForThirdSource.add(productData);
            }
        }
        return listForThirdSource;
    }

    private static List<Map<String, String>> collaborateData(List<Map<String, String>> firstSource, List<Map<String, String>> secondSource, List<Map<String, String>> thirdSource) {
        List<Map<String, String>> collaboratingData = new ArrayList<>();
        collaboratingData.addAll(firstSource);
        collaboratingData.addAll(secondSource);
        collaboratingData.addAll(thirdSource);
        return collaboratingData;
    }

    private static void storeDataAsJSON(List<Map<String, String>> scrapedData, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            JSONArray jsonArray = new JSONArray();
            for (Map<String, String> productData : scrapedData) {
                JSONObject jsonObject = new JSONObject(productData);
                jsonArray.add(jsonObject);
            }
            file.write(formatJsonArray(jsonArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatJsonArray(JSONArray jsonArray) {
        StringBuilder formattedJson = new StringBuilder("[\n");
        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;
            formattedJson.append("  {\n");
            for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                String value = (String) jsonObject.get(key);
                formattedJson.append("    \"").append(key).append("\": \"").append(value).append("\"");
                if (iterator.hasNext()) {
                    formattedJson.append(",");
                }
                formattedJson.append("\n");
            }
            formattedJson.append("  }");
            if (jsonArray.indexOf(obj) < jsonArray.size() - 1) {
                formattedJson.append(",");
            }
            formattedJson.append("\n");
        }
        formattedJson.append("]");
        return formattedJson.toString();
    }
}