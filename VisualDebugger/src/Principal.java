
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Comm communication = new Comm();
		communication.start();
		Command wlogin = new Command();
		wlogin.setVisible(true);
		
	}

}

