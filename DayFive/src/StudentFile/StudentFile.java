package StudentFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StudentFile {

    public static void main(String[] args) {
        String filePath1 = "src/StudentFile/student.csv";
        String filePath2 = "src/StudentFile/student.json";
        String line;
        String delimiter = ",";
        boolean firstStudent = true;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath1));
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath2))){
            br.readLine(); // skips line 1
            bw.write("[");
            bw.newLine();

            while((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                if (!firstStudent) {
                    bw.write(",");
                    bw.newLine();
                }
                bw.write("  {");
                bw.newLine();
                
                bw.write("    \"id\": \"" + values[0] + "\",");
                bw.newLine();
                bw.write("    \"name\": \"" + values[1] + "\",");
                bw.newLine();
                bw.write("    \"course\": \"" + values[2] + "\"");
                bw.newLine();
                bw.write("  }");

                firstStudent = false;
            }
            bw.newLine();   
            bw.write("]");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}