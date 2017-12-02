package parsing;

import com.mongodb.*;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;
import static parsing.LogCreator.createJSONlistFromSCV;

public class DBConnector {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    /**
     * Connects to Mongo through default port on localhost. Gets the collection of user logs.
     */
    public void connectToDB() {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("userlogs");
        collection = database.getCollection("logs");
        System.out.println("Successfully connected to database.");
    }

    /**
     * Reads the file with logs and puts all logs into collection.
     */
    public void insertDocumentsToDB(){
        ArrayList<Document> listJSONdocs = createJSONlistFromSCV("logs.csv");
        collection.insertMany(listJSONdocs);
        System.out.println("Documents were inserted to Database.");
        mongoClient.close();
    }

    //region Mongo Selection Tools

    /**
     * @param URLvalue URL string for query.
     * Prints ascending ordered IP list of logs where URL is URLvalue.
     */
    public Iterator<Document> getOrderedIPListByGivenURL(String URLvalue) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

        MongoCursor<Document> mongoCursor = collection.find(new BasicDBObject("URL", URLvalue)).projection(include("IP", "URL", "timeStamp"))
                .projection(fields(include("IP", "timeSpent"), excludeId()))
                .sort(ascending("IP")).iterator();

        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next().toJson()
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }"));
        }

        return mongoCursor;
    }

    /**
     * @param startTime Start time stamp for query.
     * @param endTime End time stamp for query.
     * Prints ascending ordered URL list of logs in the period of time from startTime to endTime sorted by spent time.
     */
    public Iterator<Document> getOrderedURLListByTimeStamp(String startTime, String endTime) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        MongoCursor<Document> mongoCursor = collection.find(new BasicDBObject("timeStamp", new BasicDBObject("$gte", startTime).append("$lte", endTime)))
                .projection(fields(include("IP", "timeSpent"), excludeId()))
                .sort(ascending("timeSpent")).iterator();

        while (mongoCursor.hasNext()) {
            System.out.println(mongoCursor.next().toJson()
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }"));
        }

        return mongoCursor;
    }

    /**
     * @param IP string for query.
     * Prints ascending ordered URL list of logs with given IP address sorted by spent time.
     */
    public Iterator<Document> getOrderedURLListByGivenIP(String IP) {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        MongoCursor<Document> mongoCursor = collection.find(new BasicDBObject("IP", IP))
                .projection(fields(include("IP", "URL", "timeSpent"), excludeId()))
                .sort(ascending("timeSpent")).iterator();
        String docJson;
        while (mongoCursor.hasNext()) {
            docJson = mongoCursor.next().toJson()
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }");
            System.out.println(docJson);
        }

        return mongoCursor;
    }

    //endregion

    //region Queries with usage of Map Reduce paradigm
    /**
     * Prints descending ordered URL list of logs with total duration of URL visit.
     * The result of mapReduce method is written into new collection to avoid changes in global original collection.
     * Fields of new collection are replaced with proper values for printing them to concole.
     */
    public MongoCursor<Document> getDescendingURLListByTimeSpent() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String map = "function() { emit(this.URL, this.timeSpent);}";
        String reduce = "function(key, values) { return Array.sum(values); }";
        String collectionURLTimeSpent = "url_timespent";
        collection.mapReduce(map, reduce).collectionName(collectionURLTimeSpent).toCollection();
        MongoCollection<Document> collectionTimeSpent = database.getCollection(collectionURLTimeSpent);

        MongoCursor<Document> mongoCursor = collectionTimeSpent.find().sort(descending("value")).iterator();
        String docJson;
        while (mongoCursor.hasNext()) {
            docJson = mongoCursor.next().toJson()
                    .replace("_id", "url")
                    .replace("value", "timeSpent")
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }");
            System.out.println(docJson);
        }

        return mongoCursor;
    }

    /**
     * Prints descending ordered URL list of logs with total number of url visits.
     */
    public MongoCursor<Document> getDescendingURLListByVisitsNumber() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String map = "function() { emit(this.URL, 1);}";
        String reduce = "function(key, values) { return Array.sum(values); }";
        String collectionURLVisits = "url_visits";
        collection.mapReduce(map, reduce).collectionName(collectionURLVisits).toCollection();
        MongoCollection<Document> collectionVisitsNumber = database.getCollection(collectionURLVisits);

        MongoCursor<Document> mongoCursor = collectionVisitsNumber.find().sort(descending("value")).iterator();
        String docJson;
        while (mongoCursor.hasNext()) {
            docJson = mongoCursor.next().toJson()
                    .replace("_id", "url")
                    .replace("value", "visits")
                    .replace(".0", "")
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }");
            System.out.println(docJson);
        }

        return mongoCursor;
    }

    /**
     * @param startTime Start time stamp for query.
     * @param endTime End time stamp for query.
     *                Prints sorted list of logs in specified time period.
     */
    public MongoCursor<Document> getDescendingURLListInTimePeriod(String startTime, String endTime){
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String map = "function() { "
                + " var timeStamp = this.timeStamp; "
                + " if ( (timeStamp >=" + "\"" +startTime + "\"" + ") && (" + "timeStamp <="+ "\"" + endTime+ "\"" + ") )"
                + "  emit(this.URL, 1);  "
                + " }";
        String reduce = "function(key, values) { return Array.sum(values); }";
        String collectionTimePeriod = "url_timeperiod";
        collection.mapReduce(map, reduce).collectionName(collectionTimePeriod).toCollection();
        MongoCollection<Document> collectionInTimePeriod = database.getCollection(collectionTimePeriod);

        MongoCursor<Document> mongoCursor = collectionInTimePeriod.find()
                .sort(ascending("URL"))
                .sort(descending("value"))
                .iterator();
        String docJson;
        while (mongoCursor.hasNext()) {
            docJson = mongoCursor.next().toJson()
                    .replace("_id", "url")
                    .replace("value", "visits")
                    .replace(".0", "")
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }");
            System.out.println(docJson);
        }

        return mongoCursor;
    }

    /**
     *  Prints descending ordered IP list of logs sorted by spent time.
     */
    public MongoCursor<Document> getDescendingIPLListByVisitsAndTime(){
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
        String map = "function() { emit( this.IP, this.timeSpent); }";
        String reduce = "function(key, values) { return {count:values.length, timeSpent:Array.sum(values)}; }";
        String collectionVisitsTime = "ip_visits_time";
        collection.mapReduce(map, reduce).collectionName(collectionVisitsTime).toCollection();
        MongoCollection<Document> collectionVisitsAndTime = database.getCollection(collectionVisitsTime);

        MongoCursor<Document> mongoCursor = collectionVisitsAndTime.find()
                .sort(ascending("_id"))
                .sort(descending("value"))
                .iterator();
        String docJson;
        while (mongoCursor.hasNext()) {
            docJson = mongoCursor.next().toJson()
                    .replace("_id", "ip")
                    .replace("value", "timeSpent")
                    .replace("{ \"$numberLong\" : \"", "")
                    .replace("\" } }", " }");
            System.out.println(docJson);
        }

        mongoClient.close();
        return mongoCursor;
    }
    //endregion

}
