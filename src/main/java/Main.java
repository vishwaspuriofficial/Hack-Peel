import UserInterface.MainFrame;

public class Main {
    private static MainFrame gui;
    public static void main(String[] args) {
        gui = new MainFrame();
    }

    public static MainFrame getGui() {
        return gui;
    }
}
