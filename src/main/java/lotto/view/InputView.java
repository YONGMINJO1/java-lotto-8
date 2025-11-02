package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    private static final int LOTTO_PRICE = 1000;

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        while (true) {
            try {
                int amount = parseIntInput(Console.readLine());
                validatePurchaseAmount(amount);
                return amount;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validatePurchaseAmount(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %,d원 단위여야 합니다.", LOTTO_PRICE)
            );
        }
        if (amount < LOTTO_PRICE) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %,d원 이상이어야 합니다.", LOTTO_PRICE)
            );
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        while (true) {
            try {
                return parseNumberList(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int readBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        while (true) {
            try {
                return parseIntInput(Console.readLine());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int parseIntInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }

    private List<Integer> parseNumberList(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }
}
