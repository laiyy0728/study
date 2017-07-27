package stream.entity;

/**
 * Created by laiyy
 * Date 2017/7/20.
 */
public class Transaction {
    private Trader trader;

    private int year;

    private int value;

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Transaction(Trader trader, int year, int value) {

        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Transaction() {

    }
}
