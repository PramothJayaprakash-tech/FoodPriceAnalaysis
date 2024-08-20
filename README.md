# Food Price Analysis

## Project Overview

Welcome to the Food Price Analysis project! This project is designed to help you easily compare food prices across different stores. Whether you're hunting for the best deals or tracking how prices change over time, this tool has you covered. We’re working with a JSON data file that includes details like store names, product prices, and product names. Plus, we’ve added web scraping to keep the data fresh and accurate.

## Key Components

### 1. Data Source
At the core of this project is the `Food_Info_Data.json` file. It’s packed with information about various food items, their prices, and the stores where you can find them.

### 2. Dependencies
To make everything work seamlessly, we’ve included a few important tools:
- **`jsoup`**: Our go-to library for parsing HTML, perfect for web scraping tasks.
- **`json-simple`**: A simple yet powerful library that helps us handle and parse JSON data.
- **Selenium**: This tool automates web browsers, allowing us to scrape dynamic content from websites as needed.

### 3. Functionality
This project incorporates several cool features to handle and analyze the data effectively:

- **Frequency Count**: We’ve got a function that checks how often certain products appear in the dataset, giving you insights into popular items or common price points.
- **Page Ranking**: We rank items based on criteria like price or popularity, making it easier for you to find the best options quickly.
- **Web Scraping**: We use web scraping to pull in real-time data from various websites, ensuring that our price information is always up-to-date.
- **Data Validation Using Regular Expressions**: To keep the data clean and accurate, we use regular expressions to validate entries, making sure everything fits the expected format.
- **Spell Checking**: We’ve added spell-checking to ensure that product names and other text data are correctly spelled, which reduces errors in the dataset.
- **Search Frequency**: You can search for specific terms and see how often they pop up in the data, which is great for spotting trends.
- **Finding Patterns Using Regular Expressions**: We use regular expressions to find and analyze patterns in the data, like specific pricing formats or product categories.

### 4. Data Structures
We’ve used some core data structure concepts to keep everything organized and running smoothly. Arrays, lists, and dictionaries (or maps) are key to storing, sorting, and filtering the data.

## How It Works
1. **Data Input**: The program kicks off by reading the `Food_Info_Data.json` file to gather all the information.
2. **Optional Web Scraping**: If we need more data or want to update what we have, the program can use web scraping to fetch it.
3. **Analysis and Output**: Finally, the program analyzes the data to help you find the best deals, track price trends, or create helpful reports.

## Why It’s Useful
This project is a handy tool for anyone who wants to be a savvy shopper. By comparing prices across different stores and tracking how they change over time, you can make informed decisions and save money on your groceries.

## Authors
**Pramoth Jayaprakash**

