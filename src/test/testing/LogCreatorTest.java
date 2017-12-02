package testing;

import org.bson.Document;
import org.junit.BeforeClass;
import org.junit.Test;
import parsing.LogCreator;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LogCreatorTest {

    private static LogCreator logCreator;

    @BeforeClass
    public static void init() {
        logCreator = new LogCreator();
    }

    @Test
    public void testConvertCSVtoJSON() throws Exception {
        String testLog = "https://stackoverflow.com/,3.3.3.3,13:50,119";
        assertNotNull(logCreator.convertCSVtoJSON(testLog));
    }

    @Test
    public void testCreateJSONlistFromSCV() throws IOException {
        ArrayList<Document> listJSONdocs= LogCreator.createJSONlistFromSCV("test.csv");
        assertNotNull(listJSONdocs);
    }

}