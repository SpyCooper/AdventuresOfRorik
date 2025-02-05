// Description: 
// This file contains the Slime class.
// The Slime class is a subclass of the Enemy class and represents a weak enemy in the game.

// Slime class that inherits from Enemy
public class Slime extends Enemy
{
    // Slime constructor
    public Slime()
    {
        // Slime stats inherited from Enemy
        maxHealth = 10;
        currentHealth = maxHealth;
        attack = 1;
        experience = 5;
        name = "Slime";
    }

    // Slime attack method (overridden from Enemy)
    public int Attack()
    {
        // Vary the attack value
        int randomAttackValue = (int)(Math.random() * 2) + attack;
        return randomAttackValue;
    }

    // Slime voice line method (overridden from Enemy)
    public String VoiceLine()
    {
        // Play a random voice line
        int random = (int)(Math.random() * 5);
        if (random % 5 == 0)
        {
            return "Squish!";
        }
        else if (random % 4 == 0)
        {
            return "Bloop!";
        }
        else if (random % 3 == 0)
        {
            return "Gloop!";
        }
        else if (random % 2 == 0)
        {
            return "Splat!";
        }
        else
        {
            return "Blorp!";
        }
    }
}
