import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.IDNotRecognisedException;
import cycling.IllegalNameException;
import cycling.InvalidNameException;

/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author Diogo Pacheco
 * @version 1.0
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");

		CyclingPortalInterface portal = new CyclingPortal();

		try {
			portal.createRace("Fuck_Josh", "This is a fucking test an i hop it works for josh sake");
			portal.createRace("test", "This is test race 1");
			portal.createRace("test2", "This is test race 2");
			portal.createRace("test3", "This is test race 3");
			portal.createRace("Test4", "This is test race 4");

		} catch (IllegalNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		System.out.println(portal.getRaceIds()[0]);
		try {
			System.out.println(portal.viewRaceDetails(1));
		} catch (IDNotRecognisedException e) {
			System.out.println(e.getMessage());
		}

	}

}
