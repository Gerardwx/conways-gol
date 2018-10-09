package io.github.liutaurasvilda.gol.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {

    @Test
    public void empty_world_has_no_population() {
        World world = World.empty();
        assertTrue(!world.hasPopulation());
    }

    @Test
    public void world_with_one_alive_cell_has_population() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(world.hasPopulation());
    }

    @Test
    public void world_with_alive_and_later_same_dead_cell_has_no_population() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.deadAt(Location.of(0, 0));
        assertTrue(!world.hasPopulation());
    }

    @Test
    public void world_with_no_population_has_no_population_in_next_generation() {
        World world = World.empty();
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_one_alive_cell_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_two_inline_alive_cells_has_no_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        assertTrue(!world.nextGeneration().hasPopulation());
    }

    @Test
    public void world_with_three_inline_alive_cells_has_population_in_next_generation() {
        World world = World.empty();
        world.aliveAt(Location.of(0, 0));
        world.aliveAt(Location.of(1, 0));
        world.aliveAt(Location.of(2, 0));
        assertTrue(world.nextGeneration().hasPopulation());
    }
}