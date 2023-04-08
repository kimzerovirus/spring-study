package me.kzv.cleanarchitecture.account.application.service;

import lombok.RequiredArgsConstructor;
import me.kzv.cleanarchitecture.account.application.port.in.GetAccountBalanceQuery;
import me.kzv.cleanarchitecture.account.application.port.out.LoadAccountPort;
import me.kzv.cleanarchitecture.account.domain.Account;
import me.kzv.cleanarchitecture.account.domain.Account.AccountId;
import me.kzv.cleanarchitecture.account.domain.Money;

import java.time.LocalDateTime;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
