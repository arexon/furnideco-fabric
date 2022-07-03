package dev.arexon.furnideco.content.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum TableShape implements StringIdentifiable {

    ALL("all"),
    NORTH_END("north_end"),
    SOUTH_END("south_end"),
    EAST_END("east_end"),
    WEST_END("west_end"),
    NE_CORNER("ne_corner"),
    SE_CORNER("se_corner"),
    SW_CORNER("sw_corner"),
    NW_CORNER("nw_corner"),
    NONE("none");

    private final String name;

    TableShape(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
