package lotto.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {

    @Test
    void 로또_1장_생성() {
        LottoGenerator generator = new LottoGenerator();
        Lotto lotto = generator.generate();

        assertThat(lotto.getNumbers()).hasSize(6);
    }

    @Test
    void 로또_여러_장_생성() {
        LottoGenerator generator = new LottoGenerator();
        List<Lotto> lottos = generator.generate(5);

        assertThat(lottos).hasSize(5);

    }
}
