package me.kingtux.jlinecommand;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.util.*;

/**
 * A manager for the command library.
 *
 * @author KingTux
 */
public class JlineCommandManager extends Thread {
    //appName
    //appName and Terminal
    //appName, key, and terminal
    //appName, key
    private LineReader reader;
    private String key;
    private boolean running = true;
    private Map<JlineCommand, JlineCompleter> commands = new HashMap<>();

    public JlineCommandManager(String appName) {
        this(appName, ">");
    }

    public JlineCommandManager(String appName, String key) {
        this(appName, key, defaultTerminal());
    }

    public JlineCommandManager(String appName, Terminal t) {
        this(appName, ">", t);
    }


    public JlineCommandManager(String appName, String key, Terminal terminal) {
        reader = LineReaderBuilder.builder().appName(appName).terminal(terminal).completer(new SimpleCompleter(this)).build();
        this.key = key;
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        start();
    }

    @SuppressWarnings("WeakerAccess")
    public void close() {
        running = false;
    }

    private void runCommand(String s) {
        String[] run = s.split(" ");
        List<String> runList = new ArrayList<>(Arrays.asList(run));
        Map.Entry<JlineCommand, JlineCompleter> command = getCommand(runList.remove(0));
        command.getKey().execute(runList);
    }

    private static Terminal defaultTerminal() {
        try {
            return TerminalBuilder.terminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void register(JlineCommand jlineCommand) {
        register(jlineCommand, args -> new ArrayList<>());
    }

    public void register(JlineCommand jlineCommand, JlineCompleter jlineCompleter) {
        commands.put(jlineCommand, jlineCompleter);
    }

    @SuppressWarnings("WeakerAccess")
    public Map<JlineCommand, JlineCompleter> getCommands() {
        return commands;
    }
    @SuppressWarnings("WeakerAccess")
    public Map.Entry<JlineCommand, JlineCompleter> getCommand(String word) {
        for (JlineCommand jlineCommand : commands.keySet()) {
            if (jlineCommand.commands().contains(word.toLowerCase())) {
                return getEntryFromKey(jlineCommand);
            }
        }
        return null;
    }

    private Map.Entry<JlineCommand, JlineCompleter> getEntryFromKey(JlineCommand jlineCommand) {
        for (Map.Entry<JlineCommand, JlineCompleter> j : commands.entrySet()) {
            if (j.getKey() == jlineCommand) {
                return j;
            }
        }
        return null;
    }

    @Override
    public void run() {
        while (running) {
            try {
                reader.getTerminal().flush();
                String s = reader.readLine(key + " ");
                if (!s.isEmpty()) {
                    runCommand(s);
                }
            } catch (UserInterruptException ignored) {

            }
        }
    }
}
