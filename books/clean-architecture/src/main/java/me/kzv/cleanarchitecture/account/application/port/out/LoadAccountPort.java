package me.kzv.cleanarchitecture.account.application.port.out;

import me.kzv.cleanarchitecture.account.domain.Account;
import me.kzv.cleanarchitecture.account.domain.Account.AccountId;

import java.time.LocalDateTime;


public interface LoadAccountPort {

    Account loadAccount(AccountId accountId, LocalDateTime baselineDate);
}
