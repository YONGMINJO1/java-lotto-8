package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.Lotto;
import org.junit.jupiter.api.Test;

public class LottoTicketsTest {

    @Test
    void 로또_여러장_구매() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );

        LottoTickets tickets = new LottoTickets(lottos);
        assertThat(tickets.size()).isEqualTo(2);
    }

    @Test
    void 빈_리스트로_생성_시_예외_발생() {
        assertThatThrownBy(() -> new LottoTickets(List.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void null로_생성_시_예외_발생() {
        assertThatThrownBy(() -> new LottoTickets(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 로또_목록_조회() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6))
        );
        LottoTickets tickets = new LottoTickets(lottos);

        assertThat(tickets.getLottos()).hasSize(1);
    }

}
