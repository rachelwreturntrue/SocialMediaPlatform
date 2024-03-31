import java.time.LocalDateTime;

public class Message {

    /*
    * do we need a equals method?
     * */

    private long senderID;
    private long receiverID;
    private String messageText;
    private boolean read;
    private boolean edited;
    private LocalDateTime timestamp;

    public Message(long senderID, long receiverID, String messageText) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.messageText = messageText;

        this.timestamp = LocalDateTime.now();
        this.read = false;
        this.edited = false;
    }

    public Message(long senderID, long receiverID, String messageText, boolean read, boolean edited) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.messageText = messageText;

        this.timestamp = LocalDateTime.now();
        this.read = read;
        this.edited = edited;
    }

    // Getters
    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getSenderID() {
        return senderID;
    }

    public void setSenderID(long senderID) {
        this.senderID = senderID;
    }

    public long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(long receiverID) {
        this.receiverID = receiverID;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String toString() {
        String senderIDString = Long.toString(senderID);
        String receiverIDString = Long.toString(receiverID);
        String readString = Boolean.toString(read);
        String editedString = Boolean.toString(edited);

        return senderIDString + "\n" + receiverIDString + "\n" + messageText + "\n" + readString + "\n" + editedString + "\n";
    }
}