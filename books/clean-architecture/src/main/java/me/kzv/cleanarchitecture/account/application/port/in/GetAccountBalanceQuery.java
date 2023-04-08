package me.kzv.cleanarchitecture.account.application.port.in;

import me.kzv.cleanarchitecture.account.domain.Account.AccountId;
import me.kzv.cleanarchitecture.account.domain.Money;

public interface GetAccountBalanceQuery {

    Money getAccountBalance(AccountId accountId);

}
