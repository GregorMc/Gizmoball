package view;

import javax.swing.*;

public interface IGBallGUI {

    JPanel createButtons();

    JMenuBar createMenuBar();

    JPanel createMsgField();

    void createBoard();

    void showError();

    void showInfo();

    void getInput();
}
