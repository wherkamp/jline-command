package me.kingtux.jlinecommand;

import java.util.List;

public interface JlineCommand {
    List<String> commands();

    void execute(List<String> args);
}
