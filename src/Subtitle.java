import java.util.StringTokenizer;

public class Subtitle {
    private String text;
    private int from;
    private int to;

    public Subtitle(String subtitle, int fromMillis, int toMillis) {
        text = subtitle;
        this.from = fromMillis;
        this.to = toMillis;
    }

    public Subtitle(String subtitle, String timestamps) {
        StringTokenizer times = new StringTokenizer(timestamps);
        String[] t1 = times.nextToken().split(",");
        times.nextToken();
        String[] t2 = times.nextToken().split(",");
        String[] time1 = t1[0].split(":");
        String[] time2 = t2[0].split(":");
        int from = Integer.parseInt(t1[1]);
        int to = Integer.parseInt(t2[1]);
        from+=Integer.parseInt(time1[2])*1000;
        from+=Integer.parseInt(time1[1])*1000*60;
        from+=Integer.parseInt(time1[0])*1000*60*60;
        to+=Integer.parseInt(time2[2])*1000;
        to+=Integer.parseInt(time2[1])*1000*60;
        to+=Integer.parseInt(time2[0])*1000*60*60;

        text = subtitle;
        this.from = from;
        this.to = to;
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
}
