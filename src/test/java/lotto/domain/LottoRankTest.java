package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.Test;

public class LottoRankTest {

    @Test
    void _6개_일치_1등() {
        Optional<LottoRank> rank = LottoRank.of(6, false);

        assertThat(rank).contains(LottoRank.FIRST);
    }

    @Test
    void _5개_일치_보너스_일치하면_2등() {
        assertThat(LottoRank.of(5, true)).contains(LottoRank.SECOND);
    }

    @Test
    void _5개_일치_보너스_불일치하면_3등() {
        assertThat(LottoRank.of(5, false)).contains(LottoRank.THIRD);
    }

    @Test
    void _4개_일치_4등() {
        assertThat(LottoRank.of(4, false)).contains(LottoRank.FOURTH);
    }

    @Test
    void _3개_일치_5등() {
        assertThat(LottoRank.of(3, false)).contains(LottoRank.FIFTH);
    }

    @Test
    void _2개_이하_일치_미당첨() {
        assertThat(LottoRank.of(2, false)).isEmpty();
    }

}
