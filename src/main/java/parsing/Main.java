package parsing;

public class Main {

    public static void main(String[] args) {
        //Comment line below to avoid second write of same logs to db
        //createLogs();
        createMongoQueries();
        createMapReduceMongoQueries();
    }

    /**
     * Opens the connection to database.
     * Reads the file and parse csv logs to JSON format.
     * Converted JSON objects are put into database.
     * @see DBConnector#insertDocumentsToDB()
     */
    private static void createLogs(){
        DBConnector dbConnector = new DBConnector();
        dbConnector.connectToDB();
        dbConnector.insertDocumentsToDB();
    }

    /**
     * Creates queries provided by MongoDB selecting tools.
     */
    private static void createMongoQueries() {
        DBConnector dbConnector = new DBConnector();
        dbConnector.connectToDB();
        dbConnector.getOrderedIPListByGivenURL("https://docs.oracle.com/");
        dbConnector.getOrderedURLListByTimeStamp("10:10", "18:00");
        dbConnector.getOrderedURLListByGivenIP("252.73.135.153");
    }

    private static void createMapReduceMongoQueries() {
        DBConnector dbConnector = new DBConnector();
        dbConnector.connectToDB();
        dbConnector.getDescendingURLListByTimeSpent();
        dbConnector.getDescendingURLListByVisitsNumber();
        dbConnector.getDescendingURLListInTimePeriod("11:00", "18:00");
        dbConnector.getDescendingIPLListByVisitsAndTime();
    }

}