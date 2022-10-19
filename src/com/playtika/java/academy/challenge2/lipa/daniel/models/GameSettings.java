package com.playtika.java.academy.challenge2.lipa.daniel.models;

public class GameSettings {

    private String clientVersion;
    private boolean autoSave;
    private int autoSaveTimeoutInSeconds;
    private String serverURL;

    public GameSettings(String clientVersion, int autoSaveTimeoutInSeconds, String serverURL) {
        this.clientVersion = clientVersion;
        this.autoSaveTimeoutInSeconds = autoSaveTimeoutInSeconds;
        this.serverURL = serverURL;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public boolean isAutoSave() {
        return autoSave;
    }

    public int getAutoSaveTimeoutInSeconds() {
        return autoSaveTimeoutInSeconds;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void stopAutoSave() {
        this.autoSave = false;
    }

    public void startAutoSave() {
        this.autoSave = true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GameSettings{");
        sb.append("clientVersion='").append(clientVersion).append('\'');
        sb.append(", autoSave=").append(autoSave);
        sb.append(", autoSaveTimeoutInSeconds=").append(autoSaveTimeoutInSeconds);
        sb.append(", serverURL='").append(serverURL).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
