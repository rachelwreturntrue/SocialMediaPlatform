import java.util.ArrayList;

public class UserInfo {
    private String username;
    private int age;
    private boolean showAge;
    private String firstName;
    private String lastName;
    private boolean showRealName;
    private String email;
    private String displayName;
    private String bio;

    public UserInfo(String firstName, String lastName, int age, String email, String displayName, String username) {
        //fields the user fills out
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.displayName = displayName;
        this.username = username;

        //fields set as null or empty as default, can be changed in settings
        this.showAge = false;
        this.bio = "";
        this.showRealName = false;

        //randomly generated to be the length of user database array + 1
        //this.userID = databaseArraylist.size() + 1
    }

    public UserInfo(String line) {
        String[] parts = line.split(",");
        this.firstName = parts[0];
        this.lastName = parts[1];
        this.age = Integer.parseInt(parts[2]);
        this.email = parts[3];
        this.displayName = parts[4];
        this.username = parts[5];

        this.showAge = Boolean.getBoolean(parts[6]);
        this.bio = parts[7];
        this.showRealName = Boolean.getBoolean(parts[8]);
    }

    public UserInfo() {
        //When an empty user created
        this.firstName = "";
        this.lastName = "";
        this.age = -1;
        this.email = "";
        this.displayName = "";
        this.username = "";

        //fields set as null or empty as default, can be changed in settings
        this.showAge = false;
        this.bio = "";
        this.showRealName = false;
    }


    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public boolean setFirstName(String firstName) {
        if (this.firstName.equals(firstName)) {
            return false;
        } else {
            this.firstName = firstName;
            return true;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public boolean setLastName(String lastName) {
        if (this.lastName.equals(lastName)) {
            return false;
        } else {
            this.lastName = lastName;
            return true;
        }
    }

    public String getRealNamePublic() {
        if (this.showRealName) {
            return firstName + " " + lastName;
        } else {
            return "";
        }
    }

    public int getAge() {
        return age;
    }

    public boolean setAge(int age) {
        if (this.age == age) {
            return false;
        } else {
            this.age = age;
            return true;
        }
    }

    public String getAgePublic() {
        if (showAge) {
            return String.valueOf(age);
        } else {
            return "";
        }
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (this.email.equals(email)) {
            return false;
        } else {
            this.email = email;
            return true;
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean setDisplayName(String displayName) {
        if (this.displayName.equals(displayName)) {
            return false;
        } else {
            this.displayName = displayName;
            return true;
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username) {
        if (this.username.equals(username)) {
            return false;
        } else {
            this.username = username;
            return true;
        }
    }

    public boolean isShowAge() {
        return showAge;
    }

    public boolean setShowAge(boolean showAge) {
        if (this.showAge == showAge) {
            return false;
        } else {
            this.showAge = showAge;
            return true;
        }
    }

    public boolean isShowRealName() {
        return showRealName;
    }

    public boolean setShowRealName(boolean showRealName) {
        if (this.showRealName == showRealName) {
            return false;
        } else {
            this.showRealName = showRealName;
            return true;
        }
    }

    public String getBio() {
        return bio;
    }

    public boolean setBio(String bio) {
        if (this.bio.equals(bio)) {
            return false;
        } else {
            this.bio = bio;
            return true;
        }
    }


    public String toString() {
        //returns all fields of the use in a string separated by ",", can be .split into a String[]
        //meant for settings, all fields including private info is returned except for friendList and blockedList
        //assuming that friendList and blockedList are accessed somewhere else
        return firstName + "," + lastName + "," + age + "," + username + "," +
                email + "," + displayName + "," + showAge + "," + showRealName + "," + bio;
    }

    public String viewUser() {
        //returns public fields visible to other users considering showAge and showRealName settings
        String viewUserOutput = displayName + "," + bio;
        if(showRealName) {
            viewUserOutput = viewUserOutput + "," + firstName + "," + lastName;
        }
        if (showAge) {
            viewUserOutput = viewUserOutput + "," + age;
        }
        return viewUserOutput;
    }
}