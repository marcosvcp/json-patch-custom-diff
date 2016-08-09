package com.github.fge.jsonpatch.diff;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;

import java.util.Map;

/*
 * Copyright (c) 2016, Marcos Candeia (marrcooos@gmail.com)
 *
 * This software is dual-licensed under:
 *
 * - the Lesser General Public License (LGPL) version 3.0 or, at your option, any
 *   later version;
 * - the Apache Software License (ASL) version 2.0.
 *
 * The text of this file and of both licenses is available at the root of this
 * project or, if you have the jar distribution, in directory META-INF/, under
 * the names LGPL-3.0.txt and ASL-2.0.txt respectively.
 *
 * Direct link to the sources:
 *
 * - LGPL 3.0: https://www.gnu.org/licenses/lgpl-3.0.txt
 * - ASL 2.0: http://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Decorator {@see DiffProcessor}
 */
public class TestAndSetDiffProcessor extends DiffProcessor {

	TestAndSetDiffProcessor(Map<JsonPointer, JsonNode> unchanged) {
		super(unchanged);
	}

	@Override
	void valueReplaced(final JsonPointer pointer, final JsonNode oldValue,
			final JsonNode newValue)
	{
		this.test(pointer, newValue);
		super.valueReplaced(pointer, oldValue, newValue);
	}

	@Override
	void valueRemoved(final JsonPointer pointer, final JsonNode value)
	{
		this.test(pointer, value);
		super.valueRemoved(pointer, value);
	}

	/**
	 * Using https://tools.ietf.org/html/rfc6902#section-4.6,
	 * test the properties that were changed
	 */
	void test(final JsonPointer pointer, final JsonNode value)
	{
		super.diffs.add(DiffOperation.test(pointer, value));
	}
}
