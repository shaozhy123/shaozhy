package project.team.service;

public class Status {

    private final String STATE;

    private Status(String state){
        this.STATE = state;
    }

    public static final Status FREE = new Status("FREE");
    public static final Status BUSY = new Status("BUSY");
    public static final Status VOCATION = new Status("VOCATION");

    public String getSTATE() {
        return STATE;
    }

    @Override
    public String toString() {
        return STATE;
    }
}
