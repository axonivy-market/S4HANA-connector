package hana.converter;

import java.util.ArrayList;
import java.util.List;

import ch.ivyteam.ivy.bpm.error.BpmError;
import hana.bo.Message;

public class MessageConverter {

//    public static List<Message> convertBadRequestMessages(Response restResponse) {
//        List<Message> messages = new ArrayList<>();
//        String errorJson = restResponse.readEntity(String.class);
//        S4UErrorWrap errorWrap = JsonUtils.jsonToObject(errorJson, S4UErrorWrap.class);
//
//        if (errorWrap.getError() != null) {
//            if (errorWrap.getError().getMessage() != null) {
//                Message main = new Message(SapMessageType.ERROR.getSapValue(), errorWrap.getError().getMessage().getValue());
//                messages.add(main);
//            }
//
//            Optional.ofNullable(errorWrap.getError().getInnererror()).stream()
//                    .filter(Objects::nonNull)
//                    .map(Innererror::getErrordetails)
//                    .forEach(errordetails -> {
//                        messages.add(new Message(SapMessageType.ERROR.getSapValue(),
//                                errordetails.stream().map(Errordetail::getMessage).collect(Collectors.joining()))
//                        );
//                    });
//        }
//        return messages;
//    }

    public static List<Message> convertToMessages(BpmError error) {
        List<Message> messages = new ArrayList<>();
        while (error != null) {
            messages.add(new Message(error.getMessage()));
            error = error.getErrorCause();
        }
        return messages;
    }
}
