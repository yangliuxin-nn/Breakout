package Presentation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
    private int direction = 2;//1:move left; 0:move right, initialize it to 2, in case its default value is 0
    private boolean readyLaser;
    private boolean offSticky;
    private boolean newGame;
    private boolean high;
    private boolean exit;
    private boolean menu;
    private boolean pause;
    private boolean about;

    public void keyTyped(KeyEvent e) {
        if (e.getExtendedKeyCode() == KeyEvent.VK_H || e.getKeyChar() == 'H'  || e.getKeyChar() == 'h') {
            high = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_X || e.getKeyChar() == 'X'  || e.getKeyChar() == 'x') {
            exit = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_M || e.getKeyChar() == 'M'  || e.getKeyChar() == 'm') {
            menu = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_N || e.getKeyChar() == 'N'  || e.getKeyChar() == 'n') {
            newGame = true;
        } else if (e.getExtendedKeyCode() == KeyEvent.VK_P || e.getKeyChar() == 'P'  || e.getKeyChar() == 'p') {
            pause = true;
        }else if (e.getExtendedKeyCode() == KeyEvent.VK_A || e.getKeyChar() == 'A'  || e.getKeyChar() == 'a') {
            about = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT||e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 2;
        } else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyChar() == 'D'  || e.getKeyChar() == 'D'){
            offSticky = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            direction = 1;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            direction = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_S || e.getKeyChar() == 'S'  || e.getKeyChar() == 's'){
            readyLaser = true;
        } else if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyChar() == 'D'  || e.getKeyChar() == 'D'){
            offSticky = true;
        }
    }

    public int getDirection(){
        return direction;
    }
    public void setReadyLaser(Boolean b){
        readyLaser = b;
    }
    public void reset() {
        high = false;
        exit = false;
        menu = false;
        newGame = false;
        direction = 2;
    }
    public boolean isReadyLaser() {
        return readyLaser;
    }
    public boolean isOffSticky() {
        return offSticky;
    }
    public boolean isHigh() {
        if (high) {
            high = false;
            return true;
        }
        return false;
    }
    public boolean isExit() {
        if (exit) {
            exit = false;
            return true;
        }
        return false;
    }
    public boolean isAbout() {
        if (about) {
            about = false;
            return true;
        }
        return false;
    }
    public boolean isMenu() {
        if (menu) {
            menu = false;
            return true;
        }
        return false;
    }
    public boolean isNew() {
        if (newGame) {
            newGame = false;
            return true;
        }
        return false;
    }
    public boolean isPlayPause() {
        if (pause) {
            pause = false;
            return true;
        }
        return false;
    }
}
