package lotto.view;

import lotto.Lotto;
import lotto.domain.LottoTickets;

public class OutputView {

    public void printPurchasedLottos(LottoTickets tickets) {
        System.out.println();
        System.out.println(tickets.size() + "개를 구매했습니다.");

        for (Lotto lotto : tickets.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }
}
