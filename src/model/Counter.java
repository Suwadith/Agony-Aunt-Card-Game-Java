package model;

public class Counter {

    private CounterColor counterColor;

    public Counter(CounterColor counterColor) {
        this.counterColor = counterColor;
    }

    public CounterColor getCounterColor() {
        return counterColor;
    }
}
