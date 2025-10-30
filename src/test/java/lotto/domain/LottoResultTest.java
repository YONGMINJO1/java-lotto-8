package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

public class LottoResultTest {

    @Test
    void 특정_등수_개수_조회() {
        Map<LottoRank, Integer> counts = Map.of(
                LottoRank.FIFTH, 1,
                LottoRank.FOURTH, 2
        );
        LottoResult result = new LottoResult(counts, 10000);

        assertThat(result.getCountByRank(LottoRank.FIFTH)).isEqualTo(1);
        assertThat(result.getCountByRank(LottoRank.FOURTH)).isEqualTo(2);

    }

    @Test
    void 총_상금_계산() {
        Map<LottoRank, Integer> counts = Map.of(
                LottoRank.FIFTH, 2,
                LottoRank.FOURTH, 1
        );
        LottoResult result = new LottoResult(counts, 10000);

        assertThat(result.getTotalPrizeMoney()).isEqualTo(60000);
    }

    @Test
    void 수익률_계산() {
        Map<LottoRank, Integer> counts = Map.of(
                LottoRank.FIFTH, 1
        );
        LottoResult result = new LottoResult(counts, 8000);

        assertThat(result.getProfitRate()).isEqualTo(62.5);
    }
}
