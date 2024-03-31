import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DirectMessage {

    private long directMessageID;
    private long participantOneID;
    private long participantTwoID;
    private List<Message> messages;

    public DirectMessage(long directMessageID, long participantOneID, long participantTwoID) {
        this.directMessageID = directMessageID;
        this.participantOneID = participantOneID;
        this.participantTwoID = participantTwoID;

        this.messages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public void deleteMessage(Message message) {
        messages.remove(message);
    }

    public void editMessage(Message message, String messageText) {
        for (int i = 0; i < messages.size(); i++) {
            Message currentMessage = messages.get(i);
            if (message.equals(currentMessage)) {
                currentMessage.setMessageText(messageText);
                currentMessage.setEdited(true);
            }
        }
    }

    public void markAsRead() {
        for (int i = 0; i < messages.size(); i++) {
            messages.get(i).setRead(true);
        }
    }

    public void swapParticipants() {
        long temp = this.participantOneID;
        this.participantOneID = this.participantTwoID;
        this.participantTwoID = temp;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void deleteAllMessages() {
        this.messages.clear();
    }

    public long getDirectMessageID() {
        return directMessageID;
    }

    public void setDirectMessageID(long directMessageID) {
        this.directMessageID = directMessageID;
    }

    public long getParticipantOneID() {
        return participantOneID;
    }

    public void setParticipantOneID(long participantOneID) {
        this.participantOneID = participantOneID;
    }

    public long getParticipantTwoID() {
        return participantTwoID;
    }

    public void setParticipantTwoID(long participantTwoID) {
        this.participantTwoID = participantTwoID;
    }

    // Getters and setters

}