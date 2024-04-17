package hana.converter;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.bpm.error.BpmError;
import hana.bo.Message;

public class MessageConverter {

    public static List<Message> convertToMessages(BpmError error) {
        List<Message> messages = new ArrayList<>();
        while (error != null) {
            messages.add(new Message(error.getMessage()));
            error = error.getErrorCause();
        }
        return messages;
    }
}
