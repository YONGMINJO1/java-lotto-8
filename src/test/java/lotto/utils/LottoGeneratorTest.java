package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.Lotto;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    void 로또_1장_생성() {
        LottoGenerator generator = new LottoGenerator();
        Lotto lotto = generator.generate();

        assertThat(lotto.getNumbers()).hasSize(6);
    }
}
