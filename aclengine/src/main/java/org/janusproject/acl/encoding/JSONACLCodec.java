package org.janusproject.acl.encoding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.janusproject.acl.ACLMessage;
import org.janusproject.acl.ACLMessageContent;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.crio.core.AddressUtil;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import de.undercouch.bson4jackson.BsonFactory;

public class JSONACLCodec implements ACLMessageContentEncodingService {

	@Override
	public byte[] encode(ACLMessage aMsg) {

		Map<String, Object> output = new HashMap<String, Object>();

		// Performative
		output.put("performative", aMsg.getPerformative().ordinal());

		// Display SENDER
		AgentAddress sender = aMsg.getSender();
		if (sender != null) {
			Map<String, String> sender_infos = new HashMap<String, String>();
			sender_infos.put("name", sender.getName());
			sender_infos.put("id", sender.getUUID().toString());
			output.put("sender", sender_infos);
		}

		// Display RECEIVERS
		Collection<AgentAddress> receivers = aMsg.getReceiver();
		Collection<Map<String, String>> receivers_infos = new ArrayList<Map<String, String>>();

		if (receivers != null) {
			for (AgentAddress receiver : receivers) {
				Map<String, String> receiver_info = new HashMap<String, String>();
				receiver_info.put("name", receiver.getName());
				receiver_info.put("id", receiver.getUUID().toString());
				receivers_infos.add(receiver_info);
			}
			output.put("receiver", receivers_infos);
		}

		// Display CONTENT
		String content = aMsg.getContent().getContent().toString();
		if (content != null && content.length() > 0) {
			output.put("content", content.trim());
		}

		// Display ENCODING
		output.put("encoding", aMsg.getEncoding());

		// Display LANGUAGE
		output.put("language", aMsg.getLanguage());

		// Display ONTOLOGY
		output.put("ontology", aMsg.getOntology());

		// Display PROTOCOL
		output.put("protocol", aMsg.getProtocol().getName());

		// Display CONVERSATION ID
		output.put("conversationid", aMsg.getConversationId());

		return fromMap(output);
	}

	@Override
	public ACLMessageContent decode(byte[] byteMsg, Object... parameters) {
		System.out.println(new String(byteMsg));
		ACLMessage.Content content = new ACLMessage.Content();

		// Get charset parameter
		String charset = PayloadEncoding.UTF8.getValue();
		for (Object parameter : parameters) {
			if (parameter instanceof PayloadEncoding) {
				charset = ((PayloadEncoding) parameter).getValue();
			}
		}

		// Try decoding with provided charset
		String message;
		try {
			message = new String(byteMsg, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}

		Map<String, Object> json = fromBytes(message);
		;

		// PERFORMATIVE
		content.setPerformative((Integer) json.get("performative"));

		// SENDER
		if (json.containsKey("sender")) {
			String sender_id = (String) ((Map<String, Object>) json
					.get("sender")).get("id");
			content.setSender(AddressUtil.createAgentAddress(UUID
					.fromString(sender_id)));
		}

		// RECEIVERS
		if (json.containsKey("receiver")) {
			ArrayList<Map<String, Object>> json_receivers = (ArrayList<Map<String, Object>>) json
					.get("receiver");
			Collection<AgentAddress> receivers = new ArrayList<AgentAddress>();

			for (int i = 0; i < json_receivers.size(); i++) {
				String receiver_info = (String) json_receivers.get(i).get("id");
				receivers.add(AddressUtil.createAgentAddress(UUID
						.fromString(receiver_info)));
			}
			content.setReceiver(receivers);
		}

		// CONTENT
		if (json.containsKey("content")) {
			content.setContent(new StringBuffer((String) json.get("content")));
		}

		// ENCODING
		content.setEncoding((String) json.get("encoding"));

		// LANGUAGE
		if (json.containsKey("language")) {
			content.setLanguage((String) json.get("language"));
		}

		// ONTOLOGY
		if (json.containsKey("ontology")) {
			content.setOntology((String) json.get("ontology"));
		}

		// PROTOCOL
		content.setProtocol((String) json.get("protocol"));

		// CONVERSATION ID
		if (json.containsKey("conversationid")
				&& json.get("conversationid") != null) {

			try {
				String uuid = (String) json.get("conversationid");
				content.setConversationId(UUID.fromString(uuid));
			} catch (IllegalArgumentException e) {
				content.setConversationId(null);
			}
		}

		return content;
	}

	private byte[] fromMap(Map m) {
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			mapper.writeValue(baos, m);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	private Map<String, Object> fromBytes(String src) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(src,
					new TypeReference<Map<String, Object>>() {
					});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
