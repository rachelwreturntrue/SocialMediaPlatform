import java.io.IOException;
import java.util.ArrayList;

public class User {
    private ArrayList<Long> blockedList; //this requires text field.(UserID)
    private ArrayList<Long> friends;
    private ArrayList<Long> directMessageID;
    private ArrayList<Long> groupMessageID;
    private ArrayList<Notification> notifications;
    private UserInfo userInfo;
    private long userID;

    public User(ArrayList<Long> blockedList, ArrayList<Long> friends, ArrayList<Long> directMessageID,
                ArrayList<Long> groupMessageID, ArrayList<Notification> notification, UserInfo userInfo, long UserID) {
        this.blockedList = blockedList;
        this.friends = friends;
        this.directMessageID = directMessageID;
        this.groupMessageID = groupMessageID;
        this.notifications = notification;
        this.userInfo = userInfo;
    }

    public User(UserInfo userInfo, long userID) {
        this.userInfo = userInfo;
        this.userID = userID;

        this.blockedList = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.directMessageID = new ArrayList<>();
        this.groupMessageID = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public User(long userID) {
        this.userInfo = null;
        this.userID = userID;

        this.blockedList = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.directMessageID = new ArrayList<>();
        this.groupMessageID = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }



    public void addBlockedUser(long blockUserID) {
        blockedList.add(blockUserID);
    }
    public void removeBlockedUser(long blockedUserID) {
        blockedList.remove(blockedUserID);
    }
    public void addFriend(long friendID) {
        friends.add(friendID);
    }
    public void removeFriend(long friendID) {
        friends.remove(friendID);
    }
    public void addDirectMessage(long directID) {
        directMessageID.add(directID);
    }
    public void addGroupMessage(long groupID) {
        groupMessageID.add(groupID);
    }
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    public boolean removeDirectMessage(long directID) {
        if (directMessageID.contains(directID)) {
            directMessageID.remove(directID);
            return true;
        }
        return false;
    }
    public boolean removeGroupMessage(long groupID) {
        if (groupMessageID.contains(groupID)) {
            groupMessageID.remove(groupID);
            return true;
        }
        return false;
    }

    public void updateAccountInfo(String info, String value) throws UserNotFoundException, IOException, WrongCommandException {
        if (info.equalsIgnoreCase("username"))
            userInfo.setUsername(value);
        else if (info.equalsIgnoreCase("firstname"))
            userInfo.setFirstName(value);
        else if (info.equalsIgnoreCase("lastname"))
            userInfo.setLastName(value);
        else if (info.equalsIgnoreCase("email"))
            userInfo.setEmail(value);
        else if (info.equalsIgnoreCase("displayname"))
            userInfo.setDisplayName(value);
        else if (info.equalsIgnoreCase("bio"))
            userInfo.setBio(value);
        else
            throw new WrongCommandException("Wrong command inputted");

        throw new UserNotFoundException("User doesn't exist");
    }

    public void updateAccountInfo(String info, int value) throws UserNotFoundException, IOException, WrongCommandException {
        if (info.equalsIgnoreCase("age"))
            userInfo.setAge(value);
        else
            throw new WrongCommandException("Wrong command inputted");

        throw new UserNotFoundException("User doesn't exist");
    }

    public void updateAccountInfo(String info, boolean value) throws WrongCommandException, UserNotFoundException, IOException {
        if (info.equalsIgnoreCase("showage"))
            userInfo.setShowAge(value);
        else if (info.equalsIgnoreCase("showrealname"))
            userInfo.setShowRealName(value);
        else
            throw new WrongCommandException("Wrong command inputted");

        throw new UserNotFoundException("User doesn't exist");
    }

    public long getUserID() {
        return userID;
    }

    public ArrayList<Long> getDirectMessageID() {
        return directMessageID;
    }

    public void setDirectMessageID(ArrayList<Long> directMessageID) {
        this.directMessageID = directMessageID;
    }

    public ArrayList<Long> getGroupMessageID() {
        return groupMessageID;
    }

    public void setGroupMessageID(ArrayList<Long> groupMessageID) {
        this.groupMessageID = groupMessageID;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ArrayList<Long> getBlockedList() {
        return blockedList;
    }

    public void setBlockedList(ArrayList<Long> blockedList) {
        this.blockedList = blockedList;
    }

    public ArrayList<Long> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Long> friends) {
        this.friends = friends;
    }
}
