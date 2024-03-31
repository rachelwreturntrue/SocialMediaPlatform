import java.time.LocalDateTime;

public class GroupMessageContent {
    private long senderID;
    private String messageText;
    private boolean edited;
    private LocalDateTime timestamp;

    public GroupMessageContent(long senderID, String messageText) {
        this.senderID = senderID;
        this.messageText = messageText;
        this.timestamp = LocalDateTime.now();
        this.edited = false;
    }
    public GroupMessageContent(long senderID, String messageText, boolean edited) {
        this.senderID = senderID;
        this.messageText = messageText;
        this.timestamp = LocalDateTime.now();
        this.edited = edited;
    }

    public long getSender() {
        return senderID;
    }

    public void setSender(long senderID) {
        this.senderID = senderID;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String toString() {
        String senderIDString = Long.toString(senderID);
        String editedString = Boolean.toString(edited);

        return senderIDString + "\n" + messageText + "\n" + editedString + "\n";
    }

    // Getters and potentially setters, if you need to modify messages post-creation

}
