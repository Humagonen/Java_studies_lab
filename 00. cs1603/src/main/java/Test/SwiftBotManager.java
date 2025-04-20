package Test;
import swiftbot.SwiftBotAPI;

public class SwiftBotManager {
    private static SwiftBotAPI swiftBot = new SwiftBotAPI();

    public static SwiftBotAPI getSwiftBot() {
        return swiftBot;
    }
}

