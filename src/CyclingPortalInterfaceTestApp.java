import cycling.CyclingPortal;
import cycling.BadMiniCyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.IllegalNameException;
import cycling.InvalidNameException;
import cycling.MiniCyclingPortalInterface;

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
		} catch (IllegalNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

}
