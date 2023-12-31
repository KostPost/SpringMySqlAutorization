package com.kostpost.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.kostpost.account.Account;
import com.kostpost.account.AccountRepository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kostpost.account"})
@ComponentScan("com.kostpost.authorization")
public class AuthorizationApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(AuthorizationApplication.class, args);
		MainController controller = context.getBean(MainController.class);

		boolean woriking = true;

		Scanner AskAction = new Scanner(System.in);
		int action;

//		Account accs =  controller.findByAccName("qwe");
//		controller.AccountPrint(accs);

		do{

			System.out.println("Choice an action:\n1 - See all accounts\n2 - Create account\n3 - Log in\n4 - Exit");
			action = AskAction.nextInt();

			switch (action){

				case 1 -> {
					controller.SeeAccounts();
				}

				case 2 -> {
					Scanner AskAccName = new Scanner(System.in);
					String accName = null;

					Scanner AskAccPassword = new Scanner(System.in);
					String accPassword = null;

					Account checkAccName = null;
					Account CreateAccount = new Account();

					do{
						System.out.print("Write account name:   ");
						accName = AskAccName.next();

						checkAccName = controller.findByAccName(accName);

						if(checkAccName != null){
							System.out.println("this name already exist");
							accName = null;
						}else {
							CreateAccount.setAccName(accName);
						}

					}while(accName == null);

					System.out.print("Write account password:   ");
					accPassword = AskAccPassword.next();

					CreateAccount.setAccPassword(accPassword);

					controller.addAccount(CreateAccount);

				}

				case 3 -> {

					Scanner AskAccountName = new Scanner(System.in);
					String AccName= null;

					Scanner AskAccountPassword = new Scanner(System.in);
					String TryAccPassword = null;

					Account LoginAccount = new Account();

					String TrueAccPassword = null;

					do{
						System.out.print("Enter account name:   ");
						AccName = AskAccountName.next();

						LoginAccount = controller.findByAccName(AccName);

						if(LoginAccount != null){
							TrueAccPassword = LoginAccount.getAccPassword();
						}else {
							System.out.println("There is no account with this username");
							AccName = null;
						}

					}while(AccName == null);

					do{
						System.out.print("Enter password for account - " + AccName + " - :\t");
						TryAccPassword = AskAccountPassword.next();

						if(Objects.equals(TryAccPassword, TrueAccPassword)){
							System.out.print("\nyou have successfully logged in");
						}else{
							System.out.println("Wrong password");
							TryAccPassword = null;
						}
					}while(TryAccPassword == null);
					controller.AccountPrint(LoginAccount);
				}

				case 4 -> {
					woriking = false;
				}
			}


		}while (woriking);





		context.close();
		SpringApplication.run(AuthorizationApplication.class, args);
	}

}
