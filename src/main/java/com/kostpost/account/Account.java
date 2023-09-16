package com.kostpost.account;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Getter
@Setter
@Entity
@SpringBootApplication
@Table(name = "acc_authorization")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name = "acc_name")
    private String accName;
    @Column(name = "acc_password")
    private String accPassword;


}
