public class Admin {

    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {

        return this.username.equals(username)
                && this.password.equals(password);
    }

    public void displayAdminMenu() {

        System.out.println("\n===== ADMIN PANEL =====");

        System.out.println("1. View All Accounts");
        System.out.println("2. Search Account");
        System.out.println("3. Block Account");
        System.out.println("4. Activate Account");
        System.out.println("5. View Total Accounts");
        System.out.println("0. Logout");
    }
}