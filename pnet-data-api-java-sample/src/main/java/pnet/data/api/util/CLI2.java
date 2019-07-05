package pnet.data.api.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.porscheinformatik.happyrest.GenericType;

/**
 * A command line interpreter - parses the input, creates process and executes them.
 *
 * @author Manfred Hantschel
 */
public class CLI2
{

    private static final Collator DICTIONARY_COLLATOR;

    static
    {
        DICTIONARY_COLLATOR = Collator.getInstance();

        DICTIONARY_COLLATOR.setStrength(Collator.PRIMARY);
        DICTIONARY_COLLATOR.setDecomposition(Collator.CANONICAL_DECOMPOSITION);
    }

    /**
     * A command.
     */
    @Documented
    @Retention(RUNTIME)
    @Target(METHOD)
    public @interface Command
    {
        String command() default "";

        String[] pattern() default {};

        String description() default "";
    }

    /**
     * A parameter.
     */
    @Documented
    @Retention(RUNTIME)
    @Target(METHOD)
    public @interface Argument
    {
        String command() default "";

        String[] pattern() default {};

        String description() default "";
    }

    public interface Arguments
    {

        <Any> Any get(String key);

    }

    public abstract static class AbstractHandler
    {
    }

    public static class CommandHandler extends AbstractHandler
    {
        private final String command;
        private final String description;
        private final Consumer<Arguments> consumer;
        private final Pattern[] patterns;
        private final List<ArgumentHandler> argumentHandlers = new ArrayList<>();

        public CommandHandler(String command, String description, Consumer<Arguments> consumer, String... patterns)
        {
            super();

            this.command = command;
            this.description = description;
            this.consumer = consumer;
            this.patterns = patterns == null || patterns.length == 0 ? new Pattern[]{Pattern.compile(command)}
                : Arrays.stream(patterns).map(Pattern::compile).toArray(count -> new Pattern[count]);
        }

        public <T> CommandHandler argument(String key, Class<?> type, String description, String... patterns)
        {
            return register(new ArgumentHandler<>(-1, key, GenericType.of(type), description, patterns));
        }

        public <T> CommandHandler argument(String key, GenericType<T> type, String description, String... patterns)
        {
            return register(new ArgumentHandler<>(-1, key, type, description, patterns));
        }

        public CommandHandler argumentOfGroup(int groupIndex, String key, GenericType<?> type, String description,
            String... patterns)
        {
            return register(new ArgumentHandler<>(groupIndex, key, type, description, patterns));
        }

        protected CommandHandler register(ArgumentHandler<?> argumentHandler)
        {
            argumentHandlers.add(argumentHandler);

            return this;
        }

        public boolean consume(String s)
        {
            Map<String, Object> arguments = new HashMap<>();
            Map<Integer, StringBuilder> commandGroups = new HashMap<>();

            for (Pattern pattern : patterns)
            {
                Matcher matcher = pattern.matcher(s);

                if (!matcher.find())
                {
                    continue;
                }

                if (matcher.start() > 0)
                {
                    continue;
                }

                int end = matcher.end();

                commandGroups.put(-1, new StringBuilder(s.substring(end)));

                for (ArgumentHandler<?> argumentHandler : argumentHandlers)
                {
                    int groupIndex = argumentHandler.groupIndex;
                    StringBuilder commandGroup = commandGroups.get(groupIndex);

                    if (commandGroup == null)
                    {
                        commandGroup = new StringBuilder(matcher.group(groupIndex));
                        commandGroups.put(groupIndex, commandGroup);
                    }

                    Object argument = argumentHandler.match(commandGroup);

                    if (argument != null)
                    {
                        arguments.put(argumentHandler.key, argument);
                    }
                }

                consumer.accept(new Arguments()
                {
                    @SuppressWarnings("unchecked")
                    @Override
                    public <Any> Any get(String key)
                    {
                        return (Any) arguments.get(key);
                    }
                });

                return true;
            }

            return false;
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder(command).append(" ");

            while (builder.length() < 39)
            {
                builder.append(".");
            }

            builder.append(" ").append(description);

            for (ArgumentHandler<?> argumentHandler : argumentHandlers)
            {
                builder.append("\n").append(argumentHandler);
            }

            return builder.toString();
        }
    }

    // "^find companies"
    // "by id ([0-9\s,]*)"
    // "^find persons"
    // "with company mc ([\w\d\s,]*)"
    // "^help

    public static class ArgumentHandler<T> extends AbstractHandler
    {
        private final int groupIndex;
        private final String key;
        private final GenericType<T> type;
        private final String description;
        private final Pattern[] patterns;

        public ArgumentHandler(int groupIndex, String key, GenericType<T> type, String description, String... patterns)
        {
            super();

            this.groupIndex = groupIndex;
            this.key = key;
            this.type = type;
            this.description = description;
            this.patterns = patterns == null || patterns.length == 0 ? new Pattern[]{Pattern.compile(".*")}
                : Arrays.stream(patterns).map(Pattern::compile).toArray(count -> new Pattern[count]);
        }

        public T match(StringBuilder builder)
        {
            for (Pattern pattern : patterns)
            {
                Matcher matcher = pattern.matcher(builder.toString());

                if (!matcher.find())
                {
                    continue;
                }

                int start = matcher.start();
                int end = matcher.end();
                String match = matcher.group();

                builder.replace(start, end, "");

                return convertTo(type, match);
            }

            return null;
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder("  ").append(key).append(" ");

            while (builder.length() < 41)
            {
                builder.append(".");
            }

            builder.append(" ").append(description);

            return builder.toString();
        }
    }

