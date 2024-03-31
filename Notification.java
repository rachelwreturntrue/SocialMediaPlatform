import java.util.UUID;

public class Notification {
    public final int TYPE_MESSAGE = 1;
    public final int TYPE_FRIEND_REQUEST = 2;
    public final int TYPE_FRIEND_REQUEST_ACCEPTED = 3;

    private long notificationID;
    private long senderID;
    private long receiverID;
    private int type;
    private String message;
    private boolean isRead;

    public Notification(long notificationID, long senderID, long receiverID, int type, String message, boolean isRead) {
        this.notificationID = notificationID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.type = type;
        this.message = message;
        this.isRead = isRead;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(long notificationID) {
        this.notificationID = notificationID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String toString() {
        return notificationID + "," +
                message + "," +
                isRead + "," +
                type;
    }
}