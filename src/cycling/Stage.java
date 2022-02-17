package cycling;

public class Stage {
    private final static int[] sprintFlatPoints = { 50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2 };
    private final static int[] sprintHillyPoints = { 30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2 };
    private final static int[] sprintHighPoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] sprintTTPoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] sprintIntermediatePoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] mountainCat4Points = { 1 };
    private final static int[] mountainCat3Points = { 2, 1 };
    private final static int[] mountainCat2Points = { 5, 3, 2, 1 };
    private final static int[] mountainCat1Points = { 10, 8, 6, 4, 2, 1 };
    private final static int[] mountainHCPoints = { 20, 15, 12, 10, 8, 6, 4, 2 };

    public static int[] getSprintFlat() {
        return sprintFlatPoints;
    }

    public static int[] getSprintHilly() {
        return sprintHillyPoints;
    }

    public static int[] getSprintTT() {
        return sprintTTPoints;
    }

    public static int[] getSprintHigh() {
        return sprintHighPoints;
    }

    public static int[] getSprintIntermediate() {
        return sprintIntermediatePoints;
    }

    public static int[] getMountainC4() {
        return mountainCat4Points;
    }

    public static int[] getMountainC3() {
        return mountainCat3Points;
    }

    public static int[] getMountainC2() {
        return mountainCat2Points;
    }

    public static int[] getMountainC1() {
        return mountainCat1Points;
    }

    public static int[] getMountainHC() {
        return mountainHCPoints;
    }
}
