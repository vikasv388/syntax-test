public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Customer(int customerId, String name,
                    String email, String phone,
                    String address) {

        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void displayProfile() {

        System.out.println("\n===== CUSTOMER PROFILE =====");

        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
    }
}