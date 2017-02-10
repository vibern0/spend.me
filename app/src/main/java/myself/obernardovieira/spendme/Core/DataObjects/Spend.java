package myself.obernardovieira.spendme.Core.DataObjects;

public class Spend
{
    private float value;
    private Category category;
    private String description;

    public Spend(float value, Category category, String description)
    {
        this.value = value;
        this.category = category;
        this.description = description;
    }

    public float getValue()
    {
        return value;
    }

    public Category getCategory()
    {
        return category;
    }

    public String getDescription()
    {
        return description;
    }
}
