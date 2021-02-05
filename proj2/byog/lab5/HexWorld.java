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
        int originalX = calculateStartX(maxRowLength);
        int originalY = calculateStartY(maxRowLength);

        construct(world);
        buildMiddle(world, maxRowLength, originalX, originalY);
        buildTop(world, s, maxRowLength, originalX, originalY);
        buildBot(world, s, maxRowLength, originalX, originalY);
    }

    public static void buildMiddle(TETile[][] world, int rowLength, int posX, int posY) {
        for (int y = posY; y < posY + 2; y += 1) {
            for (int x = posX; x < posX + rowLength; x += 1) {
                world[x][y] = Tileset.FLOWER;
            }
        }
    }

    private static void buildTop(TETile[][] world, int size, int rowLength, int posX, int posY) {
        // For building top:
        int startY = posY + 2; // 21 -> 23
        int startX = posX + 1; // 21 -> 22
        int endY = startY + size - 1; // 25
        int endX = startX + rowLength - 2; // 22 + 7 - 1 -> 28
        for (int y = startY; y < endY; y++) {
            for (int x = startX; x < endX; x++) {
                world[x][y] = Tileset.FLOWER;
            }
            startX++;
            endX--;
        }
    }

    private static void buildBot(TETile[][] world, int size, int rowLength, int posX, int posY) {
        int startY = posY - 1; // Starting y-pos for building 1st bot row
        int startX = posX + 1; // Starting x-pos for building 1st bot row
        int endY = startY - size + 1; // End y-pos for building bot
        int endX = startX + rowLength - 2; // End x-pos for each row
        for (int y = startY; y > endY; y--) {
            for (int x = startX; x < endX; x++) {
                world[x][y] = Tileset.FLOWER;
            }
            startX++; // Increments starting x-pos after building a row (moves 1-tile to right)
            endX--; // Decrements ending x-pos after building a row (moves 1 tile to left)
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
        addHexagon(tiles, 2);

        ter.renderFrame(tiles);
    }
}
