// Description: 
// This file contains the Game class, which is the main class that runs the game logic.
// The Game class contains the main method that starts the game and controls the game loop.

// Import the necessary classes
import java.io.IOException;

// Game class
public class Game
{
    // Create a player using the Adventurer class
    Adventurer player = new Adventurer();

    // Boolean to check if the player leveled up, used to determine if the player can visit the shop
    boolean playerLeveledUp = false;

    // Main method
    public static void main(String[] args) throws Exception
    {
        // Create a new game object
        Game game = new Game();

        // Clear the screen before starting the game
        game.ClearScreen();

        // Start the game with the introduction section
        game.Start();

        // Wait for user input before clearing the screen
        game.WaitForInput();
        game.ClearScreen();

        // Game loop
        boolean running = true;
        while (running)
        {
            // Room method returns a boolean value, if the player defeats the Lich, the game is completed
            boolean gameCompleted = game.Room();

            // After the room is completed, wait for user input before clearing the screen
            game.WaitForInput();
            game.ClearScreen();

            // If the game is completed, break out of the loop
            if (gameCompleted) 
            {
                break;
            }

            // If the player leveled up or is level 5, prompt the player to visit the shop
            if (game.playerLeveledUp || game.player.GetLevel() >= 5)
            {
                // Reset the player leveled up boolean
                game.playerLeveledUp = false;

                // Ask the player if they want to visit the shop
                boolean shop = game.ShopPrompt();

                // If the player chooses to visit the shop, call the shop method
                if (!shop)
                {
                    // Clear the screen if the player chooses not to visit the shop
                    game.ClearScreen();
                    continue;
                }
                else
                {
                    // Clear the screen before entering the shop
                    game.ClearScreen();

                    // Call the shop method
                    game.Shop();

                    // After the player exits the shop, wait for user input before clearing the screen
                    game.WaitForInput();
                    game.ClearScreen();
                }
            }
        }

        // When the game is completed (the player has killed the Lich), call the Win method
        game.Win();
    }

    // Print the introduction and start the game
    public void Start()
    {
        // Introduction text
        WriteLine("=============== Welcome to the Adventures of Rorik! ===============\n");
        WriteLine("\nA Lich has been terrorizing the kingdom for years.\n");
        WriteLine("The king has put out a bounty to the Lich's defeat.\n");
        WriteLine("You, Rorik, are a hero that has been called upon by the king.\n");
        WriteLine("After a short journey from your home, you arrive at the castle for a meeting with the king.\n");
        WaitForInput();
        ClearScreen();

        // Play the king's dialogue
        WriteLine("King: Welcome, Rorik. I have called you here to discuss a matter of great importance.\n");
        WriteLine("King: I give you the task of finally putting an end to the Lich's torment.\n");
        WriteLine("King: The Lich has been using a dungeon as a base for his dark arts.\n");
        WriteLine("King: You must venture into the dungeon and defeat the Lich.\n");
        WriteLine("King: You are our only hope.\n");
        WaitForInput();
        ClearScreen();

        // Play the follow up dialogue
        WriteLine("After accepting the task, you venture forth toward the location of the dungeon.\n");
        WaitForInput();
        ClearScreen();

        // Play the final dialogue before starting the game
        WriteLine("After a long journey, you finally arrive at the entrance of the dungeon.\n");
        WriteLine("You take a deep breath and enter the dungeon.\n");
        // Display the player's starting stats
        WriteLine("\n==== Starting stats ====\n");
        WriteLine(player.GetStats() + "\n========================\n");
    }

    // Shows the game over message
    private void GameOver()
    {
        // Print the game over message
        WriteLine("=============== Game Over! ===============\n");
        WriteLine("You have died.\n");

        // Wait for user input to exit the game
        WaitForInput(true);

        // Exit the game
        System.exit(0);
    }

    // Shows the win message
    private void Win()
    {
        // Print the win text
        WriteLine("=============== Congratulations! ===============\n");
        WriteLine("You have defeated the Lich!\n");
        WriteLine("You have saved the kingdom!\n");
        WriteLine("You win!\n");

        // Display the player's final stats
        WriteLine("\n==== Ending stats ====\n");
        WriteLine(player.GetStats() + "\n========================\n");

        // Wait for user input to exit the game
        WaitForInput(true);
    }

