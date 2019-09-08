package com.tw.go.plugin.model;

import com.tw.go.plugin.util.StringUtil;

import java.util.Objects;

public class GitConfig {
    private String url;
    private String username;
    private String password;
    private String branch;
    private boolean subModule = false;
    private boolean recursiveSubModuleUpdate = true;
    private boolean shallowClone = false;

    public GitConfig(String url) {
        this.url = url;
    }

    public GitConfig(String url, String username, String password, String branch) {
        this(url, username, password, branch, true, false);
    }

    public GitConfig(String url, String username, String password, String branch, boolean recursiveSubModuleUpdate, boolean shallowClone) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.branch = branch;
        this.recursiveSubModuleUpdate = recursiveSubModuleUpdate;
        this.shallowClone = shallowClone;
    }

    public boolean isRemoteUrl() {
        return url.startsWith("http://") || url.startsWith("https://");
    }

    public boolean hasCredentials() {
        return !StringUtil.isEmpty(url) && !StringUtil.isEmpty(password);
    }

    public String getEffectiveUrl() {
        if (isRemoteUrl() && hasCredentials()) {
            return getUrlWithCredentials();
        }
        return getUrl();
    }

    public String getUrlWithCredentials() {
        String[] parts = url.split("://");
        return String.format("%s://%s:%s@%s", parts[0], username, password, parts[1]);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteBranch() {
        return String.format("origin/%s", getEffectiveBranch());
    }

    public String getEffectiveBranch() {
        return StringUtil.isEmpty(branch) ? "master" : branch;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public boolean isSubModule() {
        return subModule;
    }

    public void setSubModule(boolean subModule) {
        this.subModule = subModule;
    }

    public boolean isRecursiveSubModuleUpdate() {
        return recursiveSubModuleUpdate;
    }

    public void setRecursiveSubModuleUpdate(boolean recursiveSubModuleUpdate) {
        this.recursiveSubModuleUpdate = recursiveSubModuleUpdate;
    }

    public boolean isShallowClone() {
        return shallowClone;
    }

    public void setShallowClone(boolean shallowClone) {
        this.shallowClone = shallowClone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GitConfig gitConfig = (GitConfig) o;

        if (subModule != gitConfig.subModule) return false;
        if (recursiveSubModuleUpdate != gitConfig.recursiveSubModuleUpdate) return false;
        if (shallowClone != gitConfig.shallowClone) return false;
        if (!Objects.equals(branch, gitConfig.branch)) return false;
        if (!Objects.equals(password, gitConfig.password)) return false;
        if (!Objects.equals(url, gitConfig.url)) return false;

        return Objects.equals(username, gitConfig.username);
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + (recursiveSubModuleUpdate ? 1 : 0);
        result = 31 * result + (shallowClone ? 1 : 0);
        result = 31 * result + (subModule ? 1 : 0);
        return result;
    }
}
