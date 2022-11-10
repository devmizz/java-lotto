package lotto;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteryMachineTest {
    @DisplayName("정답 로또 생성이 제대로 되는지")
    @Test
    void createWinningLottery() {
        String money = "12000";
        String numbers = "1,8,4,2,5,3";
        String input = money + System.getProperty("line.separator") + numbers;

        Lotto lotto = new Lotto(List.of(1, 8, 4, 2, 5, 3));

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Application.main(new String[]{});

        List<Integer> winningNumbers = LotteryMachine.getInstance().getWinningLottery().getNumbers();

        assertThat(winningNumbers.equals(lotto.getNumbers())).isTrue();
    }

    @DisplayName("정답 로또 입력이 6개 미만일 때 예외가 발생하는지")
    @Test
    void createLottoUnderSize() {
        String money = "12000";
        String numbers = "1,8,4,2,5";
        String input = money + System.getProperty("line.separator") + numbers;

        assertThatThrownBy(() -> {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Application.main(new String[]{});
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("정답 로또 입력이 6개 초과일 때 예외가 발생하는지")
    @Test
    void createLottoOverSize() {
        String money = "12000";
        String numbers = "1,8,4,2,5,9,4";
        String input = money + System.getProperty("line.separator") + numbers;

        assertThatThrownBy(() -> {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Application.main(new String[]{});
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("정답 로또 입력에 중복이 있을 때 예외가 발생하는지")
    @Test
    void createDuplicateLotto() {
        String money = "12000";
        String numbers = "1,8,4,2,5,5";
        String input = money + System.getProperty("line.separator") + numbers;

        assertThatThrownBy(() -> {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Application.main(new String[]{});
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("정답 로또 입력에 숫자가 아닌 값이 입력될 때 예외가 발생하는지")
    @Test
    void createLottoNotNumber() {
        String money = "12000";
        String numbers = "1,8,4,2,5,j";
        String input = money + System.getProperty("line.separator") + numbers;

        assertThatThrownBy(() -> {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            Application.main(new String[]{});
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}