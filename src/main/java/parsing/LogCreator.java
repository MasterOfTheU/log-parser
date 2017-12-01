package parsing;

import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class LogCreator {

    /**
     * @param CSVlog CSV string that will be parsed to JSON.
     * @return Document that will be inserted into database collection.
     * @see DBConnector#insertDocumentsToDB()
     */
    public Document convertCSVtoJSON(String CSVlog) {
        List<String> values = Arrays.asList(CSVlog.split(","));
        UserLog userLog = new UserLog();
        userLog.setURL(values.get(0));
        userLog.setIP(values.get(1));
        userLog.setTimeStamp(values.get(2));
        userLog.setTimeSpent(Long.valueOf(values.get(3)));

        Document doc = new Document()
                .append("URL", userLog.getURL())
                .append("IP", userLog.getIP())
                .append("timeStamp", userLog.getTimeStamp())
                .append("timeSpent", userLog.getTimeSpent());

        return doc;
    }

    /**
     * @param filename Name of csv file with logs.
     * List of string logs is read from file. Each string is converted to JSON and will be inserted to database collection.
     * @return List of JSON Documents.
     *
     * @see DBConnector#insertDocumentsToDB()
     */
    public static ArrayList<Document> createJSONlistFromSCV(String filename) {
        ArrayList<Document> listJSONdocs = new ArrayList<>();
        ArrayList<String> listCSVlogs = new ArrayList<>();
        LogCreator creator = new LogCreator();

        try(Stream<String> stream = Files.lines(Paths.get(filename))){
            stream.forEach(listCSVlogs::add);
        }
        catch (IOException e) {
            System.out.println("Cannot read from file.");
        }

        listCSVlogs.forEach(csvLog -> {
            Document jsonDoc = creator.convertCSVtoJSON(csvLog);
            listJSONdocs.add(jsonDoc);
        });

        listJSONdocs.forEach(jsonDoc -> System.out.printf("json doc %d: %s\n", listJSONdocs.indexOf(jsonDoc)+1, jsonDoc.toString()));
        return listJSONdocs;
    }

}