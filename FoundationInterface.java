import java.io.IOException;

public interface FoundationInterface {
    void createAccount(String filename)  throws IOException;
    void deleteAccount();
    User findUser(long userID);
    void addFriend();
    void removeFriend();
    void sendFriendRequest();
    void blockUser();
    void removeBlockedUser();
    void sendMessage();
    void deleteMessage();
    void editMessage();

    void rewriteDirectMessage();

}
