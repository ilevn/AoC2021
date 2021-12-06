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

import java.util.Arrays;

public class Day6 extends DayOf2021 {
    private final long[] initial = new long[9];

    {
        for (String s : getData().split(",")) {
            initial[Integer.parseInt(s)]++;
        }
    }

    @SuppressWarnings("unused")
    Day6() {
        super(6);
    }

    Day6(String data) {
        super(data);
    }

    public static void main(String[] args) {
        solve(Day6.class);
    }

    private long simulate(int afterDays) {
        var state = initial.clone();
        for (int i = 0; i < afterDays; i++) {
            var zero = state[0];
            System.arraycopy(state, 1, state, 0, state.length - 1);
            state[6] += zero;
            state[8] = zero;
        }
        return Arrays.stream(state).sum();
    }

    @Override
    public Object first() {
        return simulate(80);
    }

    @Override
    public Object second() {
        return simulate(256);
    }
}
