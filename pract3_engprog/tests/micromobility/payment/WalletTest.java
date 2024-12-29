package micromobility.payment;

import exceptions.NotEnoughWalletException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WalletTest {

    @Test
    void testInitialBalance() {
        Wallet wallet = new Wallet(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), wallet.getBalance());
    }

    @Test
    void testDeductSuccess() throws NotEnoughWalletException {
        Wallet wallet = new Wallet(new BigDecimal("100.00"));
        wallet.deduct(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("50.00"), wallet.getBalance());
    }

    @Test
    void testDeductInsufficientFunds() {
        Wallet wallet = new Wallet(new BigDecimal("100.00"));
        assertThrows(NotEnoughWalletException.class, () -> wallet.deduct(new BigDecimal("150.00")));
    }
}