package me.kingtux.jlinecommand;

import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;
import java.util.Map;

public class SimpleCompleter implements Completer {
    private JlineCommandManager jlineCommandManager;

    public SimpleCompleter(JlineCommandManager jlineCommandManager) {
        this.jlineCommandManager = jlineCommandManager;
    }

    @Override
    public void complete(LineReader lineReader, ParsedLine parsedLine, List<Candidate> list) {
        assert lineReader != null;
        assert parsedLine != null;
        if (parsedLine.wordIndex() == 0) {
            for (JlineCommand jlineCommand : jlineCommandManager.getCommands().keySet()) {
                jlineCommand.commands().stream().forEach(s -> {
                    list.add(new Candidate(s));
                });
            }
        } else {
            Map.Entry<JlineCommand, JlineCompleter> command = jlineCommandManager.getCommand(parsedLine.line().split(" ")[0]);
            command.getValue().complete(parsedLine.words()).forEach(s -> {
                list.add(new Candidate(s));
            });
        }
    }
}
