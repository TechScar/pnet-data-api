package pnet.data.api.util;

import org.junit.Test;

public class CLITest
{

    @Test
    public void test()
    {
        StringBuilder result = new StringBuilder();
        CLI2 cli = new CLI2();

        cli.register("test", "This is a test", args -> result.append("OK"));
        cli
            .register("foo (.*)", "This is a test", args -> result.append((String) args.get("#1")))
            .argument("#1", String.class, "the type", ".*");

        cli.consume("test");

        System.out.println(result);
        
        cli.consume("foo asdlfkjasdölkfjasdlökf");

        System.out.println(result);

    }
}
