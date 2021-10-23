package solution;

public class Config {

    private final int tournamentSize = 40;  // the number of individual drawn in the tournament
    private final int rouletteSize = 40;
    private final int populationSize = 100;
    private final int gAIterationValue = 100;
    private final double px = 0.7;
    private final double pm = 0.1;

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
}
