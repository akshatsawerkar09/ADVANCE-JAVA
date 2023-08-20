package dependency;

public class EmailNotification implements CustomerNotificationService {

	public EmailNotification() {
		System.out.println("In ctor of " + getClass().getName());
	}

	@Override
	public void informCustomer(String notificationMesg) {
		System.out.println("Notifying the customer with mesg : " + notificationMesg + " using " + getClass().getName());
	}

}
