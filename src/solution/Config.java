package solution;

public class Config {

    private final int tournamentSize = 5;  // the number of individual drawn in the tournament
    private final int populationSize = 10;
    private final int gAIterationValue = 20;
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
}
