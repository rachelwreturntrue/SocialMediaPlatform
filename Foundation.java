import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Foundation implements FoundationInterface {
    final String fileCreateID = "createID.txt";
    final String fileAllUserID = "allUserID.txt";
    final String fileBlockedUsers = "blockedUsers.txt";
    final String fileFriendsUsers = "friendsUsers.txt";
    final String fileUserInfos = "userInfo.txt";
    final String fileDirectMessages = "directMessages.txt";
    final String fileGroupChats = "groupChats.txt";
    final String fileGroupMessages = "groupMessages.txt";
    final String filePasswords = "passwords.txt";
    final String fileNotifications = "notifications.txt";


    private long currentUserID;
    private ArrayList<Long> createID; //User, DM, GM, notifications
    private ArrayList<User> users;
    private ArrayList<GroupMessage> groups;
    private ArrayList<DirectMessage> directMessages;
    private ArrayList<Password> passwords;
    private ArrayList<Long> allUserIDs;

    public User findUser(long userID) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID)
                return users.get(i);
        }
        return null;
    }
    public GroupMessage findGroup(long groupID) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getGroupID() == groupID)
                return groups.get(i);
        }
        return null;
    }

    public long giveNewID(int x) {
        long oldID = createID.get(x);
        long newID = createID.get(x) + 1;
        createID.set(x, newID);
        return oldID;
    }

    public ArrayList<String> readStringFile(String filename) throws IOException {
        ArrayList<String> ans = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("newUserIDFile.txt"));
            String line;
            while ((line = bfr.readLine()) != null) {
                ans.add(line);
            }
            return ans;

        } catch (IOException e)  {
            throw new IOException("Error when reading a file");
        }
    }

    public ArrayList<String[]> readArrayStringFile(String filename, int len) throws IOException {
        ArrayList<String[]> ans = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("newUserIDFile.txt"));
            String line;
            while (true) {
                line = bfr.readLine();
                if (line == null)
                    break;

                String[] parts = new String[len];
                parts[0] = line;
                for (int i = 1; i < parts.length; i++) {
                    parts[i] = bfr.readLine();
                }
                ans.add(parts);
            }
            return ans;

        } catch (IOException e)  {
            throw new IOException("Error when reading a file");
        }
    }
    public void initializeTheCreateID(ArrayList<String> listCreateID) {
        for (String id : listCreateID) {
            createID.add(Long.parseLong(id));
        }
    }

    public void initializeTheUsers(ArrayList<String> listAllUserID) {
        for (int i = 0; i < listAllUserID.size(); i++) {
            long userID = Long.parseLong(listAllUserID.get(i));
            User user = new User(userID);

            users.add(user);
            allUserIDs.add(userID);
        }
    }

    public void initializeTheBlockedUsers(ArrayList<String> listBlockedUsers) throws UserNotFoundException {
        for (String lines: listBlockedUsers) {
            String[] parts = lines.split(",");
            long userID = Long.parseLong(parts[0]);
            User user = findUser(userID);
            for (int i = 1; i < parts.length; i++) {
                long blockedUserID = Long.parseLong(parts[i]);
                user.addBlockedUser(blockedUserID);
            }
            if (user == null)
                throw new UserNotFoundException("User not found in the users list when initializing Blocked Users");
        }

    }

    public void initializeTheFriendsUsers(ArrayList<String> listFriendsUsers) throws UserNotFoundException {
        for (String lines: listFriendsUsers) {
            String[] parts = lines.split(",");
            long userID = Long.parseLong(parts[0]);
            User user = findUser(userID);

            for (int i = 1; i < parts.length; i++) {
                long friendsID = Long.parseLong(parts[i]);
                user.addFriend(friendsID);
            }

            if (user == null)
                throw new UserNotFoundException("User not found in the users list when initializing Friends Users");
        }
    }

    public void initializeTheUserInfos(ArrayList<String> listUserInfos) throws UserNotFoundException {
        for (String lines: listUserInfos) {
            String[] parts = lines.split(",", 2);
            long userID = Long.parseLong(parts[0]);

            User user = findUser(userID);

            if (user == null)
                throw new UserNotFoundException("User not found in the users list when initializing Infos of Users");

            user.setUserInfo(new UserInfo(parts[1]));
        }
    }

    public void initializeTheDirectMessages(ArrayList<String[]> listDirectMessages) throws UserNotFoundException {
        for (String[] lines: listDirectMessages) {
            long directMessagesID = Long.parseLong(lines[0]);
            long senderID = Long.parseLong(lines[1]);
            long receiverID = Long.parseLong(lines[2]);
            String messageText = lines[3];
            boolean read = Boolean.getBoolean(lines[4]);
            boolean edited = Boolean.getBoolean(lines[5]);

            User sender = findUser(senderID);
            User receiver = findUser(receiverID);

            if (sender == null || receiver == null)
                throw new UserNotFoundException("User not found in the users list when initializing Direct Messages of Users");

            Message message = new Message(senderID, receiverID, messageText, read, edited);
            DirectMessage curDirectMessage = new DirectMessage(directMessagesID, senderID, receiverID);
            DirectMessage curDirectMessageSwapped = new DirectMessage(directMessagesID, receiverID, senderID);

            sender.addDirectMessage(directMessagesID);
            receiver.addDirectMessage(directMessagesID);

            if (directMessages.contains(curDirectMessage)) {
                curDirectMessage.sendMessage(message);
                directMessages.add(curDirectMessage);
            } else if (directMessages.contains(curDirectMessageSwapped)) {
                curDirectMessageSwapped.sendMessage(message);
                directMessages.add(curDirectMessageSwapped);
            } else {
                curDirectMessage.sendMessage(message);
                directMessages.add(curDirectMessage);
            }
        }
    }

    public void initializeTheGroupChats(ArrayList<String[]> listGroupChats) {
        for (String[] lines: listGroupChats) {
            long groupMessageID = Long.parseLong(lines[0]);
            String name = lines[1];
            String[] parts = lines[2].split(",");
            ArrayList<Long> membersID = new ArrayList<>();

            for (int i = 0; i < parts.length; i++) {
                membersID.add(Long.parseLong(parts[i]));
            }

            GroupMessage groupChats = new GroupMessage(groupMessageID, name, membersID);
            this.groups.add(groupChats);
        }
    }

    public void initializeTheGroupMessages(ArrayList<String[]> listGroupMessages) {
        for (String[] lines: listGroupMessages) {
            long groupMessageID = Long.parseLong(lines[0]);
            long senderID = Long.parseLong(lines[1]);
            String messageText = lines[2];
            boolean edited = Boolean.getBoolean(lines[3]);

            GroupMessageContent message = new GroupMessageContent(senderID, messageText, edited);
            GroupMessage groupMessage = findGroup(groupMessageID);

            if (groupMessage != null) {
                groups.add(groupMessage);
            }
        }
    }
    public void initializeThePasswords(ArrayList<String> listPasswords) {
        for (String line : listPasswords) {
            String[] parts = line.split(",");
            Password curPassword = new Password(Long.parseLong(parts[0]), parts[1]);
            passwords.add(curPassword);
        }
    }
    public void initializeTheNotifications(ArrayList<String> listNotifications) {
        for (String line : listNotifications) {
            String[] parts = line.split(",");
            long notificationID = Long.parseLong(parts[0]);
            long senderID = Long.parseLong(parts[1]);
            long receiverID = Long.parseLong(parts[2]);
            int type = Integer.parseInt(parts[3]);
            String message = parts[4];
            boolean isRead = Boolean.getBoolean(parts[5]);

            Notification curNotification = new Notification(notificationID, senderID, receiverID, type, message, isRead);
            User user = findUser(receiverID);
            if (user != null)
                user.addNotification(curNotification);
        }
    }

    public void initializeTheProgram() throws IOException, UserNotFoundException {
        ArrayList<String> listCreateID = new ArrayList<>();
        ArrayList<String> listAllUserID = new ArrayList<>();
        ArrayList<String> listBlockedUsers = new ArrayList<>();
        ArrayList<String> listFriendsUsers = new ArrayList<>();
        ArrayList<String> listUserInfos = new ArrayList<>();
        ArrayList<String[]> listDirectMessages = new ArrayList<>();
        ArrayList<String[]> listGroupChats = new ArrayList<>();
        ArrayList<String[]> listGroupMessages = new ArrayList<>();
        ArrayList<String> listPasswords = new ArrayList<>();
        ArrayList<String> listNotifications = new ArrayList<>();

        try {
            listCreateID = readStringFile(fileCreateID);
            listAllUserID = readStringFile(fileAllUserID);
            listBlockedUsers = readStringFile(fileBlockedUsers);
            listFriendsUsers = readStringFile(fileFriendsUsers);
            listUserInfos = readStringFile(fileUserInfos);
            listDirectMessages = readArrayStringFile(fileDirectMessages, 6);
            listGroupChats = readArrayStringFile(fileGroupChats, 3);
            listGroupMessages = readArrayStringFile(fileGroupMessages, 4);
            listPasswords = readStringFile(filePasswords);
            listNotifications = readStringFile(fileNotifications);
        } catch (IOException e) {
            throw new IOException("Error when reading a file");
        }

        initializeTheCreateID(listCreateID);
        initializeTheUsers(listAllUserID);
        initializeTheBlockedUsers(listBlockedUsers);
        initializeTheFriendsUsers(listFriendsUsers);
        initializeTheUserInfos(listUserInfos);
        initializeTheDirectMessages(listDirectMessages);
        initializeTheGroupChats(listGroupChats);
        initializeTheGroupMessages(listGroupMessages);
        initializeThePasswords(listPasswords);
        initializeTheNotifications(listNotifications);
    }

    public void createAccount(String filename) throws IOException {
        try {
            BufferedReader bfr = new BufferedReader(new FileReader("newUserIDFile.txt"));
            String line = bfr.readLine();
            long newUserID = Long.parseLong(line);

            bfr = new BufferedReader(new FileReader("newUserInfo.txt"));
            int age = Integer.parseInt(bfr.readLine());
            String firstName = bfr.readLine();
            String lastName = bfr.readLine();
            String email = bfr.readLine();
            String displayName = bfr.readLine();
            String username = bfr.readLine();

            UserInfo userInfo = new UserInfo(firstName, lastName, age, email, displayName, username);
            User newUser = new User(userInfo, newUserID);

            rewriteUserInfo();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error when converting a String into number");
        } catch (IOException e)  {
            throw new IOException("Error when creating an account");
        }

    }

    public void removeFriend(long userID, long friendID) {
        User user = findUser(userID);
        User friend = findUser(friendID);
        if (user != null && friend != null) {
            user.removeFriend(friendID);
            friend.removeFriend(userID);
        }
        writeFriends();
    }
    public void sendFriendRequest(long userID, long friendID) {
        Notification notification = new Notification(giveNewID(3), userID, friendID, 2, "You have friends request", false);

        User friend = findUser(friendID);
        if (friend != null) {
            friend.addNotification(notification);
        }
    }

    public void acceptFriendRequest(long userID, long friendID) {
        User user = findUser(userID);
        User friend = findUser(friendID);

        user.addFriend(friendID);
        friend.addFriend(userID);
        writeFriends();
    }

    public void blockUser(long userID, long blockedUserID) {
        User user = findUser(userID);
        User blockedUser = findUser(blockedUserID);

        if (user != null && blockedUser != null) {
            user.removeFriend(blockedUserID);
            blockedUser.removeFriend(userID);
            user.addBlockedUser(blockedUserID);
        }
        writeBlockedUsers();
    }
    public void removeBlockedUser(long userID, long blockedUserID) {
        User user = findUser(userID);
        User blockedUser = findUser(blockedUserID);

        if (user != null && blockedUser != null) {
            user.removeBlockedUser(blockedUserID);
        }
        writeBlockedUsers();
    }
    public void sendMessageNotification(long senderID, long recipientID, String text) {
        Notification notification = new Notification(giveNewID(3), senderID, recipientID, 2, text, false);

        User recipient = findUser(recipientID);
        if (recipient != null) {
            recipient.addNotification(notification);
        }
    }
    private DirectMessage findDirectMessage(long senderID, long recipientID) {
        for (DirectMessage directMessage : directMessages) {
            if ((directMessage.getParticipantOneID() == senderID && directMessage.getParticipantTwoID() == recipientID) ||
                    (directMessage.getParticipantOneID() == recipientID && directMessage.getParticipantTwoID() == senderID)) {
                return directMessage;
            }
        }
        return null;
    }
    public void sendDirectMessage(DirectMessage directMessage, long senderID, long recipientID, String messageText) {
        Message message = new Message(senderID, recipientID, messageText);

        if (directMessage == null) {
            directMessage = new DirectMessage(giveNewID(1), senderID, recipientID);
            directMessages.add(directMessage);
        }
        directMessage.sendMessage(message);
        sendMessageNotification(senderID, recipientID, message.getMessageText());

        rewriteDirectMessage();
    }
    public void deleteDirectMessage(long senderID, long recipientID, Message message) {
        DirectMessage directMessage = findDirectMessage(senderID, recipientID);
        if (directMessage != null) {
            directMessage.deleteMessage(message);
        } else {
            System.out.println("Direct message not found.");
        }

        rewriteDirectMessage();
    }
    public void editDirectMessage(long senderID, long recipientID, Message message, String newContent) {
        DirectMessage directMessage = findDirectMessage(senderID, recipientID);
        if (directMessage != null) {
            directMessage.editMessage(message, newContent);
        } else {
            System.out.println("Direct message not found.");
        }

        rewriteDirectMessage();
    }

    public void sendGroupMessageNotification(GroupMessage groupMessage, long senderID, String messageText) {
        for (int i = 0; i < groupMessage.getMembersID().size(); i++) {
            long recipientID = groupMessage.getMembersID().get(i);

            Notification notification = new Notification(giveNewID(3), senderID, recipientID, 2, messageText, false);
            User recipient = findUser(recipientID);
            if (recipient != null) {
                recipient.addNotification(notification);
            }
        }
    }
    public void sendGroupMessage(GroupMessage groupMessage, long senderID, String messageText) {
        GroupMessageContent groupMessageContent = new GroupMessageContent(senderID, messageText);
        groupMessage.sendMessage(groupMessageContent);

        sendGroupMessageNotification(groupMessage, senderID, messageText);
        rewriteGroupMessages();
    }

    public void writeFriends() {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileFriendsUsers));
            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                bWriter.write(String.valueOf(currentUser.getUserID()));
                for (int j = 0; j < currentUser.getFriends().size(); j++) {
                    Long currentFriendID = currentUser.getFriends().get(j);
                    bWriter.write("," + String.valueOf(currentFriendID));
                    bWriter.newLine();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBlockedUsers() {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileBlockedUsers));
            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                bWriter.write(String.valueOf(currentUser.getUserID()));
                for (int j = 0; j < currentUser.getBlockedList().size(); j++) {
                    Long currentBlockedID = currentUser.getBlockedList().get(j);
                    bWriter.write("," + String.valueOf(currentBlockedID));
                    bWriter.newLine();
                }
                bWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewriteUserInfo() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileUserInfos));
            for (int i = 0; i < users.size(); i++) {
                String userID = Long.toString(users.get(i).getUserID());
                String userInfo = users.get(i).toString();

                bwf.write(userID + "," + userInfo);
                bwf.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewriteDirectMessages() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileDirectMessages));
            for (int i = 0; i < directMessages.size(); i++) {
                DirectMessage directMessage = directMessages.get(i);
                String directMessageID = Long.toString(directMessage.getDirectMessageID());
                for (int j = 0; j < directMessage.getMessages().size(); j++) {
                    String messageString = directMessage.getMessages().get(i).toString();
                    bwf.write(directMessageID + "\n" + messageString);
                    bwf.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewriteGroupChats() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileGroupChats));
            for (int i = 0; i < groups.size(); i++) {
                bwf.write(groups.get(i).toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewriteGroupMessages() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileGroupMessages));
            for (int i = 0; i < groups.size(); i++) {
                for (GroupMessageContent groupMessageContent : groups.get(i).getMessages()) {
                    String groupID = Long.toString(groups.get(i).getGroupID());
                    bwf.write(groupID + "\n" + groupMessageContent.toString());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewritePasswords() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(filePasswords));
            for (int i = 0; i < passwords.size(); i++) {
                bwf.write(passwords.get(i).toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void rewriteNotifications() {
        try {
            BufferedWriter bwf = new BufferedWriter(new FileWriter(fileNotifications));
            for (int i = 0; i < users.size(); i++) {
                User currentUser = users.get(i);
                String userIDString = Long.toString(currentUser.getUserID());
                for (Notification notification : currentUser.getNotifications()) {
                    bwf.write(userIDString + "," + notification.toString());
                    bwf.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
