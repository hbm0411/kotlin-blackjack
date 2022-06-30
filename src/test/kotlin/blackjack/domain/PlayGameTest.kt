package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayGameTest {
    @Test
    internal fun `start()를 실행하면 중복되지 않은 2장의 카드를 받는다`() {
        val player = Player("name")
        PlayGame().start(player)

        assertThat(player.hands.cards.distinct()).hasSize(2)
    }

    @Test
    internal fun `hit()을 실행하면 1장의 카드를 받는다`() {
        val player = Player("name")
        PlayGame().hit(player)

        assertThat(player.hands.cards).hasSize(1)
    }
}
