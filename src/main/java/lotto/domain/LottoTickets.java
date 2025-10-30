package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.Lotto;

public class LottoTickets {
    private final List<Lotto> lottos;

    public LottoTickets(List<Lotto> lottos) {
        validateNull(lottos);
        validateNotEmpty(lottos);
        this.lottos = new ArrayList<>(lottos);
    }

    private void validateNull(List<Lotto> lottos) {
        if (lottos == null) {
            throw new IllegalArgumentException("[ERROR] 로또 목록이 null일 수 없습니다.");
        }
    }

    private void validateNotEmpty(List<Lotto> lottos) {
        if (lottos.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 로또는 최소 1장 이상이어야 합니다.");
        }
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
