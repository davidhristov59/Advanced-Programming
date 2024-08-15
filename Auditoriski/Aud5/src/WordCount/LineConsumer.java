package WordCount;

import java.util.function.Consumer;

public class LineConsumer implements Consumer<String> {

    int lines = 0, words =0 , chars = 0;

    @Override
    public void accept(String string) {
        ++lines;
        words+=string.split("\\s+").length;
        chars += string.length();
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Chars: %d", lines,words,chars);
    }
}