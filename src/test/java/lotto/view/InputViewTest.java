package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class InputViewTest {

    @Test
    void 정수_파싱_성공() {
        InputView view = new InputView();

        int result = view.parseIntInput("8000");

        assertThat(result).isEqualTo(8000);
    }

    @Test
    void 정수_파싱_실패_문자열() {
        InputView view = new InputView();

        assertThatThrownBy(() -> view.parseIntInput("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void 정수_파싱_실패_빈문자열() {
        InputView view = new InputView();

        assertThatThrownBy(() -> view.parseIntInput(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
