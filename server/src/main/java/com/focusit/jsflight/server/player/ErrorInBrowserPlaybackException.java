package com.focusit.jsflight.server.player;

/**
 * Created by dkirpichenkov on 16.05.16.
 */
public class ErrorInBrowserPlaybackException extends Exception
{
    public ErrorInBrowserPlaybackException(String message)
    {
        super(message);
    }
}