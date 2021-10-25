package solution;

public class Config {

    // GA
    private final int tournamentSize = 40;  // the number of individual drawn in the tournament
    private final int rouletteSize = 40;
    private final int populationSize = 50;
    private final int gAIterationValue = 50;
    private final double px = 0.7;
    private final double pm = 0.1;

    // TS
    private final int tS_iterAtions = 1000; // number of iterations
    private final int tS_NSize = 20; // neighborhood size
    private final int tS_TSize = 50; // tabu list size

    public Config() {

    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getgAIterationValue() {
        return gAIterationValue;
    }

    public double getPx() {
        return px;
    }

    public double getPm() {
        return pm;
    }

    public int getRouletteSize() {
        return rouletteSize;
    }

    public int gettS_iterAtions() {
        return tS_iterAtions;
    }

    public int gettS_NSize() {
        return tS_NSize;
    }

    public int gettS_TSize() {
        return tS_TSize;
    }
}
