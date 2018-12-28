import me.kingtux.jlinecommand.JlineCommand;
import me.kingtux.jlinecommand.JlineCommandManager;
import me.kingtux.jlinecommand.JlineCompleter;

import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        JlineCommandManager jlineCommandManager = new JlineCommandManager("jline-test", ">");
        jlineCommandManager.register(new JlineCommand() {
            @Override
            public List<String> commands() {
                return Collections.singletonList("test");
            }

            @Override
            public void execute(List<String> args) {
                System.out.println("args.size() = " + args.size());
                if (args.size() == 1) {
                    if (args.get(0).equals("test")) {
                        System.out.println("Hello");
                    }
                }
            }
        }, new JlineCompleter() {
            @Override
            public List<String> complete(List<String> args) {
                return Collections.singletonList("test");
            }
        });
    }
}
