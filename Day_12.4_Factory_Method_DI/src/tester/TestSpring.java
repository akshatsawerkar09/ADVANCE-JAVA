package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml")) {

			System.out.println("SC started...");

			// invoke BL deposit / withdraw
			// 1. get dependent bean : already, loaded and instantiated (default ctor) - DI
			ATMImpl bean = ctx.getBean("my_atm", ATMImpl.class); // tester is requesting SC to get this readymade bean

			bean.deposit(1000);

			ATMImpl bean2 = ctx.getBean("my_atm", ATMImpl.class);

			System.out.println(bean == bean2);// true singleton

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
