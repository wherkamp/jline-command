package me.kingtux.jlinecommand;

import java.util.List;

public interface JlineCompleter {

    List<String> complete(List<String> args);
}
