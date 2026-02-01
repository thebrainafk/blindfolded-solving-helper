package view;

import view.commands.CommandHandler;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * asdf.
 *
 * @author uckhu
 */
public class UI {
    private static final String ERROR_PREFIX = "ERROR: %s%n";
    public static final String ARGUMENT_SEPARATOR = " ";

    /**
     * asdf.
     */
    private static final Pattern COMMAND_PATTERN = Pattern.compile("([A-Za-z-]+)(( \\S+){1,3})?");

    private final Scanner scanner;

    private boolean isQuit;

    /**
     * asdf.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
        this.isQuit = false;
    }

    /**
     * asdf.
     */
    public void quit() {
        this.isQuit = true;
    }

    /**
     * asdf.
     */
    public void start() {
        CommandHandler commandHandler = new CommandHandler(this);

        while (!isQuit) {
            ParsedInput input = this.readInput();
            Result result = commandHandler.runInstance(input);
            processResult(result);
        }
    }

    private ParsedInput readInput() {
        String command;
        String[] arguments = null;

        String input = scanner.nextLine();

        Matcher matcher = COMMAND_PATTERN.matcher(input);

        if (matcher.matches()) {
            command = matcher.group(1);
            if (matcher.group(2) != null) {
                arguments = matcher.group(2).trim().split(ARGUMENT_SEPARATOR);
            }
        } else {
            return null;
        }
        return new ParsedInput(command, arguments);
    }

    private static void processResult(Result result) {
        if (!result.success()) {
            printErrorMessage(result.message());
        }
        if (result.success()) {
            printSuccessMessage(result.message());
        }
    }

    private static void printSuccessMessage(String message) {
        System.out.print(message);
        if (!message.isEmpty()) {
            System.out.println();
        }
    }

    private static void printErrorMessage(String message) {
        System.out.printf(ERROR_PREFIX, message);
    }
}