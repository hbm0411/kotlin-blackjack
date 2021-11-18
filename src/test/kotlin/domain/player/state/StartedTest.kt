package domain.player.state

import domain.card.*
import exception.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class StartedTest {
    lateinit var started: Started
    private val cardList = listOf(
        PlayingCard.of(Denomination.JACK, Suit.SPADES),
        PlayingCard.of(Denomination.QUEEN, Suit.HEARTS)
    )

    @BeforeEach
    fun setUp() {
        started = Started(PlayingCards(cardList))
    }

    @DisplayName("시작할 때는 반드시 두 장의 카드를 받아야 한다.")
    @ParameterizedTest
    @ValueSource(ints = [1, 3, 4, 5, 6, 7])
    fun constructor(size: Int) {
        val cardGenerator = MockedCardGenerator()
        val cards = PlayingCards((1..size).map { cardGenerator.getCard() })
        assertThatExceptionOfType(IllegalPlayerStateException::class.java)
            .isThrownBy { Started(cards) }
    }

    @DisplayName("Started 상태에서는 earningRate 를 알 수 없다.")
    @Test
    fun illegalEarning() {
        assertThatExceptionOfType(IllegalEarningRate::class.java)
            .isThrownBy { started.earningRate() }
    }

    @DisplayName("Started 상태에서는 score 를 알 수 없다.")
    @Test
    fun illegalScore() {
        assertThatExceptionOfType(IllegalScoreException::class.java)
            .isThrownBy { started.score() }
    }

    @DisplayName("Started 상태에서는 stay 가 불가능하다.")
    @Test
    fun illegalStay() {
        assertThatExceptionOfType(IllegalStayException::class.java)
            .isThrownBy { started.stay() }
    }

    @DisplayName("Started 상태에서는 draw 가 불가능하다.")
    @Test
    fun illegalDraw() {
        assertThatExceptionOfType(IllegalDrawException::class.java)
            .isThrownBy { started.draw(PlayingCard.of(Denomination.ACE, Suit.SPADES)) }
    }

    @DisplayName("Score 가 21 이면 finished 상태로 이동한다.")
    @Test
    fun blackjack() {
        val cardList = listOf(
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.KING, Suit.HEARTS)
        )
        assertThat(Started(PlayingCards(cardList)).nextState().isFinished()).isTrue
    }

    @DisplayName("Score 가 21 미만이면 hit 상태로 이동한다.")
    @Test
    fun hit() {
        val cardList = listOf(
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS)
        )
        assertThat(Started(PlayingCards(cardList)).nextState().isFinished()).isFalse
    }
}