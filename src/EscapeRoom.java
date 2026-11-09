
/*
Name: Lincoln Westover
Class: AP CS A
Date: 11/8/2026
 */

/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/*
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom {
  /*
   * TO-DO: Process game commands from user input:
   * right, left, up, down: move player size of move, m, if player try to go off
   * grid or bump into wall, score decreases
   * jump over 1 space: player cannot jump over walls
   * pick up prize: score increases, if there is no prize, penalty
   * help: display all possible commands
   * end: reach the far right wall, score increase, game ends, if game ends
   * without reaching far right wall, penalty
   * replay: shows number of player steps and resets the board, player or another
   * player can play the same board
   * 
   * if player land on a trap, spring a trap to increase score: the program must
   * first check if there is a trap, if none exists, penalty
   * Note that you must adjust the score with any method that returns a score
   * Optional: create a custom image for player - use the file player.png on disk
   */

  public static void main(String[] args) {
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");

    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60;
    // individual player moves
    int px = 0;
    int py = 0;

    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
        "jumpright", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
        "pickup", "p", "quit", "q", "replay", "help", "?", "score" };

    // set up game
    boolean play = true;
    while (play) {

      // get user command and validate
      System.out.print("Enter command (type help for a list of commands):");
      String input = UserInput.getLine().toLowerCase();

      boolean isValid = false;
      for (String cmd : validCommands) {
        if (input.equals(cmd)) {
          isValid = true;
          break;
        }
      }

      if (!isValid) {
        System.out.println("Invalid input. Please try again");
        score -= 5;
        continue;

      /* process user commands */

      // i really want to use a switch here
      if (input.equals("right") || input.equals("r")) {
        if (game.isTrap(m, 0)) {
          score += game.springTrap(m, 0);
        }
        score += game.movePlayer(m, 0);
      }
      else if (input.equals("left") || input.equals("l")) {
        if (game.isTrap(-m, 0)) {
          score += game.springTrap(-m, 0);
        }
        score += game.movePlayer(-m, 0);
      }
      else if (input.equals("up") || input.equals("u")) {
        if (game.isTrap(0, -m)) {
          score += game.springTrap(0, -m);
        }
        score += game.movePlayer(0, -m);
      }
      else if (input.equals("down") || input.equals("d")) {
        if (game.isTrap(0, m)) {
          score += game.springTrap(0, m);
        }
        score += game.movePlayer(0, m);
      }
      else if (input.equals("jumpright") || input.equals("jr")) {
        if (game.isTrap(2 * m, 0)) {
          score += game.springTrap(2 * m, 0);
        }
        score += game.movePlayer(2 * m, 0);
      }
      else if (input.equals("jumpleft") || input.equals("jl")) {
        if (game.isTrap(-2 * m, 0)) {
          score += game.springTrap(-2 * m, 0);
        }
        score += game.movePlayer(-2 * m, 0);
      }
      else if (input.equals("jumpup") || input.equals("ju")) {
        if (game.isTrap(0, -2 * m)) {
          score += game.springTrap(0, -2 * m);
        }
        score += game.movePlayer(0, -2 * m);
      }
      else if (input.equals("jumpdown") || input.equals("jd")) {
        if (game.isTrap(0, 2 * m)) {
          score += game.springTrap(0, 2 * m);
        }
        score += game.movePlayer(0, 2 * m);
      }
      else if (input.equals("score")) {
        System.out.println("\nyour current score: " + score + "\n");
      }
      else if (input.equals("pickup") || input.equals("p")) {
        score += game.pickupPrize();
      }
      else if (input.equals("replay")) {
        score += game.replay();
      }
      else if (input.equals("help") || input.equals("?")) {
        System.out.println("Valid commands: right/r, left/l, up/u, down/d, jumpright/jr, jumpleft/jl, jumpup/ju, jumpdown/jd, pickup/p, replay, help/?, quit/q");
      }
      else if (input.equals("quit") || input.equals("q")) {
        play = false;
      }

    }

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}
