package camp.nextstep.lotto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class LottoTicketTest {

    @DisplayName("로또 티켓은 6개의 숫자를 가진다.")
    @Test
    fun sixNumbersTest() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val ticket = LottoTicket(numbers)

        assertEquals(6, ticket.numbers.size)
        assertTrue(ticket.numbers.containsAll(numbers))
    }

    @DisplayName("로또 티켓은 6개보다 적거나 많은 숫자를 가질 수 없다.")
    @ParameterizedTest
    @MethodSource("illegalNumbers")
    fun moreThanSixNumbersTest() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
        assertThrows<IllegalArgumentException> { LottoTicket(numbers) }
    }

    @DisplayName("로또 티켓은 같은 숫자를 여러 개 가질 수 없다.")
    @ParameterizedTest
    @MethodSource("illegalSameNumbers")
    fun ticketDuplicatedNumberTest() {
        val numbers = listOf(1, 2, 1, 4, 5, 6)
        assertThrows<IllegalArgumentException> { LottoTicket(numbers) }
    }

    @DisplayName("로또 티켓은 1부터 45까지 숫자로 이루어질 수 있다.")
    @ParameterizedTest(name = "1 <= {0} <= 45 을 만족하지 않는다.")
    @MethodSource("illegalRangeNumbers")
    fun ticketNumberRangeTest(illegalNumbers: IntArray) {
        assertThrows<IllegalArgumentException> { LottoTicket(illegalNumbers.asList()) }
    }

    companion object {

        @JvmStatic
        fun illegalRangeNumbers(): Stream<IntArray> {
            return Stream.of(
                intArrayOf(0, 1, 2, 3, 4, 5),
                intArrayOf(1, 2, 3, 4, 5, 100),
                intArrayOf(-1, 1, 2, 3, 4, 5),
            )
        }

        @JvmStatic
        fun illegalSameNumbers(): Stream<IntArray> {
            return Stream.of(
                intArrayOf(10, 20, 23, 24, 35, 10),
                intArrayOf(1, 1, 23, 30, 40, 40),
                intArrayOf(1, 1, 1, 1, 1, 1),
            )
        }

        @JvmStatic
        fun illegalNumbers(): Stream<IntArray> {
            return Stream.of(
                intArrayOf(),
                intArrayOf(1),
                intArrayOf(1, 2),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 2, 3, 4),
                intArrayOf(1, 2, 3, 4, 5),
                intArrayOf(1, 2, 3, 4, 5, 6, 7),
                intArrayOf(1, 2, 3, 4, 5, 6, 7, 8),
            )
        }
    }
}
