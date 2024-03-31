import java.util.ArrayList;
import java.util.List;

public class GroupMessage {
    private long groupID;
    private String groupName;
    private ArrayList<Long> membersID;
    private List<GroupMessageContent> messages;

    public GroupMessage(long groupID, String groupName, ArrayList<Long> membersID) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.membersID = membersID;

        this.messages = new ArrayList<>();
    }

    public boolean addMember(long userID) {
        if (!membersID.contains(userID)) {
            membersID.add(userID);
            return true;
        }
        return false;
    }

    public boolean removeMember(long userID) {
        if (!membersID.contains(userID)) {
            membersID.remove(userID);
            return true;
        }
        return false;
    }

    public void sendMessage(GroupMessageContent content) {
        messages.add(content);
    }

    public void removeMessage(GroupMessageContent content) {
        for (int i = 0; i < messages.size(); i++) {
            if (content.equals(messages.get(i))) {
                messages.remove(i);
                break;
            }
        }
    }

    public void editMessage(GroupMessageContent content, String newMessage) {
        for (int i = 0; i < messages.size(); i++) {
            if (content.equals(messages.get(i))) {
                messages.get(i).setMessageText(newMessage);
                break;
            }
        }
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Long> getMembersID() {
        return membersID;
    }

    public List<GroupMessageContent> getMessages() {
        return messages;
    }

    public void setMembersID(ArrayList<Long> membersID) {
        this.membersID = membersID;
    }

    public void setMessages(List<GroupMessageContent> messages) {
        this.messages = messages;
    }

    public String toString() {
        String groupIDString = Long.toString(groupID);
        String membersString = "";
        for (int i = 0; i < membersID.size(); i++) {
            membersString += membersID.get(i) + ",";
        }

        return groupIDString + "\n" + groupName + "\n" + membersString;
    }
}

