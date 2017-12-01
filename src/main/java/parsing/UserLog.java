package parsing;

public class UserLog {

    private String URL;
    private String IP;
    private String timeStamp;
    private long timeSpent;

    public UserLog() {}

    public UserLog(String URL, String IP, String timeStamp, long timeSpent) {
        this.URL = URL;
        this.IP = IP;
        this.timeStamp = timeStamp;
        this.timeSpent = timeSpent;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }
}

