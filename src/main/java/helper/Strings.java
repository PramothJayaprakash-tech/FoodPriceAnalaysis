package helper;

public enum Strings {
    //- General Strings

    MenuSeparator("|===================================================|"),
    AllProducts("|                   ALL PRODUCTS                    |"),

    LookingForProducts("|                Are you looking for:               | "),

    NoSimilarWords("No similar items found. Please try another search."),
    Space("                                                     "),
    SorryWordCompletion("Sorry! Couldn't complete the word :( "),
    WelcomeMessage("Welcome to Food Explorer!!"),
    Menu("|                      Menu                         |"),
    MenuSearch("|   1. Search Details                               |"),
    MenuExit("|   0. Exit                                         |"),
    EnterOption("> Enter an option: "),
    EmptyInputError("Input cannot be empty."),
    EnterFoodItem("Enter the food item name to search for (or 'exit' to quit): "),
    Exiting("Exiting Food Explorer. Goodbye!"),
    InvalidOption("Invalid option. Please enter a valid option."),
    InvalidInput("Invalid input. Please enter 1 or 0 to continue:)"),
    AutoComplete("Autocomplete - Did you mean:"),
    ;
    public final String value;

    Strings() {
        this.value = "";
    }
    Strings(String value) {
        this.value = value;
    }


}
