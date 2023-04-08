package me.kzv.cleanarchitecture.account.application.service;


import me.kzv.cleanarchitecture.account.application.port.out.AccountLock;
import me.kzv.cleanarchitecture.account.domain.Account;
import me.kzv.cleanarchitecture.account.domain.Account.AccountId;
import org.springframework.stereotype.Component;

@Component
class NoOpAccountLock implements AccountLock {

    @Override
    public void lockAccount(AccountId accountId) {
        // do nothing
    }

    @Override
    public void releaseAccount(AccountId accountId) {
        // do nothing
    }
}
