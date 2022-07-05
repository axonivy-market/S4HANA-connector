package hana.bo;

public class Message {
	/**
	 * 
	 */
	private String type;
	private String messageContent;

	public Message(String content) {
		this.messageContent = content;
	}

	public Message(String type, String content) {
		this.type = type;
		this.messageContent = content;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the messageContent
	 */
	public String getMessageContent() {
		return messageContent;
	}

	/**
	 * @param messageContent the messageContent to set
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
}
