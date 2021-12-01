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

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Day1Test {
    private final Day1 day1 = new Day1("""
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263""");


    @Test
    void first_example_should_equal_7() {
        assertThat(day1.first()).isEqualTo(7L);
    }

    @Test
    void second_example_should_equal_5() {
        assertThat(day1.second()).isEqualTo(5L);
    }
}
