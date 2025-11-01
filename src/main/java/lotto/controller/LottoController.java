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
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

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
//        int purchaseAmount = inputView.readPurchaseAmount();
//        validatePurchaseAmount(purchaseAmount);
//
//        LottoTickets tickets = purchaseLottos(purchaseAmount);
//        outputView.printPurchasedLottos(tickets);
//
//        WinningLotto winning = createWinningLotto();
//
//        LottoResult result = calculateResult(tickets, winning, purchaseAmount);
//        outputView.printStatistics(result);
    }

    private void validatePurchaseAmount(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
        if (amount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 이상이어야 합니다.");
        }
    }

    private int purchaseAmount;

    private LottoTickets purchaseLottos() {
        while (true) {
            try {
                int purchaseAmount = inputView.readPurchaseAmount();
                validatePurchaseAmount(purchaseAmount);

                this.purchaseAmount = purchaseAmount;
                int lottoCount = purchaseAmount / 1000;
                List<Lotto> lottos = lottoGenerator.generate(lottoCount);
                this.purchaseAmount = purchaseAmount;
                return new LottoTickets(lottos);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

//        int lottoCount = purchaseAmonut / 1000;
//        List<Lotto> lottos = lottoGenerator.generate(lottoCount);
//        return new LottoTickets(lottos);
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
//        List<Integer> winningNumbers = inputView.readWinningNumbers();
//        Lotto winningLotto = new Lotto(winningNumbers);
//
//        int bonusNumber = inputView.readBonusNumber();
//        return new WinningLotto(winningLotto, bonusNumber);
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
