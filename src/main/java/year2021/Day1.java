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

import java.util.List;

import static java.util.stream.IntStream.range;
import static utils.Utils.sliding;

public class Day1 extends DayOf2021 {
    private final List<Integer> nums = getLines().stream().mapToInt(Integer::parseInt).boxed().toList();

    Day1() {
        super(1);
    }

    Day1(String data) {
        super(data);
    }

    private long computeCount(List<Integer> list) {
        return range(0, list.size() - 1).filter(i -> list.get(i) < list.get(i + 1)).count();
    }

    @Override
    public Object first() {
        return computeCount(nums);
    }


    @Override
    public Object second() {
        var sums = sliding(nums, 3).map(l -> l.stream().mapToInt(i -> i).sum()).toList();
        return computeCount(sums);
    }


    public static void main(String[] args) {
        solve(Day1.class);
    }
}
