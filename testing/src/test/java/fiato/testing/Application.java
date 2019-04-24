package fiato.testing;

public class Application {

	public static void main(String[] args) {

		Facebook fb = Facebook.getInstance();
		fb.SetUserNameAndPassword("0922224002", "aloha123");
		
		fb.login();
		fb.Scrool();
		//fb.LogoutAndRelogin();
		fb.GetStory(1);
		
//		Facebook fb1 = Facebook.getInstance();
//		fb1.SetUserNameAndPassword("Binhnguyen@daoedu.us", "123456?v");
//		fb1.login();
//		fb1.Scrool();
//		fb1.LogoutAndRelogin();
		
		
		try {
			Thread.sleep((long) (10000 + Math.random() % 60000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
