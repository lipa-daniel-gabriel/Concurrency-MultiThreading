package com.playtika.java.academy.challenge2.lipa.daniel.spaceInvaders;

public class SpaceInvader {
    private int X;
    private int Y;
    private boolean hasIncreasedDageme;

    public SpaceInvader(int x, int y, boolean hasIncreasedDageme) {
        X = x;
        Y = y;
        this.hasIncreasedDageme = hasIncreasedDageme;

    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isHasIncreasedDageme() {
        return hasIncreasedDageme;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SpaceInvader{");
        sb.append("X=").append(X);
        sb.append(", Y=").append(Y);
        sb.append(", hasIncreasedDageme=").append(hasIncreasedDageme);
        sb.append('}');
        return sb.toString();
    }
}
