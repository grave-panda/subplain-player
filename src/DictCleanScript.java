import java.io.*;

public class DictCleanScript {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dict/cleaned.csv"));
            BufferedWriter indices = new BufferedWriter(new FileWriter("dict/indices.dat"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("dict/0.dat"));

            String st;
            try {
                int i = 1;
                int sr = 0;
                indices.write("a 0\n");
                while ((st = reader.readLine()) != null) {
                    try {
                        String x = st.split("\\|")[1];
                        writer.write(st);
                        writer.write('\n');
                        if (i == 512) {
                            sr++;
                            i = 0;
                            String s = st.split("\\|")[0].trim();
                            indices.write(s + " " + Integer.toString(sr) + "\n");
                            writer.close();
                            writer = new BufferedWriter(new FileWriter("dict/"+Integer.toString(sr)+".dat"));
                        }
                        i++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                reader.close();
                writer.close();
                indices.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (Exception w){
            w.printStackTrace();
        }
    }
}
