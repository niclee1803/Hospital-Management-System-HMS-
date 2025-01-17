package filehandlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MedRequestFileHandler} class is responsible for handling the writing and reading of
 * replenishment requests in a CSV file. It extends {@code ItemFileHandler} to reuse file
 * reading and writing functionalities.
 */
public class MedRequestFileHandler extends ItemFileHandler {
    /**
     * Default constructor for {@code MedRequestFileHandler}.
     * Initializes the handler with the predefined file path.
     */
    public MedRequestFileHandler() {
        super("Database/Replenishment_Requests.csv");
    }

    /**
     * Appends a new row to the file.
     * The row is provided as an array with columns: Medicine, Amount, Unit, and Status.
     *
     * @param requestData The data to be added as a row. The array should contain the values:
     *                    [Medicine, Amount, Unit, Status].
     * @throws IOException              if the file cannot be written to.
     * @throws IllegalArgumentException if the input data is invalid.
     */
    public void addRequest(String[] requestData) throws IOException {
        if (requestData == null || requestData.length != 4) {
            throw new IllegalArgumentException("Request data must have exactly 4 elements: Medicine, Amount, Unit, Status.");
        }
    
        // Read existing rows to preserve existing data
        List<String[]> rows = readFile();
    
        // Add the new request as a row
        rows.add(requestData);
    
        // Write all rows back to the file
        writeFile(rows);
    
    }

    /**
     * Reads all replenishment requests from the CSV file.
     *
     * @return a List of String arrays, each representing a row from the file.
     * @throws IOException if an error occurs while reading the file.
     */
    public List<String[]> readRequests() throws IOException {
    List<String[]> requests = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("Database/Replenishment_Requests.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            requests.add(line.split(","));
        }
    }
    return requests;
}

    /**
     * Updates the replenishment requests file with the provided list of requests.
     *
     * @param requests a List of String arrays representing the updated rows.
     * @throws IOException if an error occurs while writing to the file.
     */
    public void updateRequests(List<String[]> requests) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Database/Replenishment_Requests.csv"))) {
        for (String[] request : requests) {
            bw.write(String.join(",", request));
            bw.newLine();
        }
    }
}

    
}
