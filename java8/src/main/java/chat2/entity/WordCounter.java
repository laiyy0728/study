package chat2.entity;

/**
 * 计算字数
 * Created by laiyy
 * Date 2017/7/24.
 */
public class WordCounter {

    private final int counter;

    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    @Override
    public String toString() {
        return "WordCounter{" +
                "counter=" + counter +
                ", lastSpace=" + lastSpace +
                '}';
    }

    /**
     * 定义了如何更改 WordCounter 的状态（用哪个状态建立新的 WordCounter）
     * @param character
     * @return
     */
    public WordCounter accumulate(Character character) {
        if (Character.isWhitespace(character)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            // 当上一个字符是空格，新字符串不是空格时，计数器加一
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        // 将两个不同的子部分的两个 WordCounter 的部分进行汇总。也就是把计数器加起来
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
