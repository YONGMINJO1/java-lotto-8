package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return parseIntInput(Console.readLine());
    }

    public List<Integer> readWinningNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return parseNumberList(Console.readLine());
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