    private final List<CommandHandler> commandHandlers = new ArrayList<>();

    public CommandHandler register(String command, String description, Consumer<Arguments> consumer, String... patterns)
    {
        CommandHandler handler = new CommandHandler(command, description, consumer, patterns);

        commandHandlers.add(handler);

        return handler;
    }

    public void consume(String s)
    {
        for (CommandHandler commandHandler : commandHandlers)
        {
            if (commandHandler.consume(s))
            {
                return;
            }
        }
    }

    //    @CLI2.Command(name = {"help", "?"}, format = "[q]", description = "Prints this help.")
    //    public void help(String... qs)
    //    {
    //        String help = formulary.getHelp("", qs);
    //
    //        if (help.length() == 0)
    //        {
    //            info("No help found for: %s", Arrays.stream(qs).collect(Collectors.joining(" ")));
    //        }
    //        else
    //        {
    //            info(help);
    //        }
    //    }
    //
    //    @CLI2.Command(description = "Exit this program.")
    //    public void exit()
    //    {
    //        info("Good bye.");
    //        System.exit(0);
    //    }

    //    protected void writeOut(String message, Object... args)
    //    {
    //        if (args == null || args.length == 0)
    //        {
    //            out.print(message);
    //        }
    //        else
    //        {
    //            out.printf(message, args);
    //        }
    //    }
    //
    //    protected void writeOut(Throwable ex)
    //    {
    //        if (ex != null)
    //        {
    //            out.printf("\n");
    //            ex.printStackTrace(out);
    //        }
    //    }
    //
    //    protected void writeErr(Object message, Object... args)
    //    {
    //        err.printf(String.valueOf(message), args);
    //    }
    //
    //    protected void writeErr(Throwable ex)
    //    {
    //        if (ex != null)
    //        {
    //            err.printf("\n");
    //            ex.printStackTrace(err);
    //        }
    //    }
    //
    //    public void info(Object message, Object... args)
    //    {
    //        info(message, (Throwable) null, args);
    //    }
    //
    //    public void info(Object message, Throwable ex, Object... args)
    //    {
    //        writeOut("\n" + message, args);
    //        writeOut(ex);
    //    }
    //
    //    public void warn(Object message, Object... args)
    //    {
    //        warn(message, (Throwable) null, args);
    //    }
    //
    //    public void warn(Object message, Throwable ex, Object... args)
    //    {
    //        writeErr("\n" + message, args);
    //        writeErr(ex);
    //    }
    //
    //    public void error(Object message, Object... args)
    //    {
    //        error(message, (Throwable) null, args);
    //    }
    //
    //    public void error(Object message, Throwable ex, Object... args)
    //    {
    //        writeErr("\n" + message, args);
    //        writeErr(ex);
    //    }

    private static String simplify(String s)
    {
        return s.replaceAll("[^\\p{IsLatin}^\\d]", "").toLowerCase();
    }

    private static boolean containsEach(String s, String... qs)
    {
        if (qs == null || qs.length == 0)
        {
            return true;
        }

        s = s.toLowerCase();

        for (String q : qs)
        {
            if (!s.contains(q.toLowerCase()))
            {
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    protected static <Any, T extends Enum<T>> Any convertTo(GenericType<Any> type, String value)
    {
        if (value == null)
        {
            return null;
        }

        if (String.class.isAssignableFrom(type.getType()))
        {
            return (Any) value;
        }

        if (Boolean.class.isAssignableFrom(type.getType()))
        {
            return (Any) Boolean.valueOf(Boolean.parseBoolean(value));
        }

        if (Byte.class.isAssignableFrom(type.getType()))
        {
            return (Any) Byte.decode(value);
        }

        if (Short.class.isAssignableFrom(type.getType()))
        {
            return (Any) Short.decode(value);
        }

        if (Integer.class.isAssignableFrom(type.getType()))
        {
            return (Any) Integer.decode(value);
        }

        if (Long.class.isAssignableFrom(type.getType()))
        {
            return (Any) Long.decode(value);
        }

        if (Float.class.isAssignableFrom(type.getType()))
        {
            return (Any) Float.valueOf(Float.parseFloat(value));
        }

        if (Double.class.isAssignableFrom(type.getType()))
        {
            return (Any) Double.valueOf(Double.parseDouble(value));
        }

        if (Character.class.isAssignableFrom(type.getType()))
        {
            if (value.length() > 1)
            {
                throw new IllegalArgumentException("Character expected");
            }

            return (Any) Character.valueOf(value.charAt(0));
        }

        if (Enum.class.isAssignableFrom(type.getType()))
        {
            return (Any) Enum.valueOf((Class<T>) type.getType(), value);
        }

        if (Collection.class.isAssignableFrom(type.getType()))
        {
            GenericType<Object> subType = type.getArgument(0);
            String[] subValues = value.split("\\s*[,\\s]+\\s*");
            List<Object> list = new ArrayList<>();

            for (String subValue : subValues)
            {
                list.add(convertTo(subType, subValue));
            }

            if (Set.class.isAssignableFrom(type.getType()))
            {
                return (Any) new HashSet<>(list);
            }

            return (Any) list;
        }

        throw new UnsupportedOperationException("Type not supported: " + type);
    }

}
