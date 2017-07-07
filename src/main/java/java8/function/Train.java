package java8.function;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by tianhe on 2017/6/20.
 */
public class Train {
    static class Trader{
        private final String name;
        private final String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }


        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }
    }

    static class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "trader=" + trader +
                    ", year=" + year +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("mario","Milan");
        Trader alan = new Trader("alan","Cambridge");
        Trader brian = new Trader("brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011,300),
                new Transaction(raoul, 2012,1000),
                new Transaction(raoul, 2011,400),
                new Transaction(mario, 2012,710),
                new Transaction(mario, 2012,700),
                new Transaction(alan, 2012,950)
        );

        List<Transaction> transactionList = transactions.stream().filter((Transaction t)-> t.getYear()==2011).sorted(Comparator.comparing( Transaction::getYear )).collect(Collectors.toList());
        System.out.println(transactionList);
        List<String> cityName = transactions.stream().map(t->t.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(cityName);
        List<String> tradeName = transactions.stream().filter(t->t.getTrader().getCity()=="Cambridge").map(t->t.getTrader().getName()).sorted(Comparator.comparing(t->t)).collect(Collectors.toList());
        System.out.println(tradeName);

    }
}
