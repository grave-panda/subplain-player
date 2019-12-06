import java.io.BufferedReader;
import java.io.FileReader;

public class Dictionary {
    static String find(String query) {
        if (query == null || query.isEmpty()) return "NA";
        try {
            BufferedReader indices = new BufferedReader(new FileReader("dict/indices.dat"));
            BufferedReader words = null;
            String st;
            while ((st = indices.readLine()) != null) {
                if (query.compareTo(st.split(" ")[0]) >= 0) {
                    System.out.println(st.split(" ")[0]);
                    words = new BufferedReader(new FileReader("dict/"+Integer.parseInt(st.split(" ")[1])+".dat"));
                    break;
                }
            }
            indices.close();
            if (words == null) return "Not found";

            while ((st = words.readLine()) != null) {
                if (st.split(" \\|")[0].equals(query)) {
                    words.close();
                    return st.split(" \\|")[1];
                }
            }
            words.close();
            return "Not found";
        } catch(Exception e) {
            e.printStackTrace();
            return "Error retriving data";
        }
    }
}
