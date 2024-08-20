package org.example;

import java.awt.*;
import java.util.*;

import helper.*;
import lib.E;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static helper.Strings.*;


public class FoodPriceSite {
    public void searchFoodItem(String itemName, Scanner scanner) {
        searchItem(itemName, scanner);
    }
    static SpellCheck spellCheck;
    private static HashMap<String, Long> foodItems;
    private static HashMap<String, Integer> searchHistory;

    private static List<String> invertedProductList;
    public FoodPriceSite() {
        foodItems = FoodItemsDatabase.populateFoodItems();

        searchHistory = new HashMap<>();

        spellCheck = new SpellCheck();
    }
    public static boolean isAlphabetic(String input) {
        return input.matches("[a-zA-Z]+");
    }
    public static void searchItem(String itemName, Scanner scanner) {
        searchHistory.put(itemName, searchHistory.getOrDefault(itemName, 0) + 1);
        if (foodItems.containsKey(itemName)) {
            invertedProductList = FoodItemsDatabase.indexedIndexing(itemName);

            System.out.println();
            System.out.println(itemName + " - $" + foodItems.get(itemName) + ". Searched " + searchHistory.get(itemName) + " time(s).");
            System.out.println();
            if (invertedProductList.size() > 1) {
                System.out.println(MenuSeparator.value);
                System.out.println(AllProducts.value);
                System.out.println(MenuSeparator.value);
                System.out.println("  "+invertedProductList.size() + "  results for '" + itemName + "'");
                System.out.println(MenuSeparator.value);
                for (String products : invertedProductList) {
                    System.out.println("  "+products);
                }
                System.out.println(MenuSeparator.value);
                invertedProductList.removeAll(invertedProductList);
            }
        } else {
            WordCompletion.Trie t = new WordCompletion.Trie();
            List<String> food = new ArrayList<>(foodItems.keySet());
            for (String w : food) {
                t.insert(w);
            }

            List<String> outputList = spellCheck.sortpairs(itemName, food);
            List<String> autofills = new ArrayList<>();
            autofills.addAll(t.autocomplete(itemName));

            if (outputList.size() == 0 && itemName.length() > 2) {
                System.out.println(NoSimilarWords.value);
            } else {
                if (itemName.length() > 2) {
                    System.out.println(MenuSeparator.value);
                    System.out.println(LookingForProducts.value);
                    System.out.println(MenuSeparator.value);

                    // Display up to 3 suggestions in the table
                    for (int i = 0; i < Math.min(outputList.size(), 3); i++) {
                        System.out.println(" " + (i+1) + ". " + outputList.get(i) + "        ");
                    }
                    System.out.println(MenuSeparator.value);
                    System.out.println(Space.value);
                }
            }
            if (!autofills.isEmpty()) {
                System.out.println(MenuSeparator.value);
                System.out.println(AutoComplete.value);
                System.out.println(MenuSeparator.value);
                for (String s : autofills) {
                    System.out.println(s);


                }
            } else {
                System.out.println(SorryWordCompletion.value);
                System.out.println(MenuSeparator.value);
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isFirstIteration = true;

        while (true) {

                if (isFirstIteration) {
                    System.out.println(WelcomeMessage.value);
                    isFirstIteration = false;
                }

                System.out.println(MenuSeparator.value);
                System.out.println(Menu.value);
                System.out.println(MenuSeparator.value);
                System.out.println(MenuSearch.value);
                System.out.println(MenuExit.value);
                System.out.println(MenuSeparator.value);
                System.out.print(EnterOption.value);

//                int option = scanner.nextInt();
//
//                scanner.nextLine(); // Consume newline character
                String optionStr = scanner.nextLine();// Read input as string

                if (optionStr.isEmpty()) {
                    System.out.println(EmptyInputError.value);
                    continue; // Restart the loop to prompt the user again
                }
            try {
                int option = Integer.parseInt(optionStr);

                switch (option) {
                    case 1:
                        System.out.print(EnterFoodItem.value);
                        try {
                            String input = scanner.nextLine();

                            if (input.isEmpty()) {
                                throw new NoSuchElementException();
                            }
                            if (!isAlphabetic(input)) {
                                throw new InputMismatchException();
                            }
                            if ("exit".equalsIgnoreCase(input.trim())) {
                                System.out.println(Exiting.value);
                                return;
                            }
                            searchItem(input.trim(), scanner);
                            break;
                        }
                        catch (InputMismatchException e) {
                            System.out.println(E.InputShouldContainOnlyAlphabets.getMessage());
                            scanner.nextLine();
                            continue;
                        }
                        catch (NoSuchElementException e) {
                            System.out.println(E.StringInputCannotBeEmpty.getMessage());
                            // Clear the newline character from the scanner buffer
                            scanner.nextLine();
                            continue;
                        }

                    case 0:
                        System.out.println(Exiting.value);
                        return;
                    default:
                        System.out.println(InvalidOption.value);
                }
            } catch (InputMismatchException e) {
                System.out.println(InvalidInput.value);
                scanner.nextLine(); // Consume the invalid input
            } catch (NoSuchElementException e) {
                System.out.println(EmptyInputError.value);
                scanner.nextLine(); // Consume the newline character
            }
            catch (NumberFormatException e) {
                System.out.println(InvalidInput.value);
            }
            catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }


    //Main Function
    public static void main(String[] args) {

        FoodPriceSite site = new FoodPriceSite();

        site.start();

    }

}
