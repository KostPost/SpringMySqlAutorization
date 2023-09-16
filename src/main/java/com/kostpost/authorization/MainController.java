package com.kostpost.authorization;

import com.kostpost.account.Account;
import com.kostpost.account.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ComponentScan(basePackages = {"com.kostpost.account"})
public class MainController {
    private final AccountRepository accountRepository;

    @Autowired
    public MainController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public void SeeAccounts(){

        List<Account> accounts = accountRepository.findAll();

        for(Account account : accounts)
        {
            AccountPrint(account);
        }
    }

    public void AccountPrint(Account account){
        System.out.println();
        System.out.println("ID = " + account.getUser_id());
        System.out.println("account name = " + account.getAccName());
        System.out.println("Account password = " + account.getAccPassword());
        System.out.println();
    }

    @PostMapping("/add-data")
    public Account addAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/find-by-AccName")
    public Account findByAccName(@RequestParam String AccName) {
        return accountRepository.findByAccName(AccName);
    }


}
