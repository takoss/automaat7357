package weathergetter.file;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by admin on 20.12.2017.
 */
public class OutputWriter {

    public void write(String str, String outputUrl) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(outputUrl), "utf-8"))) {
            writer.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
