package careerdevs.cohort14.JsonPlaceholderAPI.Models.User;

public class UserMulti {
    User address;
    User company;

    public User getAddress() {
        return address;
    }

    public void setAddress(User address) {
        this.address = address;
    }

    public User getCompany() {
        return company;
    }

    public void setCompany(User company) {
        this.company = company;
    }
}
