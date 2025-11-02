package lotto.domain;

import lotto.Lotto;

public class WinningLotto {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;

    private final Lotto winningNumbers;
    private final int bonusNumber;

    public WinningLotto(Lotto winningNumbers, int bonusNumber) {

        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(winningNumbers, bonusNumber);

        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 보너스 번호는 %d~%d 사이여야 합니다.",
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    private void validateBonusNumberDuplicate(Lotto winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int countMatches(Lotto lotto) {
        int count = 0;
        for (Integer number : winningNumbers.getNumbers()) {
            if (lotto.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
