package helper;

import org.example.Product;

import java.util.*;

/**
 * This class implements an inverted index for products.
 * It allows building an index from a list of products and querying for products based on keywords.
 */
public class InvertedIndex {

    // Map to store the inverted index where the key is a word and the value is a set of products containing that word
    private Map<String, Set<Product>> index;

    /**
     * Constructs an InvertedIndex object.
     */
    public InvertedIndex() {
        index = new HashMap<>();
    }

    /**
     * Builds the inverted index from a list of products.
     *
     * @param productList the list of products to build the index from
     */
    public void buildIndex(List<Product> productList) {
        for (Product product : productList) {
            // Get the product name and convert it to lowercase
            String productName = product.getProductName().toLowerCase();

            // Split the product name into individual words
            String[] words = productName.split("\\s+");
            for (String word : words) {
                // Convert the word to lowercase
                word = word.toLowerCase();

                // Add the product to the set associated with the word in the index map
                index.putIfAbsent(word, new HashSet<>());
                index.get(word).add(product);
            }
        }
    }

    /**
     * Retrieves products containing the specified keywords.
     *
     * @param query the query string containing keywords separated by spaces
     * @return a set of products containing all the keywords in the query
     */
    public Set<Product> getProducts(String query) {
        // Initialize a result set to store products matching the query
        HashSet<Product> resultSet = new HashSet<>();

        // Split the query into individual keywords
        for (String s : query.split(" ")) {
            // Retrieve the set of products associated with the keyword from the index
            HashSet<Product> tempSet = (HashSet<Product>) index.getOrDefault(s.toLowerCase(), new HashSet<>());

            // If it's the first keyword, assign the result set to the set of products associated with the keyword
            if (resultSet.size() == 0) {
                resultSet = tempSet;
            } else {
                // Intersect the result set with the set of products associated with the keyword
                resultSet.retainAll(tempSet);
            }
        }
        return resultSet;
    }
}
