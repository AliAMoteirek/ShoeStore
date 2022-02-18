package Data;

public class Main {

	public static void main(String[] args) {

		Repository r = new Repository();
		LoginPage loginPage = new LoginPage(r.getLoginInfo());
		r.getAllShoesInStock().forEach(System.out::println);

	}
}