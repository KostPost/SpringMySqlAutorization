package com.kostpost.account;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan(basePackages = "com.kostpost.account")
@ComponentScan(basePackages = "com.kostpost.authorization")
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccName(String AccName);
}
