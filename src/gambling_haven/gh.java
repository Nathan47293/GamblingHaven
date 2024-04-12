package gambling_haven;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Version 1.3
/* Auto-Bet
 * Blackjack (define a number, if x > num hit, define number of rounds, bet)
 * poker (define a minimum value, if combined value of hand >= num, bet, count hands that are too low as a round, define number of rounds, bet)
 * dice roll (define type of bet, number of bets, bet)
 * slot machine (define number of spins, bet)
 * war (define number of bets, bet)
 * roulette (define type of bet, number of bets, bet)
 * high-low (define a number, if x < num pick higher, if x > num pick lower, define number of bets, bet)
 * 
 * Other features
 * When repeating games, change input to enter instead of typing a number
 * Sound Queue when winning
 */

public class gh {

	static ArrayList<Integer> balances = new ArrayList<Integer>();
	static LinkedHashMap<String, String> acc_hm = new LinkedHashMap<String, String>();

	// Universal Variables
	static int game_option = 0;
	static int money = 100;
	static int bet = 0;
	static boolean switch_game = true;
	static boolean rerun = true;
	static int user_indx = -1;
	static int reward_wheel_spins = 3;

	public static void main(String[] args) {
		try {
			// Creation of usernames.txt, passwords.txt
			File u = new File("usernames.txt");
			File p = new File("passwords.txt");
			File b = new File("balances.txt");
			u.createNewFile();
			p.createNewFile();
			b.createNewFile();

			// Assignment of usernames.txt, passwords.txt to accounts hashmap
			Scanner sc = new Scanner(u);
			Scanner sc2 = new Scanner(p);
			while (sc.hasNext() & sc2.hasNext()) {
				acc_hm.put(sc.nextLine(), sc2.nextLine());

			}

			Scanner sc3 = new Scanner(b);
			while(sc3.hasNext()) {
				balances.add(Integer.valueOf(sc3.nextLine()));
			}
			sc.close();
			sc2.close();
			sc3.close();

			// User chooses to register or login
			System.out.println("Register or login? (r/l)");
			Scanner scc = new Scanner(System.in);
			String u_i = scc.nextLine();
			boolean start = true;
			boolean match = false;
			String username = null;
			// Register
			if (u_i.contains("r")) {
				user_indx = balances.size();
				// Checks if username is already in accounts hashmap
				while (start == true || match == true) {
					match = false;
					System.out.print("Enter username: ");
					username = scc.nextLine();
					System.out.println("");

					for (String us : acc_hm.keySet()) {
						if (us.equals(username)) {
							match = true;
							System.out.println("Username already exists");
							break;
						}
					}
					start = false;
				}

				System.out.print("Enter password: ");
				String password = scc.nextLine();
				System.out.println("");

				// Adds username and password to accounts hashmap
				acc_hm.put(username, password);
				balances.add(money);

				// Rewrites usernames.txt with updated accounts hashmap keysets
				FileWriter wr = new FileWriter("usernames.txt");
				for (String us : acc_hm.keySet()) {
					wr.write(us + System.getProperty("line.separator"));
				}
				wr.close();

				// Rewrites passwords.txt with updated accounts hashmap values
				wr = new FileWriter("passwords.txt");
				for (String pa : acc_hm.values()) {
					wr.write(pa + System.getProperty("line.separator"));
				}
				wr.close();

				// Adds default balance
				wr = new FileWriter("balances.txt"); 
				for (int ba : balances) {
					wr.write(ba + System.getProperty("line.separator"));
				}
				wr.close();
			}

			// Login
			if (u_i.contains("l")) {
				while (match == false) {
					System.out.print("Enter username: ");
					username = scc.nextLine();
					System.out.println("");

					int i = 0;
					// Checks if username is in accounts hashmap keysets
					for (String us : acc_hm.keySet()) {

						if (us.equals(username)) {
							match = true;
							if(balances.get(i)!= null) {
								money = balances.get(i);
								user_indx=i;
							}

							break;
						}
						i++;
					}

					if (match == false) {
						System.out.println("username doesn't exist");
					}
				}
				match = false;

				while (match == false) {
					System.out.print("Enter password: ");
					String password = scc.nextLine();
					System.out.println("");

					// Checks if password matches keyset value in accounts hashmap
					if (acc_hm.get(username).equals(password)) {
						match = true;
					} else {
						System.out.println("password doesn't match");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		hub();
	}

	public static void hub() {
		while(rerun == true && switch_game == true) {
			System.out.println("Welcome to the Gambling Haven");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Type the number corresponding to the game you would like to play:");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("1 - Blackjack");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("2 - Poker");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("3 - Dice Roll");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("4 - Slot Machine");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("5 - War");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("6 - Roulette");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("7 - High-Low");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("8 - Coin Flip");
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("9 - Reward Wheel (" + reward_wheel_spins + " free spins remaining)");

			Scanner game_option_scanner = new Scanner(System.in);
			game_option = Integer.valueOf(game_option_scanner.nextLine());

			while(game_option < 1 && game_option > 9) {
				System.out.println("That is not a valid option");
				game_option = Integer.valueOf(game_option_scanner.nextLine());
			}

			while(game_option == 9) {
				if(reward_wheel_spins == 0) {
					System.out.println("You don't have anymore free spins");
					game_option = Integer.valueOf(game_option_scanner.nextLine());
				} else {
					break;
				}
			}

			switch_game = false;
			hub();
		}

		while (rerun == true && switch_game == false) {
			System.out.println("You have $" + money);

			bet = 0;

			switch(game_option) {
			case 1:
				blackjack(); // basically perfectly fair
			case 2:
				poker(); // EV is 99.67% of original bet, however user can chose to fold upon receiving a bad hand
			case 3:
				dice_roll(); // fair
			case 4:
				slot_machine(); // basically fair
				// 54 * (2/18)^3 + 24 * 2 * (2/18)^2 + 1 * 3 * 2/18 = 1
			case 5:
				war(); // perfectly fair
			case 6:
				roulette(); // fair
			case 7:
				high_low(); // fair
			case 8:
				coin_flip(); // Perfectly fair
			case 9:
				reward_wheel(); // free money baby
			}
		}
	}

	public static void blackjack() {
		boolean natural_bj = false;
		boolean bj_double_down = false;
		String bj_dd_confirmation = "";
		int bj_d_aces = 0;
		int bj_u_aces = 0;
		int bj_u_aces_used = 0;
		int bj_d_aces_used = 0;
		String bj_user_input = "";
		int bj_d_indx1 = 0;
		int bj_d_indx2 = 0;
		String bj_dealer_card = "";
		String bj_hole_card = "";
		int bj_dealer_value = 0;
		int bj_dealer_total = 0;
		int bj_u_indx1 = 0;
		int bj_u_indx2 = 0;
		String bj_user_card1 = "";
		String bj_user_card2 = "";
		int bj_user_value = 0;
		int bj_user_total = 0;

		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11 };

		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[][] deck = new String[ranks.length][suits.length]; // [rows][columns]
		for (int x = 0; x < ranks.length; x++) {
			for (int y = 0; y < suits.length; y++) {
				deck[x][y] = ranks[x] + " of " + suits[y];
			}
		}
		Scanner sc = new Scanner(System.in);


		while (true) {
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("how much are you betting? ");
			bet = Integer.valueOf(sc.nextLine());


			if (money - bet >= 0) {
				break;
			}
			System.out.println("You don't have enough money");
		}

		// Player draws first card
		bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[bj_u_indx1][bj_u_indx2] == null) {
			bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		bj_user_card1 = deck[bj_u_indx1][bj_u_indx2];
		deck[bj_u_indx1][bj_u_indx2] = null;
		bj_user_value = values[bj_u_indx1];
		bj_user_total += bj_user_value;

		if (bj_user_card1.contains("Ace")) {
			bj_u_aces += 1;
		}

		// dealer draws first card face up
		bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[bj_d_indx1][bj_d_indx2] == null) {
			bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		bj_dealer_card = deck[bj_d_indx1][bj_d_indx2];
		deck[bj_d_indx1][bj_d_indx2] = null;

		if (bj_dealer_card.contains("Ace")) {
			bj_d_aces += 1;
		}

		bj_dealer_value = values[bj_d_indx1];
		bj_dealer_total += bj_dealer_value;


		// Player draws second card
		bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[bj_u_indx1][bj_u_indx2] == null) {
			bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		bj_user_card2 = deck[bj_u_indx1][bj_u_indx2];
		deck[bj_u_indx1][bj_u_indx2] = null;
		bj_user_value = values[bj_u_indx1];
		bj_user_total += bj_user_value;

		if (bj_user_card2.contains("Ace")) {
			bj_u_aces += 1;
		}
		System.out.print("You have been dealt: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(bj_user_card1 + ", ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(bj_user_card2);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Total Value: " + bj_user_total);

		if (bj_user_total == 21) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Congratulations! You have dealt a natural blackjack!");
			natural_bj = true;
		}

		// dealer draws second card face down
		bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[bj_d_indx1][bj_d_indx2] == null) {
			bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		bj_dealer_card = deck[bj_d_indx1][bj_d_indx2];
		deck[bj_d_indx1][bj_d_indx2] = null;

		if (bj_dealer_card.contains("Ace")) {
			bj_d_aces += 1;
		}

		bj_dealer_value = values[bj_d_indx1];
		bj_dealer_total += bj_dealer_value;

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print("The dealer has drawn: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(bj_dealer_card + ", ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Hole Card");
		bj_hole_card = bj_dealer_card;

		if (money - (bet * 2) >= 0 && natural_bj == false) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("double down? (y/n)");
			bj_dd_confirmation = sc.nextLine();
		}

		// player doubles down
		if (bj_dd_confirmation.contains("y")) {
			bj_double_down = true;
			bet = bet * 2;

			bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[bj_u_indx1][bj_u_indx2] == null) {
				bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			bj_user_card1 = deck[bj_u_indx1][bj_u_indx2];
			deck[bj_u_indx1][bj_u_indx2] = null;
			bj_user_value = values[bj_u_indx1];
			bj_user_total += bj_user_value;

			if (bj_user_card1.contains("Ace")) {
				bj_u_aces += 1;
			}

			if (bj_user_total > 21) {
				if (bj_u_aces - bj_u_aces_used == 1) {
					bj_user_total -= 10;
					bj_u_aces_used += 1;
				}
				if (bj_u_aces - bj_u_aces_used == 2) {
					if (bj_user_total - 10 < 22) {
						bj_user_total -= 10;
						bj_u_aces_used += 1;
					}
					if (bj_user_total - 20 < 22 && bj_user_total - 10 > 21) {
						bj_user_total -= 20;
						bj_u_aces_used += 2;
					}
				}
			}
			System.out.print("You have been dealt: ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(bj_user_card1);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Total Value: " + bj_user_total);
		}

		if (bj_double_down == false && natural_bj == false) {
			System.out.println("Hit or stand (h/s)?");
			bj_user_input = sc.nextLine();
		}

		while (bj_user_input.contains("h") && bj_double_down == false && natural_bj == false && bj_user_total < 21) {
			// Player Draws
			bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[bj_u_indx1][bj_u_indx2] == null) {
				bj_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				bj_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			bj_user_card1 = deck[bj_u_indx1][bj_u_indx2];
			deck[bj_u_indx1][bj_u_indx2] = null;
			bj_user_value = values[bj_u_indx1];
			bj_user_total += bj_user_value;

			if (bj_user_card1.contains("Ace")) {
				bj_u_aces += 1;
			}

			// Using any aces in hand
			if (bj_user_total > 21) {
				if (bj_u_aces - bj_u_aces_used == 1) {
					bj_user_total -= 10;
					bj_u_aces_used += 1;
				}
				if (bj_u_aces - bj_u_aces_used == 2) {
					if (bj_user_total - 10 < 22) {
						bj_user_total -= 10;
						bj_u_aces_used += 1;
					}
					if (bj_user_total - 20 < 22 && bj_user_total - 10 > 21) {
						bj_user_total -= 20;
						bj_u_aces_used += 2;
					}
				}
			}

			System.out.print("You have been dealt: ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(bj_user_card1);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Total Value: " + bj_user_total);

			if (bj_user_total > 21) {
				break;
			}

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Hit or stand (h/s)?");
			bj_user_input = sc.nextLine();
		}

		System.out.print("The dealer's Hole Card is: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(bj_hole_card);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Total Value: " + bj_dealer_total);

		// Once the player stands, the dealer reveals the hole and draws if his total is below 17
		while (bj_dealer_total < 17 && bj_user_total < 22) {
			bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[bj_d_indx1][bj_d_indx2] == null) {
				bj_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				bj_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			bj_dealer_card = deck[bj_d_indx1][bj_d_indx2];
			deck[bj_d_indx1][bj_d_indx2] = null;

			if (bj_dealer_card.contains("Ace")) {
				bj_d_aces += 1;
			}

			bj_dealer_value = values[bj_d_indx1];
			bj_dealer_total += bj_dealer_value;

			if (bj_dealer_total > 21) {
				if (bj_d_aces - bj_d_aces_used == 1) {
					bj_dealer_total -= 10;
					bj_d_aces_used += 1;
				}
				if (bj_d_aces - bj_d_aces_used == 2) {
					if (bj_dealer_total - 10 < 22) {
						bj_dealer_total -= 10;
						bj_d_aces_used += 1;
					}
					if (bj_dealer_total - 20 < 22 && bj_dealer_total - 10 > 21) {
						bj_user_total -= 20;
						bj_d_aces_used += 2;
					}
				}
			}
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The dealer has been dealt: ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(bj_dealer_card);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Total Value: " + bj_dealer_total);

			if (bj_dealer_total > 21) {
				break;
			}
		}

		// End Game, displaying totals and outcome
		if (bj_dealer_total > 21) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("The dealer has busted! You Win");
			if (natural_bj == true) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("For recieving a natural blackjack, you get a 1.5x payout");
				money += bet * 1.5;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 1x payout");
				money += bet;

			}
		}
		if (bj_user_total > 21) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You busted! You Lose the round and your bet");
			money -= bet;
		}
		if (bj_dealer_total < 22 && bj_user_total < 22) {
			if (bj_user_total > bj_dealer_total) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				if (natural_bj == true) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("For recieving a natural blackjack, you get a 1.5x payout");
					money += bet * 1.5;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You recieve a 1x payout");
					money += bet;
				}
			}
			if (bj_user_total < bj_dealer_total) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the round and your bet");
				money -= bet;
			}
			if (bj_user_total == bj_dealer_total) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Tie! You get your bet back");
			}
		}
		balances.set(user_indx, money);
		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(game_option_scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}

	}

	public static void poker() {
		int answer = -1;
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[][] deck = new String[ranks.length][suits.length]; // [rows][columns]

		for (int x = 0; x < ranks.length; x++) {
			for (int y = 0; y < suits.length; y++) {
				deck[x][y] = ranks[x] + " of " + suits[y];
			}
		}
		double user_hand_strength = 0; // The calculation for this doesn't allign with standard rules
		// Instead, I have formulated my own arbitrary hand strength rating system
		double dealer_hand_strength = 0;

		int poker_u_indx1 = 0;
		int poker_u_indx2 = 0;
		String poker_user_card1 = "";
		String poker_user_card2 = "";
		int poker_user_value1 = 0;
		int poker_user_value2 = 0;
		int poker_user_suit1 = 0;
		int poker_user_suit2 = 0;

		int poker_d_indx1 = 0;
		int poker_d_indx2 = 0;
		String poker_dealer_card1 = "";
		String poker_dealer_card2 = "";
		int poker_dealer_value1 = 0;
		int poker_dealer_value2 = 0;
		int poker_dealer_suit1 = 0;
		int poker_dealer_suit2 = 0;

		Scanner sc = new Scanner(System.in);

		// Player draws their hand
		poker_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		poker_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[poker_u_indx1][poker_u_indx2] == null) {
			poker_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		poker_user_card1 = deck[poker_u_indx1][poker_u_indx2];
		deck[poker_u_indx1][poker_u_indx2] = null;
		poker_user_value1 = values[poker_u_indx1];
		poker_user_suit1 = poker_u_indx2;

		poker_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		poker_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[poker_u_indx1][poker_u_indx2] == null) {
			poker_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		poker_user_card2 = deck[poker_u_indx1][poker_u_indx2];
		deck[poker_u_indx1][poker_u_indx2] = null;
		poker_user_value2 = values[poker_u_indx1];
		poker_user_suit2 = poker_u_indx2;

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print("Your hand is ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(poker_user_card1 + ", ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(poker_user_card2);

		while(true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Do you bet (0) or fold (1)?");
			answer = Integer.valueOf(sc.nextLine());
			if(answer == 0 || answer == 1) {
				break;
			}
			System.out.println("That's not a viable option");
		}

		if(answer == 1) {
			Scanner game_option_scanner = new Scanner(System.in);
			System.out.println("Do you want to play again? (0)?");

			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Do you want to switch game? (1)?");

			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Do you want to quit the Gambling Haven? (2)?");

			int input = Integer.valueOf(game_option_scanner.nextLine());
			if(input == 0) {
				switch_game = false;
				rerun = true;
				hub();
			}

			if(input == 1) {
				switch_game = true;
				rerun = true;
				hub();
			}

			if (input == 2) {
				System.out.println("Your final balance: $" + money);
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Thanks for playing!");
				System.exit(0);
			}
		}


		if(answer == 0) {
			while (true) {
				System.out.println("how much are you betting?");
				bet = Integer.valueOf(sc.nextLine());


				if (money - bet >= 0) {
					break;
				}
				System.out.println("You don't have enough money");
			}

			/* https://www.pokerstrategy.com/strategy/various-poker/texas-holdem-probabilities/
			 * Royal Flush - 0.003232062%
			 * Straight Flush - 0.027850748%
			 * Four of a Kind - 0.168067227%
			 * Full House - 2.596102271%
			 * Flush - 3.025494123%
			 * Straight - 4.619382087%
			 * Three of a Kind - 4.829869755%
			 * Two Pair - 23.49553641%
			 * Pair - 43.82254574%
			 * High card - 17.41191958%
			 * EV contributions add to 0.9967
			 * So EV is 99.67% of initial bet, basically fair */

			/*System.out.println("Royal Flush: 100 times the initital bet");
			System.out.println("Straight Flush: 50 times the initial bet");
			System.out.println("Four of a Kind: 30 times the inital bet");
			System.out.println("Full House: 7 times the initial bet");
			System.out.println("Flush: 5 times the inital bet");
			System.out.println("Straight: 4 times the intial bet");
			System.out.println("Three of a Kind: 3 times the initial bet");
			System.out.println("Two Pair: 1 times the intial bet");
			System.out.println("One Pair: 0.3 times the inital bet");
			System.out.println("High Card: No payout");*/


			// dealer draws their hand
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			poker_dealer_card1 = deck[poker_d_indx1][poker_d_indx2];
			deck[poker_d_indx1][poker_d_indx2] = null;
			poker_dealer_value1 = values[poker_d_indx1];
			poker_dealer_suit1 = poker_d_indx2;

			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			poker_dealer_card2 = deck[poker_d_indx1][poker_d_indx2];
			deck[poker_d_indx1][poker_d_indx2] = null;
			poker_dealer_value2 = values[poker_d_indx1];
			poker_dealer_suit2 = poker_d_indx2;


			// First Table Card
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String tablecard1 = deck[poker_d_indx1][poker_d_indx2];

			deck[poker_d_indx1][poker_d_indx2] = null;
			int tablecard1_value = values[poker_d_indx1];
			int tablecard1_suit = poker_d_indx2;

			// Second Table Card
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String tablecard2 = deck[poker_d_indx1][poker_d_indx2];

			deck[poker_d_indx1][poker_d_indx2] = null;
			int tablecard2_value = values[poker_d_indx1];
			int tablecard2_suit = poker_d_indx2;

			// Third Table Card
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String tablecard3 = deck[poker_d_indx1][poker_d_indx2];

			deck[poker_d_indx1][poker_d_indx2] = null;
			int tablecard3_value = values[poker_d_indx1];
			int tablecard3_suit = poker_d_indx2;

			System.out.print("The Flop is ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(tablecard1 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(tablecard2 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(tablecard3);

			// Fourth Table Card
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String tablecard4 = deck[poker_d_indx1][poker_d_indx2];

			deck[poker_d_indx1][poker_d_indx2] = null;
			int tablecard4_value = values[poker_d_indx1];
			int tablecard4_suit = poker_d_indx2;

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The Turn is ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(tablecard4);

			// Fifth Table Card
			poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[poker_d_indx1][poker_d_indx2] == null) {
				poker_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				poker_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String tablecard5 = deck[poker_d_indx1][poker_d_indx2];

			deck[poker_d_indx1][poker_d_indx2] = null;
			int tablecard5_value = values[poker_d_indx1];
			int tablecard5_suit = poker_d_indx2;

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The River is ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(tablecard5);

			// Check User Hand Strength
			int[] user_card_values = {tablecard1_value, tablecard2_value, tablecard3_value, tablecard4_value, tablecard5_value, poker_user_value1, poker_user_value2};
			int[] user_card_suits = {tablecard1_suit, tablecard2_suit, tablecard3_suit, tablecard4_suit, tablecard5_suit, poker_user_suit1, poker_user_suit2};
			int[] dealer_card_values = {tablecard1_value, tablecard2_value, tablecard3_value, tablecard4_value, tablecard5_value, poker_dealer_value1, poker_dealer_value2};
			int[] dealer_card_suits = {tablecard1_suit, tablecard2_suit, tablecard3_suit, tablecard4_suit, tablecard5_suit, poker_dealer_suit1, poker_dealer_suit2};

			for (int i = 0; i < user_card_values.length - 1; i++) {
				for (int j = 0; j < user_card_values.length - i - 1; j++) {
					if (user_card_values[j] < user_card_values[j + 1]) { // Change comparison condition to >
						// Swap values in user_card_values
						int tempValue = user_card_values[j];
						user_card_values[j] = user_card_values[j + 1];
						user_card_values[j + 1] = tempValue;
						// Swap corresponding values in user_card_suits
						int tempSuit = user_card_suits[j];
						user_card_suits[j] = user_card_suits[j + 1];
						user_card_suits[j + 1] = tempSuit;
					}
				}
			}

			for (int i = 0; i < dealer_card_values.length - 1; i++) {
				for (int j = 0; j < dealer_card_values.length - i - 1; j++) {
					if (dealer_card_values[j] < dealer_card_values[j + 1]) { // Change comparison condition to <
						// Swap values in dealer_card_values
						int tempValue = dealer_card_values[j];
						dealer_card_values[j] = dealer_card_values[j + 1];
						dealer_card_values[j + 1] = tempValue;
						// Swap corresponding values in dealer_card_suits
						int tempSuit = dealer_card_suits[j];
						dealer_card_suits[j] = dealer_card_suits[j + 1];
						dealer_card_suits[j + 1] = tempSuit;
					}
				}
			}

			// High Card
			int user_high_card = poker_user_value1;
			int user_highcard_kicker = poker_user_value2;

			if(poker_user_value2 > poker_user_value1) {
				user_high_card = poker_user_value2;
				user_highcard_kicker = poker_user_value1;
			}

			int dealer_high_card = poker_dealer_value1;
			int dealer_highcard_kicker = poker_dealer_value2;

			if(poker_dealer_value2 > poker_dealer_value1) {
				dealer_high_card = poker_dealer_value2;
				dealer_highcard_kicker = poker_dealer_value1;
			}

			if(user_high_card > dealer_high_card) {
				user_hand_strength = 1;
			}

			if(user_high_card < dealer_high_card) {
				dealer_hand_strength = 1;
			}

			if(user_high_card == dealer_high_card) {
				if(user_highcard_kicker > dealer_highcard_kicker){
					user_hand_strength = 1;
				}
				if(user_highcard_kicker < dealer_highcard_kicker) {
					dealer_hand_strength = 1;
				}
			}

			// One Pair and two pair

			// Checking for user pairs
			List<Integer> user_duplicates = new ArrayList<>();

			for (int i = 0; i < user_card_values.length - 1; i++) {
				for (int j = i + 1; j < user_card_values.length; j++) {
					if (user_card_values[i] == user_card_values[j] && !user_duplicates.contains(user_card_values[i])) {
						user_duplicates.add(user_card_values[i]); // Add the value to duplicates list if it's not already present
						break; // Break to avoid adding the same value multiple times
					}
				}
			}
			int user_pair_amount = user_duplicates.size();

			// Checking for dealer pairs
			List<Integer> dealer_duplicates = new ArrayList<>();

			for (int i = 0; i < dealer_card_values.length - 1; i++) {
				for (int j = i + 1; j < dealer_card_values.length; j++) {
					if (dealer_card_values[i] == dealer_card_values[j] && !dealer_duplicates.contains(dealer_card_values[i])) {
						dealer_duplicates.add(dealer_card_values[i]); // Add the value to duplicates list if it's not already present
						break; // Break to avoid adding the same value multiple times
					}
				}
			}
			int dealer_pair_amount = dealer_duplicates.size();

			Collections.sort(user_duplicates);
			Collections.reverse(user_duplicates);
			Collections.sort(dealer_duplicates);
			Collections.reverse(dealer_duplicates);

			int user_first_pair_value = 0;
			int user_second_pair_value = 0;
			int dealer_first_pair_value = 0;
			int dealer_second_pair_value = 0;
			int user_pair_kicker = 0;
			int dealer_pair_kicker = 0;

			if(user_pair_amount == 1){
				user_hand_strength = 2;
				user_first_pair_value = user_duplicates.get(0);

				if(poker_user_value1 != user_first_pair_value) {
					if(poker_user_value2 == user_first_pair_value) {
						user_pair_kicker = poker_user_value1;
					}
				}

				if(poker_user_value2 != user_first_pair_value) {
					if(poker_user_value1 == user_first_pair_value) {
						user_pair_kicker = poker_user_value2;
					}
				}

				if(poker_user_value1 != user_first_pair_value && poker_user_value2 != user_first_pair_value) {
					if(poker_user_value1 > poker_user_value2) {
						user_pair_kicker = poker_user_value1;
					} else {
						user_pair_kicker = poker_user_value2;
					}
				}

			}

			if(user_pair_amount >= 2) {
				user_hand_strength = 3;
				user_first_pair_value = user_duplicates.get(0);
				user_second_pair_value = user_duplicates.get(1);

				if(poker_user_value1 != user_first_pair_value && poker_user_value1 != user_second_pair_value) {
					if(poker_user_value2 == user_first_pair_value || poker_user_value2 == user_second_pair_value) {
						user_pair_kicker = poker_user_value1;
					}
				}

				if(poker_user_value2 != user_first_pair_value && poker_user_value2 != user_second_pair_value) {
					if(poker_user_value1 == user_first_pair_value || poker_user_value1 == user_second_pair_value) {
						user_pair_kicker = poker_user_value2;
					}
				}

				if(poker_user_value1 != user_first_pair_value && poker_user_value1 != user_second_pair_value && poker_user_value2 != user_first_pair_value && poker_user_value2 != user_second_pair_value) {
					if(poker_user_value1 > poker_user_value2) {
						user_pair_kicker = poker_user_value1;
					} else {
						user_pair_kicker = poker_user_value2;
					}
				}

			}

			if(dealer_pair_amount == 1) {
				dealer_hand_strength = 2;
				dealer_first_pair_value = dealer_duplicates.get(0);

				if(poker_dealer_value1 != dealer_first_pair_value) {
					if(poker_dealer_value2 == dealer_first_pair_value) {
						dealer_pair_kicker = poker_dealer_value1;
					}
				}

				if(poker_dealer_value2 != dealer_first_pair_value) {
					if(poker_dealer_value1 == dealer_first_pair_value) {
						dealer_pair_kicker = poker_dealer_value2;
					}
				}

				if(poker_dealer_value1 != dealer_first_pair_value && poker_dealer_value2 != dealer_first_pair_value) {
					if(poker_dealer_value1 > poker_dealer_value2) {
						dealer_pair_kicker = poker_dealer_value1;
					} else {
						dealer_pair_kicker = poker_dealer_value2;
					}
				}

			}

			if(dealer_pair_amount >= 2) {
				dealer_hand_strength = 3;
				dealer_first_pair_value = dealer_duplicates.get(0);
				dealer_second_pair_value = dealer_duplicates.get(1);

				if(poker_dealer_value1 != dealer_first_pair_value && poker_dealer_value1 != dealer_second_pair_value) {
					if(poker_dealer_value2 == dealer_first_pair_value || poker_dealer_value2 == dealer_second_pair_value) {
						dealer_pair_kicker = poker_dealer_value1;
					}
				}

				if(poker_dealer_value2 != dealer_first_pair_value && poker_dealer_value2 != dealer_second_pair_value) {
					if(poker_dealer_value1 == dealer_first_pair_value || poker_dealer_value1 == dealer_second_pair_value) {
						dealer_pair_kicker = poker_dealer_value2;
					}
				}

				if(poker_dealer_value1 != dealer_first_pair_value && poker_dealer_value1 != dealer_second_pair_value && poker_dealer_value2 != dealer_first_pair_value && poker_dealer_value2 != dealer_second_pair_value) {
					if(poker_dealer_value1 > poker_dealer_value2) {
						dealer_pair_kicker = poker_dealer_value1;
					} else {
						dealer_pair_kicker = poker_dealer_value2;
					}
				}

			}

			if(user_hand_strength == 2 && dealer_hand_strength == 2) {
				if(user_first_pair_value > dealer_first_pair_value) {
					user_hand_strength = 2.5;
				}
				if(dealer_first_pair_value > user_first_pair_value) {
					dealer_hand_strength = 2.5;
				}
				if(user_first_pair_value == dealer_first_pair_value) {
					if(user_pair_kicker > dealer_pair_kicker) {
						user_hand_strength = 2.5;
					}
					if(dealer_pair_kicker > user_pair_kicker) {
						dealer_hand_strength = 2.5;
					}
				}
			}

			if(user_hand_strength == 3 && dealer_hand_strength == 3) {
				if(user_first_pair_value > dealer_first_pair_value) {
					user_hand_strength = 3.5;
				}
				if(dealer_first_pair_value > user_first_pair_value) {
					dealer_hand_strength = 3.5;
				}

				if(user_first_pair_value == dealer_first_pair_value) {
					if(user_second_pair_value > dealer_second_pair_value) {
						user_hand_strength = 3.5;
					}
					if(dealer_second_pair_value > user_second_pair_value) {
						dealer_hand_strength = 3.5;
					}
					if(user_second_pair_value == dealer_second_pair_value) {
						if(user_pair_kicker > dealer_pair_kicker) {
							user_hand_strength = 3.5;
						}
						if(dealer_pair_kicker > user_pair_kicker) {
							dealer_hand_strength = 3.5;
						}
					}
				}
			}

			// Three of a Kind
			int user_highest_toak_value = 0;
			for(int value : user_card_values) {
				int frequency = 0;
				for(int v : user_card_values) {
					if(v==value) {
						frequency++;
					}
				}

				if (frequency >= 3 && value > user_highest_toak_value) {
					user_highest_toak_value = value;
				}
			}


			int dealer_highest_toak_value = 0;
			for(int value : dealer_card_values) {
				int frequency = 0;
				for(int v : dealer_card_values) {
					if(v==value) {
						frequency++;
					}
				}

				if (frequency >= 3 && value > dealer_highest_toak_value) {
					dealer_highest_toak_value = value;
				}
			}
			//Assigning TOAK kickers
			int dealer_toak_kicker = 0;
			int user_toak_kicker = 0;

			if(poker_user_value1 != user_highest_toak_value && poker_user_value2 == user_highest_toak_value) {
				user_toak_kicker = poker_user_value1;
			}

			if(poker_user_value1 == user_highest_toak_value && poker_user_value2 != user_highest_toak_value) {
				user_toak_kicker = poker_user_value2;
			}

			if(poker_dealer_value1 != dealer_highest_toak_value && poker_dealer_value2 == dealer_highest_toak_value) {
				dealer_toak_kicker = poker_dealer_value1;
			}

			if(poker_dealer_value1 == dealer_highest_toak_value && poker_dealer_value2 != dealer_highest_toak_value) {
				dealer_toak_kicker = poker_dealer_value2;
			}

			// Assigning Hand Strength
			if(user_highest_toak_value != 0 && dealer_highest_toak_value == 0) {
				user_hand_strength = 4;
			}

			if(user_highest_toak_value == 0 && dealer_highest_toak_value != 0) {
				dealer_hand_strength = 4;
			}

			if(user_highest_toak_value != 0 && dealer_highest_toak_value != 0) {
				if(user_highest_toak_value > dealer_highest_toak_value) {
					user_hand_strength = 4.5;
					dealer_hand_strength = 4;
				}
				if(user_highest_toak_value < dealer_highest_toak_value) {
					dealer_hand_strength = 4.5;
					user_hand_strength = 4;
				}
				if(user_highest_toak_value == dealer_highest_toak_value) {
					if(user_toak_kicker > dealer_toak_kicker) {
						user_hand_strength = 4.5;
						dealer_hand_strength = 4;
					}

					if(user_toak_kicker < dealer_toak_kicker) {
						user_hand_strength = 4;
						dealer_hand_strength = 4.5;
					}
					if(user_toak_kicker == dealer_toak_kicker) {
						user_hand_strength = 4;
						dealer_hand_strength = 4;
					}
				}
			}

			// Straight
			int user_consecutiveCount = 1;
			int user_highestCard = 0;
			boolean user_hasStraight = false;

			Set<Integer> user_uniqueValues = new HashSet<>();
			for (int value : user_card_values) {
			    user_uniqueValues.add(value);
			}
			// Add Ace as low value if necessary
			if (user_uniqueValues.contains(14)) {
			    user_uniqueValues.add(1);
			}

			Integer[] user_sortedValues = user_uniqueValues.toArray(new Integer[0]);
			Arrays.sort(user_sortedValues);

			for (int i = 0; i < user_sortedValues.length - 1; i++) {
			    if (user_sortedValues[i] + 1 == user_sortedValues[i + 1]) {
			        user_consecutiveCount++;
			        user_highestCard = user_sortedValues[i + 1];
			        if (user_consecutiveCount >= 5) {
			            user_hasStraight = true;
			            break;
			        }
			    } else if (user_sortedValues[i] + 1 < user_sortedValues[i + 1]) {
			        user_consecutiveCount = 1;
			    }
			}

			int dealer_consecutiveCount = 1;
			int dealer_highestCard = 0;
			boolean dealer_hasStraight = false;

			Set<Integer> dealer_uniqueValues = new HashSet<>();
			for (int value : dealer_card_values) {
			    dealer_uniqueValues.add(value);
			}
			// Add Ace as low value if necessary
			if (dealer_uniqueValues.contains(14)) {
			    dealer_uniqueValues.add(1);
			}

			Integer[] dealer_sortedValues = dealer_uniqueValues.toArray(new Integer[0]);
			Arrays.sort(dealer_sortedValues);

			for (int i = 0; i < dealer_sortedValues.length - 1; i++) {
			    if (dealer_sortedValues[i] + 1 == dealer_sortedValues[i + 1]) {
			        dealer_consecutiveCount++;
			        dealer_highestCard = dealer_sortedValues[i + 1];
			        if (dealer_consecutiveCount >= 5) {
			            dealer_hasStraight = true;
			            break;
			        }
			    } else if (dealer_sortedValues[i] + 1 < dealer_sortedValues[i + 1]) {
			        dealer_consecutiveCount = 1;
			    }
			}

			// Determine hand strength based on the straight detection
			if (user_hasStraight && !dealer_hasStraight) {
			    user_hand_strength = 5;
			} else if (!user_hasStraight && dealer_hasStraight) {
			    dealer_hand_strength = 5;
			} else if (user_hasStraight && dealer_hasStraight) {
			    if (user_highestCard > dealer_highestCard) {
			        user_hand_strength = 5.5;
			    } else if (user_highestCard < dealer_highestCard) {
			        dealer_hand_strength = 5.5;
			    } else {
			        user_hand_strength = 5;
			        dealer_hand_strength = 5;
			    }
			}

			// Flush
			// Checking for user flush and recording highest value in flush
			int[] user_suitCount = new int[4]; // Array to count the occurrences of each suit
			int user_highestCardValue = 0;
			boolean user_has_flush = false;

			// Count the occurrences of each suit
			for (int suit : user_card_suits) {
				user_suitCount[suit]++;
			}

			// Check if any suit has at least 5 occurrences, indicating a flush
			for (int i = 0; i < user_suitCount.length; i++) {
				if (user_suitCount[i] >= 5) {
					// Iterate through the cards to find the highest value of the specified suit
					for (int x = 0; x < user_card_values.length; x++) {
						if (user_card_suits[x] == i && user_card_values[x] > user_highestCardValue) {
							user_highestCardValue = user_card_values[x];
						}
					}
					user_has_flush = true;
					break; // Exit the loop once a flush is detected
				}
			}


			// Checking for dealer flush and recording highest value in flush
			int[] dealer_suitCount = new int[4]; // Array to count the occurrences of each suit
			int dealer_highestCardValue = 0;
			boolean dealer_has_flush = false;

			// Count the occurrences of each suit
			for (int suit : dealer_card_suits) {
				dealer_suitCount[suit]++;
			}

			// Check if any suit has at least 5 occurrences, indicating a flush
			for (int i = 0; i < dealer_suitCount.length; i++) {
				if (dealer_suitCount[i] >= 5) {
					// Iterate through the cards to find the highest value of the specified suit
					for (int x = 0; x < dealer_card_values.length; x++) {
						if (dealer_card_suits[x] == i && dealer_card_values[x] > dealer_highestCardValue) {
							dealer_highestCardValue = dealer_card_values[x];
						}
					}
					dealer_has_flush = true;
					break; // Exit the loop once a flush is detected
				}
			}

			if(user_has_flush == true && dealer_has_flush == false) {
				user_hand_strength = 6;
			}

			if(user_has_flush == false && dealer_has_flush == true) {
				dealer_hand_strength = 6;
			}

			if(user_has_flush == true && dealer_has_flush == true) {
				if(user_highestCardValue > dealer_highestCardValue) {
					user_hand_strength = 6.5;
					dealer_hand_strength = 6;
				}

				if(user_highestCardValue < dealer_highestCardValue) {
					user_hand_strength = 6;
					dealer_hand_strength = 6.5;
				}

				if(user_highestCardValue == dealer_highestCardValue) {
					user_hand_strength = 6;
					dealer_hand_strength = 6;
				}
			}
			// Full House Detection for User
			int userThreeOfAKindValue = -1;
			int userPairValue = -1;
			int userFullHouseStrength = -1;

			Map<Integer, Integer> userValueCounts = new HashMap<>();
			for (int value : user_card_values) {
			    userValueCounts.put(value, userValueCounts.getOrDefault(value, 0) + 1);
			}

			for (Map.Entry<Integer, Integer> entry : userValueCounts.entrySet()) {
			    if (entry.getValue() == 3 && entry.getKey() > userThreeOfAKindValue) {
			        userThreeOfAKindValue = entry.getKey();
			    }
			}

			for (Map.Entry<Integer, Integer> entry : userValueCounts.entrySet()) {
			    if (entry.getValue() >= 2 && entry.getKey() != userThreeOfAKindValue && entry.getKey() > userPairValue) {
			        userPairValue = entry.getKey();
			    }
			}

			if (userThreeOfAKindValue != -1 && userPairValue != -1) {
				userFullHouseStrength = userThreeOfAKindValue * 100 + userPairValue;
			}

			// Full House Detection for Dealer
			int dealerThreeOfAKindValue = -1;
			int dealerPairValue = -1;
			int dealerFullHouseStrength = -1;

			Map<Integer, Integer> dealerValueCounts = new HashMap<>();
			for (int value : dealer_card_values) {
			    dealerValueCounts.put(value, dealerValueCounts.getOrDefault(value, 0) + 1);
			}

			for (Map.Entry<Integer, Integer> entry : dealerValueCounts.entrySet()) {
			    if (entry.getValue() == 3 && entry.getKey() > dealerThreeOfAKindValue) {
			        dealerThreeOfAKindValue = entry.getKey();
			    }
			}

			for (Map.Entry<Integer, Integer> entry : dealerValueCounts.entrySet()) {
			    if (entry.getValue() >= 2 && entry.getKey() != dealerThreeOfAKindValue && entry.getKey() > dealerPairValue) {
			        dealerPairValue = entry.getKey();
			    }
			}

			if (dealerThreeOfAKindValue != -1 && dealerPairValue != -1) {
				dealerFullHouseStrength = dealerThreeOfAKindValue * 100 + dealerPairValue;
			}
			
			if(userFullHouseStrength != -1 && dealerFullHouseStrength != -1) {
				if(userFullHouseStrength > dealerFullHouseStrength) {
					user_hand_strength = 7.5;
					dealer_hand_strength = 7;
				}

				if(dealerFullHouseStrength > userFullHouseStrength) {
					dealer_hand_strength = 7.5;
					user_hand_strength = 7;
				}
			}

			// Four of a Kind

			int user_highest_foak_value = 0;
			for (int value : user_card_values) {
			    int frequency = 0;
			    for (int v : user_card_values) {
			        if (v == value) {
			            frequency++;
			        }
			    }

			    // Check for exactly four occurrences to determine Four of a Kind
			    if (frequency == 4 && value > user_highest_foak_value) {
			        user_highest_foak_value = value;
			    }
			}

			int dealer_highest_foak_value = 0;
			for (int value : dealer_card_values) {
			    int frequency = 0;
			    for (int v : dealer_card_values) {
			        if (v == value) {
			            frequency++;
			        }
			    }

			    // Corrected the condition to frequency == 4 for accurate FOAK detection
			    if (frequency == 4 && value > dealer_highest_foak_value) {
			        dealer_highest_foak_value = value;
			    }
			}


			// Assigning Hand Strength
			if (user_highest_foak_value > 0 && dealer_highest_foak_value == 0) {
			    user_hand_strength = 8;
			}
			if (user_highest_foak_value == 0 && dealer_highest_foak_value > 0) {
			    dealer_hand_strength = 8;
			}

			if(user_highest_foak_value > 0 && dealer_highest_foak_value > 0){
				if(user_highest_foak_value > dealer_highest_foak_value) {
					user_hand_strength = 8.5;
					dealer_hand_strength = 8;
				}
				if(user_highest_foak_value < dealer_highest_foak_value) {
					user_hand_strength = 8;
					dealer_hand_strength = 8.5;
				}
				if(user_highest_foak_value == dealer_highest_foak_value) {
					user_hand_strength = 8;
					dealer_hand_strength = 8;
				}
			}

			// Straight Flush detection for the user
			boolean user_hasStraightFlush = false;
			int user_highestStraightFlush = 0;

			// Iterate through each suit for the user
			for (int suit = 0; suit <= 3; suit++) {
			    final int currentSuit = suit;
			    // Checking for Ace-low straight flush (A-2-3-4-5)
			    boolean isLowAceStraight = true;
			    for (int card : new int[]{14, 2, 3, 4, 5}) {
			        boolean hasValueAndSuit = IntStream.range(0, user_card_suits.length).anyMatch(i -> user_card_values[i] == card && user_card_suits[i] == currentSuit);
			        if (!hasValueAndSuit) {
			            isLowAceStraight = false;
			            break;
			        }
			    }
			    if (isLowAceStraight) {
			        user_hasStraightFlush = true;
			        user_highestStraightFlush = 5;  // Lowest straight flush value
			        break;
			    }
			    
			    // Check for other straight flushes
			    for (int startValue = 14; startValue >= 6; startValue--) {  // Adjusted to avoid redundant Ace check
			        boolean isStraightFlush = true;
			        for (int offset = 0; offset < 5; offset++) {
			            int valueToCheck = startValue - offset;
			            boolean hasValueAndSuit = IntStream.range(0, user_card_suits.length).anyMatch(i -> user_card_values[i] == valueToCheck && user_card_suits[i] == currentSuit);
			            if (!hasValueAndSuit) {
			                isStraightFlush = false;
			                break;
			            }
			        }

			        if (isStraightFlush) {
			            user_hasStraightFlush = true;
			            user_highestStraightFlush = startValue;
			            break;
			        }
			    }
			    if (user_hasStraightFlush) break;
			}

			// Repeat similar logic for dealer
			boolean dealer_hasStraightFlush = false;
			int dealer_highestStraightFlush = 0;

			// Iterate through each suit for the dealer
			for (int suit = 0; suit <= 3; suit++) {
			    final int currentSuit = suit;
			    // Checking for Ace-low straight flush (A-2-3-4-5)
			    boolean isLowAceStraight = true;
			    for (int card : new int[]{14, 2, 3, 4, 5}) {
			        boolean hasValueAndSuit = IntStream.range(0, dealer_card_suits.length).anyMatch(i -> dealer_card_values[i] == card && dealer_card_suits[i] == currentSuit);
			        if (!hasValueAndSuit) {
			            isLowAceStraight = false;
			            break;
			        }
			    }
			    if (isLowAceStraight) {
			        dealer_hasStraightFlush = true;
			        dealer_highestStraightFlush = 5;  // Lowest straight flush value
			        break;
			    }

			    // Check for other straight flushes
			    for (int startValue = 14; startValue >= 6; startValue--) {
			        boolean isStraightFlush = true;
			        for (int offset = 0; offset < 5; offset++) {
			            int valueToCheck = startValue - offset;
			            boolean hasValueAndSuit = IntStream.range(0, dealer_card_suits.length).anyMatch(i -> dealer_card_values[i] == valueToCheck && dealer_card_suits[i] == currentSuit);
			            if (!hasValueAndSuit) {
			                isStraightFlush = false;
			                break;
			            }
			        }

			        if (isStraightFlush) {
			            dealer_hasStraightFlush = true;
			            dealer_highestStraightFlush = startValue;
			            break;
			        }
			    }
			    if (dealer_hasStraightFlush) break;
			}

			// Hand strength comparison
			if (user_hasStraightFlush && !dealer_hasStraightFlush) {
			    user_hand_strength = 9;
			} else if (!user_hasStraightFlush && dealer_hasStraightFlush) {
			    dealer_hand_strength = 9;
			} else if (user_hasStraightFlush && dealer_hasStraightFlush) {
			    if (user_highestStraightFlush > dealer_highestStraightFlush) {
			        user_hand_strength = 9.5;
			        dealer_hand_strength = 9;
			    } else if (user_highestStraightFlush < dealer_highestStraightFlush) {
			        dealer_hand_strength = 9.5;
			        user_hand_strength = 9;
			    } else {
			        user_hand_strength = 9;
			        dealer_hand_strength = 9;
			    }
			}
			
			// Royal Flush
			boolean user_HasRoyalFlush = false;

			// Check each suit to find a Royal Flush
			for (int suit = 0; suit < 4; suit++) {
			    boolean hasRoyalFlush = true;
			    for (int value : new int[]{10, 11, 12, 13, 14}) {
			        final int suitToCheck = suit;
			        boolean hasValue = IntStream.range(0, user_card_values.length)
			            .anyMatch(i -> user_card_values[i] == value && user_card_suits[i] == suitToCheck);
			        if (!hasValue) {
			            hasRoyalFlush = false;
			            break;
			        }
			    }

			    if (hasRoyalFlush) {
			        user_HasRoyalFlush = true;
			        break;
			    }
			}

			// Royal Flush detection for Dealer
			boolean dealer_HasRoyalFlush = false;

			// Check each suit to find a Royal Flush
			for (int suit = 0; suit < 4; suit++) {
			    boolean hasRoyalFlush = true;
			    for (int value : new int[]{10, 11, 12, 13, 14}) {
			        final int suitToCheck = suit;
			        boolean hasValue = IntStream.range(0, dealer_card_values.length)
			            .anyMatch(i -> dealer_card_values[i] == value && dealer_card_suits[i] == suitToCheck);
			        if (!hasValue) {
			            hasRoyalFlush = false;
			            break;
			        }
			    }

			    if (hasRoyalFlush) {
			        dealer_HasRoyalFlush = true;
			        break;
			    }
			}

			// Assigning hand strength
			if (user_HasRoyalFlush) {
			    user_hand_strength = 10;
			}

			if (dealer_HasRoyalFlush) {
			    dealer_hand_strength = 10;
			}

			String user_hand = "";
			String dealer_hand = "";
			double payout_multiplier = 0;

			// Assigning user hand string and payout multiplier
			if(user_hand_strength == 0) {
				user_hand = "N/A";
			}
			if(user_hand_strength == 1) {
				user_hand = "High Card";
			}
			if(user_hand_strength == 2 || user_hand_strength == 2.5) {
				user_hand = "One Pair";
				payout_multiplier = 0.3;
			}
			if(user_hand_strength == 3 || user_hand_strength == 3.5) {
				user_hand = "Two Pair";
				payout_multiplier = 1;
			}
			if(user_hand_strength == 4 || user_hand_strength == 4.5) {
				user_hand = "Three of a Kind";
				payout_multiplier = 3;
			}
			if(user_hand_strength == 5 || user_hand_strength == 5.5) {
				user_hand = "Straight";
				payout_multiplier = 4;
			}
			if(user_hand_strength == 6 || user_hand_strength == 6.5) {
				user_hand = "Flush";
				payout_multiplier = 5;
			}
			if(user_hand_strength == 7 || user_hand_strength == 7.5) {
				user_hand = "Full House";
				payout_multiplier = 7;
			}
			if(user_hand_strength == 8 || user_hand_strength == 8.5) {
				user_hand = "Four of a Kind";
				payout_multiplier = 30;
			}
			if(user_hand_strength == 9 || user_hand_strength == 9.5) {
				user_hand = "Straight Flush";
				payout_multiplier = 50;
			}
			if(user_hand_strength == 10) {
				user_hand = "Royal Flush";
				payout_multiplier = 100;
			}

			// Assigning dealer hand string
			if(dealer_hand_strength == 0) {
				dealer_hand = "N/A";
			}
			if(dealer_hand_strength == 1) {
				dealer_hand = "High Card";
			}
			if(dealer_hand_strength == 2 || dealer_hand_strength == 2.5) {
				dealer_hand = "One Pair";
			}
			if(dealer_hand_strength == 3 || dealer_hand_strength == 3.5) {
				dealer_hand = "Two Pair";
			}
			if(dealer_hand_strength == 4 || dealer_hand_strength == 4.5) {
				dealer_hand = "Three of a Kind";
			}
			if(dealer_hand_strength == 5 || dealer_hand_strength == 5.5) {
				dealer_hand = "Straight";
			}
			if(dealer_hand_strength == 6 || dealer_hand_strength == 6.5) {
				dealer_hand = "Flush";
			}
			if(dealer_hand_strength == 7 || dealer_hand_strength == 7.5) {
				dealer_hand = "Full House";
			}
			if(dealer_hand_strength == 8 || dealer_hand_strength == 8.5) {
				dealer_hand = "Four of a Kind";
			}
			if(dealer_hand_strength == 9 || dealer_hand_strength == 9.5) {
				dealer_hand = "Straight Flush";
			}
			if(dealer_hand_strength == 10) {
				dealer_hand = "Royal Flush";
			}

			if(user_hand_strength != 0) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You have a " + user_hand);
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You have no significant hand");
			}

			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The dealer's hand is ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(poker_dealer_card1 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(poker_dealer_card2);

			if(dealer_hand_strength != 0) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The dealer has a " + dealer_hand);
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The dealer has no significant hand");
			}

			if(dealer_hand_strength > user_hand_strength) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The dealer wins!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the round and your bet");
				money -= bet;
			}

			if(dealer_hand_strength < user_hand_strength) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				if(user_hand_strength == 1) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("No payouts are recieved for winning with a high card");
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You recieve a " + payout_multiplier + "x payout");
					money += bet * payout_multiplier;
				}

			}

			if(dealer_hand_strength == user_hand_strength) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Players hands are equal");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You get your bet back");
			}

			balances.set(user_indx, money);

			try {
				FileWriter wr = new FileWriter("balances.txt"); 
				for (int ba : balances) {
					wr.write(ba + System.getProperty("line.separator"));
				}
				wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(game_option_scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}

	public static void dice_roll() {
		Scanner sc = new Scanner(System.in);
		int answer = -1;
		int answer2 = -1;

		while (true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Choose what type of dice roll bet you're placing:");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Bet on sum being odd or even (1x payout) - 0");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Bet on range of sum (3.6x payout) - 1");
			answer = Integer.valueOf(sc.nextLine());

			if (answer == 0 || answer == 1) {
				break;
			}

			System.out.println("that's not a valid option");
		}

		while (answer == 0) {
			System.out.println("do you bet on odd (0) or even (1)?");
			answer2 = Integer.valueOf(sc.nextLine());


			if (answer2 == 0 || answer2 == 1) {
				break;
			}
			System.out.println("that's not a valid option");

		}

		while (answer == 1) {
			System.out.println("Bet on the sum of the die");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("(2, 3, 4, 5) - 0"); // 10/36 chance
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("(6, 8) - 1"); // 10/36 chance
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("(9, 10, 11, 12) - 2"); // 10/36 chance
			answer2 = Integer.valueOf(sc.nextLine());

			if (answer2 == 0 || answer2 == 1 || answer2 == 2) {
				break;
			}

			System.out.println("that's not a valid option");
		}

		while (true) {
			System.out.println("how much are you betting? ");
			bet = Integer.valueOf(sc.nextLine());

			if (money - bet >= 0) {
				break;
			}
			System.out.println("You don't have enough money");
		}

		int die1 = (int) Math.floor(1 + Math.random() * 7); // between 1 and 6
		int die2 = (int) Math.floor(1 + Math.random() * 7); // between 1 and 6
		int sum = die1 + die2;

		System.out.print("The value of the first die is ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(die1);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print("The value of the second die is ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(die2);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print("The sum of the die is ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(sum);


		if(answer == 0 && answer2 == 0) { // Betting on odd
			if(sum % 2 == 1) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 1x payout");
				money += bet;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the game and your bet");
				money -= bet;
			}
		}

		if(answer == 0 && answer2 == 1) { // Betting on even
			if(sum % 2 == 0) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 1x payout");
				money += bet;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the game and your bet");
				money -= bet;
			}
		}

		if(answer == 1 && answer2 == 0) { // Betting on (2,3,4,5)
			if(sum == 2 || sum == 3 || sum == 4 || sum == 5) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 2.6x payout");
				money = (int) (money + bet*2.6);
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the game and your bet");
				money -= bet;
			}
		}

		if(answer == 1 && answer2 == 1) { // Betting on (6,8)
			if(sum == 6 || sum == 8) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 2.6x payout");
				money = (int) (money + bet*2.6);
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the game and your bet");
				money -= bet;
			}
		}

		if(answer == 1 && answer2 == 2) { // Betting on (9,10,11,12)
			if(sum == 9 || sum == 10 || sum == 11 || sum == 12) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Win!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a 2.6x payout");
				money = (int) (money + bet*2.6);
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You Lose the game and your bet");
				money -= bet;
			}
		}

		balances.set(user_indx, money);

		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(game_option_scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}

	public static void slot_machine() {
		int free_spins = 0;
		boolean keep_playing = false;

		Scanner sc = new Scanner(System.in);
		while (true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("how much you betting for each spin: ");
			bet = Integer.valueOf(sc.nextLine());

			if (money - bet >= 0 && bet < 10000 && bet > 0) {
				break;
			}
			System.out.println("you either dont have the cash or the bet isn't between $0 and $9999");
		}
		
		System.out.println("One $ is 1x your bet");
		try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Two $ is 24x your bet");
		try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Three $ is 54x your bet");
		try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("One 7 is a free spin");
		try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Two 7 is 7 free spins and 17x your bet");
		try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Three 7 is 17 free spins and 37x your bet");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		keep_playing = false;
		
		do {
			// Define symbols for each column
			char[] symbols1 = {'-', '1', '$', '7', '#', '~', '|','I', 'R', '@', 'O', '%', '_', '&', '4', 'H', '>', 'L'};
			char[] symbols2 = {'5', '<', '(', '*', 'M', '^', '2', '$', '7', '!', '?', 'P', '+', ':','S', 'J', 'G','{'};
			char[] symbols3 = {'N', ')', 'A', 'B','}', 'C', '3', 'T', 'D','`', 'Q', 'E', 'F', 'K',';', '6', '$', '7'};
			char[] result = {'0', '0', '0'};
			int dollar_count = 0;
			int seven_count = 0;

			int free_spin_reward = 0;
			int bet_multiplier_reward = 0;
			double displayfreespins = free_spins;
			String formattedfreespins = String.format("%02.0f", displayfreespins);

			
			double displaybet = bet;
			String formattedbet = String.format("%04.0f", displaybet);
			// ASCII art of the slot machine screen with a lever
			String[] screen = {
					" ___________________",
					"|   __     ______   |",
					"|  |" + formattedfreespins + "|   |  " + formattedbet + "|  |",
					"|  |__|   |______|  |",
					"|                   |",
					"|                   |",
					"|  _______________  |    [___]",
					"| |               | |      |",
					"| |               | |      |",
					"| |_______________| |      |",
					"|___________________|      |",
			};

			// Display initial slot machine screen
			displayScreen(screen);

			if(free_spins == 0) {
				money -=bet;
			} else {
				free_spins--;
				displayfreespins = free_spins;
				formattedfreespins = String.format("%02.0f", displayfreespins);
				screen[2] = "|  |" + formattedfreespins + "|   |  " + formattedbet + "|  |";
			}
			
			if(keep_playing == false) {
				// Wait for user input to activate the lever
				System.out.println("Press Enter to activate the lever.");
				Scanner scanner = new Scanner(System.in);
				scanner.nextLine();
			}
			
			// Generate random number of rotations for each column
			int rotations1 = (int) Math.floor(1 + Math.random() * 19); // Between 1 and 18
			int rotations2 = (int) Math.floor(1 + Math.random() * 19);
			int rotations3 = (int) Math.floor(1 + Math.random() * 19);

			// Rotate symbols as if spinning with a rotating animation
			for (int i = 0; i < Math.max(rotations1, Math.max(rotations2, rotations3)); i++) {
				if (i < rotations1) rotateSymbols(symbols1);
				if (i < rotations2) rotateSymbols(symbols2);
				if (i < rotations3) rotateSymbols(symbols3);
				displayAnimatedScreen(screen, symbols1, symbols2, symbols3);
				try {
					Thread.sleep(200); // Adjust the delay as needed for animation speed
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Display result
			result[0] = symbols1[2];
			result[1] = symbols2[2];
			result[2] = symbols3[2];
			try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("\nResult: ");
			System.out.println(result);

			// Calculating dollar count and seven count
			if(result[0] == '$') {dollar_count++;}
			if(result[1] == '$') {dollar_count++;}
			if(result[2] == '$') {dollar_count++;}

			if(result[0] == '7') {seven_count++;}
			if(result[1] == '7') {seven_count++;}
			if(result[2] == '7') {seven_count++;}

			// Calculating spin reward
			if(dollar_count == 1) {bet_multiplier_reward += 1;}
			if(dollar_count == 2) {bet_multiplier_reward += 24;}
			if(dollar_count == 3) {bet_multiplier_reward += 54;}

			if(seven_count == 1) {free_spin_reward = 1;}
			if(seven_count == 2) {bet_multiplier_reward += 17; free_spin_reward = 7;}
			if(seven_count == 3) {bet_multiplier_reward += 37; free_spin_reward = 17;}

			if(dollar_count != 0 && seven_count == 0) {
				money += bet*bet_multiplier_reward;
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You got " + dollar_count + " $ in your spin");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a " + bet_multiplier_reward + "x payout");
			}

			if(dollar_count == 0 && seven_count != 0) {
				money += bet*bet_multiplier_reward;
				free_spins += free_spin_reward;
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You got " + seven_count + " 7 in your spin");
				if(seven_count > 1) {
					try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You recieve a " + bet_multiplier_reward + "x payout");
				}
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve " + free_spin_reward + " free spins!");
			}

			if(dollar_count != 0 && seven_count != 0) {
				money += bet*bet_multiplier_reward;
				free_spins += free_spin_reward;
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You got " + seven_count + " 7" + " and " + dollar_count + " $ in your spin");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve a " + bet_multiplier_reward + "x payout");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve " + free_spin_reward + " free spins!");
			}

			if(dollar_count == 0 && seven_count == 0) {
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You didn't get any 7 or $ in your spin");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You recieve no reward");
			}

			try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Your balance is now $" + money);
			balances.set(user_indx, money);

			try {
				FileWriter wr = new FileWriter("balances.txt"); 
				for (int ba : balances) {
					wr.write(ba + System.getProperty("line.separator"));
				}
				wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			Scanner game_option_scanner = new Scanner(System.in);
			int input = -1;

			while(true) {
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to contiue (0)?");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to contiue but change your bet (only works when you have no free spins) (1)?");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to switch game? (2)?");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to quit the Gambling Haven? (3)?");
				try {Thread.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Keep in mind that switching games or quitting will reset your free spins");
				input = Integer.valueOf(game_option_scanner.nextLine());
				if(input == 0 || input == 2 || input == 3) {
					break;
				}
				if(input == 1 && free_spins == 0) {
					break;
				}
				if(input == 1 && free_spins != 0) {
					System.out.println("You need to use the rest of your free spins before changing your bet");
				}
			}

			if(input == 0) {
				keep_playing = true;
			}

			if(input == 1) {
				keep_playing = true;

				while (true) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("how much you betting for each spin: ");
					bet = Integer.valueOf(sc.nextLine());

					if (money - bet >= 0 && bet < 10000 && bet > 0) {
						break;
					}
					System.out.println("you either dont have the cash or the bet isn't between $0 and $9999");
				}
			}

			if(input == 2) {
				switch_game = true;
				keep_playing = false;
				rerun = true;
				hub();
			}

			if (input == 3) {
				System.out.println("Your final balance: $" + money);
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Thanks for playing!");
				System.exit(0);
			}


		}
		while (keep_playing == true);
	}



	// Method to display the slot machine screen
	private static void displayScreen(String[] screen) {
		for (String line : screen) {
			System.out.println(line);
		}
	}

	// Method to display the slot machine screen with rotating symbols
	private static void displayAnimatedScreen(String[] screen, char[] symbols1, char[] symbols2, char[] symbols3) {
		// Print the top part of the screen
		for (int i = 0; i < 8; i++) {
			System.out.println(screen[i]);
		}

		// Print the middle part of the screen with current symbols rotated sideways
		System.out.print("| |   ");
		System.out.print(symbols1[2] + "   ");
		System.out.print(symbols2[2] + "   ");
		System.out.print(symbols3[2] + "   ");
		System.out.println("| |      |");

		// Print the bottom part of the screen
		for (int i = 9; i < screen.length; i++) {
			System.out.println(screen[i]);
		}
	}

	// Method to rotate symbols in an array
	private static void rotateSymbols(char[] symbols) {
		char lastSymbol = symbols[symbols.length - 1];
		for (int i = symbols.length - 1; i > 0; i--) {
			symbols[i] = symbols[i - 1];
		}
		symbols[0] = lastSymbol;

	}

	public static void war() {
		int war_u_indx1 = 0;
		int war_u_indx2 = 0;
		int war_d_indx1 = 0;
		int war_d_indx2 = 0;
		String war_user_card = "";
		int war_user_value = 0;
		String war_dealer_card = "";
		int war_dealer_value = 0;
		boolean war = false;
		int[] values = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[][] deck = new String[ranks.length][suits.length]; // [rows][columns]
		for (int x = 0; x < ranks.length; x++) {
			for (int y = 0; y < suits.length; y++) {
				deck[x][y] = ranks[x] + " of " + suits[y];
			}
		}
		Scanner sc = new Scanner(System.in);


		while (true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("how much are you betting? ");
			bet = Integer.valueOf(sc.nextLine());

			if (money - bet >= 0) {
				break;
			}
			System.out.println("You don't have enough money");
		}

		// Player draws first card
		war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[war_u_indx1][war_u_indx2] == null) {
			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		war_user_card = deck[war_u_indx1][war_u_indx2];
		deck[war_u_indx1][war_u_indx2] = null;
		war_user_value = values[war_u_indx1];

		System.out.print("You have dealt: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(war_user_card);

		// dealer draws first card face up
		war_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		war_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[war_d_indx1][war_d_indx2] == null) {
			war_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		war_dealer_card = deck[war_d_indx1][war_d_indx2];
		deck[war_d_indx1][war_d_indx2] = null;


		war_dealer_value = values[war_d_indx1];
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print("The dealer has dealt: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(war_dealer_card);

		if(war_dealer_value == war_user_value) {
			war = true;
		}

		while(war) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("The cards have the same value");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("War has started!");

			// Player draws 3 face_down cards
			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String player_1 = deck[war_u_indx1][war_u_indx2];

			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String player_2 = deck[war_u_indx1][war_u_indx2];

			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String player_3 = deck[war_u_indx1][war_u_indx2];

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The player draws a ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(player_1 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(player_2 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(player_3);


			// Dealer draws 3 face_down cards
			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String dealer_1 = deck[war_u_indx1][war_u_indx2];

			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String dealer_2 = deck[war_u_indx1][war_u_indx2];

			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}
			String dealer_3 = deck[war_u_indx1][war_u_indx2];

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The dealer draws a ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(dealer_1 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print(dealer_2 + ", ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(dealer_3);


			// Player draws fourth card
			war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_u_indx1][war_u_indx2] == null) {
				war_u_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_u_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			war_user_card = deck[war_u_indx1][war_u_indx2];
			deck[war_u_indx1][war_u_indx2] = null;
			war_user_value = values[war_u_indx1];


			// dealer draws fourth card
			war_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			war_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[war_d_indx1][war_d_indx2] == null) {
				war_d_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				war_d_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			war_dealer_card = deck[war_d_indx1][war_d_indx2];
			deck[war_d_indx1][war_d_indx2] = null;

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The player's fourth card is a ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(war_user_card);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.print("The dealer's fourth card is a ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(war_dealer_card);

			if(war_dealer_value != war_user_value) {
				war = false;
			}

		}

		// End Game, displaying totals and outcome


		if(war_dealer_value > war_user_value) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("The dealer wins!");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You Lose the round and your bet");
			money -= bet;
		}

		if(war_dealer_value < war_user_value) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You Win!");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You recieve a 1x payout");
			money += bet;
		}

		balances.set(user_indx, money);

		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(game_option_scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}


	}

	public static void roulette() {
		int[] values = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 ,24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36};
		// 0 is red, 1 is black, 2 is green
		int [] colors = {2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0};

		while (true) {
			Scanner sc = new Scanner(System.in);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("how much you betting: ");
			bet = Integer.valueOf(sc.nextLine());

			if (money - bet >= 0 && bet > 0) {
				break;
			}
			System.out.println("either you dont got that kinda cash or you didnt write a number above 0");
		}

		System.out.println("What type of bet would you like to make?");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Inside Bet (higher payout, lower probability) - 0");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Outsdie Bet (lower payout, higher probability) - 1");
		Scanner scanner = new Scanner(System.in);
		int answer1 = Integer.valueOf(scanner.nextLine());
		int answer2 = -1;
		int answer3 = -1;
		int answer4 = -1; // In the case of a split bet

		if(answer1 == 0) {
			System.out.println("What bet what you like to make?");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Straight Up (Betting on a single number)- 0");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Split Bet (Betting on two numbers) - 1");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Trio (Betting on zero and its two neighboring numbers) - 2");
			answer2 = Integer.valueOf(scanner.nextLine());

		}

		if(answer1 == 1) {
			System.out.println("What bet what you like to make?");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Red/Black - 0");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Even/Odd - 1");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Low/High (Betting on 1-18 or 19-36) - 2");
			try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Dozens (Betting on 1-12, 13-24, or 25-36) - 3");
			answer2 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 0 && answer2 == 0) {
			System.out.println("Type the number you are betting on (0-36)");
			answer3 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 0 && answer2 == 1) {
			System.out.println("Type the first number of the split bet");
			answer3 = Integer.valueOf(scanner.nextLine());
			System.out.println("Type the second number of the split bet");
			answer4 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 1 && answer2 == 0) {
			System.out.println("Are you betting on red (0) or black (1)?");
			answer3 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 1 && answer2 == 1) {
			System.out.println("Are you betting on even (0) or odd (1)?");
			answer3 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 1 && answer2 == 2) {
			System.out.println("Are you betting on 1-18 (0) or 19-36 (1)?");
			answer3 = Integer.valueOf(scanner.nextLine());
		}

		if(answer1 == 1 && answer2 == 3) {
			System.out.println("Are you betting on 1-12 (0), 13-24 (1), or 25-36 (2)?");
			answer3 = Integer.valueOf(scanner.nextLine());
		}

		int winning_indx = (int) Math.floor(Math.random() * 37); // between 0 and 36
		int winning_number = values[winning_indx];

		String winning_color = "";

		if(colors[winning_indx] == 0) {winning_color = "Red";}
		if(colors[winning_indx] == 1) {winning_color = "Black";}
		if(colors[winning_indx] == 2) {winning_color = "Green";}

		System.out.print("The Wheel is Spinning");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(".");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(".");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(".");

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("The Winning Number is " + winning_number + " Which is " + winning_color);

		if(answer1 == 0 && answer2 == 0) {
			if(winning_number == answer3) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You guessed the corret number!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You win 37x your bet");
				money += bet * 36;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The winning number was not " + answer3 + ", you lose your bet");
				money -= bet;
			}
		}

		if(answer1 == 0 && answer2 == 1) {
			if(winning_number == answer3 || winning_number == answer4) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You guessed the corret number!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You win 18.5x your bet");
				money += bet * 18.5;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The winning number was not " + answer3 + " or " + answer4 + ", you lose your bet");
				money -= bet;
			}
		}

		if(answer1 == 0 && answer2 == 2) {
			if(winning_number == 36 || winning_number == 0 || winning_number == 1) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You guessed the corret number!");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You win 12.35x your bet");
				money += bet * 12.35;
			} else {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("The winning number was not 36, 0, or 1, you lose your bet");
				money -= bet;
			}
		}

		if(answer1 == 1 && answer2 == 0) {
			if(answer3 == 0) { // if they bet red
				if(winning_color.equals("Red")) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed the corret color!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("The color you guessed was wrong");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			} else {
				if(winning_color.equals("Black")) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed the corret color!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("The color you guessed was wrong");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			}
		}

		if(answer1 == 1 && answer2 == 1) { // even is zero, odd is one
			if(answer3 == 0) { // if they bet even
				if(winning_number % 2 == 0) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			} else {
				if(winning_number % 2 == 1) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			}
		}

		if(answer1 == 1 && answer2 == 2) { // 1-18 (0) or 19-36 (2)
			if(answer3 == 0) { // if they bet 1-18
				if(winning_number > 0 && winning_number < 19) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			} else {
				if(winning_number > 18) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 1x your bet");
					money += bet;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			}
		}

		if(answer1 == 1 && answer2 == 3) { //1-12 (0), 13-24 (1), or 25-36 (2)
			if(answer3 == 0) { // 1-12
				if(winning_number > 0 && winning_number < 13) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 2x your bet");
					money += bet * 2;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			} 
			if(answer3 == 1) { // 13-24
				if(winning_number > 12 && winning_number < 25) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 2x your bet");
					money += bet * 2;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			}
			if(answer3 == 2) { // 25-36
				if(winning_number > 24) {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed correctly!");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You win 2x your bet");
					money += bet * 2;
				} else {
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You guessed incorrectly");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
					money -= bet;
				}
			}
		}

		balances.set(user_indx, money);

		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}

	}

	public static void high_low() {
		int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		boolean keep_playing = false;
		String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
		String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
		String[][] deck = new String[ranks.length][suits.length]; // [rows][columns]
		for (int x = 0; x < ranks.length; x++) {
			for (int y = 0; y < suits.length; y++) {
				deck[x][y] = ranks[x] + " of " + suits[y];
			}
		}
		Scanner sc = new Scanner(System.in);


		while (true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("how much you betting per round: ");
			bet = Integer.valueOf(sc.nextLine());


			if (money - bet >= 0 && bet > 0) {
				break;
			}
			System.out.println("either you dont got that kinda cash or you didnt write a number above 0");
		}

		int card1_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
		int card1_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

		while (deck[card1_indx1][card1_indx2] == null) {
			card1_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			card1_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
		}

		String card1 = deck[card1_indx1][card1_indx2];
		deck[card1_indx1][card1_indx2] = null;
		int card1_value = values[card1_indx1];

		double probability_of_higher = 13 - card1_value;
		probability_of_higher = probability_of_higher / 13;

		double probability_of_lower = card1_value - 1;
		probability_of_lower = probability_of_lower / 13;

		double return_multiplier_higher = probability_of_lower / probability_of_higher;
		return_multiplier_higher = Double.parseDouble(String.format("%.2f", return_multiplier_higher));
		double return_multiplier_lower = probability_of_higher / probability_of_lower;
		return_multiplier_lower = Double.parseDouble(String.format("%.2f", return_multiplier_lower));

		System.out.print("The dealer has dealt: ");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(card1);
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}

		do {
			keep_playing = false;

			int answer = -1;
			while (true) {
				System.out.println("Higher (0) or Lower (1)?");
				answer = Integer.valueOf(sc.nextLine());
				if (answer == 0 || answer == 1) {break;}
				System.out.println("that's not a valid option");
			}

			// Player draws future card
			int card2_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
			int card2_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3

			while (deck[card2_indx1][card2_indx2] == null) {
				card2_indx1 = (int) Math.floor(Math.random() * 13); // between 0 and 12
				card2_indx2 = (int) Math.floor(Math.random() * 4); // between 0 and 3
			}

			String card2 = deck[card2_indx1][card2_indx2];
			deck[card2_indx1][card2_indx2] = null;
			int card2_value = values[card2_indx1];

			System.out.print("The dealer has dealt: ");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println(card2);

			if(answer == 0) {
				if(card2_value > card1_value) {
					money += bet * return_multiplier_higher;
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You were right");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You recieve a " + return_multiplier_higher + "x payout");
				} else {
					money -= bet;
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You were wrong");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
				}
			}

			if(answer == 1) {
				if(card2_value < card1_value) {
					money += bet * return_multiplier_lower;
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You were right");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You recieve a " + return_multiplier_lower + "x payout");
				} else {
					money -= bet;
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You were wrong");
					try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
					System.out.println("You lose your bet");
				}
			}

			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Your balance is: $" + money);

			balances.set(user_indx, money);

			try {
				FileWriter wr = new FileWriter("balances.txt"); 
				for (int ba : balances) {
					wr.write(ba + System.getProperty("line.separator"));
				}
				wr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			probability_of_higher = 13 - card2_value;
			probability_of_higher = probability_of_higher / 13;

			probability_of_lower = card2_value - 1;
			probability_of_lower = probability_of_lower / 13;

			return_multiplier_higher = probability_of_lower / probability_of_higher;
			return_multiplier_higher = Double.parseDouble(String.format("%.2f", return_multiplier_higher));
			return_multiplier_lower = probability_of_higher / probability_of_lower;
			return_multiplier_lower = Double.parseDouble(String.format("%.2f", return_multiplier_lower));

			card1 = card2;
			card1_value = card2_value;

			int input = -1;
			while(true) {
				Scanner game_option_scanner = new Scanner(System.in);
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to contiue (0)?");
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to contiue but change your bet (1)?");
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to switch game? (2)?");
				try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Do you want to quit the Gambling Haven? (3)?");
				input = Integer.valueOf(game_option_scanner.nextLine());
				if(input == 0 || input == 1 || input == 2 || input == 3) {
					break;
				}
				System.out.println("That is not a valid option");
			}

			if(input == 0) {
				keep_playing = true;
			}

			if(input == 1) {
				keep_playing = true;

				while (true) {
					System.out.println("how much you betting per round: ");
					bet = Integer.valueOf(sc.nextLine());
					if (money - bet >= 0 && bet > 0) {
						break;
					}
					System.out.println("either you dont got that kinda cash or you didnt write a number above 0");
				}
			}

			if(input == 2) {
				switch_game = true;
				keep_playing = false;
				rerun = true;
				hub();
			}

			if (input == 3) {
				System.out.println("Your final balance: $" + money);
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Thanks for playing!");
				System.exit(0);
			}


		} while(keep_playing == true);

	}

	public static void coin_flip() {
		Scanner sc = new Scanner(System.in);
		int answer = -1;
		while (true) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Are you betting on heads (0) or tails (1)? ");
			answer = Integer.valueOf(sc.nextLine());

			if (answer == 0 || answer == 1) {
				break;
			}

			System.out.println("that's not a valid option");
		}

		while (true) {
			System.out.println("how much are you betting? ");
			bet = Integer.valueOf(sc.nextLine());


			if (money - bet >= 0) {
				break;
			}
			System.out.println("You don't have enough money");
		}

		System.out.print("Flipping the coin");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(".");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.print(".");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println(".");

		int coin = (int) Math.floor(Math.random() * 2); // between 0 and 1
		if(coin == 0) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You got heads!");
		}

		if(coin == 1) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You got tails!");
		}

		if(answer == coin) {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You Win!");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You recieve a 1x payout");
			money += bet;
		} else {
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You Lose!");
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("You Lose the game and your bet");
			money -= bet;
		}

		balances.set(user_indx, money);

		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play again? (0)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");

		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");

		int input = Integer.valueOf(game_option_scanner.nextLine());
		if(input == 0) {
			switch_game = false;
			rerun = true;
			hub();
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}

	public static void reward_wheel() {
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Win up to $300 with the reward wheel, you get 3 free spins");
		int[] rewards = {300, 10, 1, 100, 5, 30, 50, 20, 80, 3, 70, 40, 60, 90};
		double reward = 0;
		String formattedreward = String.format("%03.0f", reward);

		double free_spins = reward_wheel_spins;
		String formatted_spins = String.format("%01.0f", free_spins);

		// ASCII art of reward wheel
		String[] wheel = {
				"    ______________",
				"  //              \\\\",
				" //  " + formatted_spins + "             \\\\",
				"||       " + formattedreward + "        ||",
				" \\\\                //",
				"  \\\\______________//",
				"    |           |",
				"    |  Spin Me! |",
				"    |___________|"
		};

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		// Display initial reward wheel screen
		displayScreen(wheel);

		// Wait for user input to spin the wheel
		reward_wheel_spins--;
		free_spins = reward_wheel_spins;
		formatted_spins = String.format("%01.0f", free_spins);
		wheel[2] = " //  " + formatted_spins + "             \\\\";

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Press Enter to spin the wheel");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();

		// Generate random number of rotations
		int rotations = (int) Math.floor(1 + Math.random() * 8); // Between 1 and 14

		// Rotate rewards as if spinning with a rotating animation
		for (int i = 0; i < rotations; i++) {
			rotateSymbols2(rewards);
			displayAnimatedScreen2(wheel, rewards);
			try {
				Thread.sleep(300); // Adjust the delay as needed for animation speed
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Display result
		int result = rewards[2];
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("\nResult:");
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("$" + result);

		money += result;
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Your balance is now $" + money);
		balances.set(user_indx, money);

		try {
			FileWriter wr = new FileWriter("balances.txt"); 
			for (int ba : balances) {
				wr.write(ba + System.getProperty("line.separator"));
			}
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scanner game_option_scanner = new Scanner(System.in);
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to play agian (0)?");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to switch game? (1)?");
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		System.out.println("Do you want to quit the Gambling Haven? (2)?");
		int input = Integer.valueOf(game_option_scanner.nextLine());

		if(input == 0) {
			if(reward_wheel_spins == 0) {
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("You have no reward wheel spins left");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println("Redirecting to main Hub...");
				try {Thread.sleep(1500);} catch (InterruptedException e) {e.printStackTrace();}
				input = 1;
			} else {
				switch_game = false;
				rerun = true;
				hub();
			}
		}

		if(input == 1) {
			switch_game = true;
			rerun = true;
			hub();
		}

		if (input == 2) {
			System.out.println("Your final balance: $" + money);
			try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Thanks for playing!");
			System.exit(0);
		}
	}

	// Method to display the reward wheel screen with rotating rewards
	private static void displayAnimatedScreen2(String[] screen, int[] rewards) {
		// Print the top part of the screen
		for (int i = 0; i < 3; i++) {
			System.out.println(screen[i]);
		}
		double reward = rewards[2];
		String formattedreward = String.format("%03.0f", reward);

		// Print the middle part of the screen with current symbols rotated sideways
		System.out.println("||       " + formattedreward + "        ||");

		// Print the bottom part of the screen
		for (int i = 4; i < screen.length; i++) {
			System.out.println(screen[i]);
		}
	}

	// Method to rotate symbols in an array
	private static void rotateSymbols2(int[] rewards) {
		int lastreward = rewards[rewards.length - 1];
		for (int i = rewards.length - 1; i > 0; i--) {
			rewards[i] = rewards[i - 1];
		}
		rewards[0] = lastreward;
	}
}

