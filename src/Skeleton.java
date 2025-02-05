// Description: 
// The Skeleton class is a subclass of the Enemy class and represents a skeleton enemy in the game.
// The Skeleton is a medium enemy with medium health and medium attack values.
// The Skeleton can be of two types: a Skeleton Swordsman or a Skeleton Archer.

// Skeleton class that inherits from Enemy
public class Skeleton extends Enemy
{
    // Skeleton type enum
    enum SkeletonType
    {
        SWORD,
        BOW
    }

    // The current skeleton type
    SkeletonType type;

    // Skeleton constructor
    public Skeleton()
    {
        // Skeleton stats inherited from Enemy
        experience = 10;
        // Sets the skeleton type and stats for the skeleton type
        SetRandomSkeletonType();
        if (type == SkeletonType.SWORD)
        {
            name = "Skeleton Swordsman";
            maxHealth = 25;
            currentHealth = maxHealth;
        }
        else
        {
            name = "Skeleton Archer";
            maxHealth = 15;
            currentHealth = maxHealth;
        }
    }

    // Skeleton attack method (overridden from Enemy)
    public int Attack()
    {
        // Vary the attack value
        int randomAttackValue = (int)(Math.random() * 2) + attack;
        return randomAttackValue;
    }

    // Set the skeleton type randomly
    public void SetRandomSkeletonType()
    {
        // Randomly set the skeleton type between the sword and bow types
        int random = (int)(Math.random() * 2);
        if (random % 2 == 0)
        {
            type = SkeletonType.SWORD;
            attack = 2;
        }
        else
        {
            type = SkeletonType.BOW;
            attack = 4;
        }
    }

    // Skeleton voice line method (overridden from Enemy)
    public String VoiceLine()
    {
        // Play a random voice line
        int random = (int)(Math.random() * 4);
        if (random % 4 == 0)
        {
            return "You will join us soon...";
        }
        else if (random % 3 == 0)
        {
            return "In the name of our master!";
        }
        else if (random % 2 == 0)
        {
            return "aaaaaaaaaah...";
        }
        else
        {
            return "*bones rattle*";
        }
    }
}
