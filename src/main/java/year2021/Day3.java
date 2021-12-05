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
package year2021;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;

import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.function.BinaryOperator.minBy;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Day3 extends DayOf2021 {
    private final int columnLength = getLines().get(0).length();
    private final Comparator<Entry<Character, Long>> comparator = Entry.<Character, Long>comparingByValue()
            .thenComparing(comparingByKey());

    @SuppressWarnings("unused")
    Day3() {
        super(3);
    }

    Day3(String data) {
        super(data);
    }

    public static void main(String[] args) {
        solve(Day3.class);
    }

    private char findOccurrence(List<String> source, int index, BinaryOperator<Entry<Character, Long>> op) {
        var grouped = source.stream().collect(groupingBy(s -> s.charAt(index), counting()));
        //noinspection OptionalGetWithoutIsPresent
        return grouped.entrySet().stream().reduce(op).get().getKey();
    }

    @Override
    public Object first() {
        char[] common = new char[columnLength];
        for (int i = 0; i < common.length; i++) {
            common[i] = findOccurrence(getLines(), i, maxBy(comparingByValue()));
        }

        var gamma = Integer.parseInt(String.valueOf(common), 2);
        for (int i = 0; i < common.length; i++) {
            // Flip
            common[i] = (char) (common[i] ^ 1);
        }
        var epsilon = Integer.parseInt(String.valueOf(common), 2);
        return gamma * epsilon;
    }

    @Override
    public Object second() {
        var o2 = new ArrayList<>(getLines());
        var co2 = new ArrayList<>(getLines());

        for (int i = 0; i < columnLength; i++) {
            var common = findOccurrence(o2, i, maxBy(comparator));
            var uncommon = findOccurrence(co2, i, minBy(comparator));

            // Thanks java.
            final int x = i;
            o2.removeIf(p -> p.charAt(x) != common);
            co2.removeIf(p -> p.charAt(x) != uncommon);
        }
        return Integer.parseInt(o2.get(0), 2) * Integer.parseInt(co2.get(0), 2);
    }
}
