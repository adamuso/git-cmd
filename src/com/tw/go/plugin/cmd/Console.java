package com.tw.go.plugin.cmd;


public class Console extends ConsoleBase {
    private static final Console instance = new Console();

    public static Console getInstance() {
        return instance;
    }
}
