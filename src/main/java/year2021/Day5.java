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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.signum;
import static java.lang.Math.*;
import static java.util.Map.entry;

public class Day5 extends DayOf2021 {
    private final List<Line> segments = getLines().stream().map(s -> {
        var seg = s.split(" -> ", 2);
        var x1y1 = seg[0].split(",", 2);
        var x2y2 = seg[1].split(",", 2);
        return new Line(parseInt(x1y1[0]), parseInt(x1y1[1]), parseInt(x2y2[0]), parseInt(x2y2[1]));
    }).toList();

    private record Line(int xStart, int yStart, int xEnd, int yEnd) {
    }

    @SuppressWarnings("unused")
    public Day5() {
        super(5);
    }

    public Day5(String data) {
        super(data);
    }

    @Override
    public Object first() {
        var map = new HashMap<Map.Entry<Integer, Integer>, Integer>();
        for (Line seg : segments) {
            computeHorizontalAndVertical(map, seg);
        }
        return map.values().stream().filter(v -> v > 1).count();
    }

    private void computeHorizontalAndVertical(HashMap<Map.Entry<Integer, Integer>, Integer> map, Line seg) {
        if (seg.xStart == seg.xEnd) {
            for (int y = min(seg.yStart, seg.yEnd); y <= max(seg.yStart, seg.yEnd); y++) {
                map.put(entry(seg.xStart, y), map.computeIfAbsent(entry(seg.xStart, y), f -> 0) + 1);
            }
        }
        else if (seg.yStart == seg.yEnd) {
            for (int x = min(seg.xStart, seg.xEnd); x <= max(seg.xStart, seg.xEnd); x++) {
                map.put(entry(x, seg.yStart), map.computeIfAbsent(entry(x, seg.yStart), f -> 0) + 1);
            }
        }
    }

    @Override
    public Object second() {
        var map = new HashMap<Map.Entry<Integer, Integer>, Integer>();
        for (Line seg : segments) {
            computeHorizontalAndVertical(map, seg);
            int xAbs = abs(seg.xEnd - seg.xStart);

            if (xAbs == abs(seg.yEnd - seg.yStart)) {
                var dx = signum(seg.xEnd - seg.xStart);
                var dy = signum(seg.yEnd - seg.yStart);
                for (int i = 0; i <= xAbs; i++) {
                    var xy = entry(seg.xStart + i * dx, seg.yStart + i * dy);
                    map.put(xy, map.computeIfAbsent(xy, f -> 0) + 1);
                }
            }
        }
        return map.values().stream().filter(v -> v > 1).count();
    }

    public static void main(String[] args) {
        solve(Day5.class);
    }
}
