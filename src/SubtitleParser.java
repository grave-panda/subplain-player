import java.io.*;
import java.util.Iterator;

public class SubtitleParser implements Iterator {
    BufferedReader srtFile;

    private Subtitle next;
    private boolean hasNext = true;

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
    public Subtitle next(){
        Subtitle temp = next;
        try {
            readNextSubtitle();
        } catch (IOException e) {
            hasNext = false;
        }
        return temp;
    }

    private void readNextSubtitle() throws IOException{
        if (srtFile.readLine() == null) {
            hasNext = false;
            return;
        }
        String times = srtFile.readLine();
        String text = "";

        String temp = "";
        while ((temp = srtFile.readLine()) != null && !temp.isEmpty()){
            text += temp;
        }

        next = new Subtitle(text, times);
    }
}
