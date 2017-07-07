package future;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/*
 * Created by tianhe on 2017/6/22.
 */
public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture a = new CompletableFuture();
        LocalDate today = LocalDate.now();
    }
}
