package rapidfeedback.backend.initial.CommonTools.CompletableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @author cyt
 * @date 2020/5/4
 */
public class CompletableFutureTool {

    public static <T> CompletableFuture<List<T>> allOf(List<CompletableFuture<T>> futureList) {

        CompletableFuture<Void> allFutureResult =
                CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{}));

        return allFutureResult.thenApply(v ->
                futureList.stream()
                        .map(future -> future.join())
                        .collect(Collectors.<T>toList()));

    }

}

