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

public class Day2 extends DayOf2021 {
    private final List<String[]> instructions = getLines().stream().map(s -> s.split(" ", 2)).toList();

    @SuppressWarnings("unused")
    Day2() {
        super(2);
    }

    Day2(String data) {
        super(data);
    }

    public static void main(String[] args) {
        solve(Day2.class);
    }


    @Override
    public Object first() {
        int horizontal = 0;
        int depth = 0;

        for (String[] inst : instructions) {
            var value = Integer.parseInt(inst[1]);
            switch (inst[0]) {
                case "forward" -> horizontal += value;
                case "down" -> depth += value;
                case "up" -> depth -= value;
            }
        }
        return depth * horizontal;
    }

    @Override
    public Object second() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String[] inst : instructions) {
            var value = Integer.parseInt(inst[1]);
            switch (inst[0]) {
                case "forward" -> {
                    depth += aim * value;
                    horizontal += value;
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
            }
        }
        return depth * horizontal;
    }
}
