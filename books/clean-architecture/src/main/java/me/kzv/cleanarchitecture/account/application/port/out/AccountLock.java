package me.kzv.cleanarchitecture.account.application.port.out;

import me.kzv.cleanarchitecture.account.domain.Account;

public interface AccountLock {

    void lockAccount(Account.AccountId accountId);

    void releaseAccount(Account.AccountId accountId);

}
