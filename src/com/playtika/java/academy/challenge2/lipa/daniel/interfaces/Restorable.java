package com.playtika.java.academy.challenge2.lipa.daniel.interfaces;

import java.io.IOException;

public interface Restorable {

    public void saveProfile(String fileName) throws IOException;

    public void loadProfile(String fileName) throws IOException;
}
