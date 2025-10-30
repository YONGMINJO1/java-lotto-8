package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void 일치하는_번호_개수_반환() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 7, 8, 9)),
                10
        );

        int count = winningLotto.countMatches(lotto);

        assertThat(count).isEqualTo(3);
    }
}
