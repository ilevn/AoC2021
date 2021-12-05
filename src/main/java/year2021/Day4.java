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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Map.entry;
import static java.util.stream.IntStream.range;

public class Day4 extends DayOf2021 {
    private final List<Integer> draws = stream(getLines().get(0).split(",")).map(Integer::parseInt).toList();
    private final List<Board> boards = stream(getData().split("\n{2}")).skip(1)
            .map(s -> Board.parseArray(s.split("\n"))).toList();


    @SuppressWarnings("unused")
    Day4() {
        super(4);
    }

    Day4(String data) {
        super(data);
    }

    private record Board(List<List<Integer>> grid) {
        static Board parseArray(String[] arr) {
            var mapped = range(0, 5)
                    .mapToObj(i -> stream(arr[i].trim().split("\s+"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())).toList();
            return new Board(mapped);
        }

        boolean hasBingo() {
            return grid.stream().anyMatch(row -> row.stream().allMatch(Objects::isNull))
                    || range(0, 5).anyMatch(column -> grid.stream().allMatch(c -> c.get(column) == null));
        }

        int sum() {
            return grid.stream().flatMapToInt(row -> row.stream().mapToInt(f -> f != null ? f : 0)).sum();
        }

        void draw(int number) {
            for (List<Integer> row : grid) {
                var it = row.listIterator();
                while (it.hasNext()) {
                    if (Objects.equals(it.next(), number)) it.set(null);
                }
            }
        }
    }

    @Override
    public Object first() {
        for (Integer draw : draws) {
            for (Board board : boards) {
                board.draw(draw);
                if (board.hasBingo()) {
                    return draw * board.sum();
                }
            }
        }
        return "could not find winning board";
    }

    @Override
    public Object second() {
        return boards.stream().map(b -> {
            for (int i = 0; i < draws.size(); i++) {
                int draw = draws.get(i);
                b.draw(draw);
                if (b.hasBingo()) return entry(i, b.sum() * draw);
            }
            return null;
        }).max(Map.Entry.comparingByKey()).get().getValue();
    }

    public static void main(String[] args) {
        solve(Day4.class);
    }
}
