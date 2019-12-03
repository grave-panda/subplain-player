import java.util.StringTokenizer;

public class Subtitle {
    String text;
    int from;
    int to;

    public Subtitle(String subtitle, int fromMillis, int toMillis) {
        text = subtitle;
        this.from = fromMillis;
        this.to = toMillis;
    }

    public Subtitle(String text, String timestamps) {
        StringTokenizer times = new StringTokenizer(timestamps);
        String[] t1 = times.nextToken().split(";");
        times.nextToken();
        String[] t2 = times.nextToken().split(";");
        int from;
        int to;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFromMillis() {
        return from;
    }

    public void setFromMillis(int from) {
        this.from = from;
    }

    public int getToMillis() {
        return to;
    }

    public void setToMillis(int to) {
        this.to = to;
    }

    public static timeStampToMillis(String timestamp) {

    }
}
