package lotto.domain;

import java.util.HashMap;
import java.util.Map;

public class LottoResult {
    private final Map<LottoRank, Integer> rankCounts;
    private final int purchaseAmount;

    public LottoResult(Map<LottoRank, Integer> rankCounts, int purchaseAmount) {
        this.rankCounts = new HashMap<>(rankCounts);
        this.purchaseAmount = purchaseAmount;
    }

    public int getCountByRank(LottoRank rank) {
        return rankCounts.getOrDefault(rank, 0);
    }

    public long getTotalPrizeMoney() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}
