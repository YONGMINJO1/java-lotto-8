package lotto.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000);

    private final int matchCount;
    private final boolean requireBonus;
    private final long prizeMoney;

    LottoRank(int matchCount, boolean requireBonus, long prizeMoney) {
        this.matchCount = matchCount;
        this.requireBonus = requireBonus;
        this.prizeMoney = prizeMoney;
    }

    public static Optional<LottoRank> of(int matchCount, boolean bonusMatch) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .filter(rank-> !rank.requireBonus || bonusMatch)
                .findFirst();
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
