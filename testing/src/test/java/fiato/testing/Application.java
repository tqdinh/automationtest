package fiato.testing;

public class Application {

	public static void main(String[] args) {

		Facebook fb = Facebook.getInstance();
		fb.SetUserNameAndPassword("‭0846936879", "aloha123");
	
		fb.login();
		//fb.Scrool();
		
	//	fb.scroolNewFriendList(10,false);
		fb.AddFriend();
		
		//fb.LogoutAndRelogin();
		//fb.GetStory();
		//fb.ClickStory(1);
		//fb.ClickStory(2);
		//fb.ClickStory(1);
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
