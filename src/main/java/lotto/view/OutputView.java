package lotto.view;

import lotto.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;

public class OutputView {

    public void printPurchasedLottos(LottoTickets tickets) {
        System.out.println();
        System.out.println(tickets.size() + "개를 구매했습니다.");

        for (Lotto lotto : tickets.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printStatistics(LottoResult result) {

        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        printRankStatistics(result);
        printProfitRate(result);
    }

    private void printRankStatistics(LottoResult result) {
        LottoRank[] ranks = LottoRank.values();

        for (int i = ranks.length - 1; i >= 0; i--) {
            LottoRank rank = ranks[i];
            int count = result.getCountByRank(rank);

            printRankLine(rank, count);
        }
    }

    private void printRankLine(LottoRank rank, int count) {
        String matchInfo = getMatchInfo(rank);
        String prizeInfo = String.format("%,d", rank.getPrizeMoney());

        System.out.printf("%s (%s원) - %d개%n", matchInfo, prizeInfo, count);

    }

    private String getMatchInfo(LottoRank rank) {
        if (rank.isRequireBonus()) {
            return rank.getMatchCount() + "개 일치, 보너스 볼 일치";
        }
        return rank.getMatchCount() + "개 일치";
    }

    private void printProfitRate(LottoResult result) {
        double rate = result.getProfitRate();
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
