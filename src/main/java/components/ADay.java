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
package components;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;

public abstract class ADay {
    private final int year;
    private final int day;
    private final String data;
    private List<String> linesList;

    /**
     * Returns a {@code List<String>} of the puzzle data.
     */
    public @NotNull List<String> getLines() {
        if (linesList == null) {
            linesList = getData().lines().toList();
        }
        return linesList;
    }

    /**
     * Returns the {@code String} value of the puzzle data.
     */
    public @NotNull String getData() {
        return data;
    }

    protected ADay(int year, int day) {
        this.year = year;
        this.day = day;
        data = Input.get(day, year).trim();
    }

    protected ADay(String data) {
        year = day = 0;
        this.data = data;
    }

    /**
     * Attempts to evaluate the first part of the puzzle.
     *
     * @return the solution to part one
     */
    public Object first() {
        return null;
    }

    /**
     * Attempts to evaluate the second part of the puzzle.
     *
     * @return the solution to part two
     */
    public Object second() {
        return null;
    }

    /**
     * Runs the solutions for both parts and times them.
     * This is usually done inside a main method.
     * Example:
     * <pre>{@code
     *    public static void main(String[] args) {
     *         solve(Day1.class);
     *     }
     * }</pre>
     *
     * @param clazz the child class
     */
    public static void solve(Class<? extends ADay> clazz) {
        ADay aDay;

        try {
            Constructor<? extends ADay> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            aDay = constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            return;
        }

        System.out.printf("Year %d, day %d%n%n", aDay.year, aDay.day);
        System.out.println("First: " + evalSolution(aDay::first));
        System.out.println("Second: " + evalSolution(aDay::second));
    }

    private static String evalSolution(Supplier<Object> func) {
        long startTime = System.currentTimeMillis();
        Object result = func.get();
        long endTime = System.currentTimeMillis();
        return "%s%nTime: %d ms".formatted(result == null ? "unsolved" : result, endTime - startTime);
    }
}
