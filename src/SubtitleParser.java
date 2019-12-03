import java.io.*;
import java.util.Iterator;

public class SubtitleParser implements Iterator {
    BufferedReader srtFile;

    private Subtitle next;
    boolean hasNext = true;

    public SubtitleParser(File subtitle_location) throws IOException {
        srtFile = new BufferedReader(new FileReader(subtitle_location));

        try {
            readNextSubtitle();
        } catch (IOException e) {
            hasNext = false;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public Object next() throws IOException{
        Subtitle temp = next;
        next = readNextSubtitle();
        return temp;
    }

    private void readNextSubtitle() throws IOException{
        srtFile.readLine();
        String times = srtFile.readLine();
        String text = "";

        String temp = "";
        while ((temp = srtFile.readLine()) != null && !temp.equals("\n")) text += temp;
        if (temp == null) hasNext = false;

        next = new Subtitle(text, times);
    }
}
