package myself.obernardovieira.spendme.Core.DataObjects;

/**
 * Created by bernardovieira on 02-02-2017.
 */

public class Category
{
    private int color;
    private String name;
    private String character;

    public Category(int color, String name, String character)
    {
        this.color = color;
        this.name = name;
        this.character = character;
    }

    public int getColor()
    {
        return color;
    }

    public String getName()
    {
        return name;
    }

    public String getCharacter()
    {
        return character;
    }
}
