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
import java.util.regex.Pattern;

public class Day2 extends DayOf2021 {
    private final Pattern pat = Pattern.compile("(\\w+)\\s+(\\d+)");
    private final List<Instruction> instructions = getLines().stream().map(s -> {
        var matcher = pat.matcher(s);
        matcher.find();
        return Instruction.fromMatch(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }).toList();


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

        for (Instruction inst : instructions) {
            switch (inst.direction) {
                case "forward" -> horizontal += inst.value;
                case "down" -> depth += inst.value;
                case "up" -> depth -= inst.value;
            }
        }
        return depth * horizontal;
    }

    @Override
    public Object second() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (Instruction inst : instructions) {
            switch (inst.direction) {
                case "forward" -> {
                    depth += aim * inst.value;
                    horizontal += inst.value;
                }
                case "down" -> aim += inst.value;
                case "up" -> aim -= inst.value;
            }
        }

        return depth * horizontal;
    }

    record Instruction(String direction, int value) {
        static Instruction fromMatch(String direction, int value) {
            return new Instruction(direction, value);
        }
    }
}
