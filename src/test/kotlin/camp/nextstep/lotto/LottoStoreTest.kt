package camp.nextstep.lotto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class LottoStoreTest {

    @DisplayName("주어진 금액만큼 로또 티켓을 교환할 수 있다.")
    @ParameterizedTest(name = "{0}원으로는 {1}장의 로또 티켓을 사고 {2}원이 남는다.")
    @CsvSource(
        delimiter = ',',
        value = [
            "1000,1,0",
            "5000,5,0",
            "15500,15,500",
            "500,0,500",
            "0,0,0"
        ]
    )
    fun exchangeLottoTicketsTest(money: Int, expectedTicketCount: Int, expectedBalance: Int) {
        val lottoPrice = 1000
        val lottoStore = LottoStore(lottoTicketPrice = lottoPrice)

        val (tickets, balance) = lottoStore.exchange(money)
        assertEquals(expectedTicketCount, tickets.size)
        assertEquals(expectedBalance, balance)
    }
}
