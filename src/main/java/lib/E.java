package lib;

public enum E {

    
    StringInputCannotBeEmpty("String input cannot be empty, please try again."),
    InputShouldContainOnlyAlphabets("Input should contain only alphabets.");
    private final String message;
    E(String message) {
        this.message = message;
    }

    public String getMessage() { return this.message; }
}
