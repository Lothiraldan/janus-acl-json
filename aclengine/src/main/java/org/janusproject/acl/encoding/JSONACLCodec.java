package org.janusproject.acl.encoding;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.janusproject.acl.ACLMessage;
import org.janusproject.acl.ACLMessageContent;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.crio.core.AddressUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONACLCodec implements ACLMessageContentEncodingService {

	@Override
	public byte[] encode(ACLMessage aMsg) {
		JSONObject output = new JSONObject();
		try {
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

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return output.toString().getBytes();
	}

	@Override
	public ACLMessageContent decode(byte[] byteMsg, Object... parameters) {

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

		JSONObject json = null;
		try {
			json = new JSONObject(message);

			// PERFORMATIVE
			content.setPerformative(json.getInt("performative"));

			// SENDER
			if (json.has("sender")) {
				String sender_id = json.getJSONObject("sender").getString("id");
				content.setSender(AddressUtil.createAgentAddress(UUID
						.fromString(sender_id)));
			}

			// RECEIVERS
			if (json.has("receiver")) {
				JSONArray json_receivers = json.getJSONArray("receiver");
				Collection<AgentAddress> receivers = new ArrayList<AgentAddress>();

				for (int i = 0; i < json_receivers.length(); i++) {
					String receiver_info = json_receivers.getJSONObject(i)
							.getString("id");
					receivers.add(AddressUtil.createAgentAddress(UUID
							.fromString(receiver_info)));
				}
				content.setReceiver(receivers);
			}

			// CONTENT
			if (json.has("content")) {
				content.setContent(new StringBuffer(json.getString("content")));
			}

			// ENCODING
			content.setEncoding(json.getString("encoding"));

			// LANGUAGE
			if (json.has("language")) {
				content.setLanguage(json.getString("language"));
			}

			// ONTOLOGY
			if (json.has("ontology")) {
				content.setOntology(json.getString("ontology"));
			}

			// PROTOCOL
			content.setProtocol(json.getString("protocol"));

			// CONVERSATION ID
			if (json.has("conversationid")) {
				String uuid = json.getString("conversationid");

				try {
					content.setConversationId(UUID.fromString(uuid));
				} catch (IllegalArgumentException e) {
					content.setConversationId(null);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return content;
	}
}