    // Slowly print out a message
    private void WriteLine(String message)
    {
        // Loop through each character in the message and write it to the console slowly
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i));
            try
            {
                Thread.sleep(30);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    // Waits for user input
    private void WaitForInput()
    {
        // Print a message to the user to press enter to continue
        WriteLine("\nPress enter to continue...");

        // Wait for user to press a button
        try
        {
            // Read the input from the user
            System.in.read();
            // Clear the input buffer
            ClearBuffer();
        }
        catch (IOException exception)
        {
            // Print the exception if an error occurs
            exception.printStackTrace();
        }
    }

    // Waits for user input with the text to exit the game
    private void WaitForInput(boolean toExit)
    {
        // Check if the user is exiting the game
        if (toExit)
        {
            // Print a message to the user to press enter to exit
            WriteLine("\nPress enter to exit...");
        }
        else
        {
            // Print a message to the user to press enter to continue
            WriteLine("\nPress enter to continue...");
        }

        // Wait for user to press a button
        try
        {
            // Read the input from the user
            System.in.read();
            // Clear the input buffer
            ClearBuffer();
        }
        catch (IOException exception)
        {
            // Print the exception if an error occurs
            exception.printStackTrace();
        }
    }

    // Clear the input buffer
    private void ClearBuffer()
    {
        // Clear the input buffer
        try
        {
            // For each character in the input buffer, read and discard it
            while (System.in.available() > 0)
            {
                System.in.read();
            }
        }
        catch (IOException exception)
        {
            // Print the exception if an error occurs
            exception.printStackTrace();
        }
    }

    // Clear the screen
    private void ClearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Generate a random enemy based on the player's level
    public Enemy GenerateRandomEnemy()
    {
        // Generate a random enemy based on the player's level
        if (player.level >= 5)
        {
            // If the player is level 5, generate a Lich
            return new Lich();
        }
        else if (player.level > 2)
        {
            // If the player is above level 2, generate a random skeleton or a slime
            int random = (int)(Math.random() * 2);
            if (random % 3 == 0)
            {
                return new Slime();
            }
            else if (random % 2 == 0)
            {
                return new Skeleton();
            }
            else
            {
                return new Skeleton();
            }
        }
        else
        {
            // If the player is below level 2, generate a slime
            return new Slime();
        }
    }

    // Generates a new room of the dungeon
    private boolean Room()
    {
        // Generate a random enemy and turn order
        Enemy enemy;
        int turnOrder;
        // If the player is level 5, generate a Lich
        if (player.level == 5)
        {
            // Print the Lich's dialogue
            WriteLine("You have entered the Lich's lair.\n");
            WriteLine("The Lich is sitting atop a throne of bones.\n");
            WriteLine("\nLich: Yet another weakling comes to meet their end.\n");
            WriteLine("\nThe Lich stands up and prepares to attack!\n");
            WriteLine("\nLich: You will not defeat me. No one can.\n");

            // Create a new Lich enemy
            enemy = new Lich();
            // Set the turn order to 0 (player goes first)
            turnOrder = 0;
        }
        // If the player is below level 5, generate a random enemy
        else
        {
            // Create a new random enemy
            enemy = GenerateRandomEnemy();

            // Print the room text
            WriteLine("You have entered a new room of the dungeon.\n\n");

            // Set the turn order to a random number
            turnOrder = (int)(Math.random() * 2);
            // If the turn order is even, the player goes first
            if (turnOrder % 2 == 0)
            {
                // Play a random line of dialogue for the player going first
                int randomLine = (int)(Math.random() * 4);
                if ((randomLine * 4) == 0)
                {
                    WriteLine("You see a " + enemy.name + " in the room.\n");
                }
                else if ((randomLine * 3) == 0)
                {
                    WriteLine("You get the drop on a " + enemy.name + ".\n");
                }
                else if ((randomLine * 2) == 0)
                {
                    WriteLine("You surprise the " + enemy.name + ".\n");
                }
                else
                {
                    WriteLine("A " + enemy.name + " sits in the room... waiting...\n");
                }
            }
            // If the turn order is odd, the enemy goes first
            else
            {
                // Play a random line of dialogue for the enemy going first
                int randomLine = (int)(Math.random() * 4);
                if ((randomLine * 4) == 0)
                {
                    WriteLine("A " + enemy.name + " jumps out at you!\n");
                }
                else if ((randomLine * 3) == 0)
                {
                    WriteLine("You are ambushed by a " + enemy.name + ".\n");
                }
                else if ((randomLine * 2) == 0)
                {
                    WriteLine("A " + enemy.name + " is ready to attack!\n");
                }
                else
                {
                    WriteLine("You are surprised by a " + enemy.name + "!\n");
                }
            }
        }

        // Battle loop
        while (enemy.currentHealth > 0)
        {
            Battle(enemy, turnOrder);
        }

        // After the battle is over, print a new line
        WriteLine("\n");

        // Check if the player has died
        if (player.currentHealth <= 0)
        {
            // If the player has died, call the GameOver method and exit the game
            GameOver();
        }
        // If the player is alive, mark the enemy as slain
        else
        {
            EnemySlain(enemy);
        }

        // Return true if the player has defeated the Lich
        if (enemy.name.equals("Lich"))
        {
            return true;
        }
        // Return false if the player has not defeated the Lich
        else
        {
            // Print a message to the player that the room has been cleared
            WriteLine("You have cleared the room!\n");
            return false;
        }
    }

    // Battle method
    private void Battle(Enemy enemy, int turnOrder)
    {
        // Check the turn order to determine who goes first (if the turn order is even, the player goes first)
        if (turnOrder % 2 == 0)
        {
            // If the player goes first, call the PlayerTurn method first
            PlayerTurn(enemy);

            // If the enemy has been defeated, return
            if (enemy.currentHealth <= 0)
            {
                return;
            }

            // Call the EnemyTurn method after the player's turn
            EnemyTurn(enemy);
        }
        else
        {
            // If the enemy goes first, call the EnemyTurn method first
            EnemyTurn(enemy);

            // PlayerTurn method after the enemy's turn
            PlayerTurn(enemy);

            // If the enemy has been defeated, return
            if (enemy.currentHealth <= 0)
            {
                return;
            }
        }
    }

    // Player's turn
    private void PlayerTurn(Enemy enemy)
    {
        // If the player's heal cooldown is greater than 0, decrement the cooldown
        if (player.currentHealCooldown > 0)
        {
            player.currentHealCooldown--;
        }

        // Print the player's turn text
        WriteLine("\n=============== Your Turn ===============\n");
        WriteLine("\nHealth: " + player.currentHealth + "\n");

        // While the player's turn is not completed
        boolean playerTurnCompleted = false;
        while (!playerTurnCompleted)
        {
            // Print the player's options
            WriteLine("What will you do?\n");
            WriteLine("1. Attack\n");
            // Print the heal option with the cooldown if the player's heal is on cooldown
            if (player.currentHealCooldown == 0)
            {
                WriteLine("2. Heal for " + player.healAmount + " health\n");
            }
            else
            {
                WriteLine("2. Heal for " + player.healAmount + " health (" + player.currentHealCooldown + " turns left)\n");
            }
            WriteLine("3. View stats\n");

            // Get the player's input
            WriteLine("\nEnter your choice: ");
            int input = 0;
            try
            {
                // Read the input from the user
                input = System.in.read();
                // Clear the input buffer
                ClearBuffer();
            }
            catch (IOException exception)
            {
                // Print the exception if an error occurs
                exception.printStackTrace();
            }

            // Print a new line
            WriteLine("\n");

            // Switch statement to handle the player's input
            switch (input)
            {
                // If the player chooses to attack
                case '1':
                    // Call the player's attack method
                    player.Attack(enemy);
                    // Print the attack message
                    WriteLine("You attacked the " + enemy.name + " for " + player.weapon.damage + " damage.\n");
                    // Set the player's turn to completed
                    playerTurnCompleted = true;
                    break;
                // If the player chooses to heal
                case '2':
                    // If the player's health is already at max, deny the heal
                    if (player.currentHealth == player.maxHealth)
                    {
                        WriteLine("You are already at full health!\n");
                    }
                    // If the player's heal is 0, allow the player to heal
                    else if (player.currentHealCooldown == 0)
                    {
                        // Call the player's heal method
                        player.Heal();
                        // Print the heal message
                        WriteLine("You healed yourself.\n");
                        // Set the player's turn to completed
                        playerTurnCompleted = true;
                    }
                    // If the player's heal is on cooldown, deny the heal
                    else
                    {
                        WriteLine("You have " + player.currentHealCooldown + " turns left until you can heal!\n\n");
                    }
                    break;
                // If the player chooses to view their stats
                case '3':
                    // Print the player's stats
                    WriteLine("==== Stats ====\n");
                    WriteLine(player.GetStats() + "\n===============\n\n");
                    break;
                // If the player chooses an invalid option
                default:
                    WriteLine("\nInvalid choice!\n");
                    break;
            }
        }
    }

    // Enemy's turn
    private void EnemyTurn(Enemy enemy)
    {
        // Print the enemy's turn text
        WriteLine("\n=============== " + enemy.name + "'s Turn ===============\n");

        // Print the enemy's voice line
        WriteLine("\n" + enemy.name + ": " + enemy.VoiceLine() + "\n\n");

        // Call the enemy's attack method
        int damage = enemy.Attack();
        // Deal damage to the player
        player.TakeDamage(damage);
        // Print the damage message
        WriteLine("The " + enemy.name + " has attacked you for " + damage + " damage!\n");
    }

    // Method to handle the player defeating an enemy
    private void EnemySlain(Enemy enemy)
    {
        // Print the enemy defeated message
        WriteLine("You defeated the " + enemy.name + ".\n");

        // Add experience to the player
        WriteLine("+" + enemy.experience + " experience\n");
        // Check if the player has leveled up
        if(player.AddExperience(enemy.experience))
        {
            // If the player has leveled up, print the level up message
            WriteLine("Level up!\n");
            // Set the player leveled up boolean to true
            playerLeveledUp = true;
        }
        
        // Add a random amount gold to the player
        int randomGold = (int)(Math.random() * 10) + 1;
        player.AddGold(randomGold);
        WriteLine("+" + randomGold + " gold\n");

        // Print the player's stats after the battle
        WriteLine("\n==== Stats ====\n");
        WriteLine(player.GetStats() + "\n===============\n\n");
    }

    // Method to prompt the player to visit the shop
    private boolean ShopPrompt()
    {
        // Print the shop prompt message
        WriteLine("As you leave the room, you see a mysterious shop.\n");
        WriteLine("\nWould you like to visit the shop?\n");
        WriteLine("1. Yes\n");
        WriteLine("2. No\n");

        // Get the player's input
        WriteLine("\nEnter your choice: ");
        int input = 0;
        try
        {
            // Read the input from the user
            input = System.in.read();
            // Clear the input buffer
            ClearBuffer();
        }
        catch (IOException exception)
        {
            // Print the exception if an error occurs
            exception.printStackTrace();
        }

        // Switch statement to handle the player's input
        switch (input)
        {
            case '1':
                return true;
            case '2':
                return false;
            default:
                WriteLine("\nInvalid choice!\n");
                return ShopPrompt();
        }
    }

    // Shop method
    private void Shop()
    {
        // Print the shopkeep's dialogue
        WriteLine("Shady Shopkeep: Welcome to my humble shop...\n");
        WriteLine("Shady Shopkeep: Please take a look at my wares...\n");

        // Shop loop
        boolean shopping = true;
        while (shopping)
        {
            // Print the shop menu
            WriteLine("\n=============== Shop =============== \n");
            WriteLine("1. Buy Shortsword - 10 gold\n");
            WriteLine("2. Buy Longsword - 30 gold\n");
            WriteLine("3. Reset heal - 5 gold\n");
            WriteLine("4. +5 Health per heal - 15 gold\n");
            WriteLine("5. Exit shop\n");

            // Print the player's stats
            WriteLine("==== Stats ====\n");
            WriteLine(player.GetStats() + "\n===============\n\n");

            // Get the player's input
            WriteLine("\nEnter your choice: ");
            int input = 0;
            try
            {
                // Read the input from the user
                input = System.in.read();
                // Clear the input buffer
                ClearBuffer();
            }
            catch (IOException exception)
            {
                // Print the exception if an error occurs
                exception.printStackTrace();
            }

            // Print a new line
            WriteLine("\n");

            // Switch statement to handle the player's input
            switch (input)
            {
                // If the player chooses to buy a shortsword
                case '1':
                    // Check if the player already has a shortsword
                    if (player.weapon.currentWeaponType == Weapon.WeaponType.SHORTSWORD)
                    {
                        WriteLine("You already have a Shortsword!\n");
                    }
                    // Check if the player already has a weapon better than a shortsword
                    else if (player.weapon.currentWeaponType == Weapon.WeaponType.LONGSWORD)
                    {
                        WriteLine("Your weapon is already better than a Shortsword!\n");
                    }
                    // Check if the player has enough gold to buy a shortsword
                    else if (player.gold >= 10)
                    {
                        // Remove the gold from the player
                        player.RemoveGold(10);
                        // Switch the player's weapon to a shortsword
                        player.SwitchWeapon(Weapon.WeaponType.SHORTSWORD);
                        // Print the message that the player has bought a shortsword
                        WriteLine("You have bought a Shortsword!\n");
                    }
                    // If the player does not have enough gold, print a message
                    else
                    {
                        WriteLine("You do not have enough gold!\n");
                    }
                    break;
                // If the player chooses to buy a longsword
                case '2':
                    // Check if the player already has a longsword
                    if (player.weapon.currentWeaponType == Weapon.WeaponType.LONGSWORD)
                    {
                        WriteLine("You already have a Longsword!\n");
                    }
                    // Check if the player has enough gold to buy a longsword
                    else if (player.gold >= 30)
                    {
                        // Remove the gold from the player
                        player.RemoveGold(30);
                        // Switch the player's weapon to a longsword
                        player.SwitchWeapon(Weapon.WeaponType.LONGSWORD);
                        // Print the message that the player has bought a longsword
                        WriteLine("You have bought a Longsword!\n");
                    }
                    // If the player does not have enough gold, print a message
                    else
                    {
                        // Print the message that the player does not have enough gold
                        WriteLine("You do not have enough gold!\n");
                    }
                    break;
                // If the player chooses to reset their heal
                case '3':
                    // Check if the player's heal is already ready
                    if (player.currentHealCooldown == 0)
                    {
                        WriteLine("Your heal is already ready!\n");
                    }
                    // Check if the player has enough gold to reset their heal
                    else if (player.gold >= 5)
                    {
                        // Remove the gold from the player
                        player.RemoveGold(5);
                        // Reset the player's heal cooldown
                        player.ResetHealCooldown();
                        // Print the message that the player has reset their heal cooldown
                        WriteLine("You have reset your heal cooldown!\n");
                    }
                    // If the player does not have enough gold, print a message
                    else
                    {
                        WriteLine("You do not have enough gold!\n");
                    }
                    break;
                // If the player chooses to increase their heal amount
                case '4':
                    // Check if the player has enough gold to increase their heal amount
                    if (player.gold >= 15)
                    {
                        // Remove the gold from the player
                        player.RemoveGold(15);
                        // Increase the player's heal amount
                        player.AddHealAmount(5);
                        // Print the message that the player has increased their heal amount
                        WriteLine("You now heal for 5 more health!\n");
                    }
                    // If the player does not have enough gold, print a message
                    else
                    {
                        WriteLine("You do not have enough gold!\n");
                    }
                    break;
                // If the player chooses to exit the shop
                case '5':
                    // Print the message that the player has left the shop
                    WriteLine("You leave the shop...\n");
                    // Set the shopping boolean to false to exit the shop
                    shopping = false;
                    break;
                // If the player chooses an invalid option
                default:
                    WriteLine("Invalid choice!\n");
                    break;
            }
        }
    }
}
