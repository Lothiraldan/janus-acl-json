package org.janusproject.acl.encoding;

import org.codehaus.jackson.map.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;

public class BSONACLCodec extends JSONACLCodec {

	protected ObjectMapper getMapper() {
		return new ObjectMapper(new BsonFactory());
	}
}
