package me.kingtux.jlinecommand;

import java.util.List;

/**
 * Implement this  to make a Jline command
 *
 * @author KingTux
 */
public interface JlineCommand {
    /**
     * The commands you want this class listen for
     *
     * @return all the commands you want this class to listen for
     */
    List<String> commands();

    /**
     * The execute method Put your code you want to execute when the command is ran
     *
     * @param args the command arguments
     */
    void execute(final List<String> args);
}
