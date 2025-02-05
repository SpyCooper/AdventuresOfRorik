// Description: 
// The Lich class is a subclass of the Enemy class that represents the Lich enemy in the game.
// The Lich is the strongest enemy in the game, with high health and high attack values.

// Lich class that inherits from Enemy
public class Lich extends Enemy
{
    // Lich constructor
    public Lich()
    {
        // Lich stats inherited from Enemy
        maxHealth = 60;
        currentHealth = maxHealth;
        attack = 4;
        experience = 50;
        name = "Lich";
    }

    // Lich attack method (overridden from Enemy)
    public int Attack()
    {
        // Vary the attack value
        int randomAttackValue = (int)(Math.random() * 4) + attack;
        return randomAttackValue;
    }

    // Lich voice line method (overridden from Enemy)
    public String VoiceLine()
    {
        // Play a random voice line
        int random = (int)(Math.random() * 5);
        if (random % 5 == 0)
        {
            return "You will cannot defeat a master of the dark arts!";
        }
        else if (random % 4 == 0)
        {
            return "Weakling...";
        }
        else if (random % 3 == 0)
        {
            return "You are nothing!";
        }
        else if (random % 2 == 0)
        {
            return "Everything you know will be destroyed!";
        }
        else
        {
            // Return a different voice line if the Lich is at full health or not
            if (currentHealth < maxHealth)
            {
                return "I didn't even feel that!";
            }
            else
            {
                return "You will die here today!";
            }
        }
    }
}
