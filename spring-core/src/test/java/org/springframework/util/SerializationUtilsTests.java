/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.util;

import java.math.BigInteger;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test for static utility to help with serialization.
 *
 * @author Dave Syer
 * @since 3.0.5
 */
public class SerializationUtilsTests {

	private static BigInteger FOO = new BigInteger(
			"-9702942423549012526722364838327831379660941553432801565505143675386108883970811292563757558516603356009681061" +
			"5697574744209306031461371833798723505120163874786203211176873686513374052845353833564048");


	@Test
	public void serializeCycleSunnyDay() throws Exception {
		assertEquals("foo", SerializationUtils.deserialize(SerializationUtils.serialize("foo")));
	}

	@Test
	public void deserializeUndefined() throws Exception {
		byte[] bytes = FOO.toByteArray();
		assertThatIllegalStateException().isThrownBy(() ->
				SerializationUtils.deserialize(bytes));
	}

	@Test
	public void serializeNonSerializable() throws Exception {
		assertThatIllegalArgumentException().isThrownBy(() ->
				SerializationUtils.serialize(new Object()));
	}

	@Test
	public void deserializeNonSerializable() throws Exception {
		assertThatIllegalArgumentException().isThrownBy(() ->
				SerializationUtils.deserialize("foo".getBytes()));
	}

	@Test
	public void serializeNull() throws Exception {
		assertNull(SerializationUtils.serialize(null));
	}

	@Test
	public void deserializeNull() throws Exception {
		assertNull(SerializationUtils.deserialize(null));
	}

}
