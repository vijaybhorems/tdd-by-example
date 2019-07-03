package guru.springframework;

public class Money implements Expression {

    final int amount;
    private final String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    String currency() {
        return currency;
    }

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money)object;
        return this.amount ==  money.amount
                && this.currency.equals(money.currency);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        return new Money(amount / bank.rate(this.currency, to), to);
    }

    @Override
    public Money times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }

    public Expression plus(Expression addmend) {
        return new Sum(this, addmend);
    }

}
