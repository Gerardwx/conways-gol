package io.github.liutaurasvilda.gol.core;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class World {

    static final int SIZE = 10;
    private final Map<Location, Mutable> map;

    private World() {
        this.map = new LinkedHashMap<>();
        IntStream.range(0, SIZE)
                 .forEach(r -> IntStream.range(0, SIZE)
                 .forEach(c -> map.put(Location.of(r, c), Cell.dead())));
    }

    public static World empty() {
        return new World();
    }

    public void aliveAt(Location location) {
        map.put(location, Cell.alive());
    }

    public boolean hasPopulation() {
        return map.entrySet()
                  .stream()
                  .anyMatch(e -> e.getValue().phase() == Mutable.Phase.ALIVE);
    }


    public World nextGeneration() {
        World newWorld = new World();
        IntStream.range(0, SIZE)
                 .forEach(x -> IntStream.range(0, SIZE)
                 .forEach(y -> {
                     ConwaysRules.Builder rules = new ConwaysRules.Builder();
                     rules.withLivingNeighbors(Location.of(x, y).numberOfLivingNeighborsInA(map));
                     newWorld.map.put(Location.of(x, y), this.map.get(Location.of(x, y)).mutate(rules.build()));
                 }));
        return newWorld;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, SIZE)
                 .forEach(r -> {
                     IntStream.range(0, SIZE)
                              .forEach(c -> sb.append(map.get(Location.of(r, c))));
                     sb.append("\n");
                 });
        return sb.toString();
    }
}
