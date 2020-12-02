package trust.example.trust.beans;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {

    @Id
    private int Id;
//     Adding Validation for form fields, using JSR 380 Validation method.

    @Size(min = 6, message = "{username.not.empty}")
    private String username;

    @Pattern(regexp = "((?=.*[A-Z]).{5,12})", message = "{password.valid}")
    private String password;

    @NotEmpty(message = "{first.name.not.empty}")
    private String firstName;
    private String lastName;
    private String activity;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)  // To display Gender as String
    private Gender gender;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
