package me.kzv.cleanarchitecture.account.adapter.in.web;


import lombok.RequiredArgsConstructor;
import me.kzv.cleanarchitecture.account.application.port.in.SendMoneyCommand;
import me.kzv.cleanarchitecture.account.application.port.in.SendMoneyUseCase;
import me.kzv.cleanarchitecture.account.domain.Account.AccountId;
import me.kzv.cleanarchitecture.account.domain.Money;
import me.kzv.cleanarchitecture.common.WebAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class SendMoneyController {

    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping(path = "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(
            @PathVariable("sourceAccountId") Long sourceAccountId,
            @PathVariable("targetAccountId") Long targetAccountId,
            @PathVariable("amount") Long amount) {

        SendMoneyCommand command = new SendMoneyCommand(
                new AccountId(sourceAccountId),
                new AccountId(targetAccountId),
                Money.of(amount));

        sendMoneyUseCase.sendMoney(command);
    }

}