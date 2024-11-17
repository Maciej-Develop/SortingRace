package model;

import model.tools.Type;

public class Update {

    private final Type type;

    private final int size;

    private final long action;

    private final long duration;

    public Update(Type type, int size, long action, long duration) {
        this.type = type;
        this.size = size;
        this.action = action;
        this.duration = duration;
    }

    public Type getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public long getAction() {
        return action;
    }

    public long getDuration() {
        return duration;
    }
}
