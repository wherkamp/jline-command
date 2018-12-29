package me.kingtux.jlinecommand;

import java.util.List;

/**
 * Implement this to make a tab completer for your command
 *
 * @author KingTux
 */
public interface JlineCompleter {
    /**
     * What is to be called when a command
     *
     * @param args the args already in the command
     * @return the suggestions to send
     */
    List<String> complete(final List<String> args);
}
