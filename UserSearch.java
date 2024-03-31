import java.util.ArrayList;

public class UserSearch extends AccountManager{
    public UserSearch(ArrayList<User> users, User currentUser, ArrayList<User> blockedUsers) {
        super(users, currentUser, blockedUsers);
    }

    /*
    * From the user database, it runs through all the users and proposes the ones that fits the username
        Returns the possible Users that fit the username
    * */

    public ArrayList<User> findUser(String username) {
        ArrayList<User> ans = new ArrayList<User>();
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getUsername().contains(username)) {
                ans.add(getUsers().get(i));
            }
        }
        return ans;
    }

    /*
    * From the user database, it runs through all the users and proposes the ones that fits the first and last name
     Returns the possible Users that fit the name
    * */
    public ArrayList<User> findUser(String firstName, String lastName) {
        ArrayList<User> ans = new ArrayList<User>();
        for (int i = 0; i < getUsers().size(); i++) {
            if(getUsers().get(i).getFirstName().equals(firstName) &&
                    getUsers().get(i).getLastName().equals(lastName)) {
                ans.add(getUsers().get(i));
            }
        }
        return ans;
    }

    /*
    * From the user database, it runs through all the users and proposes the one that fits the email
        Returns the possible User that fits the email
    * */
    public User findUserEmail(String email) {
        int index = -1;
        for (int i = 0; i < getUsers().size(); i++) {
            if (getUsers().get(i).getEmail().equals(email)) {
                index = i;
            }
        }
        return getUsers().get(index);
    }


    public int findUserIndex(User user) {
        int index = -1;
        for(int i = 0; i < getUsers().size(); i++) {
            if(getUsers().get(i).equals(user)) {
                index = i;
            }
        }
        return index;
    }
    public User findUserByID(Long userID) {

    }
}
