package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final int xOFF = 5;
    private static final int yOFF = 5;

    /**
     * 1. Initialize TileRenderer (TERenderer object)
     * 2. Create 2D TeTile[][] array
     * Compute 1) Height and 2) Width
     */

    /**
     * p = object from Position class with x and y coords
     * s = hexagon size
     * t = tile choice
     */
    public static void addHexagon(TETile[][] world, int s) {
        assert s >= 2;
        int maxRowLength = calculateMaxLength(s);
        int startingX = calculateStartX(maxRowLength);
        int startingY = calculateStartY(maxRowLength);
        System.out.println(startingX);
        System.out.println(startingY);
        construct(world);
        buildMiddle(world, maxRowLength, startingX, startingY);
        buildTop(world, s, maxRowLength, startingX, startingY);
    }

    public static void buildMiddle(TETile[][] world, int rowLength, int posX, int posY) {
        for (int y = posY; y < posY + 2; y += 1) {
            for (int x = posX; x < posX + rowLength; x += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
    }

    public static void buildTop(TETile[][] world, int size, int rowLength, int startX, int startY) {
        // For building top: y begins at startY + 2 & x begins at startX + 1;
        startY = startY + 2;
        startX = startX + 1;
        int endY = startY + size - 1;
        int endX = startX + rowLength - 1;
        for (int y = startY + 2; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                world[x][y] = Tileset.FLOWER;
            }
            startX++;
            endX--;
        }
    }


    /* Calculates the length for widest part of hexagon */
    public static int calculateMaxLength(int size) {
          return (((size - 2) * 3) + 4);
    }

    /* Calculates starting x-pos given max rowLength (in tiles) */
    public static int calculateStartX(int rowLength) {
        double median = WIDTH/2;
        double halfLength = (double) rowLength / 2;
        double startX = median - halfLength;
        return (int) startX;
    }

    public static int calculateStartY(int rowLength) {
        double median = HEIGHT/2;
        double halfLength = (double) rowLength / 2;
        double startY = median - halfLength;
        return (int) startY;
    }

    /* Constructs the world with preset width and height */
    private static void construct(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        addHexagon(tiles, 3);
//        for (int x = 22; x < 27; x++) {
//            for (int y = 23; y < 24; y++) {
//                tiles[x][y] = Tileset.WALL;
//            }
//        }

        ter.renderFrame(tiles);
    }
}
