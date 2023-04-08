package me.kzv.cleanarchitecture.account.application.port.out;

import me.kzv.cleanarchitecture.account.domain.Account;

public interface UpdateAccountStatePort {

    void updateActivities(Account account);

}
