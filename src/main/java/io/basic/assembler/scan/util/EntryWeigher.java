package io.basic.assembler.scan.util;

import javax.annotation.concurrent.ThreadSafe;

/**
 * A class that can determine the weight of an entry. The total weight threshold
 * is used to determine when an eviction is required.
 *
 * @param <K> The key type
 * @param <V> The value type
 * @author ben.manes@gmail.com (Ben Manes)
 * @see <a href="http://code.google.com/p/concurrentlinkedhashmap/">
 *      http://code.google.com/p/concurrentlinkedhashmap/</a>
 */
@ThreadSafe
public interface EntryWeigher<K, V> {

    /**
     * Measures an entry's weight to determine how many units of capacity that
     * the key and value consumes. An entry must consume a minimum of one unit.
     *
     * @param key the key to weigh
     * @param value the value to weigh
     * @return the entry's weight
     */
    int weightOf(K key, V value);
}
