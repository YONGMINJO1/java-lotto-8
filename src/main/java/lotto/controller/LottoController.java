package lotto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lotto.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.utils.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private int purchaseAmount;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        LottoTickets tickets = purchaseLottos();
        outputView.printPurchasedLottos(tickets);

        WinningLotto winning = createWinningLotto();

        LottoResult result = calculateResult(tickets, winning);
        outputView.printStatistics(result);
    }

    private LottoTickets purchaseLottos() {
        int amount = inputView.readPurchaseAmount();
        this.purchaseAmount = amount;

        int lottoCount = amount / LOTTO_PRICE;
        List<Lotto> lottos = lottoGenerator.generate(lottoCount);
        return new LottoTickets(lottos);
    }

    private LottoTickets generateLottoTickets(int amount) {
        int lottoCount = amount / LOTTO_PRICE;
        List<Lotto> lottos = lottoGenerator.generate(lottoCount);
        return new LottoTickets(lottos);
    }

    private WinningLotto createWinningLotto() {
        while (true) {
            try {
                List<Integer> winningNumbers = inputView.readWinningNumbers();
                Lotto winningLotto = new Lotto(winningNumbers);

                int bonusNumber = inputView.readBonusNumber();
                return new WinningLotto(winningLotto, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private LottoResult calculateResult(LottoTickets tickets, WinningLotto winning) {
        Map<LottoRank, Integer> rankCounts = calculateRanks(tickets, winning);
        return new LottoResult(rankCounts, purchaseAmount);
    }

    private Map<LottoRank, Integer> calculateRanks(LottoTickets tickets, WinningLotto winning) {
        Map<LottoRank, Integer> rankCounts = new HashMap<>();

        for (Lotto lotto : tickets.getLottos()) {
            int matchCount = winning.countMatches(lotto);
            boolean bonusMatch = lotto.contains(winning.getBonusNumber());

            Optional<LottoRank> rank = LottoRank.of(matchCount, bonusMatch);
            rank.ifPresent(r -> rankCounts.put(r, rankCounts.getOrDefault(r, 0) + 1));
        }
        return rankCounts;
    }
}
