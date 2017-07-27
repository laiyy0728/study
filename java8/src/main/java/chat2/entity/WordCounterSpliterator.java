package chat2.entity;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by laiyy
 * Date 2017/7/24.
 */
public class WordCounterSpliterator implements Spliterator<Character> {

    private final String string;

    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    /**
     * 把 string 中 当前位置的 Character 传给 Consumer，位置加一
     * 里面有一个归约函数，即 WordCounter 类的 accumulate 方法
     * 如果新的指针位置小于 string 的总长，且还有要遍历的 Character，则 返回 true
     *
     * @param action
     * @return
     */
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length();
    }

    /**
     * 首先要设定不再进一步拆分的下限，如果剩余的 Character 数量低于下限，就返回 null 表示无需进一步拆分
     * 如果需要执行拆分，就把试探的拆分位置设在要解析的String块的中间，但我
     * 们没有直接使用这个拆分位置，因为要避免把词在中间断开，于是就往前找，直到找到
     * 一个空格。一旦找到了适当的拆分位置，就可以创建一个新的 Spliterator 来遍历从
     * 当前位置到拆分位置的子串；把当前位置this设为拆分位置，因为之前的部分将由新
     * Spliterator 来处理，最后返回
     *
     * @return
     */
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            if (Character.isWhitespace(string.charAt(splitPos))) {
                WordCounterSpliterator spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    /**
     * 还需要遍历的元素的estimatedSize就是这个Spliterator解析的String的总长度和
     * 当前遍历的位置的差
     *
     * @return
     */
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    /**
     * characteristic方法告诉框架这个Spliterator是ORDERED（顺序就是String
     * 中 各 个Character 的 次序 ）、 SIZED （estimatedSize 方 法的 返 回 值 是精 确 的 ）、
     * SUBSIZED（trySplit方法创建的其他Spliterator也有确切大小）、 NONNULL（String
     * 中 不 能 有 为 null 的 Character ） 和 IMMUTABLE （ 在 解 析 String 时 不 能 再 添 加
     * Character，因为String本身是一个不可变类）的
     *
     * @return
     */
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
