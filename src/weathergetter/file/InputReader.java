package weathergetter.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by admin on 20.12.2017.
 */
public class InputReader {

    public Stream<String> readFromURL(String url) throws IOException {
        return Files.lines(Paths.get(url));
    }
}
