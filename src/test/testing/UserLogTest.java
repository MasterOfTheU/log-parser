package testing;

import org.junit.Test;
import parsing.UserLog;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class UserLogTest {

    @Test
    public void getURL() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        final Field field = userLog.getClass().getDeclaredField("URL");
        field.setAccessible(true);
        field.set(userLog, "testURL");
        String actualResult = userLog.getURL();
        assertEquals("testURL", actualResult);
    }

    @Test
    public void getIP() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        final Field field = userLog.getClass().getDeclaredField("IP");
        field.setAccessible(true);
        field.set(userLog, "testIP");
        String actualResult = userLog.getIP();
        assertEquals("testIP", actualResult);
    }

    @Test
    public void getTimeStamp() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        final Field field = userLog.getClass().getDeclaredField("timeStamp");
        field.setAccessible(true);
        field.set(userLog, "11:00");
        String actualResult = userLog.getTimeStamp();
        assertEquals("11:00", actualResult);
    }

    @Test
    public void getTimeSpent() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        final Field field = userLog.getClass().getDeclaredField("timeSpent");
        field.setAccessible(true);
        field.set(userLog, 123);
        long actualResult = userLog.getTimeSpent();
        assertEquals(123, actualResult);
    }

    @Test
    public void setURL() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        userLog.setURL("testURL");
        final Field field = userLog.getClass().getDeclaredField("URL");
        field.setAccessible(true);
        assertEquals("testURL", field.get(userLog));
    }

    @Test
    public void setIP() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        userLog.setIP("testIP");
        final Field field = userLog.getClass().getDeclaredField("IP");
        field.setAccessible(true);
        assertEquals("testIP", field.get(userLog));
    }

    @Test
    public void setTimeStamp() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        userLog.setTimeStamp("11:11");
        final Field field = userLog.getClass().getDeclaredField("timeStamp");
        field.setAccessible(true);
        assertEquals("11:11", field.get(userLog));
    }

    @Test
    public void setTimeSpent() throws NoSuchFieldException, IllegalAccessException {
        UserLog userLog = new UserLog();
        userLog.setTimeSpent(133);
        final Field field = userLog.getClass().getDeclaredField("timeSpent");
        field.setAccessible(true);
        assertEquals(133L, field.get(userLog));
    }

}