/*
 * Copyright 2015-2017 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.platform.commons.util;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static org.junit.platform.commons.meta.API.Usage.Internal;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;

import org.junit.platform.commons.meta.API;

/**
 * Collection of utilities which provide useful {@link Collector}s for
 * {@linkplain java.util.stream.Stream streams}.
 *
 * <h3>DISCLAIMER</h3>
 *
 * <p>These utilities are intended solely for usage within the JUnit framework
 * itself. <strong>Any usage by external parties is not supported.</strong>
 * Use at your own risk!
 *
 * @since 1.0
 */
@API(Internal)
public final class JUnitCollectors {

	///CLOVER:OFF
	private JUnitCollectors() {
		/* no-op */
	}
	///CLOVER:ON

	/**
	 * Returns a {@code Collector} that accumulates the input elements into a
	 * new unmodifiable list, in encounter order. There are no guarantees on
	 * the type or serializability of the list returned, so if more control
	 * over the returned list is required, use {@link
	 * #toUnmodifiableList(Supplier)}.
	 *
	 * @param <T> the type of the input elements
	 * @return a {@code Collector} which collects all the input elements into
	 * an unmodifiable list, in encounter order
	 * @see #toUnmodifiableList(Supplier)
	 * @see java.util.stream.Collectors#toList()
	 * @see java.util.stream.Collectors#toCollection(Supplier)
	 */
	public static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
		return collectingAndThen(toList(), Collections::unmodifiableList);
	}

	/**
	 * Returns a {@code Collector} that accumulates the input elements into a
	 * new unmodifiable list, in encounter order. The underlying container of
	 * the unmodifiable list is created by the provided factory and must be
	 * mutable. The returned unmodifiable list is serializable if the
	 * factory-provided container is itself serializable.
	 *
	 * @param listSupplier a {@code Supplier} which returns a new, empty
	 * mutable list of the appropriate type, which forms the underlying
	 * container of the unmodifiable list
	 * @param <T> the type of the input elements
	 * @return a {@code Collector} which collects all the input elements into
	 * an unmodifiable list, in encounter order
	 */
	public static <T> Collector<T, ?, List<T>> toUnmodifiableList(Supplier<List<T>> listSupplier) {
		return collectingAndThen(toCollection(listSupplier), Collections::unmodifiableList);
	}

}
