package micromobility.payment;

import exceptions.NotEnoughWalletException;

import java.math.BigDecimal;

/**
 * Represents a wallet for storing user credits.
 */
public class Wallet {

    private BigDecimal balance;

    public Wallet(BigDecimal initialBalance) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deduct(BigDecimal amount) throws NotEnoughWalletException {
        if (amount.compareTo(balance) > 0) {
            throw new NotEnoughWalletException("Not enough balance in wallet.");
        }
        balance = balance.subtract(amount);
    }
}