package chat1.inter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by laiyy
 * Date 2017/7/7.
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
