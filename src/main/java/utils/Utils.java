/*
 * This file is part of ilevn/AoC2021.
 *
 * ilevn/AoC2021 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ilevn/AoC2021 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ilevn/AoC2021. If not, see <https://www.gnu.org/licenses/>.
 *
 */
package utils;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class Utils {
    private Utils() {
        throw new AssertionError("Do not init this class.");
    }

    /**
     * Provide a sliding window over a fixed size, random-access list.
     *
     * @param list the source list
     * @param size the size of the sliding window
     * @return a stream of chunked elements
     */
    public static <T> Stream<List<T>> sliding(List<T> list, int size) {
        if (size > list.size())
            return Stream.empty();
        return range(0, list.size() - size + 1).mapToObj(start -> list.subList(start, start + size));
    }
}
